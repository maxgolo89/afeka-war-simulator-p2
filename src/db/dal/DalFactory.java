package db.dal;

import static db.dal.FactoryStateE.MYSQL_E;

/*************************************************************************************
 *
 * DAL Factory.
 *  Singleton class, provides instances of crud and converter classes, according to
 *  current working state (MySql database / MongoDB database).
 *
 * *********************************************************************************** */
public class DalFactory {
    private static DalFactory factory = null;
    private static FactoryStateE state = MYSQL_E; // Default MySQL

    private DalFactory() {}

    public DalFactory getInstance() {
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
                /* Not Implemented Yet */
                break;
            default:
                return new SqlCrud();
        }
        return null;
    }

    public IEntityConverter getConverter() {
        switch(state) {
            case MYSQL_E:
                return new EntitySqlConverter();
            case MONGODB_E:
                /* Not Implemented Yet */
                break;
            default:
                return new EntitySqlConverter();
        }
        return null;
    }
}
