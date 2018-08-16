package db.dal.entities;

import java.util.List;

public interface ILauncherDestructorEntity {
    String getId();
    void setId(String id);
    long getWarModelId();
    void setWarModelId(long warModelId);
    LauncherDestructorTypeEnum getType();
    void setType(LauncherDestructorTypeEnum type);
    List<IMissileLauncherEntity> getMissileLauncherEntityList();
    void setMissileLauncherEntityList(List<IMissileLauncherEntity> missileLauncherEntityList);
}
