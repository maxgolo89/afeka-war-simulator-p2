package db.dal.entities.sql;

import db.dal.entities.ILauncherDestructorEntity;
import db.dal.entities.IMissileLauncherEntity;
import db.dal.entities.LauncherDestructorTypeEnum;
import db.dal.entities.sqlpk.LauncherDestructorPK;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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
    @Column(name="id")
    private String                          id;
    @Id
    @Column(name="war_model_id")
    private long                            warModelId;
    @Column(name="type")
    private LauncherDestructorTypeEnum type;
    @Column(name="missile_launcher_id")
    @ManyToMany
    private List<MissileLauncherSqlEntity>  missileLauncherEntityList;

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
        return new ArrayList<>(missileLauncherEntityList);
    }

    @Override
    public void setMissileLauncherEntityList(List<IMissileLauncherEntity> missileLauncherEntityList) {
        if(this.missileLauncherEntityList == null)
            this.missileLauncherEntityList = new ArrayList<>();
        for(IMissileLauncherEntity m : missileLauncherEntityList)
            this.missileLauncherEntityList.add((MissileLauncherSqlEntity) m);
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
