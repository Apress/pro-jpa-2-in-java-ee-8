package examples.model;

import java.util.Date;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

public abstract class Employee {

    private int id;

    private String name;

    private Date startDate;
    
    private Address address;

    private ParkingSpace parkingSpace;

    private Employee manager;

    private Collection<Employee> directs;

    private Department department;

    private Collection<Project> projects;

    private Map<String, Integer> projectHours;

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(ParkingSpace space) {
        this.parkingSpace = space;
    }

    public Department getDepartment() {
        return department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }
    
    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Map<String, Integer> getProjectHours() {
         return projectHours;
    }
    
    public void setProjectHours(Map<String, Integer> projectHoursMap) {
        this.projectHours = projectHoursMap;
    }
    
    public void addProjectHours(String projectNameKey, Integer hoursValue) {
        if(null == projectHours) {
            projectHours = new HashMap();
        }
        projectHours.put(projectNameKey, hoursValue);            
    }
    
    public Collection<Project> getProjects() {
        return projects;
    }
    
    public void addProject(Project project) {
        if (!getProjects().contains(project)) {
            getProjects().add(project);
        }
        if (!project.getEmployees().contains(this)) {
            project.getEmployees().add(this);
        }
    }

    public String toString() {
        return "Employee(id=" + getId() + ", name=" + getName() + ")";
    }
}

