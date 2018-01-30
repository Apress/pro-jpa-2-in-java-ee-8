package examples.entitybean;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;

public abstract class EmployeeBean implements EntityBean {
    public abstract int getId();
    public abstract void setId(int id);

    public abstract String getname();
    public abstract void setname(String name);

    public abstract long getSalary();
    public abstract void setSalary(long salary);
    
    public abstract Department getDepartment();
    public abstract void setDepartment(Department department);

    public abstract Collection getDirects();
    public abstract void setDirects(Collection directs);

    public abstract Employee getManager();
    public abstract void setManager(Employee empLocal);
    
    public Integer ejbCreate(int id) throws CreateException {
        setId(id);
        return null;
    }

    public void ejbPostCreate(int id) throws CreateException {}
    public void ejbLoad() {}
    public void ejbStore() {}
    public void ejbActivate() {}
    public void ejbPassivate() {}
    public void ejbRemove() {}
    public void setEntityContext(EntityContext ctx) {}
    public void unsetEntityContext() {}
}
