package db.dal;

import db.dal.entities.*;
import program.IConstants;

import java.util.List;

public interface ICrud {

    /***********************
     ** CREATE OPERATIONS **
     ***********************/
    /* *** WarModelEntity *** */
    long createWarModel(IWarModelEntity warModelEntity);

    /* *** MissileEntity *** */
    String createMissile(IMissileEntity missile);

    /* *** MissileLauncherEntity *** */
    String createMissileLauncher(IMissileLauncherEntity missileLauncher);

    /* *** MissileDestructorEntity *** */
    String createMissileDestructor(IMissileDestructorEntity missileDestructorEntity);

    /* *** LauncherDestructorEntity *** */
    String createLauncherDestructor(ILauncherDestructorEntity launcherDestructorEntity);

    /***********************
     ** READ OPERATIONS **
     ***********************/
    /* *** WarModelEntity *** */
    IWarModelEntity readWarModelById(long id);
    List<IWarModelEntity> readWarModelByHits(int hits);
    List<IWarModelEntity> readWarModelByTotalDamage(int totalDamage);
    List<IWarModelEntity> readWarModelByLaunchedMissiles(int launchedMissiles);
    List<IWarModelEntity> readWarModelDestructedMissiles(int destructedMissiles);
    List<IWarModelEntity> readWarModelDestructedLaunchers(int destructedLaunchers);

    /* *** MissileEntity *** */
    IMissileEntity readMissileById(String id, long warModelId);
    List<IMissileEntity> readMissileByWarModel(IWarModelEntity warModelEntity);
    List<IMissileEntity> readMissileByDestination(String destination);
    List<IMissileEntity> readMissileByFlyTime(int flyTime);
    List<IMissileEntity> readMissileByDamage(int damage);
    List<IMissileEntity> readMissileByPotentialDamage(int potentialDamage);
    List<IMissileEntity> readMissileByIsDone(boolean isDone);
    List<IMissileEntity> readMissileByIsDestructed(boolean isDestructed);
    List<IMissileEntity> readMissileByLauncher(IMissileLauncherEntity missileLauncherEntity);


    /* *** MissileLauncherEntity *** */
    IMissileLauncherEntity readMissileLauncherById(String id, long warModelId);
    List<IMissileLauncherEntity> readMissileLauncherByWarModel(IWarModelEntity warModelEntity);
    IMissileLauncherEntity readMissileLauncherByMissile(IMissileEntity missileEntity);
    List<IMissileLauncherEntity> readMissileLauncherByIsDestructed(boolean isDestructed);
    List<IMissileLauncherEntity> readMissileLauncherByIsHidden(boolean isHidden);

    /* *** MissileDestructorEntity *** */
    IMissileDestructorEntity readMissileDestructorById(String id, long warModelId);
    List<IMissileDestructorEntity> readMissileDestructorByWarModel(IWarModelEntity warModelEntity);
    IMissileDestructorEntity readMissileDestructorByMissile(IMissileEntity missileEntity);


    /* *** LauncherDestructorEntity *** */
    ILauncherDestructorEntity readLauncherDestructorById(String id, long warModelId);
    List<ILauncherDestructorEntity> readLauncherDestructorByWarModel(IWarModelEntity warModelEntity);
    List<ILauncherDestructorEntity> readLauncherDestructorByType(LauncherDestructorTypeEnum type);
    List<ILauncherDestructorEntity> readLauncherDestructorByMissileLauncher(IMissileLauncherEntity missileLauncherEntity);

    /***********************
     ** UPDATE OPERATIONS **
     ***********************/
    /* *** WarModelEntity *** */
    boolean updateWarModel(IWarModelEntity warModelSqlEntity);

    /* *** MissileEntity *** */
    boolean updateMissile(IMissileEntity missile);

    /* *** MissileLauncherEntity *** */
    boolean updateMissileLauncher(IMissileLauncherEntity missileLauncher);

    /* *** MissileDestructorEntity *** */
    boolean updateMissileDestructor(IMissileDestructorEntity missileDestructorSqlEntity);

    /* *** LauncherDestructorEntity *** */
    boolean updateLauncherDestructor(ILauncherDestructorEntity launcherDestructorSqlEntity);

    /***********************
     ** DELETE OPERATIONS **
     ***********************/
    /* *** WarModelEntity *** */
    boolean deleteWarModel(IWarModelEntity warModelSqlEntity);

    /* *** MissileEntity *** */
    boolean deleteMissile(IMissileEntity missile);

    /* *** MissileLauncherEntity *** */
    boolean deleteMissileLauncher(IMissileLauncherEntity missileLauncher);

    /* *** MissileDestructorEntity *** */
    boolean deleteMissileDestructor(IMissileDestructorEntity missileDestructorSqlEntity);

    /* *** LauncherDestructorEntity *** */
    boolean deleteLauncherDestructor(ILauncherDestructorEntity launcherDestructorSqlEntity);
}
