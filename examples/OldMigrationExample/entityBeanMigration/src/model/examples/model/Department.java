package examples.model;


import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DEPT")
@NamedQueries({
    @NamedQuery(name = "Department.managerForDept", 
                query = "SELECT OBJECT(e) " + 
                        "FROM Employee e " + 
                        "WHERE e.department.id = ?1 AND e.manager.department.id <> ?1"),
    @NamedQuery(name="Department.findAll",
                query="SELECT d FROM Department d"),
    @NamedQuery(name="Department.empsWithNoDepartment",
                query="SELECT e FROM Employee e WHERE e.department IS NULL"),
    @NamedQuery(name="Department.findByName",
                query="SELECT d FROM Department d WHERE d.name = ?1")
})
public class Department {
    @Id
    private int id;
    private String name;
    @OneToMany(mappedBy = "department")
    private Collection<Employee> employees = new ArrayList<Employee>();

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

    public Collection<Employee> getEmployees() {
        return employees;
    }
    
    public void addEmployee(Employee emp) {
        getEmployees().add(emp);
        emp.setDepartment(this);
    }

    public Employee getManager() {
        EntityManager em = ServiceLocator.getInstance().getEntityManager("EmployeeService");
        try {
            return (Employee) em.createNamedQuery("Department.managerForDept")
                                .setParameter(1, getId())
                                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public String toString() {
        return "Department id: " + getId() + " name: " + getName() + " manager: " + getManager();
    }
}
