package db.dal.entities;

import db.dal.entities.sqlpk.MissileDestructorPK;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/** *************************************************************************************
 *      MISSILE DESTRUCTOR ENTITY
 *      This class represents MissileDestructor object in SQL db.
 *  ************************************************************************************* */

@Entity
@Table(name="missile_destructor")
@IdClass(MissileDestructorPK.class)
public class MissileDestructorSqlEntity implements IMissileDestructorEntity, Serializable {
    @Id
    @Column
    private String                          id;
    @Id
    @Column
    private long                            warModelId;
    @Column
    @OneToMany
    private List<IMissileEntity>            missiles;

    public MissileDestructorSqlEntity() { /* DEFAULT */ }

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

    @Override
    public void setMissiles(List<IMissileEntity> missiles) {
        this.missiles = missiles;
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
