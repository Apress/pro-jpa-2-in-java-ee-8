package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.annotation.Resource;
import javax.transaction.UserTransaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import examples.model.Employee;
import examples.model.EmployeeService;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 6: Sharing Application Managed EntityManagers Example";
    
    private final String DESCRIPTION = 
        "This example shows a brief example of how to share an applicaton " +
        "managed entity manager between components.";
    
    @PersistenceUnit(unitName="EmployeeService")
    EntityManagerFactory emf;
    @Resource UserTransaction tx;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        String action = request.getParameter("action");
        if (action == null) {
            // do nothing if no action requested
            printHtmlFooter(out);
            return;
        }
        
        EntityManager em = null;
        try {
            tx.begin();
            em = emf.createEntityManager();
            EmployeeService service = new EmployeeService(em);
            // process request
            if (action.equals("Create")) {
                Employee emp = service.createEmployee(
                        parseInt(request.getParameter("createId")),
                        request.getParameter("name"),
                        parseLong(request.getParameter("salary")));
                out.println("Created " + emp);
            } else if (action.equals("Remove")) {
                String id = request.getParameter("removeId");
                service.removeEmployee(parseInt(id));
                out.println("Removed Employee with id: " + id);
            } else if (action.equals("Update")) {
                String id = request.getParameter("raiseId");
                Employee emp = service.raiseEmployeeSalary(
                        parseInt(id),
                        parseLong(request.getParameter("raise")));
                out.println("Updated " + emp);
            } else if (action.equals("Find")) {
                Employee emp = service.findEmployee(
                        parseInt(request.getParameter("findId")));
                out.println("Found " + emp);
            } else if (action.equals("FindAll")) {
                Collection<Employee> emps = service.findAllEmployees();
                if (emps.isEmpty()) {
                    out.println("No Employees found ");
                } else {
                    out.println("Found Employees: </br>");
                    for (Employee emp : emps) {
                        out.print(emp + "<br/>");
                    }
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            em.close();
            try {
                tx.commit();
            } catch (Exception e) {
                throw new ServletException(e);
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
        // form to create
        out.println("<h3>Create an Employee</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Id:</td><td><input type=\"text\" name=\"createId\"/>(int)</td></tr>");
        out.println("<tr><td>Name:</td><td><input type=\"text\" name=\"name\"/>(String)</td></tr>");
        out.println("<tr><td>Salary:</td><td><input type=\"text\" name=\"salary\"/>(long)</td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"Create\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        // form to remove
        out.println("<h3>Remove an Employee</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Id:</td><td><input type=\"text\" name=\"removeId\"/>(int)</td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"Remove\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        // form to update
        out.println("<h3>Update an Employee</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Id:</td><td><input type=\"text\" name=\"raiseId\"/>(int)</td></tr>");
        out.println("<tr><td>Raise:</td><td><input type=\"text\" name=\"raise\"/>(long)</td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"Update\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        // form to find
        out.println("<h3>Find an Employee</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Id:</td><td><input type=\"text\" name=\"findId\"/>(int)</td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"Find\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        // form to find all
        out.println("<h3>Find all Employees</h3>");
        out.println("<input name=\"action\" type=\"submit\" value=\"FindAll\"/>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
