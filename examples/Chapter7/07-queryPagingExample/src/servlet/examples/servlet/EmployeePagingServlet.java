package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.stateful.ResultPager;

import examples.model.Employee;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
@EJB(name="ResultPager", beanInterface=ResultPager.class)
public class EmployeePagingServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 7: Query Paging Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates the use of query paging. </br>" +
        "The example allows you to browse over a set employees " +
        "by using the setFirstResult() and setMaxResults() api. ";

    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action == null) {
            out.println("<input name=\"action\" type=\"submit\" value=\"FindAll\"/>");
        } else {
            ResultPager pager = (ResultPager) request.getSession().getAttribute("pager");
            if (action.equals("FindAll")) {
                try {
                    pager = (ResultPager)
                        new InitialContext().lookup("java:comp/env/ResultPager");
                    request.getSession().setAttribute("pager", pager);
                } catch (Exception e) {
                    throw new ServletException(e);
                }
                pager.init(3, "countEmployees", "findAllEmployees");
                printCurrentPage(pager, out);
            } else if (action.equals("Next")) {
                pager.next();
                printCurrentPage(pager, out);
            } else if (action.equals("Previous")) {
                pager.previous();
                printCurrentPage(pager, out);
            } else if (action.equals("Finished")) {
                pager.finished();
                request.getSession().removeAttribute("pager");
                out.println("<input name=\"action\" type=\"submit\" value=\"FindAll\"/>");
            }
        }
        
        printHtmlFooter(out);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void printCurrentPage(ResultPager pager, PrintWriter out) {
        out.println("Employees:<br> ");
        for (Object o : pager.getCurrentResults()) {
            out.print(o + "<br/>");
        }
        
        out.println("<hr/>");
        out.println("Page " + (pager.getCurrentPage()+1) + " of " + pager.getMaxPages());
        out.println("<br>");
        out.print("<input name=\"action\" ");
        if (pager.getCurrentPage() == 0) {
            out.print(" disabled ");
        }
        out.print("type=\"submit\" value=\"Previous\"/>");
        
        out.print("<input name=\"action\" ");
        if (pager.getCurrentPage()+1 == pager.getMaxPages()) {
            out.print(" disabled ");
        }
        out.print("type=\"submit\" value=\"Next\"/>");
        out.println("<input name=\"action\" type=\"submit\" value=\"Finished\"/>");
    }
    
    private void printHtmlHeader(PrintWriter out) throws IOException {
        out.println("<body>");
        out.println("<html>");
        out.println("<head><title>" + TITLE + "</title></head>");
        out.println("<center><h1>" + TITLE + "</h1></center>");
        out.println("<p>" + DESCRIPTION + "</p>");
        out.println("<hr/>");
        out.println("<form action=\"EmployeeServlet\" method=\"POST\">");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</form>");
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
