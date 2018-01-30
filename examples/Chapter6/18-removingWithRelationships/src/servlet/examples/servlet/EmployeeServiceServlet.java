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

import examples.stateless.EmployeeService;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 6: Removing Entities with Relationships Example";
    
    private final String DESCRIPTION = 
        "This example shows how to remove an entity with relationships.";

    
    @EJB
    private EmployeeService empService;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("Remove")) {
                empService.removeParkingSpace(
                        parseInt(request.getParameter("empId")));
            } else if (action.equals("RemoveWithFailure")) {
                try {
                    empService.removeParkingSpaceWithFailure(
                            parseInt(request.getParameter("empId2")));
                } catch (Exception e) {
                    out.print("Remove caused exception: " + e);
                    out.print("<br>See log for details<br><br>");
                }
            }
        }

        out.println("Employees:<br> ");
        printCollection(empService.findAllEmployees(), out);

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
        out.println("<h3>Remove Employee Parking Space</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Id:</td><td><input type=\"text\" name=\"empId\"/>(int)</td>");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"Remove\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        out.println("<form action=\"EmployeeServlet\" method=\"POST\">");
        out.println("<h3>Remove Employee Parking Space (causes DB constraint failure)</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Id:</td><td><input type=\"text\" name=\"empId2\"/>(int)</td>");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"RemoveWithFailure\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
