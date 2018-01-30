package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.stateful.EmployeeService;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
@EJB(name="ejb/EmployeeService", 
     beanInterface=EmployeeService.class)
public class EmployeeServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 12: Entity Refresh Example";
    
    private final String DESCRIPTION = 
        "This example shows an incorrect usage of the refresh API.";

    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        EmployeeService service = (EmployeeService) request.getSession().getAttribute("EmployeeService");
        if (service == null) {
            try {
                service = (EmployeeService)
                    new InitialContext().lookup("java:comp/env/ejb/EmployeeService");
                request.getSession().setAttribute("EmployeeService", service);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
            
        // process request
        String action = request.getParameter("action");
        if (action == null) {
            printInitAction(out, service);
        } else if (action.equals("ManageEmp")) {
            service.loadEmployee(parseInt(request.getParameter("empId")));
            printManagementActions(out, service);
        } else if (action.equals("DeductVacation")) {
            service.deductEmployeeVacation(parseInt(request.getParameter("vacation")));
            printManagementActions(out, service);
        } else if (action.equals("AdjustSalary")) {
            service.adjustEmployeeSalary(parseLong(request.getParameter("salary")));
            printManagementActions(out, service);
        } else if (action.equals("Finished")) {
            service.finished();
            request.getSession().removeAttribute("EmployeeService");
            printInitAction(out, service);
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
    
    private long parseLong(String longString) {
        try {
            return Long.parseLong(longString);
        } catch (NumberFormatException e) {
            return 0L;
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
    }
    
    private void printInitAction(PrintWriter out, EmployeeService service) {
        out.println("<form action=\"EmployeeServlet\" method=\"POST\">");
        out.println("<h3>Manage Employee</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Id:</td><td><input type=\"text\" name=\"empId\"/>(int)</td></tr>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"ManageEmp\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("</form>");
        out.println("<hr/>");
        out.println("Employees:<br>");
        printCollection(service.findAllEmployees(), out);
    }
    
    private void printManagementActions(PrintWriter out, EmployeeService service) {
        out.print("Managing " + service.getEmployee() + "<br>");

        out.println("<form action=\"EmployeeServlet\" method=\"POST\">");
        out.println("<h3>Deduct Employee Vacation</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Vacation:</td><td><input type=\"text\" name=\"vacation\"/>(int)</td>");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"DeductVacation\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        out.println("<h3>Adjust Employee Salary</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Salary:</td><td><input type=\"text\" name=\"salary\"/>(long)</td></tr>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"AdjustSalary\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"Finished\"/></td></tr>");
        out.println("</form>");
        out.println("<hr/>");
    }
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
