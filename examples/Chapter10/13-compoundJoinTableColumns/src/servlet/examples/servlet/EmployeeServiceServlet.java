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
import examples.model.Project;
import examples.stateless.EmployeeService;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 10: Using Compound Join Columns Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates the basics of using compound join columns.";

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
                    request.getParameter("country"),
                    parseInt(request.getParameter("id")),
                    request.getParameter("name"),
                    parseLong(request.getParameter("salary")));
        } else if (action.equals("SetManager")) {
            service.setEmployeeManager(
                    request.getParameter("empCountry"),
                    parseInt(request.getParameter("empId")),
                    request.getParameter("mgrCountry"),
                    parseInt(request.getParameter("mgrId")));
        } else if (action.equals("AddEmployeeProject")) {
            service.addEmployeeProject(
                    request.getParameter("empCountry2"),
                    parseInt(request.getParameter("empId2")),
                    parseInt(request.getParameter("projId")));
        }
        
        out.println("Employees: </br>");
        for (Employee emp : service.findAllEmployees()) {
            out.print(emp + "<br/>");
        }
        
        out.println("</br>Projects: </br>");
        for (Project p : service.findAllProjects()) {
            out.print(p + " emp count: " + p.getEmployees().size() + "<br/>");
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
        // form to create and Employee 
        out.println("<h3>Create an Employee </h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Country:</td><td><input type=\"text\" name=\"country\"/>(String)</td></tr>");
        out.println("<tr><td>Employee Id:</td><td><input type=\"text\" name=\"id\"/>(int)</td></tr>");
        out.println("<tr><td>Employee Name:</td><td><input type=\"text\" name=\"name\"/>(String)</td></tr>");
        out.println("<tr><td>Employee Salary:</td><td><input type=\"text\" name=\"salary\"/>(long)</td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"Create\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");

        out.println("<h3>Set Employee Manager</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Country:</td><td><input type=\"text\" name=\"empCountry\"/>(String)</td></tr>");
        out.println("<tr><td>Employee Id:</td><td><input type=\"text\" name=\"empId\"/>(int)</td></tr>");
        out.println("<tr><td>Manager Country:</td><td><input type=\"text\" name=\"mgrCountry\"/>(String)</td></tr>");
        out.println("<tr><td>Manager Id:</td><td><input type=\"text\" name=\"mgrId\"/>(int)</td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"SetManager\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");

        out.println("<h3>Add Employee Project</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Country:</td><td><input type=\"text\" name=\"empCountry2\"/>(String)</td></tr>");
        out.println("<tr><td>Employee Id:</td><td><input type=\"text\" name=\"empId2\"/>(int)</td></tr>");
        out.println("<tr><td>Project Id:</td><td><input type=\"text\" name=\"projId\"/>(int)</td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"AddEmployeeProject\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
