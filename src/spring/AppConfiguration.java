package spring;

import db.app.DbAppController;
import db.dal.converter.IObjectToDaoConverter;
import db.dal.converter.ObjectToDaoConverter;
import db.dal.crud.ICrud;
import db.dal.crud.SqlCrud;
import db.dal.dbinterface.DalAppInterface;
import db.dal.dbinterface.IDalAppInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfiguration {

    @Bean(name="db_app_controller")
    @Scope("singleton")
    public DbAppController dbAppController() {
        return new DbAppController();
    }

    @Bean(name="converter")
    @Scope("singleton")
    public IObjectToDaoConverter converter() {
        return new ObjectToDaoConverter();
    }

    @Bean(name="crud")
    @Scope("singleton")
    public ICrud crud() {
        return new SqlCrud();
    }

    @Bean(name="db_app_interface")
    @Scope("singleton")
    public IDalAppInterface dbAppInterface() {
        return new DalAppInterface();
    }
}
