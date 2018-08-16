package db.dal.entities;

import db.dal.entities.sqlpk.LauncherDestructorPK;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/** *************************************************************************************
 *      LAUNCHER DESTRUCTOR ENTITY
 *      This class represents LauncherDestructor object in SQL db.
 *  ************************************************************************************* */

@Entity
@Table(name="launcher_destructor")
@IdClass(LauncherDestructorPK.class)
public class LauncherDestructorSqlEntity implements ILauncherDestructorEntity, Serializable {
    @Id
    @Column
    private String                          id;
    @Id
    @Column
    private long                            warModelId;
    @Column
    private LauncherDestructorTypeEnum      type;
    @Column
    @OneToMany
    private List<IMissileLauncherEntity>    missileLauncherEntityList;

    public LauncherDestructorSqlEntity() { /* DEFAULT */ }

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
    public LauncherDestructorTypeEnum getType() {
        return type;
    }

    @Override
    public void setType(LauncherDestructorTypeEnum type) {
        this.type = type;
    }

    @Override
    public List<IMissileLauncherEntity> getMissileLauncherEntityList() {
        return missileLauncherEntityList;
    }

    @Override
    public void setMissileLauncherEntityList(List<IMissileLauncherEntity> missileLauncherEntityList) {
        this.missileLauncherEntityList = missileLauncherEntityList;
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
