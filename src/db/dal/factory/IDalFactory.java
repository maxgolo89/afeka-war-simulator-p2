package db.dal.factory;

import db.dal.converter.IObjectToDaoConverter;
import db.dal.crud.ICrud;

public interface IDalFactory {

    FactoryStateE getState();
    void setState(FactoryStateE state);
    ICrud getCrud();
    IObjectToDaoConverter getConverter();
}
