package examples.entitybean;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

public interface ProjectHome extends EJBLocalHome {
    public Project create(int id) throws CreateException;
    public Project findByPrimaryKey(int id) throws FinderException;
    public Collection findAll() throws FinderException;
    public Project findByName(String name) throws FinderException;
    public Collection unallocatedEmployees();
}
