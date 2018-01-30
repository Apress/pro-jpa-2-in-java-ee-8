package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.model.Department;
import examples.model.Project;
import examples.stateless.ProjectService;

@WebServlet(name="ProjectServlet", 
            urlPatterns="/ProjectServlet")
public class ProjectServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 10: Using Embedded Id in Derived Identifiers Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates using an embedded identifier in a primary key that includes a relationship.";

    @EJB ProjectService service;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action == null) {
            // do nothing if no action requested
        } else if (action.equals("CreateDepartment")) {
            Department dept = service.createDepartment(
                    parseInt(request.getParameter("dNum")),
                    request.getParameter("dCountry"),
                    request.getParameter("dName"));
            out.print("Created " + dept + "<hr>");
        } else if (action.equals("CreateProject")) {
            Project proj = service.createProject(
                    request.getParameter("pName"),
                    parseInt(request.getParameter("pdNum")),
                    request.getParameter("pdCountry"));
            out.print("Created " + proj + "<hr>");
        }
        
        out.println("Project: </br>");
        for (Project p : service.findAllProjects()) {
            out.print(p + "<br/>");
        }   
        
        out.println("</br>Departments: </br>");
        for (Department d : service.findAllDepartments()) {
            out.print(d + " projCount: " + d.getProjects().size() + "<br/>");
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
        out.println("<form action=\"ProjectServlet\" method=\"POST\">");
        // form to create Dept
        out.println("<h3>Create a Department </h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Dept Number:</td><td><input type=\"text\" name=\"dNum\"/>(int)</td></tr>");
        out.println("<tr><td>Department Country:</td><td><input type=\"text\" name=\"dCountry\"/>(String)</td>");
        out.println("<tr><td>Department Name:</td><td><input type=\"text\" name=\"dName\"/>(String)</td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"CreateDepartment\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        // form to create Proj
        out.println("<h3>Create a Project </h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Project Name:</td><td><input type=\"text\" name=\"pName\"/>(String)</td></tr>");
        out.println("<tr><td>Dept Number:</td><td><input type=\"text\" name=\"pdNum\"/>(int)</td></tr>");
        out.println("<tr><td>Department Country:</td><td><input type=\"text\" name=\"pdCountry\"/>(String)</td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"CreateProject\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
