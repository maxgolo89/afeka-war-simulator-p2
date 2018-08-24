package db.dal.dbinterface;

import bl.*;
import db.dal.converter.IObjectToDaoConverter;
import db.dal.crud.ICrud;
import db.dal.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class DalAppInterface implements IDalAppInterface {

    /* Wired by Spring */
    @Autowired
    private ICrud crud;
    @Autowired
    private IObjectToDaoConverter converter;

    /* Query executor */
    private Thread queryExecutor                            = null;
    protected BlockingQueue<IWarModelDao> workQueue         = null;

    public DalAppInterface() {
        workQueue       = new LinkedBlockingQueue<>();
        queryExecutor   = new Thread(new QueryExecutor(this));
        queryExecutor.start();
    }

    protected void performWrite() {
        IWarModelDao warModelDao = workQueue.poll();
        crud.createWarModel(warModelDao);
    }

    @Override
    public boolean writeWarToDb(WarModel warModel) {
        List<IMissileDao>                   missileDaoList                  = new LinkedList<>();
        List<IMissileLauncherDao>           missileLauncherDaoList          = new LinkedList<>();
        List<IHiddenMissileLauncherDao>     hiddenMissileLauncherDaoList    = new LinkedList<>();
        List<IMissileDestructorDao>         missileDestructorDaoList        = new LinkedList<>();
        List<ILauncherDestructorDao>        launcherDestructorDaoList       = new LinkedList<>();

        // Convert WarModel to DAO
        IWarModelDao warModelDao = converter.toWarModelDao(warModel.getWarTime().getStartTime()
                , warModel.getHits()
                , warModel.getTotalDamage()
                , warModel.getLaunchedMissiles()
                , warModel.getDestructedMissiles()
                , warModel.getDestructedLaunchers());


        // Convert Missile to DAO
        for(Missile m : warModel.getAllMissiles()) {
            IMissileDao mDao = converter.toMissileDao(m.getID(), m.getDestination(), m.getFlyTime(), m.getDamage(), m.getPotentialDamage(), m.isDestructed());
            mDao.setWarModel(warModelDao);
            missileDaoList.add(mDao);
        }

        // Convert MissileLauncher to DAO
        for(MissileLauncher ml : warModel.getAllLaunchers()) {
            IMissileLauncherDao mlDao = converter.toMissileLauncherDao(ml.getId(), ml.isDestructed());

            mlDao.setMissileList(new LinkedList<>());
            for(Missile m : ml.getMissilesToLaunch()) {
                IMissileDao mDao = converter.toMissileDao(m.getID(), m.getDestination(), m.getFlyTime(), m.getDamage(), m.getPotentialDamage(), m.isDestructed());
                mDao.setWarModel(warModelDao);
                mlDao.getMissileList().add(mDao);
            }

            mlDao.setWarModel(warModelDao);
            missileLauncherDaoList.add(mlDao);
        }

        // Convert HiddenMissileLauncher to DAO
        for(MissileLauncher hml : warModel.getAllLaunchers()) {
            IHiddenMissileLauncherDao mlDao = null;
            HiddenMissileLauncher hmlInst = null;
            if(hml instanceof HiddenMissileLauncher) {
                hmlInst = (HiddenMissileLauncher)hml;
                mlDao = converter.toHiddenMissileLauncherDao(hmlInst.getId(), hmlInst.isDestructed(), hmlInst.isHidden());

                mlDao.setMissileList(new LinkedList<>());
                for(Missile m : hml.getMissilesToLaunch()) {
                    IMissileDao t = converter.toMissileDao(m.getID(), m.getDestination(), m.getFlyTime(), m.getDamage(), m.getPotentialDamage(), m.isDestructed());
                    t.setWarModel(warModelDao);
                    mlDao.getMissileList().add(t);
                }

                mlDao.setWarModel(warModelDao);
                hiddenMissileLauncherDaoList.add(mlDao);
            }
        }

        // Convert MissileDestructor to DAO
        for(MissileDestructor md: warModel.getAllMissileDestructors()) {
            IMissileDestructorDao mdDao = converter.toMissileDestructorDao(md.getID());

            mdDao.setMissileList(new LinkedList<>());
            for(Missile m : md.getTargets()) {
                IMissileDao t = converter.toMissileDao(m.getID(), m.getDestination(), m.getFlyTime(), m.getDamage(), m.getPotentialDamage(), m.isDestructed());
                t.setWarModel(warModelDao);
                mdDao.getMissileList().add(t);
            }


            mdDao.setWarModel(warModelDao);
            missileDestructorDaoList.add(mdDao);
        }

        // Convert LauncherDestructor to DAO
        for(LauncherDestructor ld : warModel.getAllLauncherDestructors()) {
            ILauncherDestructorDao ldDao = converter.toLauncherDestructorDao(ld.getID(), ld.getType());

            ldDao.setMissileLauncherList(new LinkedList<>());
            ldDao.setHiddenMissileLauncherList(new LinkedList<>());
            for(MissileLauncher md : ld.getTargets()) {
                if(md instanceof HiddenMissileLauncher) {
                    HiddenMissileLauncher temp = (HiddenMissileLauncher)md;
                    IHiddenMissileLauncherDao t = converter.toHiddenMissileLauncherDao(temp.getID(), temp.isDestructed(), temp.isHidden());
                    t.setWarModel(warModelDao);
                    ldDao.getHiddenMissileLauncherList().add(t);
                } else {
                    IMissileLauncherDao t = converter.toMissileLauncherDao(md.getID(), md.isDestructed());
                    t.setWarModel(warModelDao);
                    ldDao.getMissileLauncherList().add(t);
                }
            }
            ldDao.setWarModel(warModelDao);
            launcherDestructorDaoList.add(ldDao);
        }

        warModelDao.setMissileList(missileDaoList);
        warModelDao.setMissileLauncherList(missileLauncherDaoList);
        warModelDao.setHiddenMissileLauncherList(hiddenMissileLauncherDaoList);
        warModelDao.setMissileDestructorList(missileDestructorDaoList);
        warModelDao.setLauncherDestructorList(launcherDestructorDaoList);

        if(workQueue.add(warModelDao))
            return true;

        return false;
    }

    @Override
    public IWarModelDao readWarFromDb(long warTime) {
        IWarModelDao warModel = crud.readWarModelById(warTime);
        return warModel;
    }
}
