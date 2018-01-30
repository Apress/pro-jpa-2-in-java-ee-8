package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.CacheStoreMode;

import examples.model.Stock;

@Stateless
public class StockService {
    @PersistenceContext(unitName="StockService")
    private EntityManager em;

    public List<Stock> findAllStocks() {
        return em.createQuery("SELECT s FROM Stock s", Stock.class)
                 .getResultList();
    }
    public List<Stock> findExpensiveStocks(double threshold) {
        TypedQuery<Stock> q = em.createQuery("SELECT s FROM Stock s WHERE s.price > :amount", Stock.class);
        q.setHint("javax.persistence.cache.retrieveMode",CacheRetrieveMode.BYPASS);
        q.setHint("javax.persistence.cache.storeMode",CacheStoreMode.REFRESH);
        q.setParameter("amount", threshold);
        return q.getResultList();
    }
}

