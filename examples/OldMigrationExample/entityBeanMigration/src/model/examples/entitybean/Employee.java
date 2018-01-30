package examples.entitybean;

import java.util.Collection;

import javax.ejb.EJBLocalObject;

public interface Employee extends EJBLocalObject {
    public int getId();
    public void setId(int id);

    public String getname();
    public void setname(String name);

    public long getSalary();
    public void setSalary(long salary);

    public Department getDepartment();
    public void setDepartment(Department dept);

    public Collection getDirects();
    public void setDirects(Collection directs);
    
    public Employee getManager();
    public void setManager(Employee emp);
}

