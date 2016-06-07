package uebung03serverClientXml;

import java.time.LocalDate;

public class Test {

public static void main(String[] args) {
		
		StudentHandler handlerStudent = new StudentHandler();
		ProfessorHandler handlerProfessor = new ProfessorHandler();
		
//		Student s = handlerStudent.unmarshal(Paths.BAD_STUDENT_XML);
		
//		Student s = new Student();
//		s.setName("Tom-bla");
//		s.setSurname("Doe");
//		
//		Address a1 = new Address();
//		a1.setStreet("d");
//		a1.setHouseNumber(111);
//		a1.setPostCode(1234);
//		a1.setCity("Berlin");
//		s.setAddress(a1);
//		
//		s.setSemester(1);
//		s.setStudentNumber(5678);
//		s.setProgram("Cool Arts");
//		
//		handlerStudent.marshal(s, Paths.STUDENT_XML_SERVER);
//		
//		
		Professor p = new Professor();
		p.setName("John");
		p.setSurname("Doe");
		
		Address a2 = new Address();
		a2.setStreet("Street");
		a2.setHouseNumber(111);
		a2.setPostCode(1234);
		a2.setCity("Berlin");
		p.setAddress(a2);
		
		p.setFaculty("Cool Faculty");
		p.setBirthdate(LocalDate.now());
		p.setPersonnelNumber(112233);
		
		handlerProfessor.marshal(p, Paths.PROFESSOR_XML_SERVER);
//		
//			
//		Student st = handlerStudent.unmarshal(Paths.STUDENT_XML_SERVER);
//		System.out.println(st.toString());
//		
//		//write to disk and read
//		st.write(Paths.STUDENT_SER_SERVER);
//		Student student = (Student) st.read(Paths.STUDENT_SER_SERVER);
//		System.out.println(student.toString());
//		
//		
//		Professor pr = handlerProfessor.unmarshal(Paths.PROFESSOR_XML_CLIENT);
//		System.out.println(pr.toString());		

	}
	
}
