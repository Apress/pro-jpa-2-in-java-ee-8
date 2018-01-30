package examples.stateless;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.Metamodel;
import java.util.Map;

public class MockEntityManager implements EntityManager {
    public void persist(Object arg0) {
    }
    
    public <T> T merge(T arg0) {
        return null;
    }

    public void remove(Object arg0) {
    }

    public <T> T find(Class<T> arg0, Object arg1) {
        return null;
    }

    public <T> T getReference(Class<T> arg0, Object arg1) {
        return null;
    }

    public void flush() {
    }

    public void refresh(Object arg0) {
    }

    public boolean contains(Object arg0) {
        return false;
    }

    public Query createQuery(String arg0) {
        return null;
    }

    public Query createNamedQuery(String arg0) {
        return null;
    }

    public Query createNativeQuery(String arg0) {
        return null;
    }

    public Query createNativeQuery(String arg0, Class arg1) {
        return null;
    }

    public Query createNativeQuery(String arg0, String arg1) {
        return null;
    }

    public void close() {
    }

    public boolean isOpen() {
        return false;
    }

    public void lock(Object arg0, LockModeType arg1) {
    }

    public void clear() {
    }

    public Object getDelegate() {
        return null;
    }

    public EntityTransaction getTransaction() {
        return null;
    }

    public FlushModeType getFlushMode() {
        return null;
    }

    public void setFlushMode(FlushModeType arg0) {
    }

    public void joinTransaction() {
    }

    // New JPA 2.0 API
    public <T> T find(Class<T> entityClass, Object primaryKey,
            Map<String, Object> properties) {
        return null;
    }

    public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode) {
        return null;
    }

    public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode,
            Map<String, Object> properties) {
        return null;
    }

    public void lock(Object entity, LockModeType lockMode, Map<String, Object> properties) {
        return;
    }

    public void refresh(Object entity, Map<String, Object> properties) {
        return;
    }

    public void refresh(Object entity, LockModeType lockMode) {
        return;
    }

    public void refresh(Object entity, LockModeType lockMode,
            Map<String, Object> properties) {
        return;
    }

    public void detach(Object entity) {
        return;
    }

    public LockModeType getLockMode(Object entity) {
        return null;
    }

    public void setProperty(String propertyName, Object value) {
        return;
    }

    public Map<String, Object> getProperties() {
        return null;
    }

    public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
        return null;
    }

    public <T> TypedQuery<T> createQuery(String qlString, Class<T> resultClass) {
        return null;
    }

    public <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass) {
        return null;
    }

    public <T> T unwrap(Class<T> cls) {
        return null;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return null;
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return null;
    }

    public Metamodel getMetamodel() {
        return null;
    }
    
}
