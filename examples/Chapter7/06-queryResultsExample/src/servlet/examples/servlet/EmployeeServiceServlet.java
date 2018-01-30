package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.EmpMenu;
import examples.model.Employee;
import examples.model.Project;
import examples.stateless.QueryService;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 7: Query Results Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates a couple of ways to get query results. </br>" +
        "It shows execution of a query outside of a transaction and how to get and " +
        "deal with various result types.";

    
    @EJB
    private QueryService service;
        
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("FindDepartmentsDetached")) {
                List result = service.findAllDepartmentsDetached();
                out.println("Found Departments: </br>");
                for (Object dept : result) {
                    out.print(dept + "<br/>");
                }
            } else if (action.equals("DisplayProjectEmployees")) {
                List result = service.findProjectEmployees(
                        request.getParameter("projName"));
                out.println("Found Employees: </br>");
                printEmployees(result, out);
            } else if (action.equals("DisplayProjectEmployeesUsingConstructor")) {
                List result = service.findProjectEmployeesWithConstructor(
                        request.getParameter("projName2"));
                out.println("Found Employees: </br>");
                printEmployees2(result, out);
            }
            out.println("<hr/>");
        }
        out.println("All Employees:<br> ");
        for (Employee emp : service.findAllEmployees()) {
            out.print(emp + "<br/>");
        }
        out.println("<br>All Projects:<br> ");
        for (Project proj : service.findAllProjects()) {
            out.print(proj + "<br/>");
        }
        
        printHtmlFooter(out);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    private void printEmployees(List result, PrintWriter out) {
        int count = 0;
        for (Iterator i = result.iterator(); i.hasNext();) {
            Object[] values = (Object[]) i.next();
            out.print(++count + ": " + values[0] + ", " + values[1] + "</br>");
        }
     }
    
    private void printEmployees2(List result, PrintWriter out) {
        int count = 0;
        for (Iterator i = result.iterator(); i.hasNext();) {
            EmpMenu menu = (EmpMenu) i.next();
            out.print(++count + ": " + menu.getEmployeeName() + ", " + 
                               menu.getDepartmentName() + "</br>");
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
        // form find
        out.println("<h3>Find Departments Detached (query outside a transaction)</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"FindDepartmentsDetached\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        out.println("<h3>Find Employees for Project (multiple result types)</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Project Name:</td><td><input type=\"text\" name=\"projName\"/>(String)</td></tr>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"DisplayProjectEmployees\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        out.println("<h3>Find Employees for Project Using Constructor</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Project Name:</td><td><input type=\"text\" name=\"projName2\"/>(String)</td></tr>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"DisplayProjectEmployeesUsingConstructor\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
