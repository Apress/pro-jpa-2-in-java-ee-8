package examples.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EMP_PROJECT")
@IdClass(ProjectAssignmentId.class)
public class ProjectAssignment {
    @Id
    @ManyToOne
    @JoinColumn(name="EMP_ID")
    private Employee employee;

    @Id
    @ManyToOne
    @JoinColumn(name="PROJECT_ID")
    private Project project;

    @Temporal(TemporalType.DATE)
    @Column(name="START_DATE", updatable=false)
    private Date startDate;

    public ProjectAssignment() {}
    public ProjectAssignment(Employee emp, Project proj) {
        this.employee = emp;
        this.project = proj;
        this.startDate = new Date();
    }
    
    public Employee getEmployee() {
        return employee;
    }

    public Project getProject() {
        return project;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String toString() {
        return "Employee " + employee.getId() + " assigned to Project " + project.getId() + " on: " + startDate;
    }
}

