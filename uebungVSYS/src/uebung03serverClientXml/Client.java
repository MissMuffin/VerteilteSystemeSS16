package uebung03serverClientXml;

import java.time.LocalDate;

public class Client {
		
	public static void main(String[] args) {
		
		HandlerStudent handerStudent = new HandlerStudent();
		HandlerProfessor handlerProfessor = new HandlerProfessor();
		
		Student s = new Student();
		s.setName("Tom");
		s.setSurname("Doe");
		
		Address a1 = new Address();
		a1.setStreet("Cool Street");
		a1.setHouseNumber(111);
		a1.setPostCode(1234);
		a1.setCity("Berlin");
		s.setAddress(a1);
		
		s.setSemester(1);
		s.setStudentNumber(5678);
		s.setProgram("Cool Arts");
		
		handerStudent.marshal(s, Paths.XML_STUDENT);
		
		
		Professor p = new Professor();
		p.setName("John");
		p.setSurname("Doe");
		
		Address a2 = new Address();
		a2.setStreet("Cool Street");
		a2.setHouseNumber(111);
		a2.setPostCode(1234);
		a2.setCity("Berlin");
		p.setAddress(a2);
		
		p.setFaculty("Cool Faculty");
		p.setBirthdate(LocalDate.now());
		p.setPersonnelNumber(112233);
		
		handlerProfessor.marshal(p, Paths.XML_PROFESSOR);

	}

}
