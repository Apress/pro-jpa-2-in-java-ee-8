package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import examples.model.Employee;
import examples.stateless.QueryService;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 7: Dynamic Named Query Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates making a dynamic query into a named query. </br>";

    @EJB
    private QueryService service;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("FindSalaryForNameAndDepartment")) {
                long salary = service.queryEmpSalary(
                        request.getParameter("deptName"),
                        request.getParameter("empName"));
                out.println("Found Salary: " + salary);
            } else if (action.equals("FindByPrimaryKey")) {
                Employee emp = service.findEmployeeByPrimaryKey(
                        parseInt(request.getParameter("id")));
                out.println("Found " + emp);
            } else if (action.equals("FindByName")) {
                Employee emp = service.findEmployeeByName(
                        request.getParameter("empName2"));
                out.println("Found " + emp);
            }
            out.println("<hr/>");
        }
        out.println("Find All Employees:<br> ");
        for (Employee emp : service.findAllEmployees()) {
            out.print(emp + "<br/>");
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
        out.println("<form action=\"EmployeeServlet\" method=\"POST\">");
        // form find
        out.println("<h3>Find Employee Salary</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Name:</td><td><input type=\"text\" name=\"empName\"/>(String)</td></tr>");
        out.println("<tr><td>Department Name:</td><td><input type=\"text\" name=\"deptName\"/>(String)</td></tr>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"FindSalaryForNameAndDepartment\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        out.println("<h3>Find By Primary Key</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Id:</td><td><input type=\"text\" name=\"id\"/>(int)</td></tr>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"FindByPrimaryKey\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        out.println("<h3>Find By Name</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Name:</td><td><input type=\"text\" name=\"empName2\"/>(String)</td></tr>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"FindByName\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
