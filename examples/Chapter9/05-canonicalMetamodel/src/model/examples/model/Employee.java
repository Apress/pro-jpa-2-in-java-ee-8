package examples.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    private long salary;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @OneToOne
    private Address address;
    
    @OneToMany(mappedBy="employee")
    @MapKey(name="type")
    private Map<String,Phone> phones = new HashMap<String,Phone>();
   
    @ManyToOne
    private Department dept;
    
    @ManyToOne
    private Employee manager;
    
    @OneToMany(mappedBy="manager")
    private Collection<Employee> directs = new ArrayList<Employee>();
    
    @ManyToMany(mappedBy="employees")
    private Collection<Project> projects = new ArrayList<Project>();
	//private Project project;

	public Employee() {}

    public Employee(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int empNo) {
        this.id = empNo;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Map<String, Phone> getPhones() {
        return phones;
    }
    
    public void addPhone(String key, Phone phone) {
        if (!getPhones().containsKey(phone)) {
            getPhones().put(key, phone);
            if (phone.getEmployee() != null) {
                phone.getEmployee().getPhones().remove(phone);
            }
            phone.setEmployee(this);
        }
    }
    
    public Department getDept() {
        return dept;
    }
    
    public void setDept(Department dept) {
        if (this.dept != null) {
            this.dept.getEmployees().remove(this);
        }
        this.dept = dept;
        this.dept.getEmployees().add(this);
    }
    
    public Collection<Employee> getDirects() {
        return directs;
    }
    
    public void addDirect(Employee employee) {
        if (!getDirects().contains(employee)) {
            getDirects().add(employee);
            if (employee.getManager() != null) {
                employee.getManager().getDirects().remove(employee);
            }
            employee.setManager(this);
        }
    }
    
    public Employee getManager() {
        return manager;
    }
    
    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Collection<Project> getProjects() {
        return projects;
    }
    
    public void addProject(Project project) {
        if (!getProjects().contains(project)) {
            getProjects().add(project);
        }
        if (!project.getEmployees().contains(this)) {
            project.getEmployees().add(this);
        }
    }


//	public Project getProject() { return project; }
//	public void setProject(Project aProject) { project = aProject; }
    
    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address; 
    }
    
    public String toString() {
        return "Employee " + getId() + 
               ": name: " + getName() +
               ", salary: " + getSalary() +
               //", phones: " + getPhones() +
			   ", phones: " + getPhones() +
               ", managerNo: " + ((getManager() == null) ? null : getManager().getId()) +
               ", deptNo: " + ((getDept() == null) ? null : getDept().getId());
    }

}
