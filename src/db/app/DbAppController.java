package db.app;

import bl.WarModel;
import db.dal.commons.NotImplemented;
import db.dal.dbinterface.DalAppInterface;
import db.dal.dbinterface.IDalAppInterface;
import mvc.WarModelEventsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import program.IConstants;

@Component
public class DbAppController implements WarModelEventsListener {

    @Autowired
    private IDalAppInterface     dai;
    private WarModel             warModel            = WarModel.getInstance();

    public DbAppController() {
        warModel.registerListener(this);
    }

    public IDalAppInterface getDai() {
        return dai;
    }

    public void setDai(IDalAppInterface dai) {
        this.dai = dai;
    }

    @Override
    @NotImplemented
    public void addMissileLauncherInModel(String id, boolean isHidden) {
    }

    @Override
    @NotImplemented
    public void addMissileDestructorInModel(String id) {
    }

    @Override
    @NotImplemented
    public void addLauncherDestructorInModel(String id, IConstants.LauncherDestructorType type) {
    }

    @Override
    @NotImplemented
    public void addMissileLaunchInModel(String launcherID, String missileID, int potentialDamage, String destination, int flyTime) {
    }

    @Override
    @NotImplemented
    public void launcherStateChangedInModel(String launcherID, boolean isHidden) {
    }

    @Override
    @NotImplemented
    public void startMissileLaunchInModel(String launcherID, String missileID, int flyTime, String destination) {
    }

    @Override
    @NotImplemented
    public void endMissileLaunchInModel(String launcherID, String missileID, int damage, String destination) {
    }

    @Override
    @NotImplemented
    public void startDestructLauncherInModel(String destructorID, String launcherID, int duration) {

    }

    @Override
    @NotImplemented
    public void endDestructLauncherInModel(String destructorID, String launcherID, boolean isDestructed) {
    }

    @Override
    @NotImplemented
    public void startDestructMissileInModel(String destructorID, String missileID, int duration) {
    }

    @Override
    @NotImplemented
    public void endDestructMissileInModel(String destructorID, String missileID, boolean isDestructed) {
    }

    @Override
    public void statisticsInModel(int totalDamage, int launchedMissiles, int hits, int destructedMissiles, int destructedLaunchers, boolean exit) {
        if(exit)
            dai.writeWarToDb(WarModel.getInstance());
    }

    @Override
    @NotImplemented
    public void exitInModel() {
    }
}
