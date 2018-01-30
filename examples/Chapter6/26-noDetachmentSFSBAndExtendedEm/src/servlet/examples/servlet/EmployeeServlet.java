package examples.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import examples.stateful.EmployeeQuery;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
@EJB(name="queryBean", beanInterface=EmployeeQuery.class)
public class EmployeeServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmployeeQuery bean = createQueryBean();
        try {
            List emps = bean.findAll();
            request.setAttribute("employees", emps);
            getServletContext().getRequestDispatcher("/listEmployees.jsp")
                               .forward(request, response);
        } finally {
            bean.finished();
        }
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    private EmployeeQuery createQueryBean() throws ServletException {
        // look up queryBean
        try {
            return (EmployeeQuery)
                new InitialContext().lookup("java:comp/env/queryBean");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
