package examples.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="FT_EMP")
@DiscriminatorValue("2")
public class FullTimeEmployee extends CompanyEmployee {
    private long salary;
    private long pension;
    
    public long getPension() {
        return pension;
    }
    
    public void setPension(long pension) {
        this.pension = pension;
    }
    
    public long getSalary() {
        return salary;
    }
    
    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String toString() {
        return "FullTimeEmployee id: " + getId() + " name: " + getName();
    }
}
