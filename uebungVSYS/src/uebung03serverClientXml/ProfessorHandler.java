package uebung03serverClientXml;

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

import util.Logger;

public class ProfessorHandler implements ValidationEventHandler {

	private JAXBContext context;
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	private Logger logger = Logger.getInstance();
	private List<ValidationEvent> validationEvents = new ArrayList<ValidationEvent>();
	
	public ProfessorHandler() {
		try {
			context = JAXBContext.newInstance(Professor.class);
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			unmarshaller = context.createUnmarshaller();
			
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File(Paths.PROFESSOR_SCHEMA.toString()));
			unmarshaller.setSchema(schema);
			
			unmarshaller.setEventHandler(this);
			
		} catch (JAXBException e) {
			logger.error(Strings.JAXB_EXCEPTION);
			
		} catch (SAXException e) {
			logger.error(Strings.SAXB_EXCEPTION);
		}
	}
	
	public void marshal(Professor professor, Paths path) {
		try {
			File xmlFile = new File(path.toString());
			marshaller.marshal(professor, xmlFile);
		} catch (JAXBException e) {
			logger.error(Strings.MARSHALL_ERROR);
		}
	}
	
	public Professor unmarshal(Paths path) throws JAXBException {
		Professor p = (Professor)unmarshaller.unmarshal(new File(path.toString()));
		
		if (validationEvents.size() > 0) {
			StringBuilder builder = new StringBuilder();
			for (ValidationEvent event : validationEvents) {
				builder.append(event.getMessage()).append("\n");
			}
			throw new ValidationException(builder.toString());
		}
		
		return p;
	}

	@Override
	public boolean handleEvent(ValidationEvent event) {
		validationEvents.add(event);
		return true;
	}
}
