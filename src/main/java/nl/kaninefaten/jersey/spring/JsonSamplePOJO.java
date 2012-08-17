package nl.kaninefaten.jersey.spring;

import java.io.Serializable;

@SuppressWarnings("serial")
public class JsonSamplePOJO implements Serializable{

	private String aString = null;
	
	public JsonSamplePOJO(String string) {
		aString = string;
	}

	public String getAString() {
		return aString;
	}

	public void setAString(String eenString) {
		this.aString = eenString;
	}
	
	
}
