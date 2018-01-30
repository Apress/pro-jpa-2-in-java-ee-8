package examples.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity 
@IdClass(ProjectId.class)
public class Project {
    
    @Id 
    private String name;

    @Id
    @ManyToOne 
    @JoinColumns({
       @JoinColumn(name="DEPT_NUM", referencedColumnName="NUM"),
       @JoinColumn(name="DEPT_CTRY", referencedColumnName="COUNTRY")
    })    
    private Department dept;
    
    public Department getDept() {
        return dept;
    }

    public ProjectId buildPK() {
        return new ProjectId(
            new DeptId(getDept().getNumber(), 
                       getDept().getCountry()),
            this.getName());
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name="START_DATE")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name="END_DATE")
    private Date endDate;

    public Project() {}
    public Project(Department dept, String name) {
        this.dept = dept;
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
        
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public String toString() {
        return "Project name: " + getName() + " dept: " + getDept();
    }
}
