package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.model.Department;
import examples.model.Employee;
import examples.stateless.EmployeeService;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 7: Parameter Types Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates the basic use of various parameter types. </br>" +
        "The example allows you to execute the queries from this section of the book " +
        "and see how types like primitives and temporals are used.";

    
    @EJB
    private EmployeeService service;
        
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("FindEmployeesAboveSal")) {
                Department d = new Department();
                d.setId(parseInt(request.getParameter("deptId")));
                Collection c = service.findEmployeesAboveSal(
                        d, parseLong(request.getParameter("minSal")));
                out.println("Found Employees: </br>");
                printCollection(c, out);
            } else if (action.equals("FindEmployeesHiredDuringPeriod")) {
                Calendar start = Calendar.getInstance();
                start.set(parseInt(request.getParameter("startYear")),
                          parseInt(request.getParameter("startMonth"))-1, // month is zero-based
                          parseInt(request.getParameter("startDate")));
                Calendar end = Calendar.getInstance();
                end.set(parseInt(request.getParameter("endYear")),
                        parseInt(request.getParameter("endMonth"))-1, // month is zero-based
                        parseInt(request.getParameter("endDate")));
                Collection c = service.findEmployeesHiredDuringPeriod(
                        start.getTime(), end.getTime());
                out.println("Found Employees: </br>");
                printCollection(c, out);
            } else if (action.equals("FindHighestPaidByDepartment")) {
                Department d = new Department();
                d.setId(parseInt(request.getParameter("deptId2")));
                Employee emp = service.findHighestPaidByDepartment(d);
                out.println("Found " + emp);
            }
            out.println("<hr/>");
        }
        out.println("Find All Employees:<br> ");
        printCollection(service.findAllEmployees(), out);
        
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
        // form find
        out.println("<h3>Find Employees above Salary</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Department Id:</td><td><input type=\"text\" name=\"deptId\"/>(int)</td></tr>");
        out.println("<tr><td>Min Salary:</td><td><input type=\"text\" name=\"minSal\"/>(long)</td></tr>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"FindEmployeesAboveSal\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        out.println("<h3>Find Employees hired during period</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Start Date:</td><td>");
        out.println("<input type=\"text\" size=\"2\" name=\"startDate\"/>-");
        out.println("<input type=\"text\" size=\"2\" name=\"startMonth\"/>-");
        out.println("<input type=\"text\" size=\"4\" name=\"startYear\"/> day-month-year (xx-xx-xxxx)");
        out.println("</td></tr>");
        out.println("<tr><td>End Date:</td><td>");
        out.println("<input type=\"text\" size=\"2\" name=\"endDate\"/>-");
        out.println("<input type=\"text\" size=\"2\" name=\"endMonth\"/>-");
        out.println("<input type=\"text\" size=\"4\" name=\"endYear\"/> day-month-year (xx-xx-xxxx)");
        out.println("</td></tr>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"FindEmployeesHiredDuringPeriod\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        out.println("<h3>Find Highest paid by Department</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Department Id:</td><td><input type=\"text\" name=\"deptId2\"/>(int)</td></tr>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"FindHighestPaidByDepartment\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
