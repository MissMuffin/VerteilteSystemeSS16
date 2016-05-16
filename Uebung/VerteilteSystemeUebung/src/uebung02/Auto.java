package uebung02;

import util.Logger;

/**
 * Class represents a car thread that drives into a Parkhaus and 
 * then leaves again after a random time interval
 * 
 * @author Bianca Ploch
 */
class Auto extends Thread {
	
	private Parkhaus parkhaus;
	private String myName;
	private Logger logger = Logger.getInstance();
	private int numberInQueue = -1;
	
	/**
	 * Auto constructor, calls start() after the variables have been set
	 * @param p		Parkhaus to be driven in and out
	 * @param name	Identifier name of the car
	 */
	public Auto(Parkhaus p, String name) {
		this.myName = name;
		this.parkhaus = p;
		this.start();
	}	
	
	/**
	 * @return	The number of this thread in the waiting queue of threads
	 */
	public int getNumberInQueue() {
		return numberInQueue;
	}
	
	/**
	 * @return The name String for this Auto
	 */
	public String getMyName() {
		return myName;
	}


	/**
	 * Sets {@link Auto#numberInQueue} as an indicator of this threads place in the waiting queue
	 * @param numberInQueue		Number of place in thread queue
	 */
	public void setNumberInQueue(int numberInQueue) {
		this.numberInQueue = numberInQueue;
	}


	/* 
	 * Thread tries to sleep for a random time interval first, then
	 * drives into the Parkhaus where it again sleeps for a random time 
	 * interval and then leaves the Parkhaus
	 * 
	 * @see java.lang.Thread#run()
	 * @throws InterruptedException If a Auto's {@link Auto#sleep()} interruption occurs 
	 */
	public void run() {
		
		try {
			sleep((int)(Math.random() * 1000));
		} catch (InterruptedException e) {
			logger.error("Initialer Auto-Sleep unterbrochen");
		}
		
		logger.info("Auto " + myName + " ist am Parkhaus angekommen");
		parkhaus.einfahren(this);
		
		try {
			sleep((int)(Math.random() * 2000));
		} catch (InterruptedException e) {
			logger.error("Ausfahr-Sleep unterbrochen");
		}
		
		parkhaus.ausfahren(this);
	}
}