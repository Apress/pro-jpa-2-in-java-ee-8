package examples.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Phone {
    @Id
    private long id;
    private String number;
    private String type;
    @OneToOne Employee employee;
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String phoneNo) {
        this.number = phoneNo;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String phoneType) {
        this.type = phoneType;
    }
    
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String toString() {
        return "Phone id: " + getId() + 
               ", no: " + getNumber() +
               ", type: " + getType();
    }
}
