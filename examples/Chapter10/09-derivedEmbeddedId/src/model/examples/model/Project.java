package examples.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Project {

    @EmbeddedId 
    private ProjectId id;
    
    @MapsId("dept")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="DEPT_NUM", referencedColumnName="NUM"),
             @JoinColumn(name="DEPT_CTRY", referencedColumnName="CTRY")})
    private Department department;

    public ProjectId getId() {
        return id;
    }
    public void setId(ProjectId id) {
        this.id = id;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department dept) {
        this.department = dept;
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name="START_DATE")
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @Column(name="END_DATE")
    private Date endDate;

    public Project() {}
    public Project(Department dept) {
        this.department = dept;
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
        return "Project id: " + getId() + " dept: " + getDepartment();
    }
}
