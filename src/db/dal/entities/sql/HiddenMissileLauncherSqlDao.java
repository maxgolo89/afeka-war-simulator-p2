package db.dal.entities.sql;

import db.dal.entities.IHiddenMissileLauncherDao;
import db.dal.entities.IMissileDao;
import db.dal.entities.IWarModelDao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/** *************************************************************************************
 *      HIDDEN MISSILE LAUNCHER ENTITY
 *      This class represents HiddenMissileLauncher object in SQL db.
 *  ************************************************************************************* */
@Entity
@Table(name="hidden_missile_launcher")
public class HiddenMissileLauncherSqlDao implements IHiddenMissileLauncherDao, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="missile_launcher_id")
    private String mLId;
    @Column(name="is_destructed")
    private boolean isDestructed;
    @Column(name="is_hiding")
    private boolean isHiding;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="war_model_id")
    private WarModelSqlDao warModel;
    @OneToMany(mappedBy = "missileLauncher", cascade = CascadeType.ALL)
    private List<MissileSqlDao> missileList;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getmLId() {
        return mLId;
    }

    @Override
    public void setmLId(String mLId) {
        this.mLId = mLId;
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
    public boolean isHiding() {
        return isHiding;
    }

    @Override
    public void setHiding(boolean hiding) {
        isHiding = hiding;
    }

    @Override
    public IWarModelDao getWarModel() {
        return warModel;
    }

    @Override
    public void setWarModel(IWarModelDao warModel) {
        this.warModel = (WarModelSqlDao) warModel;
    }

    @Override
    public List<IMissileDao> getMissileList() {
        return new LinkedList<>(missileList);
    }

    @Override
    public void setMissileList(List<IMissileDao> missileList) {
        this.missileList = new LinkedList<>();
        for(IMissileDao m : missileList)
            this.missileList.add((MissileSqlDao)m);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof HiddenMissileLauncherSqlDao))
            return false;

        HiddenMissileLauncherSqlDao m = (HiddenMissileLauncherSqlDao)obj;
        if(this.getWarModel().getwMId() == m.getWarModel().getwMId() && this.getmLId().equals(m.getmLId()))
            return true;

        return false;
    }
}
