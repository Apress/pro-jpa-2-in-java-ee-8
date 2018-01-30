package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.persistence.metamodel.Metamodel;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.stateless.SearchService;
import examples.model.Phone;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = -3522516457354939873L;

    private final String TITLE = 
        "Chapter 9: Metamodel Strongly Typed API Example";
    
    private final String DESCRIPTION = 
        "This example pulls together much of what was learned in this chapter " +
        "and relates it to our earlier EmployeeService example. </br>" +
        "The example allows you to use the Metamodel for a query, and print a " +
        "summary of the metamodel of the persistence unit.";

    
    // inject a reference to the SearchService slsb
    @EJB SearchService service;
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action == null) {
            // do nothing if no action requested
        } else if (action.equals("ExecuteQuery")) {
            Collection<Object> results = service.executeQueryUsingMetamodel();
            out.println("Results: <br/>");
            for (Object o : results) {
                Object[] entry = (Object[])o;
                out.println(entry[0] + ", " +
                            entry[1] + ", " +
                            (Phone)entry[2] + "<br/>");
            }
        } else if (action.equals("Metamodel")) {
            Metamodel metamodel = service.getMetamodel();
            out.println("Metamodel: " + metamodel);
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
        out.println("<h3>Execute Query Using Metamodel</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"ExecuteQuery\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        // form print out metamodel summary
        out.println("<h3>Get Metamodel Summary</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"Metamodel\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
