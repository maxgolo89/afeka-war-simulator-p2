package db.dal;

import bl.*;
import db.dal.entities.*;
import program.IConstants;

public interface IEntityConverter {
    // To dao
    IMissileDao                 toMissileDao(String id, String destination, int flyTime, int damage, int potentialDamage, boolean isDestructed);
    IMissileDestructorDao       toMissileDestructorDao(String id);
    IMissileLauncherDao         toMissileLauncherDao(String id, boolean isDestructed);
    IHiddenMissileLauncherDao   toHiddenMissileLauncherDao(String id, boolean isDestructed, boolean isHiding);
    ILauncherDestructorDao      toLauncherDestructorDao(String id, IConstants.LauncherDestructorType type);
    IWarModelDao                toWarModelDao(long id, int hits, int totalDamage, int launchedMissiles, int destructedMissiles, int destructedLaunchers);

    // To app objects
    Missile                     toMissileObject(IMissileDao missile);
    MissileDestructor           toMissileDestructorObject(IMissileDestructorDao missileDestructor);
    MissileLauncher             toMissileLauncherObject(IMissileLauncherDao missileLauncher);
    HiddenMissileLauncher       toHiddenMissileLauncherObject(IHiddenMissileLauncherDao hiddenMissileLauncher);
    LauncherDestructor          toLauncherDestructorObject(ILauncherDestructorDao launcherDestructor);
    WarModel                    toWarModelObject(IWarModelDao warModel);
}
