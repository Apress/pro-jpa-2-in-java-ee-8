package examples.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UpperCaseConverter implements AttributeConverter<String, String> {
	
    public String convertToDatabaseColumn(String attrib) {
        return attrib.toUpperCase();
    }

    public String convertToEntityAttribute(String dbData) {
        String lower = dbData.substring(1).toLowerCase();
        return dbData.substring(0,1).concat(lower);
    }
}
