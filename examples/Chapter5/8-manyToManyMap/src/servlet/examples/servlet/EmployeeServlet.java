package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import examples.model.Department;
import examples.model.Employee;
import examples.stateless.DepartmentService;
import examples.stateless.EmployeeService;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 5: Many-To-Many Map Example Keyed by String and Enumerated Type";
    
    private final String DESCRIPTION = 
        "This example demonstrates how to work with relationship Maps.</br> " +
        "The Map contains String keys and entity values.";

    
    @EJB EmployeeService empService;
    @EJB DepartmentService deptService;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action == null) {
            // do nothing if no action requested
        } else if (action.equals("CreateEmp")) {
            Employee emp = empService.createEmployee(
                    request.getParameter("empName"),
                    parseLong(request.getParameter("salary")));
            out.println("Created " + emp);
        } else if (action.equals("CreateDept")) {
            Department dept = deptService.createDepartment(
                    request.getParameter("deptName"));
            out.println("Created " + dept);
        } else if (action.equals("AddEmployeeDept")) {
            Employee emp = empService.addEmployeeDepartment(
                    parseInt(request.getParameter("empId")),
                    request.getParameter("cubeId"),
                    parseInt(request.getParameter("deptId")));
            out.println("Updated " + emp + "</br>");
        } else if (action.equals("FindAll")) {
            Collection<Employee> emps = empService.findAllEmployees();
            if (emps.isEmpty()) {
                out.println("No Employees found ");
            } else {
                out.println("Found Employees: </br>");
                for (Employee emp : emps) {
                    out.println(emp + "<br/>");
                }
            }
            
            out.println("</br>");
            Collection<Department> depts = deptService.findAllDepartments();
            if (depts.isEmpty()) {
                out.println("No Departments found ");
            } else {
                out.println("Found Departments: </br>");
                for (Department dept : depts) {
                    out.println(dept + " with " + dept.getEmployees().size() +
                            " employees " + dept.getEmployees() + "<br/>");
                }
            }
        }
        
        printHtmlFooter(out);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    private int parseInt(String intString) {
        try {
            return Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    private long parseLong(String longString) {
        try {
            return Long.parseLong(longString);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void printHtmlHeader(PrintWriter out) throws IOException {
        out.println("<body>");
        out.println("<html>");
        out.println("<head><title>" + TITLE + "</title></head>");
        out.println("<center><h1>" + TITLE + "</h1></center>");
        out.println("<p>" + DESCRIPTION + "</p>");
        out.println("<hr/>");
        out.println("<form action=\"EmployeeServlet\" method=\"POST\">");
        // form to create and Employee and Department
        out.println("<h3>Create an Employee</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Name:</td><td><input type=\"text\" name=\"empName\"/>(String)</td></tr>");
        out.println("<tr><td>Employee Salary:</td><td><input type=\"text\" name=\"salary\"/>(long)</td></tr>");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"CreateEmp\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");

        out.println("<table><tbody>");
        out.println("<tr><td>Dept Name:</td><td><input type=\"text\" name=\"deptName\"/>(String)</td></tr>");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"CreateDept\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        
        out.println("<table><tbody>");
        out.println("<tr><td>Emp Id:</td><td><input type=\"text\" name=\"empId\"/>(int)</td></tr>");
        out.println("<tr><td>Cube Id:</td><td><input type=\"text\" name=\"cubeId\"/>(String)</td></tr>");
        out.println("<tr><td>Dept Id:</td><td><input type=\"text\" name=\"deptId\"/>(int)</td></tr>");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"AddEmployeeDept\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        // form to find all
        out.println("<h3>Find all Employees & Departments</h3>");
        out.println("<input name=\"action\" type=\"submit\" value=\"FindAll\"/>");
        out.println("</form>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
