package db.dal.entities;
import db.dal.entities.sqlpk.MissilePK;

import javax.persistence.*;
import java.io.Serializable;

/** *************************************************************************************
 *      MISSILE ENTITY
 *      This class represents Missile object in SQL db.
 *  ************************************************************************************* */

@Entity
@Table(name="missile")
@IdClass(MissilePK.class)
public class MissileSqlEntity implements IMissileEntity, Serializable {

    @Id
    @Column(name="id")
    private String                          id;
    @Id
    @Column(name="war_model_id")
    private long                            warModelId;
    @Column(name="destination")
    private String                          destination;
    @Column(name="fly_time")
    private int                             flyTime;
    @Column(name="damage")
    private int                             damage;
    @Column(name="potential_damage")
    private int                             potentialDamage;
    @Column(name="is_done")
    private boolean                         isDone;
    @Column(name="is_destructed")
    private boolean                         isDestructed;
    @ManyToOne
    @JoinColumn(name="missile_launcher_id")
    private IMissileLauncherEntity          missileLauncherEntity;

    public MissileSqlEntity() { /* DEFAULT */}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getWarModelId() {
        return warModelId;
    }

    public void setWarModelId(long warModelId) {
        this.warModelId = warModelId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getFlyTime() {
        return flyTime;
    }

    public void setFlyTime(int flyTime) {
        this.flyTime = flyTime;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getPotentialDamage() {
        return potentialDamage;
    }

    public void setPotentialDamage(int potentialDamage) {
        this.potentialDamage = potentialDamage;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDestructed() {
        return isDestructed;
    }

    public void setDestructed(boolean destructed) {
        isDestructed = destructed;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public IMissileLauncherEntity getMissileLauncherEntity() {
        return missileLauncherEntity;
    }

    public void setMissileLauncherEntity(IMissileLauncherEntity missileLauncherEntity) {
        this.missileLauncherEntity = missileLauncherEntity;
    }
}
