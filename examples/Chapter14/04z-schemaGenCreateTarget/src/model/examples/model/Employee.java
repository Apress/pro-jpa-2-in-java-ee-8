package examples.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
    @Id
    @Column(columnDefinition="VARCHAR(40)") 
    private String name;
    @Column(name="START_DATE", columnDefinition="DATE DEFAULT CURRENT_DATE") 
    private java.sql.Date startDate;
    @ManyToOne 
    @JoinColumn(name="MGR", columnDefinition="VARCHAR(40)")
    private Employee manager;


    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}
