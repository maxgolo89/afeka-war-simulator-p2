package db.dal;

import program.IConstants;

public interface IDalAppInterface {
    // Added

    boolean addMissileLauncher              (long wId, String mId, boolean isHidden);
    boolean addMissileDestructor            (long wId, String mdId);
    boolean addLauncherDestructor           (long wId, String ldId, IConstants.LauncherDestructorType type);
    boolean addMissile                      (long wId, String lId, String mId, int potentialDamage, String destination, int flyTime);
    boolean addWarModel                     (long wId);


    /* TODO */
    // Update

//    boolean updateMissileLauncherState      (long wId)

}
