package examples.model;

import javax.persistence.Embeddable;

@Embeddable
public class EmployeeName {
	
    private String firstName;
    private String lastName;

    public String toString() {
        return firstName + " " + lastName;
    }
}
