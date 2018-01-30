package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

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
        "Chapter 7: Query Hints Examples";
    
    private final String DESCRIPTION = 
        "This example demonstrates the use of query hints. </br>" +
        "The example allows you to find all employees using either " +
        "a regular query, or one configured to use a 'cacheUsage' hint. " +
        "The hint can be used with either a dynamic or named query";

    
    @EJB
    private EmployeeService service;

    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action != null) {
            Employee emp = null;
            if (action.equals("Find")) {
                emp = service.findEmployee(parseInt(request.getParameter("id")));
            } else if (action.equals("FindNoCacheDynamic")) {
                emp = service.findEmployeeNoCache(parseInt(request.getParameter("id")));
            } else if (action.equals("FindNoCacheNamed")) {
                emp = service.findEmployeeNoCacheNamed(parseInt(request.getParameter("id")));
            }
            out.println("Found " + emp);
            out.println("<hr/>");
        }
        out.println("All Employees:<br> ");
        printCollection(service.findAllEmployees(), out);
        
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

    private void printCollection(Collection c, PrintWriter out) {
        for (Object o : c) {
            out.print(o + "<br/>");
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
        out.println("<h3>Find Employee</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Id:</td><td><input type=\"text\" name=\"id\"/>(int)</td></tr>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"Find\"/></td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"FindNoCacheDynamic\"/></td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"FindNoCacheNamed\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
