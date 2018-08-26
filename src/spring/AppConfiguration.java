package spring;

import aspects.LoggerAspects;
import bl.*;
import db.app.DbAppController;
import db.dal.converter.IObjectToDaoConverter;
import db.dal.converter.ObjectToDaoConverter;
import db.dal.crud.ICrud;
import db.dal.crud.MongoCrud;
import db.dal.crud.SqlCrud;
import db.dal.dbinterface.DalAppInterface;
import db.dal.dbinterface.IDalAppInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
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

    @Bean(name="missile_launcher")
    @Scope("prototype")
    public WarObject missileLauncher() {
        return new MissileLauncher();
    }

    @Bean(name="hidden_missile_launcher")
    @Scope("prototype")
    public WarObject hiddenMissileLauncher() {
        return new HiddenMissileLauncher();
    }

    @Bean(name="missile_destructor")
    @Scope("prototype")
    public WarObject missileDestructor() {
        return new MissileDestructor();
    }

    @Bean(name="launcher_destructor")
    @Scope("prototype")
    public WarObject launcherDestructor() {
        return new LauncherDestructor();
    }

    @Bean(name="missile")
    @Scope("prototype")
    public Missile missile() {
        return new Missile();
    }

    @Bean
    public LoggerAspects loggerAspects() {
        return new LoggerAspects();
    }
}