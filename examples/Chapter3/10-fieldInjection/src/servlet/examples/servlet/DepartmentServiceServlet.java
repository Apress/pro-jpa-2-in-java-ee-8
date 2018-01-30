package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.stateless.DepartmentService;

@WebServlet(name="DepartmentServiceServlet", 
            urlPatterns="/DepartmentServiceServlet")
public class DepartmentServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 3: Field Injection Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates field injection of a dependency. </br>" +
        "Click the 'Audit' button.  This will trigger a servlet client that in turn " +
        "accesses another SLSB that was automatically injected into the field by " +
        "the container using field injection.";

    @EJB DepartmentService service;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);

        if (request.getParameter("action") != null) {
            out.println("Result: " + service.performAudit());
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
        out.println("</hr>");
        out.println("<form action=\"DepartmentServiceServlet\" method=\"POST\">");
        out.println("<input name=\"action\" type=\"submit\" value=\"Audit\"/>");
        out.println("</form>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
