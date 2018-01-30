package examples.model;

import java.text.DateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    private long salary;
    
    @Temporal(TemporalType.DATE)
    private java.util.Calendar dob;
    @Temporal(TemporalType.DATE)
    @Column(name="S_DATE")
    private java.util.Date startDate;

    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    
    public java.util.Calendar getDob() {
        return dob;
    }

    public void setDob(java.util.Calendar dob) {
        this.dob = dob;
    }

    public java.util.Date getStartDate() {
        return startDate;
    }

    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }

    public String toString() {
        return "Employee id: " + getId() + " name: " + getName() + 
        " salary: " + getSalary() + 
        " dob: " + DateFormat.getDateInstance().format(getDob().getTime()) + 
        " startDate: " + DateFormat.getDateInstance().format(getStartDate());
    }

}
