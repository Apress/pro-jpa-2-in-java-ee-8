package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.model.Conversation;
import examples.model.Message;
import examples.stateless.ConversationMaintenance;

@WebServlet(name="MessageBoardServlet", 
            urlPatterns="/MessageBoardServlet")
public class MessageBoardServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 7: Queries and Transactions Example (MessageBoard)";
    
    private final String DESCRIPTION = 
        "This example demonstrates the use of FlushMode. </br>" +
        "The example allows you to archive Conversations for a message board that " +
        "are older than a given date.  Also displays all Conversations.";

    
    @EJB
    private ConversationMaintenance service;

    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("Archive")) {
                Calendar c = Calendar.getInstance();
                c.set(parseInt(request.getParameter("year")),
                      parseInt(request.getParameter("month"))-1, // month is zero-based
                      parseInt(request.getParameter("date")));
                service.archiveConversations(c.getTime());
            }
        }
        out.println("All Conversations:<br> ");
        printConversations(service.findAllConversations(), out);
        
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

    private void printConversations(Collection<Conversation> conversations, PrintWriter out) {
        for (Conversation c : conversations) {
            out.print(c + "<br/>");
            for (Message m : c.getMessages()) {
                out.print(" &nbsp; &nbsp; &nbsp; &nbsp;" + m + "<br/>");
                
            }
        }
    }
    
    private void printHtmlHeader(PrintWriter out) throws IOException {
        out.println("<body>");
        out.println("<html>");
        out.println("<head><title>" + TITLE + "</title></head>");
        out.println("<center><h1>" + TITLE + "</h1></center>");
        out.println("<p>" + DESCRIPTION + "</p>");
        out.println("<hr/>");
        out.println("<form action=\"MessageBoardServlet\" method=\"POST\">");
        // form find
        out.println("<h3>Archive Messages older than:</h3>");
        out.println("<input type=\"text\" size=\"2\" name=\"date\"/>-");
        out.println("<input type=\"text\" size=\"2\" name=\"month\"/>-");
        out.println("<input type=\"text\" size=\"4\" name=\"year\"/> day-month-year (xx-xx-xxxx)");
        out.println("<input name=\"action\" type=\"submit\" value=\"Archive\"/>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
