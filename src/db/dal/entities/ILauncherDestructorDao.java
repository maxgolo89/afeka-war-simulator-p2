package db.dal.entities;

import java.util.List;

public interface ILauncherDestructorDao extends WarDao {
    /** Columns */
    String getlDId();
    void setlDId(String lDId);

    LauncherDestructorTypeEnum getType();
    void setType(LauncherDestructorTypeEnum type);

    /** FK */
    IWarModelDao getWarModel();
    void setWarModel(IWarModelDao warModel);

    /** Join Table */
    List<IMissileLauncherDao> getMissileLauncherList();
    void setMissileLauncherList(List<IMissileLauncherDao> missileLauncherList);

    List<IHiddenMissileLauncherDao> getHiddenMissileLauncherList();
    void setHiddenMissileLauncherList(List<IHiddenMissileLauncherDao> hiddenMissileLauncherList);

}
