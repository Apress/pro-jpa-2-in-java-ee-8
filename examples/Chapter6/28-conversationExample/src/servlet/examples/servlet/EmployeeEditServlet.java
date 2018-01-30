package examples.servlet;

import java.io.IOException;

import javax.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import examples.model.Employee;
import examples.stateful.EmployeeService;

@WebServlet(name="EmployeeEditServlet", 
            urlPatterns="/EmployeeEditServlet")
public class EmployeeEditServlet extends HttpServlet {
    
    @Inject EmployeeService bean;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter("saveAction");
    	if (action != null) {
            if (action.equals("Save All")) {
                bean.processAllChanges();
            } else if (action.equals("Abandon All")) {
                bean.abandonAllChanges();
            }
            request.setAttribute("employees", bean.findAll());
            getServletContext().getRequestDispatcher("/listEmployees.jsp")
                               .forward(request, response);
        } else {
            int id = parseInt(request.getParameter("id"));
            Employee emp = bean.loadEmployee(id);
            if (emp == null) {
                response.getWriter().println("An employee record  must be selected to edit. Press back button to continue.");
            } else {
                request.setAttribute("employee", emp);
                getServletContext().getRequestDispatcher("/editEmployee.jsp")
                                   .forward(request, response);
            }
        }
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
}
