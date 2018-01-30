package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;

import javax.naming.InitialContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.stateful.ShoppingCart;
import examples.model.CustomerOrder;
import examples.model.OrderItem;

@WebServlet(name="ShoppingCartServlet", 
            urlPatterns="/ShoppingCartServlet")
@EJB(name="ejb/ShoppingCart", 
     beanInterface=ShoppingCart.class)
public class ShoppingCartServlet extends HttpServlet {

    ShoppingCart cart;
    
    private final String TITLE = 
        "Chapter 6: Unsynchronized Persistence Context Example";
    
    private final String DESCRIPTION = 
        "This example shows one way to use an unsynchronized persistence context.";

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        if (cart == null) {
            try {
                cart = (ShoppingCart) new InitialContext().lookup("java:comp/env/ejb/ShoppingCart");
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        
        // process request
        if (request.getParameter("add") != null) {
            cart.addItem(request.getParameter("item"), 
                         parseInt(request.getParameter("quantity")));
            printCurrentOrder(cart, out);
        } else if (request.getParameter("process") != null) {
            cart.process();
            cart = null;
            out.println("Order processed.");
        } else if (request.getParameter("cancel") != null) {
            cart.cancel();
            cart = null;
            out.println("Order cancelled!");
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

    private void printCurrentOrder(ShoppingCart cart, PrintWriter out) {
        out.println("<h3>Current Order:</h3>");
        out.println("<table><tbody>");
        for (OrderItem item : cart.getOrder().getItems()) {
            out.println("<tr><td>Item:</td><td>" + item.getName() + "</td>");
            out.println(    "<td>Quantity:</td><td>" + item.getQuantity() + "</td></tr>");
        }
        out.println("</tbody></table>");
    }
    
    private void printHtmlHeader(PrintWriter out) throws IOException {
        out.println("<body>");
        out.println("<html>");
        out.println("<head><title>" + TITLE + "</title></head>");
        out.println("<center><h1>" + TITLE + "</h1></center>");
        out.println("<p>" + DESCRIPTION + "</p>");
        out.println("<hr/>");
        out.println("<form action=\"ShoppingCartServlet\" method=\"POST\">");
        out.println("<table><tbody>");
        out.println("<tr><td>Item:</td><td><input type=\"text\" name=\"item\"/>(String)</td></tr>");
        out.println("<tr><td>Quantity:</td><td><input type=\"text\" name=\"quantity\"/>(int)</td></tr>");
        out.println("</tbody></table>");
        out.println("<input name=\"add\" type=\"submit\" value=\"Add Item\"/>");
        out.println("<input name=\"process\" type=\"submit\" value=\"Process Order\"/>");
        out.println("<input name=\"cancel\" type=\"submit\" value=\"Cancel\"/>");
        out.println("</form>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
