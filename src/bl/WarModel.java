package bl;

import java.time.LocalTime;
import java.util.Optional;
import java.util.Vector;
import mvc.WarModelEventsListener;
import program.IConstants;
import program.Program;

public class WarModel implements IWar, BLConstants, IConstants {

	protected static WarModel 				war;
	private boolean 						exitFlag = false;

	private LocalTime 						startTime;
	private WarTime							warTime;
	
	private int 							hits;
	private int 							totalDamage;
	private int 							launchedMissiles;
	private int 							destructedMissiles;
	private int 							destructedLaunchers;
	
	private Vector<Missile>					allMissiles = new Vector<>();
	private Vector<MissileLauncher>			allLaunchers = new Vector<>();
	private Vector<MissileDestructor>		allMissileDestructors = new Vector<>();
	private Vector<LauncherDestructor>		allLauncherDestructors = new Vector<>();
		
	private Vector<WarModelEventsListener>	allListeners = new Vector<WarModelEventsListener>();

	private int i = 1;
	
	protected WarModel() {
		startTime = java.time.LocalTime.now();
		warTime = new WarTime();
		warTime.startTimer();
	}

	public static WarModel getInstance(){
		if (war == null)
			war = new WarModel();
		return war;
	}


    /** *****************************************************************************************
     * 		ADD WAR OBJECT
     * 	***************************************************************************************** */
	public void addMissileLauncher(String id, boolean isHidden) {
		MissileLauncher l;
		if ( isHidden )
			l = (HiddenMissileLauncher)Program.getContext().getBean("hidden_missile_launcher");
		else
			l = (MissileLauncher)Program.getContext().getBean("missile_launcher");

		l.setId(id);
		l.setWar(war);
		
		allLaunchers.add(l);
		Thread launcherThread = new Thread(l);
		launcherThread.start();	
		
		fireAddMissileLuncherEvent(id, isHidden);	
	}

	public void addMissileDestructor(String id) {
		MissileDestructor d = (MissileDestructor)Program.getContext().getBean("missile_destructor");
		d.setId(id);
		d.setWar(war);

		allMissileDestructors.add(d);
		Thread missileDestructorThread = new Thread(d);
		missileDestructorThread.start();	
		
		fireAddMissileDestructorEvent(id);
	}

	public void addLauncherDestructor(String id, LauncherDestructorType type) {
		LauncherDestructor d = (LauncherDestructor)Program.getContext().getBean("launcher_destructor");
		d.setId(id);
		d.setType(type);
		d.setWar(war);

		allLauncherDestructors.add(d);
		Thread launcherDestructorThread = new Thread(d);
		launcherDestructorThread.start();	
		
		fireAddLauncherDestructorEvent(id, type);
	}

	
	// launch
	public void addMissileLaunch(String launcherID, String missileID, int potentialDamage, String destination,
			int flyTime) {
		MissileLauncher launcher = getLauncherById(launcherID);
		if ( launcher == null )
			return;
		
		Missile m = (Missile)Program.getContext().getBean("missile");
		m.setId(missileID);
		m.setPotentialDamage(potentialDamage);
		m.setDestination(destination);
		m.setFlyTime(flyTime);
		m.setLauncher(launcher);

		launcher.addMissileToLaunchQueue(m);
		allMissiles.add(m);
		fireAddMissileLaunch(launcherID, missileID, potentialDamage, destination, flyTime);
	}
    /*  *****************************************************************************************
	 * 		END OF ADD WAR OBJECT
	 * 	***************************************************************************************** */

    /** *****************************************************************************************
     * 		LAUNCH, DESTRUCTION, UN-HIDE EVENTS
     * 	***************************************************************************************** */
	public synchronized void launchStarted(String launcherID, String missileID, String destination, int flyTime) {
		launchedMissiles++;
		fireStartMissileLaunchEvent(launcherID, missileID, destination, flyTime);
	}

	public synchronized void launchEnded(MissileLauncher l, String missileID, boolean success, String destination, int damage,
			int flightTime) {
		
		if( success ){
			hits++;
			totalDamage += damage;
		}
		
		fireEndMissileLaunchEvent(l.getID(), missileID, destination, damage);
	}

	public void launcherStateChanged(String launcherID, boolean isHidden){
		fireLauncherStateChangedEvent(launcherID, isHidden);
	}
	
	// missile-destruct
	public void addMissileDestruct(String destructorID, String missileID) {
		Missile m = getMissileById(missileID);
		if (m == null)
			return;
		
		MissileDestructor destructor = getMissileDestructorById(destructorID);
		if ( destructor == null )
			return;
			
		destructor.addTargetToQueue(m);
	}

	public synchronized void missileDestructStarted(String destructorID, String missileID, int duration) {
		fireStartDestructMissileEvent(destructorID, missileID, duration);
	}

