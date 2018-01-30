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

import examples.stateless.EmployeeService;
import examples.model.Employee;
import examples.model.Department;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 10: Converting Attribute State Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates how converters can be used to convert the state of different kinds of attributes.";
    
    @EJB
    private EmployeeService service;
        
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("PrintEntities")) {
                out.println("All Departments: </br>");
                for (Department dep : service.findAllDepartments()) {
                    out.print(dep.toString() + "<br/>");
                }
                out.println("All Employees: </br>");
                for (Employee emp : service.findAllEmployees()) {
                    out.print(emp + "<br/>");
                }
            } else if (action.equals("PrintRawDatabaseData")) {
                out.println("Department Data: </br>");
                service.printRawDepartmentData(out);
                out.println("Employee Data: </br>");
                service.printRawEmployeeData(out);
            }
            out.println("<hr/>");
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
        out.println("<hr/>");
        out.println("<form action=\"EmployeeServlet\" method=\"POST\">");
        // form find
        out.println("<h3>Display Department and Employee Entities</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"PrintEntities\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        out.println("<h3>Display Department and Employee Raw Data</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"PrintRawDatabaseData\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
