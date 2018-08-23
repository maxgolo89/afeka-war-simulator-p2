package db.dal.converter;

import db.dal.entities.*;
import db.dal.entities.sql.*;
import program.IConstants;

/*************************************************************************************
 *
 * This util intended for converting Application scope object, to persistent classes.
 * The implementation is loaded dynamically, using factory pattern.
 *
 * *********************************************************************************** */
public class ObjectToDaoConverter implements IObjectToDaoConverter {

    /************
     ** TO DAO **
     ************/
    @Override
    public IMissileDao toMissileDao(String id, String destination, int flyTime, int damage, int potentialDamage, boolean isDestructed) {
        IMissileDao m = new MissileSqlDao();
        m.setmId(id);
        m.setDestination(destination);
        m.setFlyTime(flyTime);
        m.setDamage(damage);
        m.setPotentialDamage(potentialDamage);
        m.setDestructed(isDestructed);
        return m;
    }

    @Override
    public IMissileDestructorDao toMissileDestructorDao(String id) {
        IMissileDestructorDao md = new MissileDestructorSqlDao();
        md.setmDId(id);
        return md;
    }

    @Override
    public IMissileLauncherDao toMissileLauncherDao(String id, boolean isDestructed) {
        IMissileLauncherDao ml = new MissileLauncherSqlDao();
        ml.setmLId(id);
        ml.setDestructed(isDestructed);
        return ml;
    }

    @Override
    public IHiddenMissileLauncherDao toHiddenMissileLauncherDao(String id, boolean isDestructed, boolean isHiding) {
        IHiddenMissileLauncherDao hml = new HiddenMissileLauncherSqlDao();
        hml.setmLId(id);
        hml.setDestructed(isDestructed);
        hml.setHiding(isHiding);
        return hml;
    }

    @Override
    public ILauncherDestructorDao toLauncherDestructorDao(String id, IConstants.LauncherDestructorType type) {
        ILauncherDestructorDao ld = new LauncherDestructorSqlDao();
        ld.setlDId(id);
        ld.setType(type == IConstants.LauncherDestructorType.SHIP ? LauncherDestructorTypeEnum.SHIP_E : LauncherDestructorTypeEnum.PLANE_E);
        return ld;
    }

    @Override
    public IWarModelDao toWarModelDao(long id, int hits, int totalDamage, int launchedMissiles, int destructedMissiles, int destructedLaunchers) {
        IWarModelDao w = new WarModelSqlDao();
        w.setwMId(id);
        w.setHits(hits);
        w.setTotalDamage(totalDamage);
        w.setLaunchedMissiles(launchedMissiles);
        w.setDestructedMissiles(destructedMissiles);
        w.setDestructedLaunchers(destructedLaunchers);
        return w;
    }
}
