package db.dal.entities;

public interface IMissileEntity {
    String getId();
    void setId(String id);
    long getWarModelId();
    void setWarModelId(long warModelId);
    String getDestination();
    void setDestination(String destination);
    int getFlyTime();
    void setFlyTime(int flyTime);
    int getDamage();
    void setDamage(int damage);
    int getPotentialDamage();
    void setPotentialDamage(int potentialDamage);
    boolean isDone();
    void setDone(boolean done);
    boolean isDestructed();
    void setDestructed(boolean destructed);
    IMissileLauncherEntity getMissileLauncherEntity();
    void setMissileLauncherEntity(IMissileLauncherEntity missileLauncherEntity);
}
