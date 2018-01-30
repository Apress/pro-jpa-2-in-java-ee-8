package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.ejb.EJB;

import examples.stateful.OrderBrowser;

@WebServlet(name="OrderBrowserServlet", 
            urlPatterns="/OrderBrowserServlet")
@EJB(name="ejb/OrderBrowser", beanInterface=OrderBrowser.class)
public class OrderBrowserServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 3: Stateful Session Bean Lifecycle Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates the basic use of lifecycle callbacks to " +
        "initialize/cleanup a Stateful Session Bean. </br>" +
        "NOTE: The example doesn't show the details of issuing/parsing the jdbc to create " +
        "orders, hence no orders are displayed. See the code to see how to use the lifecycle callbacks.";


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // create order browser if needed
        OrderBrowser browser = (OrderBrowser) request.getSession().getAttribute("browser");
        if (browser == null) {
            // lookup the OrderBrowser
            try {
                browser = (OrderBrowser)
                    new InitialContext().lookup("java:comp/env/ejb/OrderBrowser");
                request.getSession().setAttribute("browser", browser);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        
        // process request
        if (request.getParameter("list") != null) {
            out.println("Orders: " + browser.listOrders());
        }
        
        printHtmlFooter(out);
    }
    
    private void printHtmlHeader(PrintWriter out) throws IOException {
        out.println("<body>");
        out.println("<html>");
        out.println("<head><title>" + TITLE + "</title></head>");
        out.println("<center><h1>" + TITLE + "</h1></center>");
        out.println("<p>" + DESCRIPTION + "</p>");
        out.println("<hr/>");
        out.println("<form action=\"OrderBrowserServlet\" method=\"GET\">");
        out.println("<input name=\"list\" type=\"submit\" value=\"List Orders\"/>");
        out.println("</form>");
    }
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
