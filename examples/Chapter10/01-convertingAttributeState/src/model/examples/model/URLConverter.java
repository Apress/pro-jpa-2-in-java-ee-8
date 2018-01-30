package examples.model;

import java.net.URL;
import java.net.MalformedURLException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class URLConverter implements AttributeConverter<URL, String> {
	
    public String convertToDatabaseColumn(URL attrib) {
        return attrib.toString();
    }

    public URL convertToEntityAttribute(String dbData) {
    	try { 
    	    return new URL(dbData); 
    	} catch (MalformedURLException ex) {
    	    throw new IllegalArgumentException("DB data: " + dbData + " is not a legal URL");
    	}
    }
}
