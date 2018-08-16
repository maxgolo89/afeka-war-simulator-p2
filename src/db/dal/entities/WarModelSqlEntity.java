package db.dal.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/** *************************************************************************************
 *      WAR MODEL ENTITY
 *      This class represents WarModel object in SQL db.
 *  ************************************************************************************* */

@Entity
@Table(name="war_model")
public class WarModelSqlEntity implements IWarModelEntity, Serializable {

    @Id
    @Column
    private long                            timeStamp;
    @Column
    private int                             hits;
    @Column
    private int                             totalDamage;
    @Column
    private int                             launchedMissiles;
    @Column
    private int                             destructedMissiles;
    @Column
    private int                             destructedLaunchers;

    public WarModelSqlEntity() { /* DEFAULT */}

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getTotalDamage() {
        return totalDamage;
    }

    public void setTotalDamage(int totalDamage) {
        this.totalDamage = totalDamage;
    }

    public int getLaunchedMissiles() {
        return launchedMissiles;
    }

    public void setLaunchedMissiles(int launchedMissiles) {
        this.launchedMissiles = launchedMissiles;
    }

    public int getDestructedMissiles() {
        return destructedMissiles;
    }

    public void setDestructedMissiles(int destructedMissiles) {
        this.destructedMissiles = destructedMissiles;
    }

    public int getDestructedLaunchers() {
        return destructedLaunchers;
    }

    public void setDestructedLaunchers(int destructedLaunchers) {
        this.destructedLaunchers = destructedLaunchers;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
