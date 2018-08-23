package db.dal.crud;

import db.dal.commons.NotImplemented;
import db.dal.entities.*;
import db.dal.entities.impl.*;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.ogm.boot.OgmSessionFactoryBuilder;
import org.hibernate.ogm.cfg.OgmProperties;
import org.hibernate.ogm.datastore.mongodb.MongoDBDialect;
import org.hibernate.ogm.datastore.mongodb.MongoDBProperties;
import org.hibernate.ogm.datastore.mongodb.impl.MongoDBDatastoreProvider;
import org.hibernate.service.ServiceRegistry;
import java.util.List;

public class MongoCrud implements ICrud {

    private Logger logger = Logger.getLogger(MongoCrud.class);
    private static OgmSessionFactory factory    = null;
    private final String hibernateUrlVal        = "localhost:27017";
    private final String hibernateUsernameVal   = "";
    private final String hibernatePasswordVal   = "";
    private final String hibernateDatabaseVal   = "afeka-war";

    public MongoCrud() {
        try {
            if(factory == null) {
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySetting(OgmProperties.ENABLED, true)
                        .applySetting(MongoDBProperties.DATASTORE_PROVIDER, MongoDBDatastoreProvider.class)
                        .applySetting(MongoDBProperties.HOST, hibernateUrlVal)
                        .applySetting(MongoDBProperties.DATABASE, hibernateDatabaseVal)
                        .applySetting(MongoDBProperties.CREATE_DATABASE, true)
                        .applySetting(MongoDBProperties.USERNAME, hibernateUsernameVal)
                        .applySetting(MongoDBProperties.PASSWORD, hibernatePasswordVal)
                        .applySetting(MongoDBProperties.GRID_DIALECT, MongoDBDialect.class)
                        .build();

                factory = new MetadataSources(serviceRegistry)
                        .addAnnotatedClass(WarModelDao.class)
                        .addAnnotatedClass(MissileDao.class)
                        .addAnnotatedClass(MissileLauncherDao.class)
                        .addAnnotatedClass(MissileDestructorDao.class)
                        .addAnnotatedClass(LauncherDestructorDao.class)
                        .addAnnotatedClass(HiddenMissileLauncherDao.class)
                        .buildMetadata()
                        .getSessionFactoryBuilder()
                        .unwrap(OgmSessionFactoryBuilder.class)
                        .build();

            }
        } catch(Throwable ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
    }


    /***********************
     ** CREATE OPERATIONS **
     ***********************/
    @Override
    public int createWarModel(IWarModelDao warModelEntity) {
        OgmSession session = factory.openSession();
        Transaction tx = null;
        int id = -1;
        try {
            tx = session.beginTransaction();
            session.save((WarModelDao) warModelEntity);
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        } finally {
            session.close();
            return id;
        }
    }

    @Override
    public int createMissile(IMissileDao missile) {
        OgmSession session = factory.openSession();
        Transaction tx = null;
        int id = -1;
        try {
            tx = session.beginTransaction();
            id = (int)session.save((MissileDao) missile);
            tx.commit();
        } catch (Exception ex) {
            logger.error(ex);
        } finally {
            session.close();
            return id;
        }
    }

    @Override
    public int createMissileLauncher(IMissileLauncherDao missileLauncher) {
        OgmSession session = factory.openSession();
        Transaction tx = null;
        int id = -1;
        try {
            tx = session.beginTransaction();
            id = (int)session.save((MissileLauncherDao) missileLauncher);
            tx.commit();
        } catch (Exception ex) {
            logger.error(ex);
        } finally {
            session.close();
            return id;
        }
    }

    @Override
    public int createHiddenMissileLauncher(IHiddenMissileLauncherDao hiddenMissileLauncher) {
        OgmSession session = factory.openSession();
        Transaction tx = null;
        int id = -1;
        try {
            tx = session.beginTransaction();
            id = (int)session.save((HiddenMissileLauncherDao) hiddenMissileLauncher);
            tx.commit();
        } catch (Exception ex) {
            logger.error(ex);
        } finally {
            session.close();
            return id;
        }
    }

    @Override
    public int createMissileDestructor(IMissileDestructorDao missileDestructorEntity) {
        OgmSession session = factory.openSession();
        Transaction tx = null;
        int id = -1;
        try {
            tx = session.beginTransaction();
            id = (int)session.save((MissileDestructorDao) missileDestructorEntity);
            tx.commit();
        } catch (Exception ex) {
            logger.error(ex);
        } finally {
            session.close();
            return id;
        }
    }

    @Override
    public int createLauncherDestructor(ILauncherDestructorDao launcherDestructorEntity) {
        OgmSession session = factory.openSession();
        Transaction tx = null;
        int id = -1;
        try {
            tx = session.beginTransaction();
            id = (int)session.save((LauncherDestructorDao) launcherDestructorEntity);
            tx.commit();
        } catch (Exception ex) {
            logger.error(ex);
        } finally {
            session.close();
            return id;
        }
    }

    /***********************
     ** READ OPERATIONS **
     ***********************/
    @Override
    public IWarModelDao readWarModelById(long wid) {
        OgmSession session = factory.openSession();
        Transaction tx = null;
        IWarModelDao warModel = null;
        try {
            tx = session.beginTransaction();
            String queryString = String.format("db.war_model.find({'war_model_id': %d})", wid);
            warModel = (IWarModelDao) session.createNativeQuery(queryString)
                    .addEntity("WarModelDao", WarModelDao.class)
                    .uniqueResult();
            tx.commit();
        } catch(Exception ex) {
            logger.error(ex);
        } finally {
            session.close();
            return warModel;
        }
    }

    @Override
    @NotImplemented
    public List<IMissileDao> readMissileById(String id) {
        return null;
    }

    @Override
    @NotImplemented
    public List<IMissileDao> readMissileByWarModel(long wid) {
        return null;
    }

    @Override
    @NotImplemented
    public List<IMissileLauncherDao> readMissileLauncherById(String id) {
        return null;
    }

    @Override
    @NotImplemented
    public List<IMissileLauncherDao> readMissileLauncherByWarModel(long wid) {
        return null;
    }

    @Override
    @NotImplemented
    public List<IHiddenMissileLauncherDao> readHiddenMissileLauncherById(String id) {
        return null;
    }

    @Override
    @NotImplemented
    public List<IHiddenMissileLauncherDao> readHiddenMissileLauncherByWarModel(long wid) {
        return null;
    }

    @Override
    @NotImplemented
    public List<IMissileDestructorDao> readMissileDestructorById(String id) {
        return null;
    }

    @Override
    @NotImplemented
    public List<IMissileDestructorDao> readMissileDestructorByWarModel(long wid) {
        return null;
    }

    @Override
    @NotImplemented
    public List<ILauncherDestructorDao> readLauncherDestructorById(String id) {
        return null;
    }

    @Override
    @NotImplemented
    public List<ILauncherDestructorDao> readLauncherDestructorByWarModel(long wid) {
        return null;
    }
}
