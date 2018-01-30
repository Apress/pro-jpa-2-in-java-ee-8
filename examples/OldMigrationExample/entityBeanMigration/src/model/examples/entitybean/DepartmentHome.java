package examples.entitybean;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

public interface DepartmentHome extends EJBLocalHome {
    public Department create(int id) throws CreateException;
    public Department findByPrimaryKey(int id) throws FinderException;
    public Collection findAll() throws FinderException;
    public Department findByName(String name) throws FinderException;
    public Collection unallocatedEmployees();
}
