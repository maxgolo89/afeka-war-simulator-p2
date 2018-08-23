package db.dal.entities.mongo;

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
@Table(name="launcher_destructor")
@AssociationStorage(AssociationStorageType.IN_ENTITY)
@AssociationDocumentStorage(AssociationDocumentStorageType.GLOBAL_COLLECTION)
public class LauncherDestructorMongoDao implements ILauncherDestructorDao, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="launcher_destructor_id")
    private String lDId;
    @Column(name="launcher_destructor_type")
    private LauncherDestructorTypeEnum type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="war_model_id")
    private WarModelMongoDao warModel;
    @OneToMany(cascade = CascadeType.ALL)
    private List<MissileLauncherMongoDao> missileLauncherList;
    @OneToMany(cascade = CascadeType.ALL)
    private List<HiddenMissileLauncherMongoDao> hiddenMissileLauncherList;

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
        this.warModel = (WarModelMongoDao) warModel;
    }

    @Override
    public List<IMissileLauncherDao> getMissileLauncherList() {
        return new LinkedList<>(missileLauncherList);
    }

    @Override
    public void setMissileLauncherList(List<IMissileLauncherDao> missileLauncherList) {
        this.missileLauncherList = new LinkedList<>();
        for(IMissileLauncherDao ml : missileLauncherList)
            this.missileLauncherList.add((MissileLauncherMongoDao)ml);
    }

    @Override
    public List<IHiddenMissileLauncherDao> getHiddenMissileLauncherList() {
        return new LinkedList<>(hiddenMissileLauncherList);
    }

    @Override
    public void setHiddenMissileLauncherList(List<IHiddenMissileLauncherDao> hiddenMissileLauncherList) {
        this.hiddenMissileLauncherList = new LinkedList<>();
        for(IHiddenMissileLauncherDao ml : hiddenMissileLauncherList)
            this.hiddenMissileLauncherList.add((HiddenMissileLauncherMongoDao)ml);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof LauncherDestructorMongoDao))
            return false;

        LauncherDestructorMongoDao m = (LauncherDestructorMongoDao)obj;
        if(this.getWarModel().getwMId() == m.getWarModel().getwMId() && this.getlDId().equals(m.getlDId()))
            return true;

        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
