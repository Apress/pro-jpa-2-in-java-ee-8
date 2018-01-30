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

import examples.model.Employee;
import examples.model.EmployeeType;
import examples.stateless.EmployeeService;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 4: Enum Mapping Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates how to map an Enum entity attribute.</br> " +
        "It allows you to create/find employees. All operations " +
        "are persisted to the database. It shows how to map enumerations using " +
        "both ordinals and strings.";

    
    // inject a reference to the EmployeeService slsb
    @EJB EmployeeService service;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action == null) {
            // do nothing if no action requested
        } else if (action.equals("Create")) {
            Employee emp = service.createEmployee(
                    parseInt(request.getParameter("id")),
                    request.getParameter("name"),
                    parseLong(request.getParameter("salary")),
                    getEmployeeType(request.getParameter("type")));
            out.println("Created " + emp);
        } else if (action.equals("FindAll")) {
            Collection<Employee> emps = service.findAllEmployees();
            if (emps.isEmpty()) {
                out.println("No Employees found ");
            } else {
                out.println("Found Employees: </br>");
                for (Employee emp : emps) {
                    out.println(emp + "<br/>");
                }
            }
        }
        
        printHtmlFooter(out);
    }
    
    private EmployeeType getEmployeeType(String type) {
        if (type.equals("Full")) {
            return EmployeeType.FULL_TIME_EMPLOYEE;
        } else if (type.equals("Part")) {
            return EmployeeType.PART_TIME_EMPLOYEE;
        } else {
            return EmployeeType.CONTRACT_EMPLOYEE;
        }
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
        // form to create and Employee and Address
        out.println("<h3>Create an Employee</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Id:</td><td><input type=\"text\" name=\"id\"/>(int)</td></tr>");
        out.println("<tr><td>Employee Name:</td><td><input type=\"text\" name=\"name\"/>(String)</td></tr>");
        out.println("<tr><td>Employee Salary:</td><td><input type=\"text\" name=\"salary\"/>(long)</td></tr>");
        out.println("<tr><td>Empoyee Type:</td><td>");
        out.println("<input type=\"radio\" name=\"type\" value=\"Full\" checked> Full Time<br>");
        out.println("<input type=\"radio\" name=\"type\" value=\"Part\"> Part Time<br>");
        out.println("<input type=\"radio\" name=\"type\" value=\"Contract\"> Contract");
        out.println("</td></tr>");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"Create\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        // form to find all
        out.println("<h3>Find all Employees</h3>");
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
