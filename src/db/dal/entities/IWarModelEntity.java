package db.dal.entities;

import java.io.Serializable;

public interface IWarModelEntity {
    long getTimeStamp();
    void setTimeStamp(long timeStamp);
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
}
