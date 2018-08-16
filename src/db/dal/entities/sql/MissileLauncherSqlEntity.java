package db.dal.entities.sql;

import db.dal.entities.IMissileEntity;
import db.dal.entities.IMissileLauncherEntity;
import db.dal.entities.sqlpk.MissileLauncherPK;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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
    @Column(name="id")
    private String                          id;
    @Id
    @Column(name="war_model_id")
    private long                            warModelId;
    @Column(name="missiles")
    @OneToMany
    private List<MissileSqlEntity>          missiles;
    @Column(name="is_destructed")
    private boolean                         isDestructed;
    @Column(name="is_hidden")
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
        return new ArrayList<>(missiles);
    }

    public void setMissiles(List<IMissileEntity> missiles) {
        if(this.missiles == null)
            this.missiles = new ArrayList<>();
        for(IMissileEntity m : missiles)
            this.missiles.add((MissileSqlEntity) m);
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
