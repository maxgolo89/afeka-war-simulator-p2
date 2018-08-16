package db.dal.entities;

import db.dal.entities.sqlpk.MissileLauncherPK;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/** *************************************************************************************
 *      MISSILE LAUNCHER ENTITY
 *      This class represents MissileLauncher object in SQL db.
 *  ************************************************************************************* */

@Entity
@Table(name="missile_launcher")
@IdClass(MissileLauncherPK.class)
public class MissileLauncherSqlEntity implements IMissileLauncherEntity, Serializable {

    @Id
    @Column
    private String                          id;
    @Id
    @Column
    private long                            warModelId;
    @Column
    @OneToMany
    private List<IMissileEntity>            missiles;
    @Column
    private boolean                         isDestructed;
    @Column
    private boolean                         isHidden;

    public MissileLauncherSqlEntity() { /* DEFAULT */ }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public long getWarModelId() {
        return warModelId;
    }

    @Override
    public void setWarModelId(long warModelId) {
        this.warModelId = warModelId;
    }

    @Override
    public List<IMissileEntity> getMissiles() {
        return missiles;
    }

    public void setMissiles(List<IMissileEntity> missiles) {
        this.missiles = missiles;
    }

    @Override
    public boolean isDestructed() {
        return isDestructed;
    }

    @Override
    public void setDestructed(boolean destructed) {
        isDestructed = destructed;
    }

    @Override
    public boolean isHidden() {
        return isHidden;
    }

    @Override
    public void setHidden(boolean hidden) {
        isHidden = hidden;
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
