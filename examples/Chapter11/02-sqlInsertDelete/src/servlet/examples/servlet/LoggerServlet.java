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

import examples.stateless.Logger;

@WebServlet(name="LoggerServlet", 
            urlPatterns="/LoggerServlet")
public class LoggerServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 11: Sql INSERT/DELETE Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates the basic usage of native " +
        "queries for INSERT/DELETE operations.";

    
    @EJB
    Logger logger;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action == null) {
            // do nothing if no action requested
        } else if (action.equals("Log")) {
            logger.logMessage(request.getParameter("message"));
        } else if (action.equals("Clear")) {
            logger.clearMessageLog();
        }
        out.println("<br/>All Messages: <br/>");
        printCollection(logger.findAllMessages(), out);
        
        printHtmlFooter(out);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
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
        out.println("<form action=\"LoggerServlet\" method=\"POST\">");
        // form to create and Employee and Department
        out.println("<h3>Log Message:</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Message:</td><td><input type=\"text\" name=\"message\"/>(String)</td></tr>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"Log\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        out.println("<h3>Clear All Messages:</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"Clear\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
