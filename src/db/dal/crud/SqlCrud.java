package db.dal.crud;

import db.dal.commons.CrudConstants;
import db.dal.entities.*;
import db.dal.entities.impl.*;
import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.service.ServiceRegistry;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SqlCrud implements ICrud {
    private Logger logger = Logger.getLogger(SqlCrud.class);
    private static SessionFactory factory = null;
    private final String hibernateUrlVal = "jdbc:mysql://localhost:3306/afeka_war_simulator?serverTimezone=UTC";
    private final String hibernateUsernameVal = "root";
    private final String hibernatePasswordVal = "";
    private final String hibernateDialectVal = "org.hibernate.dialect.MariaDBDialect";
    private final String hibernateCreateIfNotExistVal = "update";
    private final String hibernateAutoCommitVal = "false";


    public SqlCrud() {
        try {
            if(factory == null) {
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySetting(CrudConstants.HIBERNATE_URL_PROPERTY_NAME, hibernateUrlVal)
                        .applySetting(CrudConstants.HIBERNATE_USERNAME_PROPERTY_NAME, hibernateUsernameVal)
                        .applySetting(CrudConstants.HIBERNATE_PASSWORD_PROPERTY_NAME, hibernatePasswordVal)
                        .applySetting(CrudConstants.HIBERNATE_DIALECT_PROPERTY_NAME, MySQL5Dialect.class)
                        .applySetting(CrudConstants.HIBERNATE_CREATEIFNOTEXITST_PROPERTY_NAME, hibernateCreateIfNotExistVal)
                        .applySetting(CrudConstants.HIBERNATE_AUTOCOMMIT_PROPERTY_NAME, hibernateAutoCommitVal)
                        .build();

                factory = new MetadataSources(serviceRegistry)
                        .addAnnotatedClass(MissileDao.class)
                        .addAnnotatedClass(MissileLauncherDao.class)
                        .addAnnotatedClass(MissileDestructorDao.class)
                        .addAnnotatedClass(LauncherDestructorDao.class)
                        .addAnnotatedClass(WarModelDao.class)
                        .addAnnotatedClass(HiddenMissileLauncherDao.class)
                        .buildMetadata()
                        .getSessionFactoryBuilder()
                        .unwrap(SessionFactoryBuilder.class)
                        .build();
            }
        } catch(Throwable ex) {
            logger.error(ex.getMessage());
        }
    }

    /***********************
     ** CREATE OPERATIONS **
     ***********************/
    public int create(WarDao daoObj) {
        Session session = factory.openSession();
        Transaction tx = null;
        int id = -1;
        try {
            tx = session.beginTransaction();
            if(daoObj instanceof IWarModelDao) {
                id = (int)session.save((WarModelDao)daoObj);
            } else if(daoObj instanceof IMissileDao) {
                id = (int)session.save((MissileDao)daoObj);
            } else if(daoObj instanceof IMissileLauncherDao) {
                id = (int)session.save((MissileLauncherDao)daoObj);
            } else if(daoObj instanceof IHiddenMissileLauncherDao) {
                id = (int)session.save((HiddenMissileLauncherDao)daoObj);
            } else if(daoObj instanceof IMissileDestructorDao) {
                id = (int)session.save((MissileDestructorDao)daoObj);
            } else if(daoObj instanceof ILauncherDestructorDao){
                id = (int)session.save((LauncherDestructorDao)daoObj);
            }
            tx.commit();
        } catch(Exception ex) {
            if(tx != null)
                tx.rollback();;
            logger.error(ex);
        } finally {
            session.close();
            return id;
        }
    }

    @Override
    public int createWarModel(IWarModelDao warModelEntity) {
        return create(warModelEntity);
    }

    @Override
    public int createMissile(IMissileDao missile) {
        return create(missile);
    }

    @Override
    public int createMissileLauncher(IMissileLauncherDao missileLauncher) {
        return create(missileLauncher);
    }

    @Override
    public int createHiddenMissileLauncher(IHiddenMissileLauncherDao hiddenMissileLauncher) {
        return create(hiddenMissileLauncher);
    }

    @Override
    public int createMissileDestructor(IMissileDestructorDao missileDestructorEntity) {
        return create(missileDestructorEntity);
    }

    @Override
    public int createLauncherDestructor(ILauncherDestructorDao launcherDestructorEntity) {
        return create(launcherDestructorEntity);
    }

    /***********************
     ** READ OPERATIONS **
     ***********************/
    @Override
    public IWarModelDao readWarModelById(long wid) {
        Session session = factory.openSession();
        Transaction tx = null;
        WarModelDao warModelSqlDao = null;
        try {
            warModelSqlDao = (WarModelDao)session.createQuery(
                    "SELECT w " +
                            "FROM WarModelDao w " +
                            "WHERE w.wMId LIKE :aId").setParameter("aId", wid).list().get(0);
            tx.commit();
        } catch (Exception ex) {
            if(tx != null)
                tx.rollback();
            logger.error(ex);
        } finally {
            session.close();
            return warModelSqlDao;
        }
    }

    @Override
    public List<IMissileDao> readMissileById(String id) {
        return null;
    }

    @Override
    public List<IMissileDao> readMissileByWarModel(long wid) { return null; }

    @Override
    public List<IMissileLauncherDao> readMissileLauncherById(String id) {
        return null;
    }

    @Override
    public List<IMissileLauncherDao> readMissileLauncherByWarModel(long wid) { return null; }

    @Override
    public List<IHiddenMissileLauncherDao> readHiddenMissileLauncherById(String id) {
        return null;
    }

    @Override
    public List<IHiddenMissileLauncherDao> readHiddenMissileLauncherByWarModel(long wid) { return null; }

    @Override
    public List<IMissileDestructorDao> readMissileDestructorById(String id) {
        return null;
    }

    @Override
    public List<IMissileDestructorDao> readMissileDestructorByWarModel(long wid) { return null;}

    @Override
    public List<ILauncherDestructorDao> readLauncherDestructorById(String id) {
        return null;
    }

    @Override
    public List<ILauncherDestructorDao> readLauncherDestructorByWarModel(long wid) { return null; }
}
