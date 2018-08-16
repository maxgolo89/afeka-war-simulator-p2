package db.dal.entities;

import java.util.List;

public interface IMissileDestructorEntity {
    String getId();
    void setId(String id);
    long getWarModelId();
    void setWarModelId(long warModelId);
    List<IMissileEntity> getMissiles();
    void setMissiles(List<IMissileEntity> missiles);
}
