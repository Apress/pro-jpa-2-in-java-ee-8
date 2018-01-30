package examples.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Null;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;


@Entity
public class Employee {

    @Id
    @NotNull
    private Integer id;

    @NotNull(message="Employee name must be specified")
    @Size(max=40)
    private String name;

    @NotNull(groups=FullTime.class)
    @Null(groups=PartTime.class)
    private Long salary;

    @NotNull(groups=PartTime.class)
    @Null(groups=FullTime.class)
    private Double hourlyWage;
 
    @Past
    @Temporal(TemporalType.DATE)
    @Column(name="S_DATE")
    private Date startDate;

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Long getSalary() {
        return salary;
    }
    
    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Double getHourlyWage() {
        return hourlyWage;
    }
    
    public void setHourlyWage(Double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String toString() {
        return "Employee id: " + getId() + " name: " + getName() + ", salary: " + getSalary() + ", hourlyWage: " + getHourlyWage(); 
    }
}