	public synchronized void missileDestructEnded(String destructorID, String missileID, boolean isDestructed) {
		if( !isDestructed )
			return;
		destructedMissiles++;
		Missile m = getMissileById(missileID);
		if ( m != null )
			allMissiles.remove(m);
		fireEndDestructMissileEvent(destructorID, missileID, isDestructed);
		
	}

	// launcher - destruct
	public void addLauncherDestruct(String destructorID, String launcherID) {
		MissileLauncher l = getLauncherById(launcherID);
		if (l == null)
			return;
		LauncherDestructor destructor = getLauncherDestructorById(destructorID);
		if ( destructor == null )
			return;
		
		destructor.addTargetToQueue(l);
	}

	public synchronized void launcherDestructStarted(String destructorID, String launcherID, int duration) {
		fireStartDestructLauncherEvent(destructorID, launcherID, duration);
	}

	public	synchronized void launcherDestructEnded(String destructorID, String launcherID, boolean isDestructed) {
		if ( isDestructed ){
			destructedLaunchers++;
			MissileLauncher l = getLauncherById(launcherID);
//			if ( l != null )
//				allLaunchers.remove(l);
			l.setDestructed(isDestructed);
			fireEndDestructLauncherEvent(destructorID, launcherID, isDestructed);
		}
		
		if ( isWarOver() )
			exit();
	}
    /*  *****************************************************************************************
	 * 		END OF LAUNCH, DESTRUCTION, UN-HIDE EVENTS
	 * 	***************************************************************************************** */

	public void statistics(boolean exit){					
		fireStatisticsEvent(totalDamage, launchedMissiles, hits, destructedMissiles, destructedLaunchers, exit);
	}

	public synchronized void exit() {
		if(!exitFlag) {
			exitFlag = true;
			System.out.println("Exiting");
			endWar();
			warTime.killTimer();
			//this
			fireExitEvent();
		}
	}


	/** *****************************************************************************************
	 * 		LISTENER REGISTRATION AND EVENT DISPATCHERS
	 * 	***************************************************************************************** */
	public void registerListener(WarModelEventsListener listener) {
		allListeners.add(listener);
	}

	protected void fireAddMissileLaunch(String launcherID, String missileID, int potentialDamage, String destination, int flyTime) {
		for (WarModelEventsListener l : allListeners)
			l.addMissileLaunchInModel(launcherID, missileID, potentialDamage, destination, flyTime);
	}

	// fire to controller
	protected void fireAddMissileLuncherEvent(String id, boolean isHidden) {
		for (WarModelEventsListener l : allListeners)
			l.addMissileLauncherInModel(id, isHidden);
	}

	protected void fireAddMissileDestructorEvent(String id) {
		for (WarModelEventsListener l : allListeners) {
			l.addMissileDestructorInModel(id);
		}
	}

	protected void fireAddLauncherDestructorEvent(String id, LauncherDestructorType type) {
		for (WarModelEventsListener l : allListeners) {
			l.addLauncherDestructorInModel(id, type);
		}
	}

	protected void fireStartMissileLaunchEvent(String launcherID, String missileID, String destination, int flyTime) {
		for (WarModelEventsListener l : allListeners) 
			l.startMissileLaunchInModel(launcherID, missileID, flyTime, destination);
	}

	public void fireEndMissileLaunchEvent(String launcherID, String missileID, String destination, int damage) {
		for (WarModelEventsListener l : allListeners) {
			l.endMissileLaunchInModel(launcherID, missileID, damage, destination);
		}
	}

	public void fireLauncherStateChangedEvent(String launcherID, boolean isHidden){
		for (WarModelEventsListener l : allListeners) {
			l.launcherStateChangedInModel(launcherID, isHidden);
		}
	}

	protected void fireStartDestructMissileEvent(String destructorID, String missileID, int duration) {
		for (WarModelEventsListener l : allListeners) {
			l.startDestructMissileInModel(destructorID, missileID, duration);
		}
	}

	protected void fireEndDestructMissileEvent(String destructorID, String missileID, boolean isDestructed) {
		for (WarModelEventsListener l : allListeners) {
			l.endDestructMissileInModel( destructorID, missileID, isDestructed);
		}
	}

	protected void fireStartDestructLauncherEvent(String destructorID, String launcherID, int duration) {
		for (WarModelEventsListener l : allListeners) {
			l.startDestructLauncherInModel(destructorID, launcherID, duration);
		}
	}

	protected void fireEndDestructLauncherEvent(String destructorID, String launcherID, boolean isDestructed) {
		for (WarModelEventsListener l : allListeners) {
			l.endDestructLauncherInModel(destructorID, launcherID, isDestructed);
		}
	}

