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
import examples.model.ProjectAssignment;

import examples.stateless.EmployeeService;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 10: Using Relationships with state Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates the basics of mapping relationships that contain state.";

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
                    parseLong(request.getParameter("salary")));
        } else if (action.equals("AddEmployeeProject")) {
            service.addEmployeeProject(
                    parseInt(request.getParameter("empId2")),
                    parseInt(request.getParameter("projId")));
        }
        
        out.println("Employees: </br>");
        for (Employee emp : service.findAllEmployees()) {
            out.print(emp + "<br/>");
        }
        
        out.println("</br>Projects: </br>");
        for (Project p : service.findAllProjects()) {
            out.print(p + "<br/>");
        }       

        out.println("</br>Assignments: </br>");
        for (ProjectAssignment asst : service.findAllAssignments()) {
            out.print(asst + "<br/>");
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
        out.println("<tr><td>Employee Id:</td><td><input type=\"text\" name=\"empId\"/>(int)</td></tr>");
        out.println("<tr><td>Employee Name:</td><td><input type=\"text\" name=\"name\"/>(String)</td></tr>");
        out.println("<tr><td>Employee Salary:</td><td><input type=\"text\" name=\"salary\"/>(long)</td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"Create\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");

        out.println("<h3>Add Employee Project</h3>");
        out.println("<table><tbody>");
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
