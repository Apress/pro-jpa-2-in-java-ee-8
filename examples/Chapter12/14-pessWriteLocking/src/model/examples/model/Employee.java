package examples.model;

import java.text.DecimalFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity 
public class Employee {
    @Id private int id;
    @Version private int version;
    private String name;
    private EmployeeStatus status;
    private long salary;
    private double vacationDays;

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }
    
    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public EmployeeStatus getStatus() {
    	return this.status; 
    }
    
    public void setStatus(EmployeeStatus status) {
    	this.status = status; 
    }

    public long getSalary() {
        return salary;
    }
    
    public void setSalary(long salary) {
        this.salary = salary;
    }
    
    public double getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(double vacation) {
        this.vacationDays = vacation;
    }

    public String toString() {
    	DecimalFormat df = new DecimalFormat("00.00");
        return "Employee id: " + getId() + " name: " + getName() + " status: " + getStatus() + " vacation: " + df.format(getVacationDays());
    }
}
