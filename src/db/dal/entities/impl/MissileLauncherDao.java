package db.dal.entities.impl;

import db.dal.entities.IMissileDao;
import db.dal.entities.IMissileLauncherDao;
import db.dal.entities.IWarModelDao;
import org.hibernate.ogm.datastore.document.options.AssociationStorage;
import org.hibernate.ogm.datastore.document.options.AssociationStorageType;
import org.hibernate.ogm.datastore.mongodb.options.AssociationDocumentStorage;
import org.hibernate.ogm.datastore.mongodb.options.AssociationDocumentStorageType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="missile_launcher")
@AssociationStorage(AssociationStorageType.IN_ENTITY)
@AssociationDocumentStorage(AssociationDocumentStorageType.GLOBAL_COLLECTION)
public class MissileLauncherDao implements IMissileLauncherDao, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="missile_launcher_id")
    private String mLId;
    @Column(name="is_destructed")
    private boolean isDestructed;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="war_model_id")
    private WarModelDao warModel;
    @OneToMany(mappedBy = "missileLauncher", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MissileDao> missileList;

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
    public List<IMissileDao> getMissileList() {
        return new LinkedList<>(missileList);
    }

    @Override
    public void setMissileList(List<IMissileDao> missileList) {
        this.missileList = new LinkedList<>();
        for(IMissileDao m : missileList)
            this.missileList.add((MissileDao)m);
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
        if(!(obj instanceof MissileLauncherDao))
            return false;

        MissileLauncherDao m = (MissileLauncherDao)obj;
        if(this.getWarModel().getwMId() == m.getWarModel().getwMId() && this.getmLId().equals(m.getmLId()))
            return true;

        return false;
    }
}
