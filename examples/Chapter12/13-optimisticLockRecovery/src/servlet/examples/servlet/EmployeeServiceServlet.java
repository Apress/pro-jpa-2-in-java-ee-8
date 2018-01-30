package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.model.Employee;
import examples.stateless.EmpServiceClient;
import examples.stateless.EmployeeService;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 12: Optimistic Locking Recovery Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates the basics of optimistic locking recovery.";

    @EJB EmpServiceClient serviceClient;
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
                    parseInt(request.getParameter("vacation")));
        } else if (action.equals("AdjustVacation")) {
            serviceClient.adjustVacation(
                    parseInt(request.getParameter("empId")),
                    parseInt(request.getParameter("vacation")));
        } else if (action.equals("AdjustVacationOptLock")) {
            serviceClient.adjustVacationOptLock(
                    parseInt(request.getParameter("empId")),
                    parseInt(request.getParameter("vacation")));
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
        out.println("<form action=\"EmployeeServlet\" method=\"POST\">");
        // form to create and Employee
        out.println("<h3>Create/Update Employees</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Id:</td><td><input type=\"text\" name=\"empId\"/>(int)</td></tr>");
        out.println("<tr><td>Employee Name:</td><td><input type=\"text\" name=\"name\"/>(String)</td></tr>");
        out.println("<tr><td>Employee Vacation:</td><td><input type=\"text\" name=\"vacation\"/>(int)</td></tr>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"Create\"/></td> ");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"AdjustVacation\"/></td>");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"AdjustVacationOptLock\"/>(see exception in log)</td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
