package examples.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;


@Entity
public class Employee {

    @NotNull
    @Id
    private Integer id;
    
    @NotNull
    @Size(max=40)
    private String name;

    @Past
    @Temporal(TemporalType.DATE)
    @Column(name="S_DATE")
    private Date startDate;

    @Min(groups=Remove.class, value=0)
    @Max(groups=Remove.class, value=0)
    @Column(name="VAC_DAYS")
    private long vacationDays;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public long getVacationDays() {
        return vacationDays;
    }
    
    public void setVacationDays(long vacationDays) {
        this.vacationDays = vacationDays;
    }
    
    public String toString() {
        return "Employee id: " + getId() + " name: " + getName() + " vacation: " + getVacationDays();
    }
}
