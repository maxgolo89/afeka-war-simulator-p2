package db.dal.entities;

import java.io.Serializable;
import java.util.List;

public interface IWarModelDao extends WarDao {
    /** Columns */
    long getwMId();
    void setwMId(long wMId);

    int getHits();
    void setHits(int hits);

    int getTotalDamage();
    void setTotalDamage(int totalDamage);

    int getLaunchedMissiles();
    void setLaunchedMissiles(int launchedMissiles);

    int getDestructedMissiles();
    void setDestructedMissiles(int destructedMissiles);

    int getDestructedLaunchers();
    void setDestructedLaunchers(int destructedLaunchers);

    /** FK */


    /** Join Tables */
    List<IMissileDao> getMissileList();
    void setMissileList(List<IMissileDao> missileList);

    List<IMissileLauncherDao> getMissileLauncherList();
    void setMissileLauncherList(List<IMissileLauncherDao> missileLauncherList);

    List<IHiddenMissileLauncherDao> getHiddenMissileLauncherList();
    void setHiddenMissileLauncherList(List<IHiddenMissileLauncherDao> hiddenMissileLauncherList);

    List<IMissileDestructorDao> getMissileDestructorList();
    void setMissileDestructorList(List<IMissileDestructorDao> missileDestructorList);

    List<ILauncherDestructorDao> getLauncherDestructorList();
    void setLauncherDestructorList(List<ILauncherDestructorDao> launcherDestructorList);
}
