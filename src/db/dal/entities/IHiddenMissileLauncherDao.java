package db.dal.entities;

import java.util.List;

public interface IHiddenMissileLauncherDao extends WarDao {
    /** Columns */
    String getmLId();
    void setmLId(String hMLId);

    boolean isHiding();
    void setHiding(boolean isHiding);

    boolean isDestructed();
    void setDestructed(boolean isDestructed);

    /** FK */
    IWarModelDao getWarModel();
    void setWarModel(IWarModelDao warModel);

    /** Join Table */
    List<IMissileDao> getMissileList();
    void setMissileList(List<IMissileDao> missileList);
}
