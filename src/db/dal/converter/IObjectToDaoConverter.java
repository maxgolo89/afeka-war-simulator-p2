package db.dal.converter;

import bl.*;
import db.dal.entities.*;
import program.IConstants;

public interface IObjectToDaoConverter {
    // To dao
    IMissileDao                 toMissileDao(String id, String destination, int flyTime, int damage, int potentialDamage, boolean isDestructed);
    IMissileDestructorDao       toMissileDestructorDao(String id);
    IMissileLauncherDao         toMissileLauncherDao(String id, boolean isDestructed);
    IHiddenMissileLauncherDao   toHiddenMissileLauncherDao(String id, boolean isDestructed, boolean isHiding);
    ILauncherDestructorDao      toLauncherDestructorDao(String id, IConstants.LauncherDestructorType type);
    IWarModelDao                toWarModelDao(long id, int hits, int totalDamage, int launchedMissiles, int destructedMissiles, int destructedLaunchers);
}
