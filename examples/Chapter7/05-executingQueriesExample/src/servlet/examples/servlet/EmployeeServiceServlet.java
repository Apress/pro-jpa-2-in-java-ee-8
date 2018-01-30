package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.model.Employee;
import examples.model.Project;
import examples.stateful.ProjectManager;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 7: Executing Queries Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates a couple of ways to execute queries.";
    
    @EJB // note: it's dangerous to inject a sfsb into servlet since servlets are typically multi-threaded
    private ProjectManager service;
        
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("DisplayProjectEmployees")) {
                List result = service.findProjectEmployees(
                        request.getParameter("projName"));
                out.println("Found Employees: </br>");
                printEmployees(result, out);
            } else if (action.equals("FindEmployeesWithoutProjects")) {
                List result = service.findEmployeesWithoutProjects();
                out.println("Found Employees: </br>");
                for (Object emp : result) {
                    out.print(emp + "<br/>");
                }
            }
            out.println("<hr/>");
        }
        out.println("All Employees:<br> ");
        for (Employee emp : service.findAllEmployees()) {
            out.print(emp + "<br/>");
        }
        out.println("<br>All Projects:<br> ");
        for (Project proj : service.findAllProjects()) {
            out.print(proj + "<br/>");
        }
        
        printHtmlFooter(out);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    private void printEmployees(List result, PrintWriter out) {
        int count = 0;
        for (Iterator i = result.iterator(); i.hasNext();) {
            Employee e = (Employee) i.next();
            out.print(++count + ": " + e.getName() + ", " +
                               e.getDepartment().getName() + "</br>");
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
        // form find
        out.println("<h3>Find Employees for Project (ordered by name)</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Project Name:</td><td><input type=\"text\" name=\"projName\"/>(String)</td></tr>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"DisplayProjectEmployees\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        out.println("<h3>Find Employees without Projects</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"FindEmployeesWithoutProjects\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
