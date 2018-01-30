package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.transaction.UserTransaction;

import examples.stateless.ProjectService;

@WebServlet(name="ProjectServlet", 
            urlPatterns="/ProjectServlet")
public class ProjectServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 3: UserTransaction Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates the basic use of a UserTransaction in a Servlet. </br>" +
        "Click the 'Go' button.  This will trigger a servlet client that uses " +
        "a UserTransaction to demarcate the transaction semantics for the EJBs upon " +
        "which it invokes.";

    @EJB
    ProjectService bean;
    @Resource UserTransaction tx;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        if (request.getParameter("action") != null) {
            int projectId = 1; //request.getParameter("projectId");
            int empId = 1; //request.getParameter("empId");
            try {
                try {
                    tx.begin();
                    bean.assignEmployeeToProject(projectId, empId);
                    bean.updateProjectStatistics();
                } finally {
                    tx.commit();
                }
            } catch (Exception e) {
                // handle all the tx.begin()/commit() exceptions
                throw new EJBException(e);
            }
            out.println("Update done");
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
        out.println("</hr>");
        out.println("<form action=\"ProjectServlet\" method=\"POST\">");
        out.println("<input name=\"action\" type=\"submit\" value=\"Go!\"/>");
        out.println("</form>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
