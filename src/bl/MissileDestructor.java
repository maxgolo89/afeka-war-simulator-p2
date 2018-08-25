package bl;

import org.springframework.aop.framework.AopContext;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class MissileDestructor extends WarObject {

	private Queue<Missile> targets = new LinkedList<>();

	public MissileDestructor() {
	}

	public MissileDestructor(String id, WarModel war) {
		super(id, war);
	}
	
	public synchronized void addTargetToQueue(Missile m){
		targets.add(m);
		notifyCheck();
	}

	public void run() {
		while ( inWar ) {
			if ( !targets.isEmpty() )
				destructMissile();
			else {
				synchronized (this) {
					try {
						waitingForWork = true;
						wait(); 
						waitingForWork = false;
					} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		}
	}
	
	public void destructMissile() {
		try {
			Missile m = targets.poll();
			if (m == null) 
				return;
			int duration = new Random().nextInt(MAX_TIME) + 1;
			war.missileDestructStarted(id, m.getID(), duration);
			boolean success = false;
			Thread.sleep(duration*ONE_SEC);

			if ( !m.isDestructed() && m.isAlive() ) {
				m.destructMissile();
				if (m.isDestructed())
					success = true;		
			}

			/** Very ugly solution but necessary.
			 *  The spring aspect use proxy class created to hook the methods called.
			 *  When a method A in class A is called from class B, the call is triggered through the aspect proxy,
			 *  but when method A in class A is called from within class A, the proxy is bypassed because it is a direct call on 'this'.
			 *  Therefore, the call to updateResults need to be hooked through the exposed proxy object. */
			MissileDestructor md = (MissileDestructor) AopContext.currentProxy();
			md.updateResults(m,  success);
			
		} catch (InterruptedException e) {e.printStackTrace();	}
	}
	
	public void updateResults(Missile m, boolean success){
		String missileID = m.getID();
		war.missileDestructEnded(id, missileID, success);
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	public Queue<Missile> getTargets() {
		return targets;
	}

	public void setTargets(Queue<Missile> targets) {
		this.targets = targets;
	}


}