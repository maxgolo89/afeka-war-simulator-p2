package aspects;

import Logging.WarLogsGenerator;
import bl.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggerAspects {

    /**********************************************
     *  Hook MissileLauncher updateResult call *
     **********************************************/
    @After("execution(public void bl.MissileLauncher.updateResults(boolean, String, int, int, String)) && args(success, destination, damage, flyTime, missileID)")
    public void logMissileLauncherUpdate(JoinPoint joinPoint, boolean success, String destination, int damage, int flyTime, String missileID) {
        MissileLauncher m = (MissileLauncher) joinPoint.getTarget();
        WarLogsGenerator.getInstance().endLaunch(missileID, m.getId(), destination, damage, flyTime, success, m, m.getWar().getWarTimeInSeconds() - flyTime);
    }

    /**********************************************
     *  Hook LauncherDestructor updateResult call *
     **********************************************/
    @After("execution(public void bl.LauncherDestructor.updateResults(String, boolean)) && args(launcherID, success)")
    public void logLauncherDestructorUpdate(JoinPoint joinPoint, String launcherID, boolean success) {
        LauncherDestructor ld = (LauncherDestructor) joinPoint.getTarget();
        WarLogsGenerator.getInstance().afterLauncherDestruct(ld, launcherID, success);
    }

    /**********************************************
     *  Hook MissileDestructor updateResult call *
     **********************************************/
    @After("execution(public void bl.MissileDestructor.updateResults(bl.Missile, boolean)) && args(m, success)")
    public void logMissileDestructorUpdate(JoinPoint joinPoint, Missile m, boolean success) {
        MissileDestructor md = (MissileDestructor)joinPoint.getTarget();
        WarLogsGenerator.getInstance().afterMissileDestruct(md, m.getID(), success, m.getDamage());
    }

    /**********************************************
     *  Hook WarObject setId call                 *
     **********************************************/
    @Pointcut("execution(public void bl.WarObject.setId(..))")
    private void warObjectIdSetter() {}

    @After("warObjectIdSetter()")
    public void logHandler(JoinPoint joinPoint) {
        WarLogsGenerator.getInstance().addWarObject((WarObject) joinPoint.getTarget());
    }
}
