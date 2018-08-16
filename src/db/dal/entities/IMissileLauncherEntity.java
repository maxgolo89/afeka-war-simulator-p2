package db.dal.entities;

import java.util.List;

public interface IMissileLauncherEntity {
    String getId();
    void setId(String id);
    long getWarModelId();
    void setWarModelId(long warModelId);
    List<IMissileEntity> getMissiles();
    void setMissiles(List<IMissileEntity> missiles);
    boolean isDestructed();
    void setDestructed(boolean destructed);
    boolean isHidden();
    void setHidden(boolean hidden);
}
