package db.dal;

import bl.*;
import db.dal.entities.*;
import db.dal.entities.sql.MissileSqlEntity;

public interface IEntityConverter {
    // To db entities
    IMissileEntity              missileToMissileEntity(Missile missile);
    IMissileDestructorEntity    missileDestructorToMissileDestructorEntity(MissileDestructor missileDestructor);
    IMissileLauncherEntity      missileLauncherToMissileLauncherEntity(MissileLauncher missileLauncher);
    IMissileLauncherEntity      hiddenMissileLauncherToMissileLauncherEntity(HiddenMissileLauncher hiddenMissileLauncher);
    ILauncherDestructorEntity   launcherDestructorToLauncherDestructorEntity(LauncherDestructor launcherDestructor);
    IWarModelEntity             warModelToWarModelEntity(WarModel warModel);

    // To app objects
    Missile                     MissileEntityToMissileObject(IMissileEntity missile);
    MissileDestructor           missileDestructorEntityToMissileDestructorObject(IMissileDestructorEntity missileDestructor);
    MissileLauncher             missileLauncherEntityToMissileLauncherObject(IMissileLauncherEntity missileLauncher);
    HiddenMissileLauncher       hiddenMissileLauncherEntityToMissileLauncherObject(IMissileLauncherEntity hiddenMissileLauncher);
    LauncherDestructor          launcherDestructorEntityToLauncherDestructorObject(ILauncherDestructorEntity launcherDestructor);
    WarModel                    warModelEntityToWarModelObject(IWarModelEntity warModel);
}
