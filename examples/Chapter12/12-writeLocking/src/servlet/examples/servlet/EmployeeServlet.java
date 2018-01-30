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

import examples.model.Uniform;
import examples.stateful.FeeManagement;
import examples.stateful.EmployeeManagement;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 12: Optimistic Write Locking Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates the basics of using an optimistic write lock." +
        "An OptimisticLockException will be thrown to prevent inconsistent data (see the server console)." + 
        "Turn off locking and redeploy to see the update succeed (incorrectly).";

    
    @EJB EmployeeManagement empManagement;
    @EJB FeeManagement feeManagement;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action == null) {
            // do nothing if no action requested
        } else if (action.equals("ShowWriteLocks")) {
            feeManagement.calculateCleaningCost(1);
            empManagement.addUniform(1, empManagement.getUniform(2));
            empManagement.addUniform(1, empManagement.getUniform(3));
            empManagement.addUniform(1, empManagement.getUniform(4));
            feeManagement.calculateCleaningCost(1);
        }
        
        printHtmlFooter(out);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void printHtmlHeader(PrintWriter out) throws IOException {
        out.println("<body>");
        out.println("<html>");
        out.println("<head><title>" + TITLE + "</title></head>");
        out.println("<center><h1>" + TITLE + "</h1></center>");
        out.println("<p>" + DESCRIPTION + "</p>");
        out.println("<hr/>");
        out.println("<form action=\"EmployeeServlet\" method=\"POST\">");

        out.println("<table><tbody>");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"ShowWriteLocks\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
