package db.dal;

import bl.LauncherDestructor;
import db.dal.commons.CrudConstants;
import db.dal.commons.NotImplemented;
import db.dal.entities.*;
import db.dal.entities.sql.*;
import db.dal.entities.sqlpk.LauncherDestructorPK;
import db.dal.entities.sqlpk.MissileDestructorPK;
import db.dal.entities.sqlpk.MissileLauncherPK;
import db.dal.entities.sqlpk.MissilePK;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.apache.log4j.Logger;

import java.util.ArrayList;
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
                conf.addAnnotatedClass(MissileSqlEntity.class);
                conf.addAnnotatedClass(MissileLauncherSqlEntity.class);
                conf.addAnnotatedClass(MissileDestructorSqlEntity.class);
                conf.addAnnotatedClass(LauncherDestructorSqlEntity.class);
                conf.addAnnotatedClass(WarModelSqlEntity.class);
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
    @Override
    public long createWarModel(IWarModelEntity warModelEntity) {
        Session session = factory.openSession();
        Transaction tx = null;
        long id = 0;
        try {
            tx = session.beginTransaction();
            WarModelSqlEntity warModel = (WarModelSqlEntity)warModelEntity;
            id = (long)session.save(warModel);
            tx.commit();

        } catch(Exception ex) {
            if (tx != null)
                tx.rollback();
            logger.error(ex.getMessage());
        } finally {
            session.close();
            return id;
        }
    }

    @Override
    public String createMissile(IMissileEntity missile) {
        Session session = factory.openSession();
        Transaction tx = null;
        String id = null;
        try {
            tx = session.beginTransaction();
            MissileSqlEntity localMissile = (MissileSqlEntity)missile;
            id = (String)session.save(localMissile);
            tx.commit();
        } catch(Exception ex) {
            if (tx != null)
                tx.rollback();
            logger.error(ex);
        } finally {
            session.close();
            return id;
        }
    }

    @Override
    public String createMissileLauncher(IMissileLauncherEntity missileLauncher) {
       Session session = factory.openSession();
       Transaction tx = null;
       String id = null;
       try {
           tx = session.beginTransaction();
           MissileLauncherSqlEntity missileLauncherSqlEntity = (MissileLauncherSqlEntity)missileLauncher;
           id = (String)session.save(missileLauncherSqlEntity);
           tx.commit();
       } catch (Exception ex) {
           if (tx != null)
               tx.rollback();
           logger.error(ex);
       } finally {
           session.close();
           return id;
       }
    }

    @Override
    public String createMissileDestructor(IMissileDestructorEntity missileDestructorEntity) {
        Session session = factory.openSession();
        Transaction tx = null;
        String id = null;
        try {
            tx = session.beginTransaction();
            MissileDestructorSqlEntity missileDestructorSqlEntity = (MissileDestructorSqlEntity)missileDestructorEntity;
            id = (String)session.save(missileDestructorSqlEntity);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null)
                tx.rollback();
            logger.error(ex);
        } finally {
            session.close();
            return id;
        }
    }

    @Override
    public String createLauncherDestructor(ILauncherDestructorEntity launcherDestructorEntity) {
        Session session = factory.openSession();
        Transaction tx = null;
        String id = null;
        try {
            tx = session.beginTransaction();
            LauncherDestructor launcherDestructor = (LauncherDestructor)launcherDestructorEntity;
            id = (String)session.save(launcherDestructor);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null)
                tx.rollback();
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
    public IWarModelEntity readWarModelById(long id) {
        Session session = factory.openSession();
        Transaction tx = null;
        WarModelSqlEntity warModelSqlEntity = null;
        try {
            tx = session.beginTransaction();
            warModelSqlEntity = session.get(WarModelSqlEntity.class, id);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null)
                tx.rollback();
            logger.error(ex);
        } finally {
            session.close();
            return warModelSqlEntity;
        }
    }

    @Override
    @NotImplemented
    public List<IWarModelEntity> readWarModelByHits(int hits) {
        /* NOT IMPLEMENTED */
        return null;
    }

    @Override
    @NotImplemented
    public List<IWarModelEntity> readWarModelByTotalDamage(int totalDamage) {
        /* NOT IMPLEMENTED */
        return null;
    }

    @Override
    @NotImplemented
    public List<IWarModelEntity> readWarModelByLaunchedMissiles(int launchedMissiles) {
        /* NOT IMPLEMENTED */
        return null;
    }

    @Override
    @NotImplemented
    public List<IWarModelEntity> readWarModelDestructedMissiles(int destructedMissiles) {
       /* NOT IMPLEMENTED */
        return null;
    }

    @Override
    @NotImplemented
    public List<IWarModelEntity> readWarModelDestructedLaunchers(int destructedLaunchers) {
        /* NOT IMPLEMENTED */
        return null;
    }

    @Override
    public IMissileEntity readMissileById(String id, long warModelId) {
        Session session = factory.openSession();
        Transaction tx = null;
        IMissileEntity missileEntity = null;
        try {
            tx = session.beginTransaction();
            missileEntity = session.get(MissileSqlEntity.class, new MissilePK(id, warModelId));
            tx.commit();
        } catch (Exception ex) {
            if (tx != null)
                tx.rollback();
            logger.error(ex);
        } finally {
            session.close();
            return missileEntity;
        }
    }

    @Override
    public List<IMissileEntity> readMissileByWarModel(IWarModelEntity warModelEntity){
        Session session = factory.openSession();
        Transaction tx = null;
        List<MissileSqlEntity> missileEntities = null;
        List<IMissileEntity> rMissileEntities = null;
        try {
            tx = session.beginTransaction();
            missileEntities = session.createQuery(
                    "SELECT missile " +
                            "FROM MissileSqlEntity missile " +
                            "WHERE missile.warModelId LIKE :aWarModelId", MissileSqlEntity.class)
                    .setParameter("aWarModelId", warModelEntity.getTimeStamp())
                    .list();

            // Convert to return type
            if(missileEntities != null) {
                rMissileEntities = new ArrayList<>();
                rMissileEntities.addAll(missileEntities);
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx != null)
                tx.rollback();
            logger.error(ex);
        } finally {
            session.close();
            return rMissileEntities;
        }
    }

    @Override
    @NotImplemented
    public List<IMissileEntity> readMissileByDestination(String destination) {
        /* NOT IMPLEMENTED */
        return null;
    }

    @Override
    @NotImplemented
    public List<IMissileEntity> readMissileByFlyTime(int flyTime) {
        /* NOT IMPLEMENTED */
        return null;
    }

    @Override
    @NotImplemented
    public List<IMissileEntity> readMissileByDamage(int damage) {
        /* NOT IMPLEMENTED */
        return null;
    }

    @Override
    @NotImplemented
    public List<IMissileEntity> readMissileByPotentialDamage(int potentialDamage) {
        /* NOT IMPLEMENTED */
        return null;
    }

    @Override
    @NotImplemented
    public List<IMissileEntity> readMissileByIsDone(boolean isDone) {
        /* NOT IMPLEMENTED */
        return null;
    }

    @Override
    @NotImplemented
    public List<IMissileEntity> readMissileByIsDestructed(boolean isDestructed) {
        /* NOT IMPLEMENTED */
        return null;
    }

    @Override
    public List<IMissileEntity> readMissileByLauncher(IMissileLauncherEntity missileLauncherEntity) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<IMissileEntity> missileEntities = null;
        List<MissileSqlEntity> missileSqlEntities = null;
        try {
            tx = session.beginTransaction();
            missileSqlEntities = session.createQuery(
                    "SELECT ml.missiles " +
                            "FROM MissileLauncherSqlEntity AS ml ", MissileSqlEntity.class).list();
            tx.commit();

            // Convert to return type
            if(missileSqlEntities != null) {
                missileEntities = new ArrayList<>();
                missileEntities.addAll(missileSqlEntities);
            }
        } catch (Exception ex) {
            if (tx != null)
                tx.rollback();
            logger.error(ex);
        } finally {
            session.close();
            return missileEntities;
        }
    }

    @Override
    public IMissileLauncherEntity readMissileLauncherById(String id, long warModelId) {
        Session session = factory.openSession();
        Transaction tx = null;
        IMissileLauncherEntity missileLauncherEntity = null;
        try {
            tx = session.beginTransaction();
            missileLauncherEntity = session.get(MissileLauncherSqlEntity.class, new MissileLauncherPK(id, warModelId));
            tx.commit();
        } catch (Exception ex) {
            if (tx != null)
                tx.rollback();
            logger.error(ex);
        } finally {
            session.close();
            return missileLauncherEntity;
        }
    }

    @Override
    public List<IMissileLauncherEntity> readMissileLauncherByWarModel(IWarModelEntity warModelEntity) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<IMissileLauncherEntity> missileLauncherEntities = null;
        List<MissileLauncherSqlEntity> missileLauncherSqlEntities = null;
        try {
            tx = session.beginTransaction();
            missileLauncherSqlEntities = session.createQuery(
                    "SELECT ml " +
                        "FROM MissileLauncherSqlEntity ml " +
                        "WHERE ml.warModelId LIKE :aWarModelId", MissileLauncherSqlEntity.class)
                    .setParameter("aWarModelId", warModelEntity.getTimeStamp())
                    .list();
            tx.commit();

            // Convert to return type
            if(missileLauncherSqlEntities != null) {
                missileLauncherEntities = new ArrayList<>();
                missileLauncherEntities.addAll(missileLauncherSqlEntities);
            }
        } catch (Exception ex) {
            if (tx != null)
                tx.rollback();
            logger.error(ex);
        } finally {
            session.close();
            return missileLauncherEntities;
        }
    }

    @Override
    public IMissileLauncherEntity readMissileLauncherByMissile(IMissileEntity missileEntity) {
        Session session = factory.openSession();
        Transaction tx = null;
        IMissileLauncherEntity rMissileLauncher = null;
        try {
            tx = session.beginTransaction();
            rMissileLauncher = session.createQuery(
                    "SELECT ml " +
                            "FROM MissileLauncherSqlEntity ml JOIN  ml.missiles m " +
                            "WHERE m.id LIKE :mId AND m.warModelId LIKE :mWMId", MissileLauncherSqlEntity.class)
                    .setParameter("mId", missileEntity.getId())
                    .setParameter("mWMId", missileEntity.getWarModelId())
                    .getSingleResult();
            tx.commit();
        } catch (Exception ex) {
            if (tx != null)
                tx.rollback();
            logger.error(ex);
        } finally {
            session.close();
            return rMissileLauncher;
        }
    }

    @Override
    @NotImplemented
    public List<IMissileLauncherEntity> readMissileLauncherByIsDestructed(boolean isDestructed) {
        /* NOT IMPLEMENTED */
        return null;
    }

    @Override
    @NotImplemented
    public List<IMissileLauncherEntity> readMissileLauncherByIsHidden(boolean isHidden) {
        /* NOT IMPLEMENTED */
        return null;
    }

    @Override
    public IMissileDestructorEntity readMissileDestructorById(String id, long warModelId) {
        Session session = factory.openSession();
        Transaction tx = null;
        IMissileDestructorEntity missileDestructorEntity = null;
        try {
            tx = session.beginTransaction();
            missileDestructorEntity = session.get(MissileDestructorSqlEntity.class, new MissileDestructorPK(id, warModelId));
            tx.commit();
        } catch (Exception ex) {
            if (tx != null)
                tx.rollback();
            logger.error(ex);
        } finally {
            session.close();
            return missileDestructorEntity;
        }
    }

    @Override
    public List<IMissileDestructorEntity> readMissileDestructorByWarModel(IWarModelEntity warModelEntity) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<IMissileDestructorEntity> missileDestructorEntities = null;
        List<MissileDestructorSqlEntity> missileDestructorSqlEntities = null;
        try {
            tx = session.beginTransaction();
            missileDestructorSqlEntities = session.createQuery(
                    "SELECT md " +
                            "FROM MissileDestructorSqlEntity md " +
                            "WHERE ml.warModelId LIKE :aWarModelId", MissileDestructorSqlEntity.class)
                    .setParameter("aWarModelId", warModelEntity.getTimeStamp())
                    .list();
            tx.commit();

            if(missileDestructorSqlEntities != null)
                missileDestructorEntities = new ArrayList<>(missileDestructorSqlEntities);

        } catch (Exception ex) {
            if (tx != null)
                tx.rollback();
            logger.error(ex);
        } finally {
            session.close();
            return missileDestructorEntities;
        }
    }

    @Override
    @NotImplemented
    public IMissileDestructorEntity readMissileDestructorByMissile(IMissileEntity missileEntity) {
        /* NOT IMPLEMENTED */
        return null;
    }

    @Override
    public ILauncherDestructorEntity readLauncherDestructorById(String id, long warModelId) {
        Session session = factory.openSession();
        Transaction tx = null;
        ILauncherDestructorEntity launcherDestructorEntity = null;
        try {
            tx = session.beginTransaction();
            launcherDestructorEntity = session.get(LauncherDestructorSqlEntity.class, new LauncherDestructorPK(id, warModelId));
            tx.commit();
        } catch (Exception ex) {
            if (tx != null)
                tx.rollback();
            logger.error(ex);
        } finally {
            session.close();
            return launcherDestructorEntity;
        }
    }

    @Override
    public List<ILauncherDestructorEntity> readLauncherDestructorByWarModel(IWarModelEntity warModelEntity) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<ILauncherDestructorEntity> launcherDestructorEntities = null;
        List<LauncherDestructorSqlEntity> launcherDestructorSqlEntities = null;
        try {
            tx = session.beginTransaction();
            launcherDestructorSqlEntities = session.createQuery("SELECT md " +
                    "FROM LauncherDestructorSqlEntity ld " +
                    "WHERE ld.warModelId LIKE :aWarModelId", LauncherDestructorSqlEntity.class)
                    .setParameter("aWarModelId", warModelEntity.getTimeStamp())
                    .list();
            tx.commit();

            if(launcherDestructorSqlEntities != null)
                launcherDestructorEntities = new ArrayList<>(launcherDestructorSqlEntities);

        } catch (Exception ex) {
            if (tx != null)
                tx.rollback();
            logger.error(ex);
        } finally {
            session.close();
            return launcherDestructorEntities;
        }
    }

    @Override
    @NotImplemented
    public List<ILauncherDestructorEntity> readLauncherDestructorByType(LauncherDestructorTypeEnum type) {
        /* NOT IMPLEMENTED */
        return null;
    }

    @Override
    @NotImplemented
    public List<ILauncherDestructorEntity> readLauncherDestructorByMissileLauncher(IMissileLauncherEntity missileLauncherEntity) {
        /* NOT IMPLEMENTED */
        return null;
    }


    /***********************
     ** UPDATE OPERATIONS **
     ***********************/
    @Override
    public boolean updateWarModel(IWarModelEntity warModelEntity) {
        Session session = factory.openSession();
        Transaction tx = null;
        boolean flag = true;
        try {
            session.update((WarModelSqlEntity)warModelEntity);
            tx.commit();
        } catch(Exception ex) {
            if (tx != null)
                tx.rollback();
            flag = false;
            logger.error(ex);
        } finally {
            session.close();
            return flag;
        }
    }

    @Override
    public boolean updateMissile(IMissileEntity missile) {
        Session session = factory.openSession();
        Transaction tx = null;
        boolean flag = true;
        try {
            session.saveOrUpdate((MissileSqlEntity)missile);
            tx.commit();
        } catch(Exception ex) {
            if (tx != null)
                tx.rollback();
            flag = false;
            logger.error(ex);
        } finally {
            session.close();
            return flag;
        }
    }

    @Override
    public boolean updateMissileLauncher(IMissileLauncherEntity missileLauncher) {
        Session session = factory.openSession();
        Transaction tx = null;
        boolean flag = true;
        try {
            session.saveOrUpdate((MissileLauncherSqlEntity)missileLauncher);
            tx.commit();
        } catch(Exception ex) {
            if (tx != null)
                tx.rollback();
            flag = false;
            logger.error(ex);
        } finally {
            session.close();
            return flag;
        }
    }

    @Override
    public boolean updateMissileDestructor(IMissileDestructorEntity missileDestructorEntity) {
        Session session = factory.openSession();
        Transaction tx = null;
        boolean flag = true;
        try {
            session.saveOrUpdate((MissileDestructorSqlEntity)missileDestructorEntity);
            tx.commit();
        } catch(Exception ex) {
            if (tx != null)
                tx.rollback();
            flag = false;
            logger.error(ex);
        } finally {
            session.close();
            return flag;
        }
    }

    @Override
    public boolean updateLauncherDestructor(ILauncherDestructorEntity launcherDestructorEntity) {
        Session session = factory.openSession();
        Transaction tx = null;
        boolean flag = true;
        try {
            session.saveOrUpdate((LauncherDestructorSqlEntity)launcherDestructorEntity);
            tx.commit();
        } catch(Exception ex) {
            if (tx != null)
                tx.rollback();
            flag = false;
            logger.error(ex);
        } finally {
            session.close();
            return flag;
        }
    }

    /***********************
     ** DELETE OPERATIONS **
     ***********************/
    @Override
    public boolean deleteWarModel(IWarModelEntity warModelEntity) {
        Session session = factory.openSession();
        Transaction tx = null;
        boolean flag = true;
        try {
            session.delete((WarModelSqlEntity)warModelEntity);
            tx.commit();
        } catch(Exception ex) {
            if (tx != null)
                tx.rollback();
            flag = false;
            logger.error(ex);
        } finally {
            session.close();
            return flag;
        }
    }

    @Override
    public boolean deleteMissile(IMissileEntity missile) {
        Session session = factory.openSession();
        Transaction tx = null;
        boolean flag = true;
        try {
            session.delete((MissileSqlEntity)missile);
            tx.commit();
        } catch(Exception ex) {
            if (tx != null)
                tx.rollback();
            flag = false;
            logger.error(ex);
        } finally {
            session.close();
            return flag;
        }
    }

    @Override
    public boolean deleteMissileLauncher(IMissileLauncherEntity missileLauncher) {
        Session session = factory.openSession();
        Transaction tx = null;
        boolean flag = true;
        try {
            session.delete((MissileLauncherSqlEntity)missileLauncher);
            tx.commit();
        } catch(Exception ex) {
            if (tx != null)
                tx.rollback();
            flag = false;
            logger.error(ex);
        } finally {
            session.close();
            return flag;
        }
    }

    @Override
    public boolean deleteMissileDestructor(IMissileDestructorEntity missileDestructorEntity) {
        Session session = factory.openSession();
        Transaction tx = null;
        boolean flag = true;
        try {
            session.delete((MissileDestructorSqlEntity)missileDestructorEntity);
            tx.commit();
        } catch(Exception ex) {
            if (tx != null)
                tx.rollback();
            flag = false;
            logger.error(ex);
        } finally {
            session.close();
            return flag;
        }
    }

    @Override
    public boolean deleteLauncherDestructor(ILauncherDestructorEntity launcherDestructorEntity) {
        Session session = factory.openSession();
        Transaction tx = null;
        boolean flag = true;
        try {
            session.delete((LauncherDestructorSqlEntity)launcherDestructorEntity);
            tx.commit();
        } catch(Exception ex) {
            if (tx != null)
                tx.rollback();
            flag = false;
            logger.error(ex);
        } finally {
            session.close();
            return flag;
        }
    }
}
