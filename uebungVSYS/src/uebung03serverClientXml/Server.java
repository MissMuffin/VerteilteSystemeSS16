package uebung03serverClientXml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Server {
		
	public static void main(String[] args) {
		
		HandlerStudent handlerStudent = new HandlerStudent();
		HandlerProfessor handlerProfessor = new HandlerProfessor();
		
		Student s = handlerStudent.unmarshal(Paths.XML_STUDENT);
		System.out.println(s.toString());
		
		//write to ser and read after
		s.write(Paths.SER_STUDENT);
		Student student = (Student) s.read(Paths.SER_STUDENT);
		System.out.println(student.toString());
		
		
		Professor p = handlerProfessor.unmarshal(Paths.XML_PROFESSOR);
		System.out.println(p.toString());		
	}

}
