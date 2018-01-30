package examples.entitybean;

import java.util.Collection;

import javax.ejb.EJBLocalObject;

public interface Project extends EJBLocalObject {
    public int getId();
    public void setId(int id);
    
    public String getName();
    public void setName(String name);
    
    public Collection getEmployees();
    public void setEmployees(Collection employees);
    
    public Employee getManager();
}

