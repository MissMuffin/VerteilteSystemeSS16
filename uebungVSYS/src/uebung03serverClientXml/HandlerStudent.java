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

public class HandlerStudent {

	private JAXBContext context;
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	
	public HandlerStudent() {
		try {
			context = JAXBContext.newInstance(Student.class); //TODO check mulitple classes new instance
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			unmarshaller = context.createUnmarshaller();
			
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File(Paths.XML_STUDENT_SERVER)); 
			marshaller.setSchema(schema);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
	
	public void marshal(Student student, String path) {
		try {
			
			File xmlFile = new File(path);
			marshaller.marshal(student, xmlFile);			
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