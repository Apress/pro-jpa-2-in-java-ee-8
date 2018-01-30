package examples.client;

import java.util.Collection;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import examples.model.Employee;

public class EmployeeClient {

    public static void main(String[] args) {
        EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("EmployeeService");
        EntityManager em = emf.createEntityManager();
        
        Collection emps = em.createQuery("SELECT e FROM Employee e")
                            .getResultList();
        for (Iterator i = emps.iterator(); i.hasNext();) {
            Employee e = (Employee) i.next();
            System.out.println("Employee " + e.getId() + ", " + e.getName());
        }
        
        em.close();
        emf.close();
    }
}
