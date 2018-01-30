package examples.model;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
@EntityListeners(EmployeeAudit.class)
public abstract class CompanyEmployee extends Employee {
    private int vacation;

    public int getVacation() {
        return vacation;
    }

    public void setVacation(int vacation) {
        this.vacation = vacation;
    }
    
    @PrePersist
    @PreUpdate
    public void verifyVacation() {
        System.out.println("CompanyEmployee.verifyVacation called on employee: " + getId());
        //...
    }
}
