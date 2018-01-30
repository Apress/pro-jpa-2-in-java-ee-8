package examples.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import examples.model.Employee;
import examples.stateful.EmployeeEdit;
import examples.stateless.EmployeeService;

@WebServlet(name="EmployeeUpdateServlet", 
            urlPatterns="/EmployeeUpdateServlet")
@EJB(name="EmployeeEdit", beanInterface=EmployeeEdit.class)
public class EmployeeUpdateServlet extends HttpServlet {
    @EJB EmployeeService empService;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        EmployeeEdit bean = (EmployeeEdit) session.getAttribute("employee.edit");

        String action = request.getParameter("action");
        if (action.equals("Save") || action.equals("Update")) {
            String name = request.getParameter("name");
            long salary = parseLong(request.getParameter("salary"));
            Employee emp = bean.getEmployee();
            emp.setName(name);
            emp.setSalary(salary);
            if (action.equals("Save")) {
                bean.save();
            } else {
                request.setAttribute("employee", bean.getEmployee());
                getServletContext().getRequestDispatcher("/editEmployee.jsp")
                                   .forward(request, response);
                return;
            }
        } else if (action.equals("Cancel")) {
            bean.cancel();
        } else if (action.equals("Revert")) {
            bean.revertEmployee();
            request.setAttribute("employee", bean.getEmployee());
            getServletContext().getRequestDispatcher("/editEmployee.jsp")
                               .forward(request, response);
            return;
        }

        session.removeAttribute("employee.edit");

        request.setAttribute("employees", empService.findAll());
        getServletContext().getRequestDispatcher("/listEmployees.jsp")
                           .forward(request, response);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    private long parseLong(String longString) {
        try {
            return Long.parseLong(longString);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
