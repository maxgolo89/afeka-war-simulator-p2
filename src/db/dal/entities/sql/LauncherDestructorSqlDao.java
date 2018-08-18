package db.dal.entities.sql;

import db.dal.entities.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/** *************************************************************************************
 *      LAUNCHER DESTRUCTOR ENTITY
 *      This class represents LauncherDestructor object in SQL db.
 *  ************************************************************************************* */

@Entity
@Table(name="launcher_destructor")
public class LauncherDestructorSqlDao implements ILauncherDestructorDao, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="launcher_destructor_id")
    private String lDId;
    @Column(name="launcher_destructor_type")
    private LauncherDestructorTypeEnum type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="war_model_id")
    private WarModelSqlDao warModel;
    @OneToMany(cascade = CascadeType.ALL)
    private List<MissileLauncherSqlDao> missileLauncherList;
    @OneToMany(cascade = CascadeType.ALL)
    private List<HiddenMissileLauncherSqlDao> hiddenMissileLauncherList;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getlDId() {
        return lDId;
    }

    @Override
    public void setlDId(String lDId) {
        this.lDId = lDId;
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
    public IWarModelDao getWarModel() {
        return warModel;
    }

    @Override
    public void setWarModel(IWarModelDao warModel) {
        this.warModel = (WarModelSqlDao) warModel;
    }

    @Override
    public List<IMissileLauncherDao> getMissileLauncherList() {
        return new LinkedList<>(missileLauncherList);
    }

    @Override
    public void setMissileLauncherList(List<IMissileLauncherDao> missileLauncherList) {
        this.missileLauncherList = new LinkedList<>();
        for(IMissileLauncherDao ml : missileLauncherList)
            this.missileLauncherList.add((MissileLauncherSqlDao)ml);
    }

    @Override
    public List<IHiddenMissileLauncherDao> getHiddenMissileLauncherList() {
        return new LinkedList<>(hiddenMissileLauncherList);
    }

    @Override
    public void setHiddenMissileLauncherList(List<IHiddenMissileLauncherDao> hiddenMissileLauncherList) {
        this.hiddenMissileLauncherList = new LinkedList<>();
        for(IHiddenMissileLauncherDao ml : hiddenMissileLauncherList)
            this.hiddenMissileLauncherList.add((HiddenMissileLauncherSqlDao)ml);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof LauncherDestructorSqlDao))
            return false;

        LauncherDestructorSqlDao m = (LauncherDestructorSqlDao)obj;
        if(this.getWarModel().getwMId() == m.getWarModel().getwMId() && this.getlDId().equals(m.getlDId()))
            return true;

        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
