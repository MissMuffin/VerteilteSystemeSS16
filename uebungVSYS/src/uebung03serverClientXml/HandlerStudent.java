package uebung03serverClientXml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class HandlerStudent {

	private JAXBContext context;
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	
	public HandlerStudent() {
		try {
			context = JAXBContext.newInstance(Student.class);
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			unmarshaller = context.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void marshal(Student student, String path) {
		try {
			File xmlFile = new File(path);
			marshaller.marshal(student, xmlFile);
			marshaller.marshal(student,  System.out);			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public Student unmarshal(String path) {
		try {
			return (Student)unmarshaller.unmarshal(new File(path));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
}
