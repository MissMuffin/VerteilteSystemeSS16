
public class LoopTest extends Thread {
	private String myName;
	
	public LoopTest(String name) {
		myName = name;
	}
	
	public void run() {
		print(myName);
	}
	
	synchronized
	private static void print(String name) {
		//synched through use of static
		for (int i = 1; i <= 10; i++) {
			System.out.println(name + " (" + i + ")");
		}
		System.out.println("\n");
	}
	
	public static void main(String[] args) {
		LoopTest t1 = new LoopTest("Thread 1");
		LoopTest t2 = new LoopTest("Thread 2");
		LoopTest t3 = new LoopTest("Thread 3");
		t1.start();
		t2.start();
		t3.start();
	}
}
