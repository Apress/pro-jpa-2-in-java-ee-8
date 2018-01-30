package examples.model;

import javax.persistence.PrePersist;

public class NameValidator {
    @PrePersist 
    public void validate(NamedEntity obj) {
        System.out.println("NameValidator.validate called on employee: " + obj.getName());
        //...
    }
}

