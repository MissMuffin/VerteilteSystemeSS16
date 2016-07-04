package uebung04;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * XML adapter to enable use of LocalDate with JAXB marshalling and unmarshalling.
 * Needs to be set whenever LocalDate is used, see {@link Professor#getBirthdate()}.
 * @author Bianca
 */
public class XMLDateAdapter extends XmlAdapter<String, LocalDate>{

	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
	 */
	@Override
	public String marshal(LocalDate date) throws Exception {
		return date.toString();
	}

	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
	 */
	@Override
	public LocalDate unmarshal(String date) throws Exception {
		return LocalDate.parse(date);
	}

}
