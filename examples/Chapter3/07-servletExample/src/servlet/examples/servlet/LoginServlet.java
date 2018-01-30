package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

@WebServlet(name="LoginServlet", 
            urlPatterns="/LoginServlet")
public class LoginServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 3: Servlet Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates the basics of defining and accessing a servlet and how it can maintain state. </br>" +
        "Enter a user id and click 'Go'. This will trigger a servlet that stores the user id " +
        "in the http session and prints it out. Note there is no persistence yet";


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        String userId = request.getParameter("user");
        HttpSession session = request.getSession();
        session.setAttribute("user", userId);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        printHtmlHeader(out);
        out.println("User id: " + session.getAttribute("user"));
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
        out.println("<form action=\"LoginServlet\" method=\"POST\">");
        out.println("<table><tbody>");
        out.println("<tr><td>User Id:</td><td><input type=\"text\" name=\"user\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<input name=\"action\" type=\"submit\" value=\"Go\"/>");
        out.println("</form>");
        out.println("<hr/>");
    }
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
