package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import examples.model.Company;
import examples.model.Employee;
import examples.model.Address;
import examples.stateless.EmployeeService;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 4: Sharing Embedded Object Types Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates the basics of sharing embedded object types between entities.";

    @EJB EmployeeService service;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action == null) {
            // do nothing if no action requested
        } else if (action.equals("CreateEmployee")) {
            service.createEmployeeAndAddress(
                    parseInt(request.getParameter("empId")),
                    request.getParameter("name"),
                    parseLong(request.getParameter("salary")),
                    request.getParameter("street"),
                    request.getParameter("city"),
                    request.getParameter("state"),
                    request.getParameter("zip"));
        } else if (action.equals("CreateCompany")) {
            service.createCompanyAndAddress(
                    parseInt(request.getParameter("cId")),
                    request.getParameter("cStreet"),
                    request.getParameter("cCity"),
                    request.getParameter("cState"),
                    request.getParameter("cZip"));
        }
        
        out.println("Employees and Addresses: </br>");
        for (Employee emp : service.findAllEmployees()) {
            out.print(emp + "<br/>");
        }
        
        out.println("<br/>Companies and Addresses: </br>");
        for (Company c : service.findAllCompanies()) {
            out.print(c + "<br/>");
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
        // form to create and Employee and Address
        out.println("<h3>Create an Employee and Address</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Id:</td><td><input type=\"text\" name=\"empId\"/>(int)</td></tr>");
        out.println("<tr><td>Employee Name:</td><td><input type=\"text\" name=\"name\"/>(String)</td></tr>");
        out.println("<tr><td>Employee Salary:</td><td><input type=\"text\" name=\"salary\"/>(long)</td></tr>");
        out.println("<tr><td>Address Street:</td><td><input type=\"text\" name=\"street\"/>(String)</td></tr>");
        out.println("<tr><td>Address City:</td><td><input type=\"text\" name=\"city\"/>(String)</td></tr>");
        out.println("<tr><td>Address State:</td><td><input type=\"text\" name=\"state\"/>(String)</td></tr>");
        out.println("<tr><td>Address Zip:</td><td><input type=\"text\" name=\"zip\"/>(String)</td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"CreateEmployee\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        
        out.println("<h3>Create a Company and Address</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Company Id:</td><td><input type=\"text\" name=\"cId\"/>(int)</td></tr>");
        out.println("<tr><td>Address Street:</td><td><input type=\"text\" name=\"cStreet\"/>(String)</td></tr>");
        out.println("<tr><td>Address City:</td><td><input type=\"text\" name=\"cCity\"/>(String)</td></tr>");
        out.println("<tr><td>Address State:</td><td><input type=\"text\" name=\"cState\"/>(String)</td></tr>");
        out.println("<tr><td>Address Zip:</td><td><input type=\"text\" name=\"cZip\"/>(String)</td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"CreateCompany\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
