package db.dal.factory;

import db.dal.converter.ObjectToDaoConverter;
import db.dal.converter.IObjectToDaoConverter;
import db.dal.crud.ICrud;
import db.dal.crud.MongoCrud;
import db.dal.crud.SqlCrud;

import static db.dal.factory.FactoryStateE.MONGODB_E;

/*************************************************************************************
 *
 * DAL Factory.
 *  Singleton class, provides instances of crud and converter classes, according to
 *  current working state (MySql database / MongoDB database).
 *
 * *********************************************************************************** */
public class DalFactory {
    private static DalFactory factory = null;
    private static FactoryStateE state = MONGODB_E; // Default MySQL

    private DalFactory() {}

    public static DalFactory getInstance() {
        if(factory == null)
            factory = new DalFactory();

        return factory;
    }

    public static FactoryStateE getState() {
        return state;
    }

    public static void setState(FactoryStateE state) {
        DalFactory.state = state;
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
