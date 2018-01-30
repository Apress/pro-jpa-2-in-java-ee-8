package examples.model;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class EmployeeDebugListener {
    @PrePersist
    public void prePersist(Employee emp) {
    	Object id = null;
        if(null != emp) {
        	id = emp.getId();
        }
        System.out.println("EmployeeDebugListener.Persist called on employee id: " + id);
    }

    @PreUpdate
    public void preUpdate(Employee emp) {
    	Object id = null;
        if(null != emp) {
        	id = emp.getId();
        }    
        System.out.println("EmployeeDebugListener.Update called on employee id: " + id);
    }

    @PreRemove
    public void preRemove(Employee emp) {
    	Object id = null;
        if(null != emp) {
        	id = emp.getId();
        }
        System.out.println("EmployeeDebugListener.Remove called on employee id: " + id);
    }

    @PostLoad
    public void postLoad(Employee emp) {
    	Object id = null;
        if(null != emp) {
        	id = emp.getId();
        }
        System.out.println("EmployeeDebugListener.Load called on employee id: " + id);        
    }
}

