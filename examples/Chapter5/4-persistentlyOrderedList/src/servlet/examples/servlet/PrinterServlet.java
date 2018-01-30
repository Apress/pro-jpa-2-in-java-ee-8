package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import examples.model.PrintQueue;
import examples.model.PrintJob;
import examples.stateless.PrintService;

@WebServlet(name="PrinterServlet", 
            urlPatterns="/PrinterServlet")
public class PrinterServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 5: Persistently Ordered List Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates how to work with Lists that maintain their order in the database.</br> ";
    
    @EJB PrintService printService;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action == null) {
            // do nothing if no action requested
        } else if (action.equals("CreatePrinter")) {
            PrintQueue printer = printService.createPrinter(
                    request.getParameter("pName"));
            out.println("Created " + printer);
        } else if (action.equals("AddToQueue")) {
            PrintJob job = printService.createPrintJob(
                    parseInt(request.getParameter("jobId")),
                    request.getParameter("schedName"));
            if (job != null)
                out.println("Sent job " + job.getId() + " to printer queue " + job.getQueue().getName());
        } else if (action.equals("CancelFromQueue")) {
            PrintJob job = printService.removePrintJob(
                    parseInt(request.getParameter("xjobId")),
                    request.getParameter("cancelName"));
            out.println("Removed job " + job.getId() + " from printer queue " + request.getParameter("cancelName"));
        } else if (action.equals("ListQueues")) {
            List<PrintQueue> queues = printService.listAllPrintQueues();
            if (queues.isEmpty()) {
                out.println("No printers");
            } else {
                out.println("Printers: </br>");
                for (PrintQueue q : queues) {
                    out.println("Print Queue: " + q.getName() + "<br/>");
                }
            }
            out.println("</br>");
        } else if (action.equals("ListJobs")) {
            List<PrintJob> jobs = printService.listAllJobs(
                    request.getParameter("listName"));
            if (jobs.isEmpty()) {
                out.println("No jobs queued ");
            } else {
                out.println("Jobs queued: </br>");
                for (PrintJob job : jobs) {
                    out.println("Job " + job.getId() + "<br/>");
                }
            }
            out.println("</br>");
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
        out.println("<form action=\"PrinterServlet\" method=\"POST\">");
        // form to create a Printer
        out.println("<h3>Create a Printer</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Printer Name:</td><td><input type=\"text\" name=\"pName\"/>(String)</td></tr>");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"CreatePrinter\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        // form to enqueue a job
        out.println("<h3>Schedule Print Job</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Job Id:</td><td><input type=\"text\" name=\"jobId\"/>(int)</td></tr>");
        out.println("<tr><td>Printer Name:</td><td><input type=\"text\" name=\"schedName\"/>(String)</td></tr>");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"AddToQueue\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        // form to cancel a job        
        out.println("<h3>Cancel Print Job</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Job Id:</td><td><input type=\"text\" name=\"xjobId\"/>(int)</td></tr>");
        out.println("<tr><td>Printer Name:</td><td><input type=\"text\" name=\"cancelName\"/>(String)</td></tr>");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"CancelFromQueue\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        // form to list queues
        out.println("<h3>List all Print Queues</h3>");
        out.println("<table><tbody>");
        out.println("<input name=\"action\" type=\"submit\" value=\"ListQueues\"/>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        // form to find jobs
        out.println("<h3>List all Jobs in a Queue</h3>");
        out.println("<tr><td>Printer Name:</td><td><input type=\"text\" name=\"listName\"/>(String)</td></tr>");
        out.println("<input name=\"action\" type=\"submit\" value=\"ListJobs\"/>");
        out.println("</form>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
