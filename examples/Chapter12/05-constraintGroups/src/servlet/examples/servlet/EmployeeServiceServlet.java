package examples.servlet;

import java.util.Set;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.model.Employee;
import examples.stateless.EmployeeService;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 12: Using Predefined Contraint Groups during Validation Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates the basics of using predefined constraint groups during entity validation.";

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
            service.createEmployee(
                    parseInt(request.getParameter("empId")),
                    request.getParameter("name"),
                    parseSalary(request.getParameter("salary")),
                    parseWage(request.getParameter("hourlyWage")),
                    request.getParameter("type"),
                    out);
        } else if (action.equals("Update")) {
            service.changeEmployeeName(
                    parseInt(request.getParameter("empId")),
                    request.getParameter("name"));
        } else if (action.equals("Remove")) {
            service.removeEmployee(
                    parseInt(request.getParameter("empId")));
        }
        
        out.println("All Employees: </br>");
        for (Employee emp : service.findAllEmployees()) {
            out.print(emp + "<br/>");
        }
        
        printHtmlFooter(out);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
            
    private Integer parseInt(String value) {
    	try {
            return new Integer(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private Long parseSalary(String value) {
        if ((value == null) || (value.length() == 0))
            return null;
    	try {
            return new Long(value);
        } catch (NumberFormatException e) {
            return 0L;
        }
    }
    
    private Double parseWage(String value) {
        if ((value == null) || (value.length() == 0))
            return null;
        try {
            return new Double(value);
        } catch (NumberFormatException e) {
            return 0.0;
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
        // form to create and Employee
        out.println("<h3>Create/Update/Remove Employees</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Id:</td><td><input type=\"text\" name=\"empId\"/>(int)</td></tr>");
        out.println("<tr><td>Employee Name:</td><td><input type=\"text\" name=\"name\"/>(String)</td></tr>");
        out.println("<tr><td>Employee Salary:</td><td><input type=\"text\" name=\"salary\"/>(int) (For Full Time employees only)</td></tr>");
        out.println("<tr><td>Employee Hourly Wage:</td><td><input type=\"text\" name=\"hourlyWage\"/>(float) (For Part Time employees only)</td></tr>");
        out.println("<tr><td>Employee Type:</td><td>");
        out.println("<input type=\"radio\" name=\"type\" value=\"Full\" checked> Full Time<br>");
        out.println("<input type=\"radio\" name=\"type\" value=\"Part\"> Part Time<br>");
        out.println("</td></tr>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"Create\"/></td> ");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"Remove\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
