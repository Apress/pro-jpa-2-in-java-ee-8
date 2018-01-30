package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import examples.model.Department;
import examples.stateless.EmployeeService;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 11: Dynamic Entity Graph with With Map Key Subgraph";
    
    private final String DESCRIPTION = 
        "This example demonstrates the basics of a dynamic entity graph with a Map key subgraph.";

    @EJB EmployeeService service;
    @PersistenceUnit(unitName="EmployeeService")
    EntityManagerFactory emf;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action == null) {
            // do nothing if no action requested
        } else if (action.equals("Find")) {
            out.println("Prevented from executing due to bug in EclipseLink. <br/>See:  https://bugs.eclipse.org/bugs/show_bug.cgi?id=417112");
        /*
            List<Department> depts = service.findAllDepartments();
            out.println("Found All: <br/>(N/L in output means Not Loaded)<br/>");
            printDepts(depts, out);
         */
        }
        
        printHtmlFooter(out);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void printDepts(List<Department> depts, PrintWriter out) {
        for (Department dept : depts) {
            out.println(dept.toLoadedString(emf.getPersistenceUnitUtil()));
            out.println("<br/>");
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
        out.println("<h3>Find all Departments</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"Find\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
