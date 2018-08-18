package db.dal.entities;

import java.util.List;

public interface IMissileDestructorDao extends WarDao {
    /** Columns */
    String getmDId();
    void setmDId(String mDId);

    /** FK */
    IWarModelDao getWarModel();
    void setWarModel(IWarModelDao warModel);

    /** Join Tables */
    List<IMissileDao> getMissileList();
    void setMissileList(List<IMissileDao> missileList);
}
