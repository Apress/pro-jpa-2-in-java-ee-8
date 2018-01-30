package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.model.Employee;
import examples.model.Project;
import examples.stateless.SearchService;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 9: Employee Search Service using Criteria Subquery Example";
    
    private final String DESCRIPTION = 
        "This example allows you to search for employees using one or more query criteria." +
        " The project criterium uses a subquery.";

    
    // inject a reference to the SearchService slsb
    @EJB SearchService service;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action == null) {
            // do nothing if no action requested
        } else if (action.equals("FindEmployees")) {
            String name = request.getParameter("name");
            if (name.equals("")) name = null;
            String dept = request.getParameter("dept");
            if (dept.equals("")) dept = null;
			String project = request.getParameter("project");
            if (project.equals("")) project = null;
			String city = request.getParameter("city");
            if (city.equals("")) city = null;

            List<Employee> emps = service.findEmployees(name, dept, project, city);

            out.println("<hr/>");
            if (emps.isEmpty()) {
                out.println("<br/>No Employees found ");
            } else {
                out.println("<br/>Found Employees:<br/>");
                for (Employee emp : emps) {
                    out.print(emp + "<br/>");
                }
            }
        }
        
        // show all Employees
        out.println("<hr/><br/>");
        out.println("All Employees: ");
        List<Employee> emps = service.findAllEmployees();
        for (Employee emp : emps) {
            out.println("<br/>" + emp);
        }
        out.println("<hr/>");

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

    private void printHtmlHeader(PrintWriter out) throws IOException {
        out.println("<body>");
        out.println("<html>");
        out.println("<head><title>" + TITLE + "</title></head>");
        out.println("<center><h1>" + TITLE + "</h1></center>");
        out.println("<p>" + DESCRIPTION + "</p>");
        out.println("<hr/>");
        out.println("<form action=\"EmployeeServlet\" method=\"POST\">");

        // form to find Employees
        out.println("<h3>Find Employees</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Name:</td><td><input type=\"text\" name=\"name\"/>(String)</td></tr>");
        out.println("<tr><td>DeptName:</td><td><input type=\"text\" name=\"dept\" />(String)</td></tr>");
        out.println("<tr><td>ProjectName:</td><td><input type=\"text\" name=\"project\" />(String)</td></tr>");
        out.println("<tr><td>City:</td><td><input type=\"text\" name=\"city\" />(String)</td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"FindEmployees\"/></td></tr>");
        out.println("</tbody></table>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
