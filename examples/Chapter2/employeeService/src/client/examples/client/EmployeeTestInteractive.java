package examples.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import examples.model.Employee;
import examples.model.EmployeeService;

public class EmployeeTestInteractive {

    public static void main(String[] args) throws IOException {
        // init the EntityManager
        EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("EmployeeService");
        EntityManager em = emf.createEntityManager();
        EmployeeService service = new EmployeeService(em);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String action;
        int id;
        try {
            while (true) {
                System.out.println("\n\n\n[L]ist| [A]dd | [R]emove | [Q]uit: \n\t\t\t");
                action = in.readLine();
                if ((action.length() == 0) || action.toUpperCase().charAt(0) == 'Q') {
                    break;
                }
    
                switch (action.toUpperCase().charAt(0)) {
                    case 'A':
                        System.out.println("Enter int value for employee id: \n\t\t\t");
                        try {
                            id = new Integer(in.readLine());
                        } catch (NumberFormatException e) {
                            break;
                        }
    
                        System.out.println("Enter value for employee name: \n\t\t\t");
                        String name = in.readLine();
    
                        System.out.println("Enter long value for employee salary: \n\t\t\t");
                        long salary = 0;
                        try {
                            salary = new Long(in.readLine());
                        } catch (NumberFormatException e) {
                            break;
                        }
    
                        em.getTransaction().begin();
                        Employee emp = service.createEmployee(id, name, salary);
                        em.getTransaction().commit();
                        
                        System.out.println("\n\nCreated " + emp);
                        break;
    
                    case 'L':
                        Collection<Employee> emps = service.findAllEmployees();
                        System.out.println("\n\nFound employees: ");
                        for (Employee e : emps)
                            System.out.println("\t" + e);                        
                        break;
    
                    case 'R':
                        System.out.println("Enter int value for employee id: \n\t\t\t");
                        try {
                            id = new Integer(in.readLine());
                        } catch (NumberFormatException e) {
                            break;
                        }
                        
                        em.getTransaction().begin();
                        service.removeEmployee(id);
                        em.getTransaction().commit();
                        
                        System.out.println("\n\nRemoved Employee " + id);
                        break;
                    default:
                        continue;
                }
            }
        } finally {        
            // close the EntityManager when done
            em.close();
            emf.close();
        }
    }
}
