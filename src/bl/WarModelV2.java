package bl;

import mvc.WarModelEventsListener;
import program.IConstants;

public class WarModelV2 extends WarModel {
    protected WarModelV2() {
        super();
    }

    @Override
    public void registerListener(WarModelEventsListener listener) {
        super.registerListener(listener);
    }

    @Override
    protected void fireAddMissileLuncherEvent(String id, boolean isHidden) {
        super.fireAddMissileLuncherEvent(id, isHidden);
    }

    @Override
    protected void fireAddMissileDestructorEvent(String id) {
        super.fireAddMissileDestructorEvent(id);
    }

    @Override
    protected void fireAddLauncherDestructorEvent(String id, LauncherDestructorType type) {
        super.fireAddLauncherDestructorEvent(id, type);
    }

    @Override
    protected void fireStartMissileLaunchEvent(String launcherID, String missileID, String destination, int flyTime) {
        super.fireStartMissileLaunchEvent(launcherID, missileID, destination, flyTime);
    }

    @Override
    public void fireEndMissileLaunchEvent(String launcherID, String missileID, String destination, int damage) {
        super.fireEndMissileLaunchEvent(launcherID, missileID, destination, damage);
    }

    @Override
    public void fireLauncherStateChangedEvent(String launcherID, boolean isHidden) {
        super.fireLauncherStateChangedEvent(launcherID, isHidden);
    }

    @Override
    protected void fireStartDestructMissileEvent(String destructorID, String missileID, int duration) {
        super.fireStartDestructMissileEvent(destructorID, missileID, duration);
    }

    @Override
    protected void fireEndDestructMissileEvent(String destructorID, String missileID, boolean isDestructed) {
        super.fireEndDestructMissileEvent(destructorID, missileID, isDestructed);
    }

    @Override
    protected void fireStartDestructLauncherEvent(String destructorID, String launcherID, int duration) {
        super.fireStartDestructLauncherEvent(destructorID, launcherID, duration);
    }

    @Override
    protected void fireEndDestructLauncherEvent(String destructorID, String launcherID, boolean isDestructed) {
        super.fireEndDestructLauncherEvent(destructorID, launcherID, isDestructed);
    }

    @Override
    protected void fireStatisticsEvent(int totalDamage, int launchedMissiles, int hits, int destructedMissiles, int destructedLaunchers, boolean exit) {
        super.fireStatisticsEvent(totalDamage, launchedMissiles, hits, destructedMissiles, destructedLaunchers, exit);
    }

    @Override
    protected void fireExitEvent() {
        super.fireExitEvent();
    }
}
