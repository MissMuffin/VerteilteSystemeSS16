package uebung02;

/**
 * Manager class with main method, instantiates Parkhaus object with 5 parking spaces and
 * 11 Auto objects with names corresponding to their index
 * 
 * @author Bianca Ploch
 */
public class Main {
	/**
	 * Manager class with main method, instantiates Parkhaus object with 5 parking spaces and
	 * 11 Auto objects with names corresponding to their index
	 * @param args arguments for main()
	 */
	public static void main(String[] args) {
		Parkhaus p = new Parkhaus(3);
		
		Auto[] autos = new Auto[11];
		for(int i = 1; i < autos.length; i++) {
			new Auto(p, "" + i);
		}
	}
}