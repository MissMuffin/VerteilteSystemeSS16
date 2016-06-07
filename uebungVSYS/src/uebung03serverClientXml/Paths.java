package uebung03serverClientXml;

public class Paths {
		
	public final static Paths PACKAGE = new Paths("C:\\Users\\Bianca\\IMI-B\\VerteilteSysteme\\"
			+ "uebungVSYS\\src\\uebung03serverClientXml\\");
	
	public final static Paths STUDENT_XML_CLIENT = new Paths(PACKAGE + "client_student.xml");
	public final static Paths PROFESSOR_XML_CLIENT = new Paths(PACKAGE + "client_professor.xml");
	
	public final static Paths STUDENT_XML_SERVER= new Paths(PACKAGE + "server_student.xml");
	public final static Paths PROFESSOR_XML_SERVER= new Paths(PACKAGE + "server_professor.xml");
	
	public static final Paths STUDENT_SER_SERVER = new Paths(PACKAGE + "server_student.ser");
	public static final Paths PROFESSOR_SER_SERVER = new Paths(PACKAGE + "server_professor.ser");
	
//	SCHEMAS
	public static final Paths STUDENT_SCHEMA = new Paths(PACKAGE + "student.xsd");
	public static final Paths PROFESSOR_SCHEMA = new Paths(PACKAGE + "professor.xsd");
	
	
	private String path;
	
	public Paths(String path) {
		this.path = path;
	}
	
	@Override
	public String toString() {
		return path;
	}
}
