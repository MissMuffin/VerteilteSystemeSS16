package uebung03serverClientXml;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

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
			
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File(Paths.PROFESSOR_SCHEMA.toString()));
			unmarshaller.setSchema(schema);
			
			unmarshaller.setEventHandler(new HumanValidationEventHandler());
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (SAXException e) {
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
