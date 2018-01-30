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
        "Chapter 12: Validation during Pre-Remove Lifecycle Event Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates how to enable validation in the pre-remove lifecycle event of an entity.";

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
                    parseLong(request.getParameter("vacDays")),
                    out);
        } else if (action.equals("Update")) {
            service.changeEmployeeName(
                    parseInt(request.getParameter("empId")),
                    request.getParameter("name"),
                    parseLong(request.getParameter("vacDays")));
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

    private long parseLong(String value) {
    	try {
            return new Long(value);
        } catch (NumberFormatException e) {
            return 0L;
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
        out.println("<tr><td>Vacation Days:</td><td><input type=\"text\" name=\"vacDays\"/>(int)</td></tr>");
        out.println("</td></tr>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"Create\"/></td>");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"Update\"/></td>");
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
