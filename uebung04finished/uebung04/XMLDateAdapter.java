package uebung04;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class XMLDateAdapter extends XmlAdapter<String, LocalDate>{

	@Override
	public String marshal(LocalDate date) throws Exception {
		return date.toString();
	}

	@Override
	public LocalDate unmarshal(String date) throws Exception {
		return LocalDate.parse(date);
	}

}
