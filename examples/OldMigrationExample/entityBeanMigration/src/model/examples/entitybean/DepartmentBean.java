package examples.entitybean;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;

public abstract class DepartmentBean implements EntityBean {
    public abstract int getId();
    public abstract void setId(int id);
    public abstract String getName();
    public abstract void setName(String name);
    public abstract Collection getEmployees();
    public abstract void setEmployees(Collection employees);
    
    public abstract Employee ejbSelectManagerForDept(int deptId);
    public abstract Collection ejbSelectEmployeesWithNoDepartment();
    
    public Employee getManager() {
        return ejbSelectManagerForDept(getId());
    }
    
    public Integer ejbCreate(int id) throws CreateException {
        setId(id);
        return null;
    }
    
    public Collection ejbHomeUnallocatedEmployees() {
        return ejbSelectEmployeesWithNoDepartment();
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

