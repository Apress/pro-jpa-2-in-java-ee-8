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
import examples.stateless.EmployeeService;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 6: Tx-scoped Persistence Context Example";
    
    private final String DESCRIPTION = 
        "This example shows the basic use of a tx-scoped persistence " +
        "context. The second create button shows what happens (an exception) when the " +
        "'logTransaction' operation is marked REQUIRES_NEW";

    @EJB EmployeeService service;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action == null) {
            // do nothing if no action requested
        } else if (action.equals("Create")) {
            Employee emp = new Employee();
            emp.setId(parseInt(request.getParameter("createId")));
            emp.setName(request.getParameter("name"));
            emp.setSalary(parseLong(request.getParameter("salary")));
            service.createEmployee(emp);
            out.println("Created " + emp);
        } else if (action.equals("Create2")) {
            Employee emp = new Employee();
            emp.setId(parseInt(request.getParameter("createId")));
            emp.setName(request.getParameter("name"));
            emp.setSalary(parseLong(request.getParameter("salary")));
            try {
                service.createEmployee2(emp);
                out.println("Created " + emp);
            } catch (Exception e) {
                out.println(e.getCause()+ "</br>");
            }
        }
        
        out.println("Employees: </br>");
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
        // form to create
        out.println("<h3>Create an Employee</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Id:</td><td><input type=\"text\" name=\"createId\"/>(int)</td></tr>");
        out.println("<tr><td>Name:</td><td><input type=\"text\" name=\"name\"/>(String)</td></tr>");
        out.println("<tr><td>Salary:</td><td><input type=\"text\" name=\"salary\"/>(long)</td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"Create\"/></td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"Create2\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
