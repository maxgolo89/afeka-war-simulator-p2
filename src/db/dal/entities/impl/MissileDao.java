package db.dal.entities.impl;

import db.dal.entities.*;
import org.hibernate.ogm.datastore.document.options.AssociationStorage;
import org.hibernate.ogm.datastore.document.options.AssociationStorageType;
import org.hibernate.ogm.datastore.mongodb.options.AssociationDocumentStorage;
import org.hibernate.ogm.datastore.mongodb.options.AssociationDocumentStorageType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="missile")
@AssociationStorage(AssociationStorageType.IN_ENTITY)
@AssociationDocumentStorage(AssociationDocumentStorageType.GLOBAL_COLLECTION)
public class MissileDao implements IMissileDao, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="missile_id")
    private String mId;
    @Column(name="destination")
    private String destination;
    @Column(name="fly_time")
    private int flyTime;
    @Column(name="damage")
    private int damage;
    @Column(name="potential_damage")
    private int potentialDamage;
    @Column(name="is_destructed")
    private boolean isDestructed;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="war_model_id")
    private WarModelDao warModel;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="missile_launcher_id")
    private MissileLauncherDao missileLauncher;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="hidden_missile_launcher_id")
    private HiddenMissileLauncherDao hiddenMissileLauncher;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="missile_destructor_id")
    private MissileDestructorDao missileDestructor;

    @Override
    public String getmId() {
        return mId;
    }

    @Override
    public void setmId(String mId) {
        this.mId = mId;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public int getFlyTime() {
        return flyTime;
    }

    @Override
    public void setFlyTime(int flyTime) {
        this.flyTime = flyTime;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public int getPotentialDamage() {
        return potentialDamage;
    }

    @Override
    public void setPotentialDamage(int potentialDamage) {
        this.potentialDamage = potentialDamage;
    }

    @Override
    public boolean isDestructed() {
        return isDestructed;
    }

    @Override
    public void setDestructed(boolean isDestructed) {
        this.isDestructed = isDestructed;
    }

    @Override
    public IWarModelDao getWarModel() {
        return warModel;
    }

    @Override
    public void setWarModel(IWarModelDao warModel) {
        this.warModel = (WarModelDao) warModel;
    }

    @Override
    public IMissileLauncherDao getMissileLauncher() {
        return missileLauncher;
    }

    @Override
    public void setMissileLauncher(IMissileLauncherDao missileLauncher) {
        this.missileLauncher = (MissileLauncherDao) missileLauncher;
    }

    @Override
    public IHiddenMissileLauncherDao getHiddenMissileLauncher() {
        return hiddenMissileLauncher;
    }

    @Override
    public void setHiddenMissileLauncher(IHiddenMissileLauncherDao hiddenMissileLauncher) {
        this.hiddenMissileLauncher = (HiddenMissileLauncherDao) hiddenMissileLauncher;
    }

    @Override
    public IMissileDestructorDao getMissileDestructor() {
        return missileDestructor;
    }

    @Override
    public void setMissileDestructor(IMissileDestructorDao missileDestructor) {
        this.missileDestructor = (MissileDestructorDao) missileDestructor;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof MissileDao))
            return false;

        MissileDao m = (MissileDao)obj;
        if(this.getWarModel().getwMId() == m.getWarModel().getwMId() && this.getmId().equals(m.getmId()))
            return true;

        return false;
    }
}
