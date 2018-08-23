package db.dal.entities.impl;

import db.dal.entities.*;
import org.hibernate.ogm.datastore.document.options.AssociationStorage;
import org.hibernate.ogm.datastore.document.options.AssociationStorageType;
import org.hibernate.ogm.datastore.mongodb.options.AssociationDocumentStorage;
import org.hibernate.ogm.datastore.mongodb.options.AssociationDocumentStorageType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="war_model")
@AssociationStorage(AssociationStorageType.IN_ENTITY)
@AssociationDocumentStorage(AssociationDocumentStorageType.GLOBAL_COLLECTION)
public class WarModelDao implements IWarModelDao, Serializable{
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

    @OneToMany(mappedBy = "warModel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MissileDao> missileList;
    @OneToMany(mappedBy = "warModel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MissileLauncherDao> missileLauncherList;
    @OneToMany(mappedBy = "warModel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<HiddenMissileLauncherDao> hiddenMissileLauncherList;
    @OneToMany(mappedBy = "warModel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MissileDestructorDao> missileDestructorList;
    @OneToMany(mappedBy = "warModel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LauncherDestructorDao> launcherDestructorList;

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
            this.missileList.add((MissileDao) m);
    }

    @Override
    public List<IMissileLauncherDao> getMissileLauncherList() {
        return new LinkedList<>(missileLauncherList);
    }

    @Override
    public void setMissileLauncherList(List<IMissileLauncherDao> missileLauncherList) {
        this.missileLauncherList = new LinkedList<>();
        for(IMissileLauncherDao ml : missileLauncherList)
            this.missileLauncherList.add((MissileLauncherDao)ml);
    }

    @Override
    public List<IHiddenMissileLauncherDao> getHiddenMissileLauncherList() {
        return new LinkedList<>(hiddenMissileLauncherList);
    }

    @Override
    public void setHiddenMissileLauncherList(List<IHiddenMissileLauncherDao> hiddenMissileLauncherList) {
        this.hiddenMissileLauncherList = new LinkedList<>();
        for(IHiddenMissileLauncherDao hml : hiddenMissileLauncherList)
            this.hiddenMissileLauncherList.add((HiddenMissileLauncherDao)hml);
    }

    @Override
    public List<IMissileDestructorDao> getMissileDestructorList() {
        return new LinkedList<>(missileDestructorList);
    }

    @Override
    public void setMissileDestructorList(List<IMissileDestructorDao> missileDestructorList) {
        this.missileDestructorList = new LinkedList<>();
        for(IMissileDestructorDao md : missileDestructorList)
            this.missileDestructorList.add((MissileDestructorDao)md);
    }

    @Override
    public List<ILauncherDestructorDao> getLauncherDestructorList() {
        return new LinkedList<>(launcherDestructorList);
    }

    @Override
    public void setLauncherDestructorList(List<ILauncherDestructorDao> launcherDestructorList) {
        this.launcherDestructorList = new LinkedList<>();
        for(ILauncherDestructorDao ld : launcherDestructorList)
            this.launcherDestructorList.add((LauncherDestructorDao)ld);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof WarModelDao))
            return false;

        WarModelDao m = (WarModelDao)obj;
        if(this.getwMId() == m.getwMId())
            return true;

        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
