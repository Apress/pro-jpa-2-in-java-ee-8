package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.model.Department;
import examples.model.Employee;
import examples.stateless.DepartmentHome;
import examples.stateless.EmployeeHome;

@PersistenceContext(name="EmployeeService", unitName="EmployeeService")
public class EmployeeServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 15: Using Migrated Home Facades";
    
    private final String DESCRIPTION = 
        "This example demonstates the basics use of some migrated home facades.";

    @EJB DepartmentHome deptHome;
    @EJB EmployeeHome empHome;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        try {
            String action = request.getParameter("action");
            if (action == null) {
                // do nothing if no action requested
            } else if (action.equals("CreateEmployee")) {
                Employee emp = empHome.create(parseInt(request.getParameter("empId")));
                out.println("Created : " + emp);
            } else if (action.equals("FindByPkEmployee")) {
                Employee emp = empHome.findByPrimaryKey(parseInt(request.getParameter("empPk")));
                out.println("Found : " + emp);
            } else if (action.equals("GetManagerStats")) {
                out.println("ManagerStats: </br>");
                for (Object stat : empHome.getManagerStats()) {
                    out.print(stat + "<br/>");
                }
            } else if (action.equals("FindAllEmployees")) {
                out.println("Employees: </br>");
                for (Object emp : empHome.findAll()) {
                    out.print(emp + "<br/>");
                }
            } else if (action.equals("RemoveEmployee")) {
                empHome.remove(parseInt(request.getParameter("removeEmpId")));
                out.println("Removed Employee");
                
            } else if (action.equals("CreateDepartment")) {
                Department dept = deptHome.create(parseInt(request.getParameter("deptId")));
                out.println("Created: " + dept);
            } else if (action.equals("FindByPkDepartment")) {
                Department dept = deptHome.findByPrimaryKey(parseInt(request.getParameter("deptPk")));
                out.println("Found : " + dept);
            } else if (action.equals("FindByNameDepartment")) {
                out.println("</br>Departments: </br>");
                for (Object dept : deptHome.findByName(request.getParameter("deptName"))) {
                    out.print(dept + "<br/>");
                }
            } else if (action.equals("FindAllDepartments")) {
                out.println("</br>Departments: </br>");
                for (Object dept : deptHome.findAll()) {
                    out.print(dept + "<br/>");
                }
            } else if (action.equals("UnallocatedEmployees")) {
                out.println("</br>Departments: </br>");
                for (Object emp : deptHome.unallocatedEmployees()) {
                    out.print(emp + "<br/>");
                }
            } else if (action.equals("RemoveDepartment")) {
                deptHome.remove(parseInt(request.getParameter("removeDeptId")));
                out.println("Removed Department");
            }
        } catch (Exception e) {
            throw new ServletException(e);
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

    private void printHtmlHeader(PrintWriter out) throws IOException {
        out.println("<body>");
        out.println("<html>");
        out.println("<head><title>" + TITLE + "</title></head>");
        out.println("<center><h1>" + TITLE + "</h1></center>");
        out.println("<p>" + DESCRIPTION + "</p>");
        out.println("<hr/>");
        out.println("<form action=\"EmployeeServiceServlet\" method=\"POST\">");
        // form for EmployeeHome Facade
        out.println("<h3>EmployeeHome Facade operations</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Id:</td><td><input type=\"text\" name=\"empId\"/>(int)</td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"CreateEmployee\"/></td></tr>");
        out.println("<tr><td>Id:</td><td><input type=\"text\" name=\"empPk\"/>(int)</td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"FindByPkEmployee\"/></td></tr>");
        out.println("<tr><td>Id:</td><td><input type=\"text\" name=\"removeEmpId\"/>(int)</td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"RemoveEmployee\"/></td></tr>");
        out.println("<tr><td colspan=\"3\"><input name=\"action\" type=\"submit\" value=\"GetManagerStats\"/></td></tr>");
        out.println("<tr><td colspan=\"3\"><input name=\"action\" type=\"submit\" value=\"FindAllEmployees\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");

        // form for DepartmentHome Facade
        out.println("<h3>DepartmentHome Facade operations</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Id:</td><td><input type=\"text\" name=\"deptId\"/>(int)</td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"CreateDepartment\"/></td></tr>");
        out.println("<tr><td>Id:</td><td><input type=\"text\" name=\"deptPk\"/>(int)</td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"FindByPkDepartment\"/></td></tr>");
        out.println("<tr><td>Name:</td><td><input type=\"text\" name=\"deptName\"/>(String)</td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"FindByNameDepartment\"/></td></tr>");
        out.println("<tr><td>Id:</td><td><input type=\"text\" name=\"removeDeptId\"/>(int)</td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"RemoveDepartment\"/></td></tr>");
        out.println("<tr><td colspan=\"3\"><input name=\"action\" type=\"submit\" value=\"UnallocatedEmployees\"/></td></tr>");
        out.println("<tr><td colspan=\"3\"><input name=\"action\" type=\"submit\" value=\"FindAllDepartments\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
