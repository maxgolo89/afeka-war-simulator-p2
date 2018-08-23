package db.dal.entities.mongo;

import db.dal.entities.IMissileDao;
import db.dal.entities.IMissileDestructorDao;
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
@Table(name="missile_destructor")
@AssociationStorage(AssociationStorageType.IN_ENTITY)
@AssociationDocumentStorage(AssociationDocumentStorageType.GLOBAL_COLLECTION)
public class MissileDestructorMongoDao implements IMissileDestructorDao, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="missile_destructor_id")
    private String mDId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="war_model_id")
    private WarModelMongoDao warModel;
    @OneToMany(mappedBy = "missileDestructor", cascade = CascadeType.ALL)
    private List<MissileMongoDao> missileList;

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
        this.warModel = (WarModelMongoDao) warModel;
    }

    @Override
    public List<IMissileDao> getMissileList() {
        return new LinkedList<>(missileList);
    }

    @Override
    public void setMissileList(List<IMissileDao> missileList) {
        this.missileList = new LinkedList<>();
        for(IMissileDao m : missileList)
            this.missileList.add((MissileMongoDao) m);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof MissileDestructorMongoDao))
            return false;

        MissileDestructorMongoDao m = (MissileDestructorMongoDao)obj;
        if(this.getWarModel().getwMId() == m.getWarModel().getwMId() && this.getmDId().equals(m.getmDId()))
            return true;

        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
