package db.dal.entities.sql;

import db.dal.entities.IMissileDestructorEntity;
import db.dal.entities.IMissileEntity;
import db.dal.entities.sqlpk.MissileDestructorPK;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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
    @Column(name="id")
    private String                          id;
    @Id
    @Column(name="war_model_id")
    private long                            warModelId;
    @Column(name="missile_id")
    @ManyToMany
    private List<MissileSqlEntity>          missiles;

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
        return new ArrayList<>(missiles);
    }

    @Override
    public void setMissiles(List<IMissileEntity> missiles) {
        if(this.missiles == null)
            this.missiles = new ArrayList<>();
        for(IMissileEntity m : missiles)
            this.missiles.add((MissileSqlEntity) m);
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
