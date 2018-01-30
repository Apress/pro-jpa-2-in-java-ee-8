package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.persistence.metamodel.Metamodel;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.stateless.SearchService;
import examples.model.Employee;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = -3522516457354939873L;

    private final String TITLE = 
        "Chapter 9: Metamodel-based Strongly Typed Query Example";
    
    private final String DESCRIPTION = 
        "This example pulls together much of what was learned in this chapter " +
        "and relates it to our earlier EmployeeService example. </br>" +
        "The example allows you to use the generated canonical metamodel for a query, " +
        "and compares it with using a JP QL or string-based query.";

    
    // inject a reference to the SearchService slsb
    @EJB SearchService service;
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        List<Employee> emps = null;

        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action == null) {
            // do nothing if no action requested
        } else {
            if (action.equals("ExecuteJPQLBasedQuery")) {
                emps = service.getEmployeesUsingJpqlQuery();
                out.println("JP QL query results: <br/>");
            } else if (action.equals("ExecuteStringBasedQuery")) {
                emps = service.getEmployeesUsingStringBasedQuery();
                out.println("String-based query results: <br/>");
            } else if (action.equals("ExecuteMetamodelBasedQuery")) {
                emps = service.getEmployeesUsingCanonicalMetamodelQuery();
                out.println("Canonical Metamodel-based query results: <br/>");
            }
            for (Employee emp : emps) {
                out.println(emp + "<br/>");
            }
        }
        printHtmlFooter(out);
    }
    
    @Override
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
        // form to execute query
        out.println("<h3>Execute Query Using JP QL</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"ExecuteJPQLBasedQuery\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        // form to execute query
        out.println("<h3>Execute String-based Query</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"ExecuteStringBasedQuery\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        // form to execute query
        out.println("<h3>Execute Query Using Canonical Metamodel</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"ExecuteMetamodelBasedQuery\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
