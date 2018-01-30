package examples.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name="findSalaryForNameAndDepartment",
        query="SELECT e.salary " +
              "FROM Employee e " +
              "WHERE e.department.name = :deptName AND " +
              "      e.name = :empName")
@NamedQueries({
    @NamedQuery(name="Employee.findAll",
                query="SELECT e FROM Employee e"),
    @NamedQuery(name="Employee.findByPrimaryKey",
                query="SELECT e FROM Employee e WHERE e.id = :id"),
    @NamedQuery(name="Employee.findByName",
                query="SELECT e FROM Employee e WHERE e.name = :name")
})
public class Employee {
    @Id
    private int id;
    private String name;
    private long salary;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @ManyToOne
    private Employee manager;
    
    @OneToMany(mappedBy="manager")
    private Collection<Employee> directs;

    @ManyToOne
    private Department department;
    
    @ManyToMany 
    private Collection<Project> projects;

    public Employee() {
        projects = new ArrayList<Project>();
        directs = new ArrayList<Employee>();
    }

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
    
    public Collection<Employee> getDirects() {
        return directs;
    }
    
    public Employee getManager() {
        return manager;
    }

    public Collection<Project> getProjects() {
        return projects;
    }
    
    public String toString() {
        return "Employee " + getId() + 
               ": name: " + getName() +
               ", salary: " + getSalary() +
               ", dept: " + ((getDepartment() == null) ? null : getDepartment().getName());
    }
}
