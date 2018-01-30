package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.model.Stock;
import examples.stateless.StockService;

@WebServlet(name="StockServlet", 
            urlPatterns="/StockServlet")
public class StockServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 12: Cache Mode Properties Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates the a way to use dyanamic cache mode properties.";

    @EJB StockService service;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action == null) {
            // do nothing if no action requested
        } else if (action.equals("List")) {
            String floorString = request.getParameter("threshold");
            if (floorString.length() == 0) {
                out.println("All Stocks: </br>");
                for (Stock stock : service.findAllStocks()) {
                    out.print(stock + "<br/>");
                }
            } else {
                double floor = new Double(floorString);
                out.println("Stocks over: " + floor + "</br></br>");
                for (Stock stock : service.findExpensiveStocks(floor)) {
                    out.print(stock + "<br/>");
                }
            }
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
        out.println("<hr/>");
        out.println("<form action=\"StockServlet\" method=\"POST\">");
        out.println("<h3>List Stocks</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Threshold:</td><td><input type=\"text\" name=\"threshold\"/>(int)</td></tr>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"List\"/></td></tr> ");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
