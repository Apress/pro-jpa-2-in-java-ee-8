package examples.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmployeeName {
    @Column(name="F_NAME")
    private String first_Name;
    @Column(name="L_NAME")
    private String last_Name;
    
    public String getFirst_Name() {
        return first_Name;
    }
    public void setFirst_Name(String firstName) {
        first_Name = firstName;
    }
    public String getLast_Name() {
        return last_Name;
    }
    public void setLast_Name(String lastName) {
        last_Name = lastName;
    }
}
