package uebung04;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

/**
 * Handles marshalling and unmarshalling of Student object. Implements ValidationEventHandler
 * for onValidation events, such as validation errors.
 * @author Bianca Ploch
 */
public class StudentHandler implements ValidationEventHandler {

	private JAXBContext context;
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	private Logger logger = Logger.getInstance();
	private List<ValidationEvent> validationEvents = new ArrayList<ValidationEvent>();
	
	/**
	 * Constructor that initializes Marshaller and Unmarshaller objects. 
	 * Sets student.xsd schema to Unmarshaller for XML validation during unmarshalling.
	 */
	public StudentHandler() {
		try {
			context = JAXBContext.newInstance(Student.class);
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			unmarshaller = context.createUnmarshaller();
			
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File(Path.STUDENT_SCHEMA.toString()));
			unmarshaller.setSchema(schema);
			
			unmarshaller.setEventHandler(this);
		} catch (JAXBException e) {
			logger.error(Strings.JAXB_EXCEPTION);
			
			
		} catch (SAXException e) {
			logger.error(Strings.SAXB_EXCEPTION);
			e.printStackTrace();
		}
	}
	
	/**
	 * Marshals the student into a XML file at the given path
	 * @param student Student Object to be marshalled
	 * @param path Path to save the resulting XML file
	 */
	public void marshal(Student student, Path path) {
		try {
			
			File xmlFile = new File(path.toString());
			marshaller.marshal(student, xmlFile);			
		} catch (JAXBException e) {
			logger.error(Strings.MARSHALL_ERROR);
		}
	}
	
	/**
	 * Unmarshals the XML file at the given path
	 * @param path Path for the XML file to be unmarshalled
	 * @return The Student object from the XML file
	 * @throws JAXBException To be handled in class that calls {@link #unmarshal(Path)} for 
	 * communicating validation error to the client
	 */
	public Student unmarshal(Path path) throws JAXBException {
		Student s = (Student)unmarshaller.unmarshal(new File(path.toString()));
		
		if (validationEvents.size() > 0) {
			StringBuilder builder = new StringBuilder();
			for (ValidationEvent event : validationEvents) {
				builder.append(event.getMessage()).append("\n");
			}
			throw new ValidationException(builder.toString());
		}
		
		return s;
	}

	/* (non-Javadoc)
	 * @see javax.xml.bind.ValidationEventHandler#handleEvent(javax.xml.bind.ValidationEvent)
	 */
	@Override
	public boolean handleEvent(ValidationEvent event) {
		validationEvents.add(event);
		return true;
	}
}