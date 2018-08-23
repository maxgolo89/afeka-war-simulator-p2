package db.app;

import bl.WarModel;
import db.dal.dbinterface.DalAppInterface;
import db.dal.dbinterface.IDalAppInterface;
import mvc.WarModelEventsListener;
import program.IConstants;

public class DbAppController implements WarModelEventsListener {

    private static DbAppController      dbAppController     = null;
    private static IDalAppInterface     dai                 = DalAppInterface.getInstance();
    private static WarModel             warModel            = WarModel.getInstance();

    private DbAppController() {
        warModel.registerListener(this);
    }

    public static DbAppController getInstance() {
        if(dbAppController == null)
            dbAppController = new DbAppController();
        return dbAppController;
    }

    @Override
    public void addMissileLauncherInModel(String id, boolean isHidden) {
    }

    @Override
    public void addMissileDestructorInModel(String id) {
    }

    @Override
    public void addLauncherDestructorInModel(String id, IConstants.LauncherDestructorType type) {
    }

    @Override
    public void addMissileLaunchInModel(String launcherID, String missileID, int potentialDamage, String destination, int flyTime) {
    }

    @Override
    public void launcherStateChangedInModel(String launcherID, boolean isHidden) {
    }

    @Override
    public void startMissileLaunchInModel(String launcherID, String missileID, int flyTime, String destination) {
    }

    @Override
    public void endMissileLaunchInModel(String launcherID, String missileID, int damage, String destination) {
    }

    @Override
    public void startDestructLauncherInModel(String destructorID, String launcherID, int duration) {

    }

    @Override
    public void endDestructLauncherInModel(String destructorID, String launcherID, boolean isDestructed) {
    }

    @Override
    public void startDestructMissileInModel(String destructorID, String missileID, int duration) {
    }

    @Override
    public void endDestructMissileInModel(String destructorID, String missileID, boolean isDestructed) {
    }

    @Override
    public void statisticsInModel(int totalDamage, int launchedMissiles, int hits, int destructedMissiles, int destructedLaunchers, boolean exit) {
        if(exit)
            dai.writeWarToDb(WarModel.getInstance());
    }

    @Override
    public void exitInModel() {
    }
}
