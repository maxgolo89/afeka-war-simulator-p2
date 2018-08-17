package db.dal;

import bl.*;
import db.dal.entities.*;
import db.dal.entities.sql.MissileSqlEntity;

public interface IEntityConverter {
    // To db entities
    IMissileEntity              missileToMissileEntity(Missile missile, long warId);
    IMissileDestructorEntity    missileDestructorToMissileDestructorEntity(MissileDestructor missileDestructor, long warId);
    IMissileLauncherEntity      missileLauncherToMissileLauncherEntity(MissileLauncher missileLauncher, long warId);
    IMissileLauncherEntity      hiddenMissileLauncherToMissileLauncherEntity(HiddenMissileLauncher hiddenMissileLauncher, long warId);
    ILauncherDestructorEntity   launcherDestructorToLauncherDestructorEntity(LauncherDestructor launcherDestructor, long warId);
    IWarModelEntity             warModelToWarModelEntity(WarModel warModel);

    // To app objects
    Missile                     missileEntityToMissileObject(IMissileEntity missile);
    MissileDestructor           missileDestructorEntityToMissileDestructorObject(IMissileDestructorEntity missileDestructor);
    MissileLauncher             missileLauncherEntityToMissileLauncherObject(IMissileLauncherEntity missileLauncher);
    HiddenMissileLauncher       hiddenMissileLauncherEntityToMissileLauncherObject(IMissileLauncherEntity hiddenMissileLauncher);
    LauncherDestructor          launcherDestructorEntityToLauncherDestructorObject(ILauncherDestructorEntity launcherDestructor);
    WarModel                    warModelEntityToWarModelObject(IWarModelEntity warModel);
}
