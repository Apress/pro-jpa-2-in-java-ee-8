package examples.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity 
public class Employee {
    @Id private int id;
    @Version private int version;
    private String name;
    private long salary;
    private int vacationDays;

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
    
    public long getSalary() {
        return salary;
    }
    
    public void setSalary(long salary) {
        this.salary = salary;
    }
    
    public int getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(int vacation) {
        this.vacationDays = vacation;
    }

    public String toString() {
        return "Employee id: " + getId() + " name: " + getName() +
            " vacation: " + getVacationDays();
    }
}
