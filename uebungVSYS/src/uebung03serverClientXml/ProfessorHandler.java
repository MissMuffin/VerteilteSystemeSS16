package uebung03serverClientXml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ProfessorHandler {

	private JAXBContext context;
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	
	public ProfessorHandler() {
		try {
			context = JAXBContext.newInstance(Professor.class); //TODO check mulitple classes new instance
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			unmarshaller = context.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void marshal(Professor professor, Paths path) {
		try {
			File xmlFile = new File(path.toString());
			marshaller.marshal(professor, xmlFile);
			marshaller.marshal(professor,  System.out);			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public Professor unmarshal(Paths path) {
		try {
			return (Professor)unmarshaller.unmarshal(new File(path.toString()));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
}
