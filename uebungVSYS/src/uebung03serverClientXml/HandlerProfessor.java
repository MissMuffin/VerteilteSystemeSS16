package uebung03serverClientXml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class HandlerProfessor{

	private JAXBContext context;
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	
	public HandlerProfessor() {
		try {
			context = JAXBContext.newInstance(Professor.class);
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			unmarshaller = context.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void marshal(Professor professor, String path) {
		try {
			File xmlFile = new File(path);
			marshaller.marshal(professor, xmlFile);
			marshaller.marshal(professor,  System.out);			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public Professor unmarshal(String path) {
		try {
			return (Professor)unmarshaller.unmarshal(new File(path));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
}
