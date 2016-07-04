package uebung04;

import java.io.File;
import java.nio.file.Paths;

/**
 * Contains paths for files used in this package.
 * All paths will point to directory 'files' and work on both Linux and Windows systems.
 * (Not tested on MacOS)
 * @author Bianca Ploch
 */
public class Path {	
	
	public final static Path STUDENT_XML_CLIENT = new Path("client_student.xml");
	public final static Path PROFESSOR_XML_CLIENT = new Path("client_professor.xml");
	
	public final static Path STUDENT_XML_SERVER= new Path("server_student.xml");
	public final static Path PROFESSOR_XML_SERVER= new Path("server_professor.xml");
	
	public static final Path STUDENT_SER_SERVER = new Path("server_student.ser");
	public static final Path PROFESSOR_SER_SERVER = new Path("server_professor.ser");
	
	public static final Path STUDENT_SCHEMA = new Path("student.xsd");
	public static final Path PROFESSOR_SCHEMA = new Path("professor.xsd");
	
	
	public static final Path BAD_STUDENT_XML = new Path("BAD_client_student.xml");
	
	
	private String path;
	
	/**
	 * Constructor 
	 * @param path The path for the file
	 */
	public Path(String path) {
		this.path = path;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "files" + File.separator + path;
	}
}
