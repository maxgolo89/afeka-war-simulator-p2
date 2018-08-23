package db.dal.entities.mongo;

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
public class MissileMongoDao implements IMissileDao, Serializable {
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="war_model_id")
    private WarModelMongoDao warModel;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="missile_launcher_id")
    private MissileLauncherMongoDao missileLauncher;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="hidden_missile_launcher_id")
    private HiddenMissileLauncherMongoDao hiddenMissileLauncher;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="missile_destructor_id")
    private MissileDestructorMongoDao missileDestructor;

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
        this.warModel = (WarModelMongoDao) warModel;
    }

    @Override
    public IMissileLauncherDao getMissileLauncher() {
        return missileLauncher;
    }

    @Override
    public void setMissileLauncher(IMissileLauncherDao missileLauncher) {
        this.missileLauncher = (MissileLauncherMongoDao) missileLauncher;
    }

    @Override
    public IHiddenMissileLauncherDao getHiddenMissileLauncher() {
        return hiddenMissileLauncher;
    }

    @Override
    public void setHiddenMissileLauncher(IHiddenMissileLauncherDao hiddenMissileLauncher) {
        this.hiddenMissileLauncher = (HiddenMissileLauncherMongoDao) hiddenMissileLauncher;
    }

    @Override
    public IMissileDestructorDao getMissileDestructor() {
        return missileDestructor;
    }

    @Override
    public void setMissileDestructor(IMissileDestructorDao missileDestructor) {
        this.missileDestructor = (MissileDestructorMongoDao) missileDestructor;
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
        if(!(obj instanceof MissileMongoDao))
            return false;

        MissileMongoDao m = (MissileMongoDao)obj;
        if(this.getWarModel().getwMId() == m.getWarModel().getwMId() && this.getmId().equals(m.getmId()))
            return true;

        return false;
    }
}
