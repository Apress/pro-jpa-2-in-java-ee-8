package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import javax.servlet.annotation.WebServlet;


@WebServlet(name="ClientServlet", 
            urlPatterns="/ClientServlet")
public class ClientServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 8: Query Language";

    @PersistenceUnit(unitName="jpqlExamples")
    private EntityManagerFactory emf;
    
    @Resource
    private UserTransaction tx;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);

        
        // check the requested query type and execute
        String query = request.getParameter("query");
        if (query.equals("dynamic")) {
            String queryString = request.getParameter("queryString");
            executeAndPrintQuery(queryString, out);
        } else if (query.equals("SELECT1")) {
            executeAndPrintQuery("SELECT e " +
                                 "FROM Employee e", out);
        } else if (query.equals("SELECT2")) {
            executeAndPrintQuery("SELECT d  " +
                                 "FROM Department d", out);
        } else if (query.equals("SELECT3")) {
            executeAndPrintQuery("SELECT OBJECT(d)  " +
                                 "FROM Department d", out);
        } else if (query.equals("SELECT4")) {
            executeAndPrintQuery("SELECT e.name  " +
                                 "FROM Employee e", out);
        } else if (query.equals("SELECT5")) {
            executeAndPrintQuery("SELECT e.department  " +
                                 "FROM Employee e", out);
        } else if (query.equals("SELECT6")) {
            executeAndPrintQuery("SELECT DISTINCT e.department  " +
                                 "FROM Employee e", out);
        } else if (query.equals("SELECT7")) {
            executeAndPrintQuery("SELECT d.employees  " +
                                 "FROM Department d", out);
        } else if (query.equals("SELECT8")) {
            executeAndPrintQuery("SELECT e.name, e.salary  " +
                                 "FROM Employee e", out);
        } else if (query.equals("SELECT9")) {
            executeAndPrintQuery("SELECT NEW examples.model.EmployeeDetails(e.name, e.salary, e.department.name)  " +
                                 "FROM Employee e", out);
        } else if (query.equals("SELECT10")) {
            executeAndPrintQuery("SELECT p  " +
                                 "FROM Project p " +
                                 "WHERE p.employees IS NOT EMPTY", out);
        } else if (query.equals("FROM1")) {
            executeAndPrintQuery("SELECT p  " +
                                 "FROM Employee e JOIN e.phones p", out);
        } else if (query.equals("FROM2")) {
            executeAndPrintQuery("SELECT DISTINCT p  " +
                                 "FROM Employee e, IN(e.phones) p", out);
        } else if (query.equals("FROM3")) {
            executeAndPrintQuery("SELECT p.number  " +
                                 "FROM Employee e JOIN e.phones p", out);
        } else if (query.equals("FROM4")) {
            executeAndPrintQuery("SELECT d  " +
                                 "FROM Employee e JOIN e.department d", out);
        } else if (query.equals("FROM5")) {
            executeAndPrintQuery("SELECT e.department  " +
                                 "FROM Employee e", out);
        } else if (query.equals("FROM6")) {
            executeAndPrintQuery("SELECT DISTINCT e.department  " +
                                 "FROM Project p JOIN p.employees e " +
                                 "WHERE p.name = 'Release1' AND e.address.state = 'CA'", out);
        } else if (query.equals("FROM7")) {
            executeAndPrintQuery("SELECT DISTINCT d  " +
                                 "FROM Project p JOIN p.employees e JOIN e.department d JOIN e.address a WHERE p.name = 'Release1' AND a.state = 'CA'", out);
        } else if (query.equals("FROM8")) {
            executeAndPrintQuery("SELECT DISTINCT d  " +
                                 "FROM Department d, Employee e " +
                                 "WHERE d = e.department", out);
        } else if (query.equals("FROM9")) {
            executeAndPrintQuery("SELECT d, m  " +
                                 "FROM Department d, Employee m " +
                                 "WHERE d = m.department AND m.directs IS NOT EMPTY", out);
        } else if (query.equals("FROM10")) {
            executeAndPrintQuery("SELECT DISTINCT p  " +
                                 "FROM Department d JOIN d.employees e JOIN e.projects p", out);
        } else if (query.equals("FROM11")) {
            executeAndPrintQuery("SELECT e, d  " +
                                 "FROM Employee e LEFT JOIN e.department d", out);
        } else if (query.equals("FROM12")) {
            executeAndPrintQuery("SELECT e  " +
                                 "FROM Employee e JOIN FETCH e.address", out);
        } else if (query.equals("FROM13")) {
            executeAndPrintQuery("SELECT e, a  " +
                                 "FROM Employee e JOIN e.address a", out);
        } else if (query.equals("FROM14")) {
            executeAndPrintQuery("SELECT d  " +
                                 "FROM Department d LEFT JOIN FETCH d.employees", out);
        } else if (query.equals("FROM15")) {
            executeAndPrintQuery("SELECT d, e  " +
                                 "FROM Department d LEFT JOIN d.employees e", out);
        } else if (query.equals("WHERE1")) {
            executeAndPrintQuery("SELECT e  " +
                                 "FROM Employee e " +
                                 "WHERE e.salary BETWEEN 40000 AND 45000", out);
        } else if (query.equals("WHERE2")) {
            executeAndPrintQuery("SELECT e  " +
                                 "FROM Employee e " +
                                 "WHERE e.salary >= 40000 AND e.salary <= 45000", out);
        } else if (query.equals("WHERE3")) {
            executeAndPrintQuery("SELECT d  " +
                                 "FROM Department d " +
                                 "WHERE d.name LIKE '__Eng%'", out);
        } else if (query.equals("WHERE4")) {
            executeAndPrintQuery("SELECT d  " +
                                 "FROM Department d " +
                                 "WHERE d.name LIKE 'QA\\_%' ESCAPE '\\'", out);
        } else if (query.equals("WHERE5")) {
            executeAndPrintQuery("SELECT e  " +
                                 "FROM Employee e " +
                                 "WHERE e.salary = (SELECT MAX(e2.salary) FROM Employee e2)", out);
        } else if (query.equals("WHERE6")) {
            executeAndPrintQuery("SELECT e  " +
                                 "FROM Employee e " +
                                 "WHERE EXISTS (SELECT p FROM Phone p WHERE p.employee = e AND p.type = 'Cell')", out);
        } else if (query.equals("WHERE7")) {
            executeAndPrintQuery("SELECT e  " +
                                 "FROM Employee e " +
                                 "WHERE EXISTS (SELECT p FROM e.phones p WHERE p.type = 'Cell')", out);
        } else if (query.equals("WHERE8")) {
            executeAndPrintQuery("SELECT e  " +
                                 "FROM Employee e " +
                                 "WHERE e.address.state IN ('NY', 'CA')", out);
        } else if (query.equals("WHERE9")) {
            executeAndPrintQuery("SELECT e " +
                                 "FROM Employee e " +
                                 "WHERE e.department IN (SELECT DISTINCT d " +
                                 "FROM Department d JOIN d.employees de JOIN de.projects p " +
                                 "WHERE p.name LIKE 'QA%')", out);
        } else if (query.equals("WHERE10")) {
            executeAndPrintQuery("SELECT p " +
                                 "FROM Phone p " +
                                 "WHERE p.type NOT IN ('Office', 'Home')", out);
        } else if (query.equals("WHERE11")) {
            executeAndPrintQuery("SELECT e " +
                                 "FROM Employee e " +
                                 "WHERE e.directs IS NOT EMPTY", out);
        } else if (query.equals("WHERE12")) {
            executeAndPrintQuery("SELECT m " +
                                 "FROM Employee m " +
                                 "WHERE (SELECT COUNT(e) " +
                                 "FROM Employee e " +
                                 "WHERE e.manager = m) > 0", out);
        } else if (query.equals("WHERE13")) {
            executeAndPrintQuery("SELECT e " +
                                 "FROM Employee e " +
                                 "WHERE e MEMBER OF e.directs", out);
        } else if (query.equals("WHERE14")) {
            executeAndPrintQuery("SELECT e " +
                                 "FROM Employee e " +
                                 "WHERE NOT EXISTS (SELECT p " +
                                 "FROM e.phones p " +
                                 "WHERE p.type = 'Cell')", out);
        } else if (query.equals("WHERE15")) {
            executeAndPrintQuery("SELECT e " +
                                 "FROM Employee e " +
                                 "WHERE e.directs IS NOT EMPTY AND " +
                                 "e.salary < ALL (SELECT d.salary " +
                                 "FROM e.directs d)", out);
        } else if (query.equals("WHERE16")) {
            executeAndPrintQuery("SELECT e " +
                                 "FROM Employee e " +
                                 "WHERE e.department = ANY (SELECT DISTINCT d FROM Department d JOIN d.employees de JOIN de.projects p " +
                                 "WHERE p.name LIKE 'QA%')", out);
        } else if (query.equals("WHERE17")) {
            executeAndPrintQuery("SELECT d " +
                    "FROM Department d " +
                    "WHERE SIZE(d.employees) = 2", out);
        } else if (query.equals("WHERE18")) {
            executeAndPrintQuery("SELECT d " +
                    "FROM Department d " +
                    "WHERE (SELECT COUNT(e) " +
                    "FROM d.employees e) = 2", out);
        } else if (query.equals("ORDERBY1")) {
            executeAndPrintQuery("SELECT e " +
                    "FROM Employee e " +
                    "ORDER BY e.name DESC", out);
        } else if (query.equals("ORDERBY2")) {
            executeAndPrintQuery("SELECT e " +
                    "FROM Employee e JOIN e.department d " +
                    "ORDER BY d.name, e.name DESC", out);
        } else if (query.equals("ORDERBY3")) {
            executeAndPrintQuery("SELECT e.name " +
                    "FROM Employee e " +
                    "ORDER BY e.salary DESC", out);
        } else if (query.equals("Agg1")) {
            executeAndPrintQuery("SELECT AVG(e.salary) " +
                    "FROM Employee e", out);
        } else if (query.equals("Agg2")) {
            executeAndPrintQuery("SELECT d.name, AVG(e.salary) " +
                    "FROM Department d JOIN d.employees e " +
                    "GROUP BY d.name", out);
        } else if (query.equals("Agg3")) {
            executeAndPrintQuery("SELECT d.name, AVG(e.salary) " +
                    "FROM Department d JOIN d.employees e " +
                    "WHERE e.directs IS EMPTY " +
                    "GROUP BY d.name", out);
        } else if (query.equals("Agg4")) {
            executeAndPrintQuery("SELECT d.name, AVG(e.salary) " +
                    "FROM Department d JOIN d.employees e " +
                    "WHERE e.directs IS EMPTY " +
                    "GROUP BY d.name " +
                    "HAVING AVG(e.salary) > 50000", out);
        } else if (query.equals("Agg5")) {
            executeAndPrintQuery("SELECT d.name, e.salary " +
                    "FROM Department d JOIN d.employees e " +
                    "WHERE e.directs IS EMPTY", out);
        } else if (query.equals("Agg6")) {
            executeAndPrintQuery("SELECT e, COUNT(p), COUNT(DISTINCT p.type) " +
                    "FROM Employee e JOIN e.phones p " +
                    "GROUP BY e", out);
        } else if (query.equals("GROUPBY1")) {
            executeAndPrintQuery("SELECT d.name, COUNT(e) " +
                    "FROM Department d JOIN d.employees e " +
                    "GROUP BY d.name", out);
        } else if (query.equals("GROUPBY2")) {
            executeAndPrintQuery("SELECT d.name, COUNT(e), AVG(e.salary) " +
                    "FROM Department d JOIN d.employees e " +
                    "GROUP BY d.name", out);
        } else if (query.equals("GROUPBY3")) {
            executeAndPrintQuery("SELECT d.name, e.salary, COUNT(p) " +
                    "FROM Department d JOIN d.employees e JOIN e.projects p " +
                    "GROUP BY d.name, e.salary", out);
        } else if (query.equals("GROUPBY4")) {
            executeAndPrintQuery("SELECT COUNT(e), AVG(e.salary) " +
                    "FROM Employee e", out);
        } else if (query.equals("HAVING")) {
            executeAndPrintQuery("SELECT e, COUNT(p) " +
                    "FROM Employee e JOIN e.projects p " +
                    "GROUP BY e " +
                    "HAVING COUNT(p) >= 2", out);
        } else if (query.equals("UPDATE1")) {
            executeBulkQuery("UPDATE Employee e " +
                    "SET e.salary = 60000 " +
                    "WHERE e.salary = 55000", out);
        } else if (query.equals("UPDATE2")) {
            executeBulkQuery("UPDATE Employee e " +
                    "SET e.salary = e.salary + 5000 " +
                    "WHERE EXISTS (SELECT p " +
                    "FROM e.projects p " +
                    "WHERE p.name = 'Release1')", out);
        } else if (query.equals("UPDATE3")) {
            executeBulkQuery("UPDATE Phone p " +
                    "SET p.number = CONCAT('288', SUBSTRING(p.number, LOCATE(, '-', p.number), 4)), " +
                    "p.type = 'Business' " +
                    "WHERE p.employee.address.city = 'New York' AND p.type = 'Office'", out);
        } else if (query.equals("DELETE")) {
            executeBulkQuery("DELETE FROM Employee e " +
                    "WHERE e.department IS NULL", out);
        }
        
        printHtmlFooter(out);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }    

    private void executeAndPrintQuery(String queryString, PrintWriter out) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery(queryString);
            printQueryResult(queryString, query.getResultList(), out);
        } finally {
            em.close();
        }
    }

    private void executeBulkQuery(String queryString, PrintWriter out) {
        EntityManager em = emf.createEntityManager();
        try {
            tx.begin();
            em.joinTransaction();
            em.createQuery(queryString).executeUpdate();
            tx.commit();
            out.println("<b>JP QL: </b>" + queryString + " </br>Done.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }
    
    
    private void printQueryResult(String queryString, List result, PrintWriter out) {
        out.println("<table><tbody>");
        out.println("<tr><td><b>JP QL: </b>" + queryString + "</td></tr>");
        out.println("<tr><td><b>Result:</b></td></tr>");
        if (result.isEmpty()) {
            out.println("<tr><td>No results Found</td></tr>");
        } else {
            for (Object o : result) {
                out.println("<tr><td>" + resultAsString(o) + "</td></tr>");
            }
        }
        out.println("</tbody></table>");
    }

    
    private String resultAsString(Object o) {
        if (o instanceof Object[]) {
            return Arrays.asList((Object[])o).toString();
        } else {
            return String.valueOf(o);
        }
    }
    
    
    private void printHtmlHeader(PrintWriter out) throws IOException {
        out.println("<body>");
        out.println("<html>");
        out.println("<head><title>" + TITLE + "</title></head>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("<hr/>");
        out.println("<a href=\"index.html\">Back</a>");
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