	protected void fireStatisticsEvent(int totalDamage, int launchedMissiles, int hits, int destructedMissiles, int destructedLaunchers, boolean exit) {
		for (WarModelEventsListener l : allListeners)
			l.statisticsInModel(totalDamage, launchedMissiles, hits, destructedMissiles, destructedLaunchers, exit);
	}

	protected void fireExitEvent() {
		for (WarModelEventsListener l : allListeners) {
			l.exitInModel();
		}
	}
	/*  *****************************************************************************************
	 * 		END OF LISTENER REGISTRATION AND EVENT DISPATCHERS
	 * 	***************************************************************************************** */

	
	public void endWar(){
		for ( MissileLauncher l : allLaunchers)
			l.terminate();
		for( MissileDestructor md : allMissileDestructors )
			md.terminate();
		for( LauncherDestructor ld : allLauncherDestructors )
			ld.terminate();
	}
	
	/** *****************************************************************************************
	 * 		GETTERS
	 * 	***************************************************************************************** */
	public MissileLauncher getLauncherById(String id){
		Optional<MissileLauncher> matchingObject = allLaunchers.stream().
				filter(l -> l.getID().equals(id)).findFirst();
		return matchingObject.orElse(null);		
	}
	
	public Missile getMissileById(String id){
		Optional<Missile> matchingObject = allMissiles.stream().
				filter(m -> m.getID().equals(id)).findFirst();
		return matchingObject.orElse(null);		
	}
	
	public MissileDestructor getMissileDestructorById(String id){
		Optional<MissileDestructor> matchingObject = allMissileDestructors.stream().
				filter(d -> d.getID().equals(id)).findFirst();
		return matchingObject.orElse(null);		
	}
	
	public LauncherDestructor getLauncherDestructorById(String id){
		Optional<LauncherDestructor> matchingObject = allLauncherDestructors.stream().
				filter(d -> d.getID().equals(id) ).findFirst();
		return matchingObject.orElse(null);		
	}
	/*  *****************************************************************************************
	 * 		END OF GETTERS
	 * 	***************************************************************************************** */
	
	public boolean isWarOver(){
		for ( MissileLauncher l : allLaunchers){
			if ( !l.isDestructed() )
				return false;
		}
		return true;
	}

	public int getWarTimeInSeconds(){
		// LocalTime time =  java.time.LocalTime.now();
		// int warTimeNow =  time.getHour() * 60 * 60 - startTime.getHour() * 60 * 60
		// 			 + time.getMinute() * 60 - startTime.getMinute() * 60 
		// 			 + time.getSecond() - startTime.getSecond() - 9;
		return warTime.getTime();
	}

	public static WarModel getWar() {
		return war;
	}

	public static void setWar(WarModel war) {
		WarModel.war = war;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public WarTime getWarTime() {
		return warTime;
	}

	public void setWarTime(WarTime warTime) {
		this.warTime = warTime;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getTotalDamage() {
		return totalDamage;
	}

	public void setTotalDamage(int totalDamage) {
		this.totalDamage = totalDamage;
	}

	public int getLaunchedMissiles() {
		return launchedMissiles;
	}

	public void setLaunchedMissiles(int launchedMissiles) {
		this.launchedMissiles = launchedMissiles;
	}

	public int getDestructedMissiles() {
		return destructedMissiles;
	}

	public void setDestructedMissiles(int destructedMissiles) {
		this.destructedMissiles = destructedMissiles;
	}

	public int getDestructedLaunchers() {
		return destructedLaunchers;
	}

	public void setDestructedLaunchers(int destructedLaunchers) {
		this.destructedLaunchers = destructedLaunchers;
	}

	public Vector<Missile> getAllMissiles() {
		return allMissiles;
	}

	public void setAllMissiles(Vector<Missile> allMissiles) {
		this.allMissiles = allMissiles;
	}

	public Vector<MissileLauncher> getAllLaunchers() {
		return allLaunchers;
	}

	public void setAllLaunchers(Vector<MissileLauncher> allLaunchers) {
		this.allLaunchers = allLaunchers;
	}

	public Vector<MissileDestructor> getAllMissileDestructors() {
		return allMissileDestructors;
	}

	public void setAllMissileDestructors(Vector<MissileDestructor> allMissileDestructors) {
		this.allMissileDestructors = allMissileDestructors;
	}

	public Vector<LauncherDestructor> getAllLauncherDestructors() {
		return allLauncherDestructors;
	}

	public void setAllLauncherDestructors(Vector<LauncherDestructor> allLauncherDestructors) {
		this.allLauncherDestructors = allLauncherDestructors;
	}

	public Vector<WarModelEventsListener> getAllListeners() {
		return allListeners;
	}

	public void setAllListeners(Vector<WarModelEventsListener> allListeners) {
		this.allListeners = allListeners;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
}
