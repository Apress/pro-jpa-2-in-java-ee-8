package examples.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name="FT_EMP")
public class FullTimeEmployee extends CompanyEmployee {
    private long salary;
    @Column(name="PENSION")
    private long pensionContribution;
    
    public long getPensionContribution() {
        return pensionContribution;
    }
    
    public void setPensionContribution(long pension) {
        this.pensionContribution = pension;
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
