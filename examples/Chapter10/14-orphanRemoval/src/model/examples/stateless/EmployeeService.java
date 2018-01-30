package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Employee;
import examples.model.Evaluation;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public Employee createEmployee(int id, String name) {
        Employee emp = new Employee();
        emp.setId(id);
        emp.setName(name);
        em.persist(emp);        
        return emp;
    }

    public void addEmployeeEvaluation(int empId, int evalId, String text) {
        Employee emp = em.find(Employee.class, empId);
        Evaluation eval = new Evaluation(evalId, text);
        em.persist(eval);
        emp.getEvals().add(eval);
    }

    public void removeEmployeeEvaluation(int empId, int evalId) {
        Employee emp = em.find(Employee.class, empId);
        Evaluation eval = em.find(Evaluation.class, evalId);
        emp.getEvals().remove(eval);
    }

    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }

    public List<Evaluation> findAllEvaluations() {
        return em.createQuery("SELECT e FROM Evaluation e", Evaluation.class)
                 .getResultList();
    }
}
