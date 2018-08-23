package db.dal.entities.sql;

import db.dal.entities.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/** *************************************************************************************
 *      WAR MODEL ENTITY
 *      This class represents WarModel object in SQL db.
 *  ************************************************************************************* */

@Entity
@Table(name="war_model")
public class WarModelSqlDao implements IWarModelDao, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="war_model_id")
    private long wMId;
    @Column(name="hits")
    private int hits;
    @Column(name="total_damage")
    private int totalDamage;
    @Column(name="launched_missiles")
    private int launchedMissiles;
    @Column(name="destructed_missiles")
    private int destructedMissiles;
    @Column(name="destructed_launchers")
    private int destructedLaunchers;

    @OneToMany(mappedBy = "warModel", cascade = CascadeType.ALL)
    private List<MissileSqlDao> missileList;
    @OneToMany(mappedBy = "warModel", cascade = CascadeType.ALL)
    private List<MissileLauncherSqlDao> missileLauncherList;
    @OneToMany(mappedBy = "warModel", cascade = CascadeType.ALL)
    private List<HiddenMissileLauncherSqlDao> hiddenMissileLauncherList;
    @OneToMany(mappedBy = "warModel", cascade = CascadeType.ALL)
    private List<MissileDestructorSqlDao> missileDestructorList;
    @OneToMany(mappedBy = "warModel", cascade = CascadeType.ALL)
    private List<LauncherDestructorSqlDao> launcherDestructorList;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public long getwMId() {
        return wMId;
    }

    public void setwMId(long wMId) {
        this.wMId = wMId;
    }

    @Override
    public int getHits() {
        return hits;
    }

    @Override
    public void setHits(int hits) {
        this.hits = hits;
    }

    @Override
    public int getTotalDamage() {
        return totalDamage;
    }

    @Override
    public void setTotalDamage(int totalDamage) {
        this.totalDamage = totalDamage;
    }

    @Override
    public int getLaunchedMissiles() {
        return launchedMissiles;
    }

    @Override
    public void setLaunchedMissiles(int launchedMissiles) {
        this.launchedMissiles = launchedMissiles;
    }

    @Override
    public int getDestructedMissiles() {
        return destructedMissiles;
    }

    @Override
    public void setDestructedMissiles(int destructedMissiles) {
        this.destructedMissiles = destructedMissiles;
    }

    @Override
    public int getDestructedLaunchers() {
        return destructedLaunchers;
    }

    @Override
    public void setDestructedLaunchers(int destructedLaunchers) {
        this.destructedLaunchers = destructedLaunchers;
    }

    @Override
    public List<IMissileDao> getMissileList() {
        return new LinkedList<>(missileList);
    }

    @Override
    public void setMissileList(List<IMissileDao> missileList) {
        this.missileList = new LinkedList<>();
        for(IMissileDao m : missileList)
            this.missileList.add((MissileSqlDao) m);
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
        for(IHiddenMissileLauncherDao hml : hiddenMissileLauncherList)
            this.hiddenMissileLauncherList.add((HiddenMissileLauncherSqlDao)hml);
    }

    @Override
    public List<IMissileDestructorDao> getMissileDestructorList() {
        return new LinkedList<>(missileDestructorList);
    }

    @Override
    public void setMissileDestructorList(List<IMissileDestructorDao> missileDestructorList) {
        this.missileDestructorList = new LinkedList<>();
        for(IMissileDestructorDao md : missileDestructorList)
            this.missileDestructorList.add((MissileDestructorSqlDao)md);
    }

    @Override
    public List<ILauncherDestructorDao> getLauncherDestructorList() {
        return new LinkedList<>(launcherDestructorList);
    }

    @Override
    public void setLauncherDestructorList(List<ILauncherDestructorDao> launcherDestructorList) {
        this.launcherDestructorList = new LinkedList<>();
        for(ILauncherDestructorDao ld : launcherDestructorList)
            this.launcherDestructorList.add((LauncherDestructorSqlDao)ld);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof WarModelSqlDao))
            return false;

        WarModelSqlDao m = (WarModelSqlDao)obj;
        if(this.getwMId() == m.getwMId())
            return true;

        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
