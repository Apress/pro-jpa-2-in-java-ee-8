package examples.model;

import java.text.DateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    private long salary;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @ManyToOne
    private Department department;

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public long getSalary() {
        return salary;
    }

    public Date getStartDate() {
        return startDate;
    }
    
    public Department getDepartment() {
        return department;
    }
    
    public String toString() {
        return "Employee " + getId() + 
               ": name: " + getName() +
               ", salary: " + getSalary() +
               ", startDate: " + DateFormat.getDateInstance().format(getStartDate()) +
               ", dept: " + ((getDepartment() == null) ? null : 
                   getDepartment().getId() + ":" + getDepartment().getName());
    }
}
