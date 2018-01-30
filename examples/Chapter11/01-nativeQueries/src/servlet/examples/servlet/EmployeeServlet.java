package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.stateless.OrgStructure;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 11: Native Queries Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates the basic usage of native " +
        "queries.  It compares jdbc to native queries.";

    
    @EJB (beanName="JdbcOrgStructureBean")
    OrgStructure jdbcOrgStructure;
    @EJB (beanName="QueryOrgStructureBean")
    OrgStructure queryOrgStructure;
    @EJB (beanName="NamedQueryOrgStructureBean")
    OrgStructure namedQueryOrgStructure;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action == null) {
            // do nothing if no action requested
        } else if (action.equals("SqlAndJdbc")) {
            List emps = jdbcOrgStructure.findEmployeesReportingTo(
                    parseInt(request.getParameter("mgrId")));
            out.println("Found using JDBC: <br/>");
            printCollection(emps, out);
        } else if (action.equals("SqlAndQuery")) {
            List emps = queryOrgStructure.findEmployeesReportingTo(
                    parseInt(request.getParameter("mgrId")));
            out.println("Found using NativeQuery: <br/>");
            printCollection(emps, out);
        } else if (action.equals("SqlAndNamedQuery")) {
            List emps = namedQueryOrgStructure.findEmployeesReportingTo(
                    parseInt(request.getParameter("mgrId")));
            out.println("Found using NamedNativeQuery: <br/>");
            printCollection(emps, out);
        }
        out.println("<br/>All Employees: <br/>");
        List emps = queryOrgStructure.findAllEmployees();
        printCollection(emps, out);
        
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
            out.println(o + "<br/>");
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
        // form to create and Employee and Department
        out.println("<h3>Find Employees Reporting to:</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Manager Id:</td><td><input type=\"text\" name=\"mgrId\"/>(int)</td></tr>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"SqlAndJdbc\"/> </td></tr>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"SqlAndQuery\"/> </td></tr>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"SqlAndNamedQuery\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
