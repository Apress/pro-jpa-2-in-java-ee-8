package examples.stateless;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;

import examples.model.Department;

public interface DepartmentHome {
    public Department create(int id) throws CreateException;
    public Department findByPrimaryKey(int id) throws FinderException;
    public Collection findAll() throws FinderException;
    public Collection findByName(String name) throws FinderException;
    public Collection unallocatedEmployees();
    public void remove (Object pk) throws RemoveException;
    public void remove (Department dept) throws RemoveException;
}

