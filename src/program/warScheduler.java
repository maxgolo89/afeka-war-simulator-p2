package program;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import bl.Missile;
import bl.MissileDestructor;
import bl.WarModel;
import program.IConstants.LauncherDestructorType;

/**
 * This class receives a map of objects with the properties and times
 * and schedules launches at the correct time.
 */

public class warScheduler {
	
	static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
	final static List<MissileDestructor> launchList = new ArrayList<MissileDestructor>();

	//Static maps to hold objects for scheduling 
	final static Map<String, Integer> missiles = new LinkedHashMap<>();
	final static Map<String, Integer> missileDestructors = new LinkedHashMap<>();
	final static Map<String, Integer> launcherDestructors = new LinkedHashMap<>();
	
	static java.lang.reflect.Method method;
	
	static WarModel m;
	
	public warScheduler(WarModel warModel){
		this.m = warModel;
	}
	
	public static void MissileLaunch(Map<String, Integer> list) {
		//Sort the list by launch time
		list = sortByValue(list);
		
		//Copy list to static map for scheduling
		Iterator it = list.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        missiles.put((String)pair.getKey(), (Integer)pair.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    
	    for (Map.Entry<String, Integer> entry : missiles.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
			//scheduleLaunch((Integer)entry.getValue(), missiles);
		}
	}
	
	public static void LDLaunch(Map<String, Integer> list) {
		//Sort the list by launch time
		list = sortByValue(list);
		
		//Copy list to static map for scheduling
		Iterator it = list.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        launcherDestructors.put((String)pair.getKey(), (Integer)pair.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    
	    for (Map.Entry<String, Integer> entry : launcherDestructors.entrySet()) {
			scheduleLaunch((Integer)entry.getValue(), launcherDestructors, "destructLauncher");
		}
	}
	
	public static void MDLaunch(Map<String, Integer> list) {
		//Sort the list by launch time
		list = sortByValue(list);
		//Copy list to static map for scheduling
		Iterator it = list.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        missileDestructors.put((String)pair.getKey(), (Integer)pair.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    
	    for (Map.Entry<String, Integer> entry : missileDestructors.entrySet()) {
			scheduleLaunch((Integer)entry.getValue(), missileDestructors, "destructMissile");
		}
	}
	
	private static void scheduleLaunch(int time, Map<String, Integer> list, String methodName) {
		ScheduledFuture<Object> scheduledFuture = scheduledExecutorService.schedule(new Callable<Object>() {
	        public Object call() throws Exception {
	        	//Create pair variable to hold launch details
	        	Map.Entry<String, Integer> pair = null;
	        	
	        	//Retrieve element from the list
	        	Iterator it = list.entrySet().iterator();
	        	pair = (Entry<String, Integer>) it.next();
	        	
	        	//remove the launched element from the list
	        	list.remove(list.entrySet().iterator().next().getKey());
	        	
	        	//Invoke the correct launching method
//	        	if(methodName == "destructLauncher") 
//	        		Program.destructLauncher(pair.getKey());
//	        	else if (methodName == "destructMissile")
//	        		Program.destructMissile(pair.getKey());
//	        	else if (methodName == "")
//	        		Program.destructLauncher(pair.getKey());
//	        	else
//	        		System.out.println("Log: Unrecognised method");

	            return "Complete";
	        }
	    },
		time,
		TimeUnit.SECONDS);
	}
	
	private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

}