package examples.stateful;

import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Employee;

@Stateful
public class ResultPager {
    @PersistenceContext(unitName="QueryPaging")
    private EntityManager em;
    
    private String reportQueryName;
    private int currentPage;
    private int maxResults;
    private int pageSize;
    
    public int getPageSize() {
        return pageSize;
    }
    
    public int getMaxPages() {
        return maxResults / pageSize;
    }

    public void init(int pageSize, String countQueryName, String reportQueryName) {
        this.pageSize = pageSize;
        this.reportQueryName = reportQueryName;
        maxResults = (em.createNamedQuery(countQueryName, Long.class)
                        .getSingleResult()).intValue();
        currentPage = 0;
    }
    
    public List<Employee> getCurrentResults() {
        return em.createNamedQuery(reportQueryName, Employee.class)
                 .setFirstResult(currentPage * pageSize)
                 .setMaxResults(pageSize)
                 .getResultList();
    }
    
    public void next() {
        currentPage++;
    }
    
    public void previous() {
        currentPage--;
        if (currentPage < 0) {
            currentPage = 0;
        }
    }
    
    public int getCurrentPage() {
        return currentPage;
    }
    
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    
    @Remove
    public void finished() {
    }
}
