package mvc;

import program.IConstants;

public interface WarModelEventsListenerV2 extends WarModelEventsListener {
    void addMissileLauncherInModel(String id, long warId, boolean isHidden);
    void addMissileDestructorInModel(String id, long warId);
    void addLauncherDestructorInModel(String id, long warId, IConstants.LauncherDestructorType type) ;

    void launcherStateChangedInModel(String launcherID, long warId, boolean isHidden);

    void startMissileLaunchInModel(String launcherID, long warId, String missileID, int flyTime, String destination);
    void endMissileLaunchInModel(String launcherID, long warId, String missileID, int damage, String destination);

    void startDestructLauncherInModel(String destructorID, long warId, String missileID, int duration);
    void endDestructLauncherInModel(String destructorID, long warId, String launcherID, boolean isDestructed);

    void startDestructMissileInModel(String destructorID, long warId, String launcherID, int duration);
    void endDestructMissileInModel(String destructorID, long warId, String missileID, boolean isDestructed);

    void statisticsInModel(long warId, int totalDamage, int launchedMissiles, int hits, int destructedMissiles, int destructedLaunchers, boolean exit);
}
