package examples.model;

import javax.persistence.PrePersist;

public class LongNameValidator {
    @PrePersist public void validateLongName(NamedEntity obj) {
        System.out.println("LongNameValidator.validate called on employee: " + obj.getName());
        //...
    }
}
