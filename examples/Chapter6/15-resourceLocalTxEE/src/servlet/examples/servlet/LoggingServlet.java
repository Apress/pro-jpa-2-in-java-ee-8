package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import examples.stateless.LogService;

@WebServlet(name="LoggingServlet", 
            urlPatterns="/LoggingServlet")
public class LoggingServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 6: Resource-Local Transaction in EE Example";
    
    private final String DESCRIPTION = 
        "This example shows the basic use of an application managed " +
        "EntityManager with ResourceLocal transactions in EE.";

    
    @EJB
    private LogService service;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action != null) {
            int id = parseInt(request.getParameter("id"));
            String access = request.getParameter("access");
            service.logAccess(id, access);
            out.print("Logged id: " + id + " access: " + access + "</br>");
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

    private void printHtmlHeader(PrintWriter out) throws IOException {
        out.println("<body>");
        out.println("<html>");
        out.println("<head><title>" + TITLE + "</title></head>");
        out.println("<center><h1>" + TITLE + "</h1></center>");
        out.println("<p>" + DESCRIPTION + "</p>");
        out.println("<hr/>");

        out.println("<form action=\"LoggingServlet\" method=\"POST\">");
        out.println("<h3>Log Access</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Id:</td><td><input type=\"text\" name=\"id\"/>(int)</td></tr>");
        out.println("<tr><td>Action:</td><td><input type=\"text\" name=\"access\"/>(String)</td>");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"Log\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
