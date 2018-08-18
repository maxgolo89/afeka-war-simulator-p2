package db.dal.entities.sql;
import db.dal.entities.*;
import javax.persistence.*;
import java.io.Serializable;

/** *************************************************************************************
 *      MISSILE ENTITY
 *      This class represents Missile object in SQL db.
 *  ************************************************************************************* */

@Entity
@Table(name="missile")
public class MissileSqlDao implements IMissileDao, Serializable {
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
    private WarModelSqlDao warModel;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="missile_launcher_id")
    private MissileLauncherSqlDao missileLauncher;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="hidden_missile_launcher_id")
    private HiddenMissileLauncherSqlDao hiddenMissileLauncher;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="missile_destructor_id")
    private MissileDestructorSqlDao missileDestructor;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

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
    public void setDestructed(boolean destructed) {
        isDestructed = destructed;
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
    public IMissileLauncherDao getMissileLauncher() {
        return missileLauncher;
    }

    @Override
    public void setMissileLauncher(IMissileLauncherDao missileLauncher) {
        this.missileLauncher = (MissileLauncherSqlDao) missileLauncher;
    }

    @Override
    public IHiddenMissileLauncherDao getHiddenMissileLauncher() {
        return hiddenMissileLauncher;
    }

    @Override
    public void setHiddenMissileLauncher(IHiddenMissileLauncherDao hiddenMissileLauncher) {
        this.hiddenMissileLauncher = (HiddenMissileLauncherSqlDao) hiddenMissileLauncher;
    }

    @Override
    public IMissileDestructorDao getMissileDestructor() {
        return missileDestructor;
    }

    @Override
    public void setMissileDestructor(IMissileDestructorDao missileDestructor) {
        this.missileDestructor = (MissileDestructorSqlDao) missileDestructor;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof MissileSqlDao))
            return false;

        MissileSqlDao m = (MissileSqlDao)obj;
        if(this.getWarModel().getwMId() == m.getWarModel().getwMId() && this.getmId().equals(m.getmId()))
            return true;

        return false;
    }
}
