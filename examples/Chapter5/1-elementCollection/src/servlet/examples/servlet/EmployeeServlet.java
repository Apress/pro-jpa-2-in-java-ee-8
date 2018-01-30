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
import examples.stateless.EmployeeService;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 5: ElementCollection Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates how to specify an ElementCollection of Embeddables or Basic types.</br> ";

  
    @EJB EmployeeService empService;
    
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
        } else if (action.equals("AddNickname")) {
            Employee emp = empService.addEmployeeNickname(
                    parseInt(request.getParameter("id")),
                    request.getParameter("nickname"));
            if (emp != null) {
                out.println("Added nickname to Employee " + emp);
            }
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
        // form to create Employee 
        out.println("<h3>Create an Employee</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Name:</td><td><input type=\"text\" name=\"empName\"/>(String)</td></tr>");
        out.println("<tr><td>Employee Salary:</td><td><input type=\"text\" name=\"salary\"/>(long)</td></tr>");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"CreateEmp\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        // form to Add a nickname 
        out.println("<h3>Add a Nickname</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Id:</td><td><input type=\"text\" name=\"id\"/>(int)</td></tr>");
        out.println("<tr><td>Nickname:</td><td><input type=\"text\" name=\"nickname\"/>(String)</td></tr>");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"AddNickname\"/></td></tr>");
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
