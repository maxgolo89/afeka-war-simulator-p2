package db.app;

import bl.WarModel;
import db.dal.DalAppInterface;
import db.dal.IDalAppInterface;
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
//        dai.addMissileLauncher(warModel.getWarTime().getStartTime(), id, isHidden);
    }

    @Override
    public void addMissileDestructorInModel(String id) {
//        dai.addMissileDestructor(warModel.getWarTime().getStartTime(), id);
    }

    @Override
    public void addLauncherDestructorInModel(String id, IConstants.LauncherDestructorType type) {
//        dai.addLauncherDestructor(warModel.getWarTime().getStartTime(), id, type);
    }

    @Override
    public void addMissileLaunchInModel(String launcherID, String missileID, int potentialDamage, String destination, int flyTime) {
//        dai.addMissile(warModel.getWarTime().getStartTime(), launcherID, missileID, potentialDamage, destination, flyTime);
    }

    @Override
    public void launcherStateChangedInModel(String launcherID, boolean isHidden) {
//        dai.updateMissileLauncherState(warModel.getWarTime().getStartTime(), launcherID, isHidden, false);
    }

    @Override
    public void startMissileLaunchInModel(String launcherID, String missileID, int flyTime, String destination) {
//        dai.updateMissileState(warModel.getWarTime().getStartTime(), missileID, flyTime, destination, -1, false);
    }

    @Override
    public void endMissileLaunchInModel(String launcherID, String missileID, int damage, String destination) {
//        dai.updateMissileState(warModel.getWarTime().getStartTime(), missileID, -1, destination, damage, true);
    }

    @Override
    public void startDestructLauncherInModel(String destructorID, String launcherID, int duration) {

    }

    @Override
    public void endDestructLauncherInModel(String destructorID, String launcherID, boolean isDestructed) {
//        dai.updateMissileLauncherState(warModel.getWarTime().getStartTime(), launcherID, false, isDestructed);
    }

    @Override
    public void startDestructMissileInModel(String destructorID, String missileID, int duration) {

    }

    @Override
    public void endDestructMissileInModel(String destructorID, String missileID, boolean isDestructed) {
//        dai.updateMissileState(warModel.getWarTime().getStartTime(), missileID, -1, null, -1, isDestructed);
    }

    @Override
    public void statisticsInModel(int totalDamage, int launchedMissiles, int hits, int destructedMissiles, int destructedLaunchers, boolean exit) {
//        dai.updateWarStatistics(warModel.getWarTime().getStartTime(), totalDamage, launchedMissiles, hits, destructedMissiles, destructedLaunchers);
        dai.writeWarToDb(WarModel.getInstance());
    }

    @Override
    public void exitInModel() {

    }
}
