package bl;

import Logging.WarLogsGenerator;

public abstract class WarObject implements Runnable, BLConstants{

	protected String 			id;
	protected boolean			inWar;
	protected boolean			waitingForWork;
	protected WarModel 			war;
	
	protected WarLogsGenerator 	logsGen = WarLogsGenerator.getInstance();

	public WarObject() {
		inWar = true;
		waitingForWork = false;
	}

	public WarObject(String id, WarModel war){
		this.id = id;
		this.war = war;
		
		inWar = true;	
		waitingForWork = false;
		
		logsGen.addWarObject(this);
	}
	
	public String getID() {
		return this.id;
	}
	
	public void terminate(){
		inWar = false;
		notifyCheck();
	}
	
	protected void notifyCheck(){
		synchronized (this) {
			if ( waitingForWork )
				notify();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WarObject other = (WarObject) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isInWar() {
		return inWar;
	}

	public void setInWar(boolean inWar) {
		this.inWar = inWar;
	}

	public boolean isWaitingForWork() {
		return waitingForWork;
	}

	public void setWaitingForWork(boolean waitingForWork) {
		this.waitingForWork = waitingForWork;
	}

	public WarModel getWar() {
		return war;
	}

	public void setWar(WarModel war) {
		this.war = war;
	}

	public WarLogsGenerator getLogsGen() {
		return logsGen;
	}

	public void setLogsGen(WarLogsGenerator logsGen) {
		this.logsGen = logsGen;
	}
}