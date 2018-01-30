package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Department;
import examples.model.DeptId;
import examples.model.Project;
import examples.model.ProjectId;

@Stateless
public class ProjectService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public Department createDepartment(int number, String country, String name) {
        Department dept = new Department();
        dept.setId(new DeptId(number, country));
        dept.setName(name);
        em.persist(dept);
        return dept;
    }

    public Project createProject(String name, int deptNum, String deptCountry) {
        DeptId deptId = new DeptId(deptNum, deptCountry);
        Department dept = em.find(Department.class, deptId);
        if (dept != null) {
            Project proj = new Project(dept);
            ProjectId projId = new ProjectId(dept.getId(),name);
            proj.setId(projId);
            dept.getProjects().add(proj);
            em.persist(proj);
            return proj;
        }
        return null;
    }

    public List<Project> findAllProjects() {
        return em.createQuery("SELECT p FROM Project p", Project.class)
                 .getResultList();
    }
    
    public List<Department> findAllDepartments() {
        return em.createQuery("SELECT d FROM Department d", Department.class)
                 .getResultList();
    }
}
