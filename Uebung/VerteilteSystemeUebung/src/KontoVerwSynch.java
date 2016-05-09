
public class KontoVerwSynch {
	public static void main(String[] s) {
		Konto konto = new Konto();
		Einzahler eu = new Einzahler(konto);
		Abholder au = new Abholder(konto);
		eu.start();
		au.start();
	}
}

/* Die Anzeige kann Sie in die Irre führen, aber das
Programm arbeitet korrekt und konsistent! (Grund: Es
kann je nach Wert bei sleep vorkommen, dass der
Stand, den der Einzahler abholt, schon vom Abholer
wieder auf 0 gesetzt wurde)
Verändern Sie die Werte für sleep beim Einzahler und
Abholer (z.B. auf 3000ms) */

/*
 * Konto hat 3 attribute:
 * stand: wird bei jeder Einzahlung erhoht und bei jeder Auzahlung verringert
 * einz: der letzte eingezahlte Betrag
 * ausz: der letzte ausgezahlte Betrag
 * im Beispiel ist Hohe von ausz = einz
 */
class Konto {
	private int einz, ausz, stand;
	private boolean zugriff = false;
	
	public synchronized int auszahlung() {
		while (!zugriff) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		ausz = einz;
		stand -= ausz;
		zugriff = false;
		notifyAll();
		return ausz;
	}
	
	public synchronized void einzahlung(int wert) {
		while (zugriff) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		einz = wert;
		stand += wert;
		zugriff = true;
		notifyAll();
	}
	
	public int stand() {
		return stand;
	}
}

class Einzahler extends Thread {
	private Konto konto;
	
	public Einzahler(Konto k) {
		this.konto = k;
	}
	
	public void run() {
		int betrag; 
		for (int i = 0; i < 10; i++) {
			betrag = (int)(Math.random() * 1000);
			konto.einzahlung(betrag);
			System.out.println("Einzahler " + betrag + " Stand: " + konto.stand());
			try {
				sleep((int)(Math.random() * 1000));
			} catch (InterruptedException e) {}
		}
	}
}

class Abholder extends Thread {
	private Konto konto;
	
	public Abholder(Konto k) {
		this.konto = k;
	}
	
	public void run() {
		int wert = 0;
		for (int i = 0; i < 10; i++) {
			wert = konto.auszahlung();
			System.out.println("Abholer " + wert + " Stand: " + konto.stand());
			try {
				sleep((int)(Math.random() * 1000));
			} catch (InterruptedException e) {}
		}
	}
}
