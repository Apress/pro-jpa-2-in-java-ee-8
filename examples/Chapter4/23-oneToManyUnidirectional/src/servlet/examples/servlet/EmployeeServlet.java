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

import examples.model.Employee;
import examples.model.Phone;
import examples.stateless.EmployeeService;
import examples.stateless.PhoneService;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 4: One-to-Many Unidirectional Example";
    
    private final String DESCRIPTION = 
        "This example demonstates how to specify one-to-many unidirectional relationships for entities.</br> " +
        "It allows you to create/find employees & phones and associate them.";

    
    @EJB EmployeeService empService;
    @EJB PhoneService phoneService;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action == null) {
            // do nothing if no action requested
        } else if (action.equals("CreateEmp")) {
            Employee emp = empService.createEmployee(
                    request.getParameter("empName"),
                    parseLong(request.getParameter("salary")));
            out.println("Created " + emp);
        } else if (action.equals("CreatePhone")) {
            Phone phone = phoneService.createPhone(
                    request.getParameter("phoneNum"),
                    request.getParameter("phoneType"));
            out.println("Created " + phone);
        } else if (action.equals("addEmpPhone")) {
            Employee emp = empService.addEmployeePhone(
                    parseInt(request.getParameter("empId")),
                    parseInt(request.getParameter("phoneId")));
            out.println("Updated " + emp + "</br>");
        } else if (action.equals("FindAll")) {
            Collection<Employee> emps = empService.findAllEmployees();
            if (emps.isEmpty()) {
                out.println("No Employees found ");
            } else {
                out.println("Found Employees: </br>");
                for (Employee emp : emps) {
                    out.println(emp + "<br/>");
                }
            }
            
            out.println("</br>");
            Collection<Phone> phones = phoneService.findAllPhones();
            if (phones.isEmpty()) {
                out.println("No Phones found ");
            } else {
                out.println("Found Phones: </br>");
                for (Phone phone : phones) {
                    out.println(phone + "<br/>");
                }
            }
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
        out.println("<h3>Create an Employee</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Name:</td><td><input type=\"text\" name=\"empName\"/>(String)</td></tr>");
        out.println("<tr><td>Employee Salary:</td><td><input type=\"text\" name=\"salary\"/>(long)</td></tr>");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"CreateEmp\"/></td></tr>");
        out.println("</tbody></table>");

        out.println("<hr/>");
        out.println("<h3>Create a Phone</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Phone No:</td><td><input type=\"text\" name=\"phoneNum\"/>(String)</td></tr>");
        out.println("<tr><td>Phone Type:</td><td><input type=\"text\" name=\"phoneType\"/>(String)</td></tr>");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"CreatePhone\"/></td></tr>");
        out.println("</tbody></table>");
        
        out.println("<hr/>");
        out.println("<h3>Add a Phone to an Employee</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Emp Id:</td><td><input type=\"text\" name=\"empId\"/>(int)</td></tr>");
        out.println("<tr><td>Phone Id:</td><td><input type=\"text\" name=\"phoneId\"/>(int)</td></tr>");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"addEmpPhone\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        // form to find all
        out.println("<h3>Find all Employees & Phones</h3>");
        out.println("<input name=\"action\" type=\"submit\" value=\"FindAll\"/>");
        out.println("</form>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
