package examples.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmployeeName {
    private String firstName;
    private String lastName;

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public boolean equals(Object o) {
    	// Assume non-null fields
        if (!(o instanceof EmployeeName)) 
            return false;
        EmployeeName empName = (EmployeeName) o; 
        return firstName.equals(empName.getFirstName()) &&
               lastName.equals(empName.getLastName());
    }
    
    public int hashCode() { 
        return firstName.hashCode() + lastName.hashCode(); 
    }
    	    
    public String toString() {
        return firstName + " " + lastName;
    }
}