package db.dal;

import bl.*;
import db.dal.entities.*;
import db.dal.entities.sql.*;
import program.IConstants;

import java.util.LinkedList;


/*************************************************************************************
 *
 * This util intended for converting Application scope object, to persistent classes.
 * The implementation is loaded dynamically, using factory pattern.
 *
 * *********************************************************************************** */
public class EntitySqlConverter implements IEntityConverter {

    @Override
    public IMissileEntity missileToMissileEntity(Missile missile, long warId) {
        if(missile == null)
            return null;

        IMissileEntity missileE = new MissileSqlEntity();
        missileE.setId(missile.getID());
        missileE.setDamage(missile.getDamage());
        missileE.setDestination(missile.getDestination());
        missileE.setDestructed(missile.isDestructed());
        missileE.setDone(missile.isDone());
        missileE.setFlyTime(missile.getFlyTime());
        missileE.setPotentialDamage(missile.getPotentialDamage());
        missileE.setMissileLauncherEntity(missileLauncherToMissileLauncherEntity(missile.getLauncher(), warId));
        missileE.setWarModelId(warId);

        return missileE;
    }

    @Override
    public IMissileDestructorEntity missileDestructorToMissileDestructorEntity(MissileDestructor missileDestructor, long warId) {
        if(missileDestructor == null)
            return null;

        IMissileDestructorEntity missileDestructorEntity = new MissileDestructorSqlEntity();
        missileDestructorEntity.setId(missileDestructor.getID());
        missileDestructorEntity.setWarModelId(warId);
        missileDestructorEntity.setMissiles(new LinkedList<>());

        for(Missile m : missileDestructor.getTargets())
            missileDestructorEntity.getMissiles().add(missileToMissileEntity(m, warId));

        return missileDestructorEntity;
    }

    @Override
    public IMissileLauncherEntity missileLauncherToMissileLauncherEntity(MissileLauncher missileLauncher, long warId) {
        if(missileLauncher == null)
            return null;

        IMissileLauncherEntity missileLauncherEntity = new MissileLauncherSqlEntity();
        missileLauncherEntity.setId(missileLauncher.getID());
        missileLauncherEntity.setDestructed(missileLauncher.isDestructed());
        missileLauncherEntity.setWarModelId(warId);
//        if(missileLauncher instanceof HiddenMissileLauncher)
//            missileLauncherEntity.setHidden(true);
//        else
        missileLauncherEntity.setHidden(false);

        missileLauncherEntity.setMissiles(new LinkedList<>());
        for(Missile m : missileLauncher.getMissilesToLaunch())
            missileLauncherEntity.getMissiles().add(missileToMissileEntity(m, warId));

        return missileLauncherEntity;

    }

    @Override
    public IMissileLauncherEntity hiddenMissileLauncherToMissileLauncherEntity(HiddenMissileLauncher hiddenMissileLauncher, long warId) {
        if(hiddenMissileLauncher == null)
            return null;

        IMissileLauncherEntity missileLauncherEntity = new MissileLauncherSqlEntity();
        missileLauncherEntity.setId(hiddenMissileLauncher.getID());
        missileLauncherEntity.setDestructed(hiddenMissileLauncher.isDestructed());
        missileLauncherEntity.setWarModelId(warId);
//        if(missileLauncher instanceof HiddenMissileLauncher)
        missileLauncherEntity.setHidden(true);
//        else
//        missileLauncherEntity.setHidden(false);

        missileLauncherEntity.setMissiles(new LinkedList<>());
        for(Missile m : hiddenMissileLauncher.getMissilesToLaunch())
            missileLauncherEntity.getMissiles().add(missileToMissileEntity(m, warId));

        return missileLauncherEntity;
    }

    @Override
    public ILauncherDestructorEntity launcherDestructorToLauncherDestructorEntity(LauncherDestructor launcherDestructor, long warId) {
        if(launcherDestructor == null)
            return null;

        ILauncherDestructorEntity launcherDestructorEntity = new LauncherDestructorSqlEntity();
        launcherDestructorEntity.setId(launcherDestructor.getID());
        if(launcherDestructor.getType().ordinal() == 0)
            launcherDestructorEntity.setType((LauncherDestructorTypeEnum.PLANE_E));
        else
            launcherDestructorEntity.setType((LauncherDestructorTypeEnum.SHIP_E));
        launcherDestructorEntity.setWarModelId(warId);

        launcherDestructorEntity.setMissileLauncherEntityList(new LinkedList<>());

        for(MissileLauncher ml : launcherDestructor.getTargets())
            launcherDestructorEntity.getMissileLauncherEntityList().add(missileLauncherToMissileLauncherEntity(ml, warId));

        return launcherDestructorEntity;
    }

