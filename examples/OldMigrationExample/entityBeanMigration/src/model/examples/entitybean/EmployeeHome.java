package examples.entitybean;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

public interface EmployeeHome extends EJBLocalHome {
    public Employee create(int id) throws CreateException;
    public Employee findByPrimaryKey(int id) throws FinderException;
}
