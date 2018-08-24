package db.dal.factory;

import db.dal.converter.ObjectToDaoConverter;
import db.dal.converter.IObjectToDaoConverter;
import db.dal.crud.ICrud;
import db.dal.crud.MongoCrud;
import db.dal.crud.SqlCrud;

import static db.dal.factory.FactoryStateE.MONGODB_E;
import static db.dal.factory.FactoryStateE.MYSQL_E;

/*************************************************************************************
 *
 * DAL Factory.
 *  Singleton class, provides instances of crud and converter classes, according to
 *  current working state (MySql database / MongoDB database).
 *
 * *********************************************************************************** */
public class DalFactory implements IDalFactory {
    private FactoryStateE state = MYSQL_E; // Default MySQL

    public DalFactory() {}

    public FactoryStateE getState() {
        return state;
    }

    public void setState(FactoryStateE state) {
        this.state = state;
    }

    public ICrud getCrud() {
        switch(state) {
            case MYSQL_E:
                return new SqlCrud();
            case MONGODB_E:
                return new MongoCrud();
            default:
                return new SqlCrud();
        }
    }

    public IObjectToDaoConverter getConverter() {
        return new ObjectToDaoConverter();
    }
}
