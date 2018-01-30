package examples.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Transient;

@Entity
@EntityListeners({EmployeeDebugListener.class, NameValidator.class})
public class Employee implements NamedEntity {
    @Id private int id;
    private String name;
    @Transient private long syncTime;

    @PostPersist 
    @PostUpdate
    @PostLoad 
    private void resetSyncTime() {
        syncTime = System.currentTimeMillis(); 
        System.out.println("Employee.resetSyncTime called on employee id: " + getId());
    }

    public long getCachedAge() {
        return System.currentTimeMillis() - syncTime; 
    }

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

    public String toString() {
        return "Employee id: " + getId() + " name: " + getName();
    }
}
