package examples.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class EmployeeHistory {
    @Id
    @OneToOne
    @JoinColumn(name = "EMP_ID")
    private Employee employee;

    // ...

    public EmployeeHistory() {}
    public EmployeeHistory(Employee emp) {
        employee = emp;
    }
    
    public Employee getEmployee() {
        return employee;
    }

    public String toString() {
        return "EmployeeHistory(" + getEmployee() + ")";
    }

}
