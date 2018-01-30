package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.stateful.ShoppingCart;

@WebServlet(name="ShoppingCartServlet", 
            urlPatterns="/ShoppingCartServlet")
// declare a dependency on the ShoppingCart sfsb
@EJB(name="cart", beanInterface=ShoppingCart.class)
public class ShoppingCartServlet extends HttpServlet {
    
    private final String TITLE = 
        "Chapter 3: Container Managed Transaction (CMT) Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates the basic use of container managed transactions in an EJB. </br>" +
        "This will trigger a servlet client that invokes a SFSB that uses container managed transactions. " +
        "The SFSB methods use different TransactionAttributes on its methods to achieve different transaction semantics.";

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // create shopping cart if needed
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
        if (cart == null) {
            // lookup the ShoppingCart
            try {
                cart = (ShoppingCart)
                    new InitialContext().lookup("java:comp/env/cart");
                request.getSession().setAttribute("cart", cart);
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
            out.println("Order processed.");
            cart.process();
            request.getSession().removeAttribute("cart");
        } else if (request.getParameter("cancel") != null) {
            out.println("Order cancelled!");
            cart.cancel();
            request.getSession().removeAttribute("cart");
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
        for (String item : cart.getItems().keySet()) {
            out.println("<tr><td>Item:</td><td>" + item + "</td>");
            out.println(    "<td>Quantity:</td><td>" + cart.getItems().get(item) + "</td></tr>");
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
