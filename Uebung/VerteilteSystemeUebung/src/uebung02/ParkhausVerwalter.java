package uebung02;

import util.Logger;

/**
 * Manager class with main method, instantiates Parkhaus object with 5 parking spaces and
 * 11 Auto objects with names corresponding to their index
 * 
 * @author Bianca Ploch
 */
public class ParkhausVerwalter {
	public static void main(String[] args) {
		Parkhaus p = new Parkhaus(5);
		
		Auto[] autos = new Auto[11];
		for(int i = 1; i < autos.length; i++) {
			new Auto(p, "" + i);
		}
	}
}


/**
 * Class that represents Parkhaus, has synchronised methods for
 * letting a car drive into the Parkhaus and letting it leave
 * 
 * @author Bianca Ploch
 */
class Parkhaus {
	private int freiePlaetze;
	private Logger logger = Logger.getInstance();
	
	/**
	 * Parkhaus constructor
	 * @param spaces	The amount of parking spaces the Parkhaus should have
	 */
	public Parkhaus(int spaces) {
		this.freiePlaetze = spaces;
	}
	
	/**
	 * Synchronized method that represents Autos driving into the Parkhaus.
	 * While there are no more free parking spaces available the Auto trying to
	 * drive into the Parkhaus it calls {@link Auto#wait()} which will resume after
	 * receiving a notify after an Auto left. If there are still free spaces then
	 * {@link Parkhaus#freiePlaetze} is decremented by 1 and the name of the no
	 * parked Auto and the remaining number of free spaces are logged.
	 * 
	 * @param name						name of the Auto that is driving in
	 * @throws InterruptedException		If an Auto's {@link Auto#wait()} is interrupted
	 */
	public synchronized void einfahren(String name) {
		while(freiePlaetze == 0) {
			try {
				logger.info("Auto " + name + " wartet jetzt in Schlange");
				wait();
			} catch (InterruptedException e) {
				logger.error("Auto-wait() vor Parkhaus wurde unterbrochen");
			}
		}
		freiePlaetze--;
		logger.info("Auto "+ name + " eingefahren: " + freiePlaetze + " Plaetze verbleiben");						
	}
	
	/**
	 * Synchronized method that counts up {@link Parkhaus#freiePlaetze} by one, logs the 
	 * name of the Auto that is leaving and calls notify for a Auto waiting to drive in
	 * 
	 * @param name	name of the Auto that is leaving
	 */
	public synchronized void ausfahren(String name) {
		freiePlaetze++;
		logger.info("Auto "+ name +" ausgefahren: " + freiePlaetze + " Plaetze verbleiben");			
		notify();
	}		
}

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
		parkhaus.einfahren(myName);
		
		try {
			sleep((int)(Math.random() * 1000));
		} catch (InterruptedException e) {
			logger.error("Ausfahr-Sleep unterbrochen");
		}
		
		parkhaus.ausfahren(myName);
	}
}
