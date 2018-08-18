package db.dal.entities.sql;

import db.dal.entities.IMissileDestructorDao;
import db.dal.entities.IMissileDao;
import db.dal.entities.IWarModelDao;
import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/** *************************************************************************************
 *      MISSILE DESTRUCTOR ENTITY
 *      This class represents MissileDestructor object in SQL db.
 *  ************************************************************************************* */

@Entity
@Table(name="missile_destructor")
public class MissileDestructorSqlDao implements IMissileDestructorDao, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="missile_destructor_id")
    private String mDId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="war_model_id")
    private WarModelSqlDao warModel;
    @OneToMany(mappedBy = "missileDestructor", cascade = CascadeType.ALL)
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
    public String getmDId() {
        return mDId;
    }

    @Override
    public void setmDId(String mDId) {
        this.mDId = mDId;
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
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof MissileDestructorSqlDao))
            return false;

        MissileDestructorSqlDao m = (MissileDestructorSqlDao)obj;
        if(this.getWarModel().getwMId() == m.getWarModel().getwMId() && this.getmDId().equals(m.getmDId()))
            return true;

        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