    @Override
    public IWarModelEntity warModelToWarModelEntity(WarModel warModel) {
        if(warModel == null)
            return null;

        IWarModelEntity warModelEntity = new WarModelSqlEntity();

        warModelEntity.setTimeStamp(warModel.getWarTime().getStartTime());
        warModelEntity.setDestructedLaunchers(warModel.getDestructedLaunchers());
        warModelEntity.setDestructedMissiles(warModel.getDestructedMissiles());
        warModelEntity.setHits(warModel.getHits());
        warModelEntity.setLaunchedMissiles(warModel.getLaunchedMissiles());
        warModelEntity.setTotalDamage(warModel.getTotalDamage());

        return warModelEntity;
    }

    @Override
    public Missile missileEntityToMissileObject(IMissileEntity missile) {
        if(missile == null)
            return null;

        Missile rMissile = new Missile();
        rMissile.setPotentialDamage(missile.getPotentialDamage());
        rMissile.setDone(missile.isDone());
        rMissile.setDamage(missile.getDamage());
        rMissile.setDestination(missile.getDestination());
        rMissile.setDestructed(missile.isDestructed());
        rMissile.setFlyTime(missile.getFlyTime());
        rMissile.setId(missile.getId());
        rMissile.setLauncher(missileLauncherEntityToMissileLauncherObject(missile.getMissileLauncherEntity()));

        return rMissile;
    }

    @Override
    public MissileDestructor missileDestructorEntityToMissileDestructorObject(IMissileDestructorEntity missileDestructor) {
        if(missileDestructor == null)
            return null;

        MissileDestructor rMissileDestructor = new MissileDestructor();
        rMissileDestructor.setId(missileDestructor.getId());
        rMissileDestructor.setTargets(new LinkedList<>());
        for(IMissileEntity m : missileDestructor.getMissiles())
            rMissileDestructor.getTargets().add(missileEntityToMissileObject(m));

        return rMissileDestructor;
    }

    @Override
    public MissileLauncher missileLauncherEntityToMissileLauncherObject(IMissileLauncherEntity missileLauncher) {
        if(missileLauncher == null)
            return null;

        MissileLauncher rMissileLauncher = new MissileLauncher();

        rMissileLauncher.setId(missileLauncher.getId());
        rMissileLauncher.setDestructed(missileLauncher.isDestructed());
        rMissileLauncher.setMissilesToLaunch(new LinkedList<>());

        return rMissileLauncher;
    }

    @Override
    public HiddenMissileLauncher hiddenMissileLauncherEntityToMissileLauncherObject(IMissileLauncherEntity hiddenMissileLauncher) {
        if(hiddenMissileLauncher == null)
            return null;

        HiddenMissileLauncher rHiddenMissileLauncher = new HiddenMissileLauncher();

        rHiddenMissileLauncher.setHiddenNow(hiddenMissileLauncher.isHidden());
        rHiddenMissileLauncher.setDestructed(hiddenMissileLauncher.isDestructed());
        rHiddenMissileLauncher.setId(hiddenMissileLauncher.getId());
        rHiddenMissileLauncher.setMissilesToLaunch(new LinkedList<>());

        return rHiddenMissileLauncher;
    }

    @Override
    public LauncherDestructor launcherDestructorEntityToLauncherDestructorObject(ILauncherDestructorEntity launcherDestructor) {
        if(launcherDestructor == null)
            return null;

        LauncherDestructor rLauncherDestructor = new LauncherDestructor();

        rLauncherDestructor.setId(launcherDestructor.getId());
        rLauncherDestructor.setType(launcherDestructor.getType().ordinal() == 0 ? IConstants.LauncherDestructorType.PLANE : IConstants.LauncherDestructorType.SHIP);
        rLauncherDestructor.setTargets(new LinkedList<>());

        return rLauncherDestructor;
    }

    @Override
    public WarModel warModelEntityToWarModelObject(IWarModelEntity warModel) {
        if(warModel == null)
            return null;

        WarModel rWarModel = WarModel.getInstance();

        rWarModel.setDestructedLaunchers(warModel.getDestructedLaunchers());
        rWarModel.setDestructedMissiles(warModel.getDestructedMissiles());
        rWarModel.setHits(warModel.getHits());
        rWarModel.setTotalDamage(warModel.getTotalDamage());

        return rWarModel;
    }
}
