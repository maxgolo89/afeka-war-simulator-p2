package db.dal.entities;

public interface IMissileDao extends WarDao {
    /** Columns */
    String getmId();
    void setmId(String mId);

    String getDestination();
    void setDestination(String destination);

    int getFlyTime();
    void setFlyTime(int flyTime);

    int getDamage();
    void setDamage(int damage);

    int getPotentialDamage();
    void setPotentialDamage(int potentialDamage);

    boolean isDestructed();
    void setDestructed(boolean isDestructed);

    /** FK */
    IWarModelDao getWarModel();
    void setWarModel(IWarModelDao warModel);

    IMissileLauncherDao getMissileLauncher();
    void setMissileLauncher(IMissileLauncherDao missileLauncher);

    IHiddenMissileLauncherDao getHiddenMissileLauncher();
    void setHiddenMissileLauncher(IHiddenMissileLauncherDao hiddenMissileLauncher);

    IMissileDestructorDao getMissileDestructor();
    void setMissileDestructor(IMissileDestructorDao missileDestructor);
}
