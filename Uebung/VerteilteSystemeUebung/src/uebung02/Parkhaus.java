package uebung02;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import util.Logger;

/**
 * Class that represents Parkhaus, has synchronised methods for
 * letting a car drive into the Parkhaus and letting it leave
 * 
 * @author Bianca Ploch
 */
class Parkhaus {
	private int freiePlaetze;
	private Logger logger = Logger.getInstance();
	private Queue<Auto> queue = new ConcurrentLinkedQueue<>();
	
	/**
	 * Parkhaus constructor
	 * @param spaces	The amount of parking spaces the Parkhaus should have
	 */
	public Parkhaus(int spaces) {
		this.freiePlaetze = spaces;
	}
	
	/**
	 * Synchronized method that represents Autos driving into the Parkhaus.
	 * If no more free parking spaces are left, the Auto logs its name and gets added
	 * to the waiting queue and {@link Auto#wait()} is called on it.
	 * When receiving a notifyAll() wake up it is checked if the Auto is the first
	 * element in the queue and if there are free parking spaces. Is that not the case
	 * wait() is called again on the Auto.
	 * If both conditions  are true the Auto is removed from the queue, then
	 * {@link Parkhaus#freiePlaetze} is decremented by 1 and the name of the parked 
	 * Auto and the remaining number of free spaces are logged. 
	 * 
	 * @param 	auto						The Auto that is driving in
	 */
	public synchronized void einfahren(Auto auto) {
		if (freiePlaetze == 0) {
			try {				
				logger.info("Auto " + auto.getMyName() + " wartet jetzt in Schlange");
				queue.add(auto);
				
				do { 
					wait();
				} while (freiePlaetze == 0 || !queue.element().equals(auto));
				queue.poll();
				
			} catch (InterruptedException e) {
				logger.error("Auto-wait() vor Parkhaus wurde unterbrochen");
			}
		}
		freiePlaetze--;
		logger.info("Auto "+ auto.getMyName() + " eingefahren: " + freiePlaetze + " Plaetze verbleiben");						
	}
	
	/**
	 * Synchronized method that counts up {@link Parkhaus#freiePlaetze} by one, logs the 
	 * name of the Auto that is leaving and calls notifyAll() to notify the next Auto to enter the Parkhaus
	 * 
	 * @param auto	The Auto that is leaving
	 */
	public synchronized void ausfahren(Auto auto) {
		freiePlaetze++;
		logger.info("Auto "+ auto.getMyName() +" ausgefahren: " + freiePlaetze + " Plaetze verbleiben");			
		notifyAll();
	}		
}