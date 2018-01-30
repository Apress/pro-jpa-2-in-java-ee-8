package examples.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ExcludeSuperclassListeners;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="CONTRACT_EMP")
@DiscriminatorValue("1")
@ExcludeSuperclassListeners
@EntityListeners(LongNameValidator.class)
public class ContractEmployee extends Employee {
    @Column(name="D_RATE")
    private int dailyRate;
    private int term;
    
    @PrePersist
    public void verifyTerm() { 
        System.out.println("ContractEmployee.verifyTerm called on employee: " + getId());
        //...
    }

    public int getDailyRate() {
        return dailyRate;
    }
    
    public void setDailyRate(int dailyRate) {
        this.dailyRate = dailyRate;
    }
    
    public int getTerm() {
        return term;
    }
    
    public void setTerm(int term) {
        this.term = term;
    }

    public String toString() {
        return "ContractEmployee id: " + getId() + " name: " + getName();
    }
}
