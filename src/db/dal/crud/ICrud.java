package db.dal.crud;

import db.dal.entities.*;

import java.util.List;

public interface ICrud {

    /***********************
     ** CREATE OPERATIONS **
     ***********************/
    /* *** WarModelEntity *** */
    int createWarModel(IWarModelDao warModelEntity);

    /* *** MissileEntity *** */
    int createMissile(IMissileDao missile);

    /* *** MissileLauncherEntity *** */
    int createMissileLauncher(IMissileLauncherDao missileLauncher);

    /* *** HiddenMissileLauncherEntity *** */
    int createHiddenMissileLauncher(IHiddenMissileLauncherDao hiddenMissileLauncher);

    /* *** MissileDestructorEntity *** */
    int createMissileDestructor(IMissileDestructorDao missileDestructorEntity);

    /* *** LauncherDestructorEntity *** */
    int createLauncherDestructor(ILauncherDestructorDao launcherDestructorEntity);

    /***********************
     ** READ OPERATIONS **
     ***********************/
    /* *** WarModelEntity *** */
    IWarModelDao readWarModelById(long wid);

    /* *** MissileEntity *** */
    List<IMissileDao> readMissileById(String id);
    List<IMissileDao> readMissileByWarModel(long wid);

    /* *** MissileLauncherEntity *** */
    List<IMissileLauncherDao> readMissileLauncherById(String id);
    List<IMissileLauncherDao> readMissileLauncherByWarModel(long wid);

    /* *** HiddenMissileLauncherEntity *** */
    List<IHiddenMissileLauncherDao> readHiddenMissileLauncherById(String id);
    List<IHiddenMissileLauncherDao> readHiddenMissileLauncherByWarModel(long wid);

    /* *** MissileDestructorEntity *** */
    List<IMissileDestructorDao> readMissileDestructorById(String id);
    List<IMissileDestructorDao> readMissileDestructorByWarModel(long wid);

    /* *** LauncherDestructorEntity *** */
    List<ILauncherDestructorDao> readLauncherDestructorById(String id);
    List<ILauncherDestructorDao> readLauncherDestructorByWarModel(long wid);
}
