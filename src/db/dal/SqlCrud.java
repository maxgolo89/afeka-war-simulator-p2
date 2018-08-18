package db.dal;

import bl.HiddenMissileLauncher;
import db.dal.commons.CrudConstants;
import db.dal.entities.*;
import db.dal.entities.sql.*;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

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
                Properties properties = new Properties();
                properties.setProperty(CrudConstants.HIBERNATE_URL_PROPERTY_NAME, hibernateUrlVal);
                properties.setProperty(CrudConstants.HIBERNATE_USERNAME_PROPERTY_NAME, hibernateUsernameVal);
                properties.setProperty(CrudConstants.HIBERNATE_PASSWORD_PROPERTY_NAME, hibernatePasswordVal);
                properties.setProperty(CrudConstants.HIBERNATE_DIALECT_PROPERTY_NAME, hibernateDialectVal);
                properties.setProperty(CrudConstants.HIBERNATE_CREATEIFNOTEXITST_PROPERTY_NAME, hibernateCreateIfNotExistVal);
                properties.setProperty(CrudConstants.HIBERNATE_AUTOCOMMIT_PROPERTY_NAME, hibernateAutoCommitVal);

                Configuration conf = new Configuration();
                conf.addAnnotatedClass(MissileSqlDao.class);
                conf.addAnnotatedClass(MissileLauncherSqlDao.class);
                conf.addAnnotatedClass(MissileDestructorSqlDao.class);
                conf.addAnnotatedClass(LauncherDestructorSqlDao.class);
                conf.addAnnotatedClass(WarModelSqlDao.class);
                conf.addAnnotatedClass(HiddenMissileLauncherSqlDao.class);
                conf.setProperties(properties);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();

                factory = conf.buildSessionFactory(serviceRegistry);
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
                id = (int)session.save((WarModelSqlDao)daoObj);
            } else if(daoObj instanceof IMissileDao) {
                id = (int)session.save((MissileSqlDao)daoObj);
            } else if(daoObj instanceof IMissileLauncherDao) {
                id = (int)session.save((MissileLauncherSqlDao)daoObj);
            } else if(daoObj instanceof IHiddenMissileLauncherDao) {
                id = (int)session.save((HiddenMissileLauncherSqlDao)daoObj);
            } else if(daoObj instanceof IMissileDestructorDao) {
                id = (int)session.save((MissileDestructorSqlDao)daoObj);
            } else if(daoObj instanceof ILauncherDestructorDao){
                id = (int)session.save((LauncherDestructorSqlDao)daoObj);
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
        WarModelSqlDao warModelSqlDao = null;
        try {
            warModelSqlDao = session.createQuery(
                    "SELECT w " +
                            "FROM war_model w " +
                            "WHERE w.war_model_id LIKE :aId", WarModelSqlDao.class).setParameter("aId", wid).getSingleResult();
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
    public List<IMissileDao> readMissileByWarModel(long wid) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<MissileSqlDao> missileSqlDaoList = null;
        List<IMissileDao> missileDaoList = null;
        try {
            missileSqlDaoList = session.createQuery(
                    "SELECT m " +
                            "FROM missile m " +
                            "WHERE m.war_model_id LIKE :aId", MissileSqlDao.class)
                    .setParameter("aId", wid)
                    .list();
            tx.commit();

            if(missileSqlDaoList == null)
                return null;

            missileDaoList = new LinkedList<>(missileSqlDaoList);
        } catch (Exception ex) {
            if(tx != null)
                tx.rollback();
            logger.error(ex);
        } finally {
            session.close();
            return missileDaoList;
        }
    }

    @Override
    public List<IMissileLauncherDao> readMissileLauncherById(String id) {
        return null;
    }

    @Override
    public List<IMissileLauncherDao> readMissileLauncherByWarModel(long wid) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<MissileLauncherSqlDao> missileLauncherSqlDaoList = null;
        List<IMissileLauncherDao> missileLauncherDaoList = null;
        try {
            missileLauncherSqlDaoList = session.createQuery(
                    "SELECT ml " +
                            "FROM missile_launcher ml " +
                            "WHERE ml.war_model_id LIKE :aId", MissileLauncherSqlDao.class)
                    .setParameter("aId", wid)
                    .list();
            tx.commit();

            if(missileLauncherSqlDaoList == null)
                return null;

            missileLauncherDaoList = new LinkedList<>(missileLauncherSqlDaoList);
        } catch (Exception ex) {
            if(tx != null)
                tx.rollback();
            logger.error(ex);
        } finally {
            session.close();
            return missileLauncherDaoList;
        }
    }

    @Override
    public List<IHiddenMissileLauncherDao> readHiddenMissileLauncherById(String id) {
        return null;
    }

    @Override
    public List<IHiddenMissileLauncherDao> readHiddenMissileLauncherByWarModel(long wid) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<HiddenMissileLauncherSqlDao> hiddenMissileLauncherSqlDaoList = null;
        List<IHiddenMissileLauncherDao> hiddenMissileLauncherDaoList = null;
        try {
            hiddenMissileLauncherSqlDaoList = session.createQuery(
                    "SELECT hml " +
                            "FROM hidden_missile_launcher hml " +
                            "WHERE hml.war_model_id LIKE :aId", HiddenMissileLauncherSqlDao.class)
                    .setParameter("aId", wid)
                    .list();
            tx.commit();

            if(hiddenMissileLauncherSqlDaoList == null)
                return null;

            hiddenMissileLauncherDaoList = new LinkedList<>(hiddenMissileLauncherSqlDaoList);
        } catch (Exception ex) {
            if(tx != null)
                tx.rollback();
            logger.error(ex);
        } finally {
            session.close();
            return hiddenMissileLauncherDaoList;
        }
    }

    @Override
    public List<IMissileDestructorDao> readMissileDestructorById(String id) {
        return null;
    }

    @Override
    public List<IMissileDestructorDao> readMissileDestructorByWarModel(long wid) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<MissileDestructorSqlDao> missileDestructorSqlDaoList = null;
        List<IMissileDestructorDao> missileDestructorDaoList = null;
        try {
            missileDestructorSqlDaoList = session.createQuery(
                    "SELECT md " +
                            "FROM missile_destructor md " +
                            "WHERE md.war_model_id LIKE :aId", MissileDestructorSqlDao.class)
                    .setParameter("aId", wid)
                    .list();
            tx.commit();

            if(missileDestructorSqlDaoList == null)
                return null;

            missileDestructorDaoList = new LinkedList<>(missileDestructorSqlDaoList);
        } catch (Exception ex) {
            if(tx != null)
                tx.rollback();
            logger.error(ex);
        } finally {
            session.close();
            return missileDestructorDaoList;
        }
    }

    @Override
    public List<ILauncherDestructorDao> readLauncherDestructorById(String id) {
        return null;
    }

    @Override
    public List<ILauncherDestructorDao> readLauncherDestructorByWarModel(long wid) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<LauncherDestructorSqlDao> launcherDestructorSqlDaoList = null;
        List<ILauncherDestructorDao> launcherDestructorDaoList = null;
        try {
            launcherDestructorSqlDaoList = session.createQuery(
                    "SELECT ld " +
                            "FROM launcher_destructor ld " +
                            "WHERE ld.war_model_id LIKE :aId", LauncherDestructorSqlDao.class)
                    .setParameter("aId", wid)
                    .list();
            tx.commit();

            if(launcherDestructorSqlDaoList == null)
                return null;

            launcherDestructorDaoList = new LinkedList<>(launcherDestructorSqlDaoList);
        } catch (Exception ex) {
            if(tx != null)
                tx.rollback();
            logger.error(ex);
        } finally {
            session.close();
            return launcherDestructorDaoList;
        }
    }
}
