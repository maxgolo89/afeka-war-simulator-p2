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
    long updateWarModel(IWarModelEntity warModelSqlEntity);

    /* *** MissileEntity *** */
    String updateMissile(IMissileEntity missile);

    /* *** MissileLauncherEntity *** */
    String updateMissileLauncher(IMissileLauncherEntity missileLauncher);

    /* *** MissileDestructorEntity *** */
    String updateMissileDestructor(IMissileDestructorEntity missileDestructorSqlEntity);

    /* *** LauncherDestructorEntity *** */
    String updateLauncherDestructor(ILauncherDestructorEntity launcherDestructorSqlEntity);

    /***********************
     ** DELETE OPERATIONS **
     ***********************/
    /* *** WarModelEntity *** */
    long deleteWarModel(IWarModelEntity warModelSqlEntity);

    /* *** MissileEntity *** */
    String deleteMissile(IMissileEntity missile);

    /* *** MissileLauncherEntity *** */
    String deleteMissileLauncher(IMissileLauncherEntity missileLauncher);

    /* *** MissileDestructorEntity *** */
    String deleteMissileDestructor(IMissileDestructorEntity missileDestructorSqlEntity);

    /* *** LauncherDestructorEntity *** */
    String deleteLauncherDestructor(ILauncherDestructorEntity launcherDestructorSqlEntity);
}
