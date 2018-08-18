package db.dal.entities;

import java.util.List;

public interface IMissileLauncherDao extends WarDao {
    /** Columns */
    String getmLId();
    void setmLId(String mLId);

    boolean isDestructed();
    void setDestructed(boolean isDestructed);

    /** FK */
    IWarModelDao getWarModel();
    void setWarModel(IWarModelDao warModel);

    /** Join Table */
    List<IMissileDao> getMissileList();
    void setMissileList(List<IMissileDao> missileList);
}
