package uebung03serverClientXml;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

public class HumanValidationEventHandler implements ValidationEventHandler {
	
	@Override
	public boolean handleEvent(ValidationEvent event) {
		System.out.println("\nInvalid input:");
        System.out.println("MESSAGE:  " + event.getMessage());
        return true;
	}
}
