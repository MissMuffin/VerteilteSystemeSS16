
public class KontoVerwUnsynch {
	public static void main(String[] s) {
		KontoUnsynch konto = new KontoUnsynch();
		EinzahlerUnsynch eu = new EinzahlerUnsynch(konto);
		AbholderUnsynch au = new AbholderUnsynch(konto);
		eu.start();
		au.start();
	}
}

/*
 * Konto hat 3 attribute:
 * stand: wird bei jeder Einzahlung erhoht und bei jeder Auzahlung verringert
 * einz: der letzte eingezahlte Betrag
 * ausz: der letzte ausgezahlte Betrag
 * im Beispiel ist Hohe von ausz = einz
 */
class KontoUnsynch {
	private int einz, ausz, stand;
	
	public int auszahlung() {
		ausz = einz;
		stand -= ausz;
		return ausz;
	}
	
	public void einzahlung(int wert) {
		einz = wert;
		stand += wert;
	}
	
	public int stand() {
		return stand;
	}
}

class EinzahlerUnsynch extends Thread {
	private KontoUnsynch konto;
	
	public EinzahlerUnsynch(KontoUnsynch k) {
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

class AbholderUnsynch extends Thread {
	private KontoUnsynch konto;
	
	public AbholderUnsynch(KontoUnsynch k) {
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
