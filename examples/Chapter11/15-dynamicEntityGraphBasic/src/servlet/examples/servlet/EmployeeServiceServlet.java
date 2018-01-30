package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnitUtil;

import examples.model.Address;
import examples.stateless.EmployeeService;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 11: Dynamic Entity Graph with Basic Attributes";
    
    private final String DESCRIPTION = 
        "This example demonstrates the basics of a dynamic entity graph.";

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
            List<Address> addrs = service.findAllAddresses();
            out.println("Found All: <br/>");
            printAddresses(addrs, out);
        }
        
        printHtmlFooter(out);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void printAddresses(List<Address> addrs, PrintWriter out) {
        for (Address a : addrs) {
            out.println(a.toLoadedString(emf.getPersistenceUnitUtil()) + "<br/>");
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

        out.println("<h3>Find all Addresses</h3>");
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
