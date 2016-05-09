package uebung02;

import util.Logger;

public class ParkhausVerwalter {
	public static void main(String[] args) {
		Parkhaus p = new Parkhaus(5);
		
		Auto[] autos = new Auto[11];
		for(int i = 1; i < autos.length; i++) {
			Auto auto = new Auto(p, "" + i);
		}
	}
}

class Parkhaus {
	private int freiePlaetze;
	private Logger logger = Logger.getInstance();
	
	public Parkhaus(int spaces) {
		this.freiePlaetze = spaces;
	}
	
	public synchronized void einfahren(String name) {
		while(freiePlaetze == 0) {
			try {
				logger.info("car " + name + " now waiting in queue");
				wait();
			} catch (InterruptedException e) {}
		}
		freiePlaetze--;
		logger.info("Auto "+ name + " eingefahren: " + freiePlaetze + " Plaetze verbleiben");						
	}
	
	public synchronized void ausfahren(String name) {
		freiePlaetze++;
		logger.info("Auto "+ name +" ausgefahren: " + freiePlaetze + " Plaetze verbleiben");			
		notify();
	}		
}

class Auto extends Thread {
	private Parkhaus parkhaus;
	private String myName;
	
	public Auto(Parkhaus p, String name) {
		this.myName = name;
		this.parkhaus = p;
		this.start();
	}
	
	public void run() {
		// versuch einzufahren ansonsten warte auf notify
		//nach rnd zeit intervall versuche auszufahren ansonsten warte auf notify
		try {
			sleep((int)(Math.random() * 1000));
		} catch (InterruptedException e) {}
		Logger.getInstance().info("car " + myName + " arrived at carpark");
		parkhaus.einfahren(myName);
		try {
			sleep((int)(Math.random() * 1000));
		} catch (InterruptedException e) {}
		parkhaus.ausfahren(myName);
	}
}
