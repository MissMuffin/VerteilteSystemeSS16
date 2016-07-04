package uebung04;

import java.io.File;
import java.nio.file.Paths;

public class Path {
//		
//	public final static Path PACKAGE = new Path(
//			Paths.get("").toAbsolutePath().normalize().toString() 
//			+ "\\src\\uebung03serverClientXml\\");

//	public final static Path PACKAGE = new Path(
//			Paths.get("").toAbsolutePath().normalize().toString());

//	public final static Path PACKAGE = new Path(
//			getClass().getProtectionDomain().getCodeSource().getLocation().getPath().toString());
	
	
	public final static Path STUDENT_XML_CLIENT = new Path("client_student.xml");
	public final static Path PROFESSOR_XML_CLIENT = new Path("client_professor.xml");
	
	public final static Path STUDENT_XML_SERVER= new Path("server_student.xml");
	public final static Path PROFESSOR_XML_SERVER= new Path("server_professor.xml");
	
	public static final Path STUDENT_SER_SERVER = new Path("server_student.ser");
	public static final Path PROFESSOR_SER_SERVER = new Path("server_professor.ser");
	
	/**
	 * Schema paths
	 */
	public static final Path STUDENT_SCHEMA = new Path("student.xsd");
	public static final Path PROFESSOR_SCHEMA = new Path("professor.xsd");
	
	
	public static final Path BAD_STUDENT_XML = new Path("BAD_client_student.xml");
	
	
	private String path;
	
	public Path(String path) {
		this.path = path;
	}
	
//	public String getPath() {
//		return Paths.get(PACKAGE.toString()) + File.separator + path;
//	}
	
	@Override
	public String toString() {
		return "files" + File.separator + path;
	}
}
