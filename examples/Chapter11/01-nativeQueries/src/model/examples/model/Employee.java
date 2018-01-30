package examples.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="EMP")
@NamedNativeQuery(
    name="orgStructureReportingTo",
//  Oracle-specific SQL string
//    query="SELECT emp_id, name, salary, manager_id, dept_id, address_id " +
//          "FROM emp " +
//          "START WITH manager_id = ? " +
//          "CONNECT BY PRIOR emp_id = manager_id",
    query = "SELECT emp1.emp_id, emp1.name, emp1.salary, emp1.manager_id, " +
            "emp1.dept_id, emp1.address_id " +
            "FROM EMP emp1, EMP emp2 " +
            "WHERE ((emp2.EMP_ID = ?) AND (emp2.EMP_ID = emp1.MANAGER_ID))",
    resultClass=Employee.class
)
public class Employee {
    @Id
    @Column(name="EMP_ID")
    private int id;
    private String name;
    private long salary;
    
    @OneToOne
    private Address address;
    
    @ManyToOne
    @JoinColumn(name="DEPT_ID")
    private Department department;
    
    @ManyToOne
    @JoinColumn(name="MANAGER_ID")
    private Employee manager;
    
    @OneToMany(mappedBy="manager")
    private Collection<Employee> directs = new ArrayList<Employee>();
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

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }
    
    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address; 
    }
    
    public Department getDepartment() {
        return department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
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
    
    public String toString() {
        return "Employee id: " + getId() + " name: " + getName() + 
               " with MgrId: " + (getManager() == null ? null : getManager().getId());
    }
}
