
public class ThreadDemo01 extends Thread {
	char c;
	
	public static void main(String[] args) {
		ThreadDemo01 t1 = new ThreadDemo01('x');
		ThreadDemo01 t2 = new ThreadDemo01('u');
		t1.start();
		t2.start();
	}

	public ThreadDemo01(char c) {
		this.c = c;
	}
	
	public void run() {
			Ausgabe.writeInLine(c);
	}
}
	
class Ausgabe {
	synchronized
	static void writeInLine(char c) {
		for (int i = 0; i < 10; i++) {
			for (int t = 0; t < 10; t++) {
				System.out.print(c + " ");
				int n = 0;
				double w;
				while(n < 1000) w = Math.sqrt(n++);
			}
		System.out.println();
		}
	}
}

