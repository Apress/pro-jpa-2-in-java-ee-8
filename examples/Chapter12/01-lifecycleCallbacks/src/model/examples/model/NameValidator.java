package examples.model;

import javax.persistence.PrePersist;

public class NameValidator {
    static final int MAX_NAME_LEN = 40;

    @PrePersist 
    public void validate(NamedEntity obj) {
        System.out.println("NameValidator.validate called on employee name: " + obj.getName());
        if (obj.getName().length() > MAX_NAME_LEN)
            throw new ValidationException("Identifier out of range");
    }
}

