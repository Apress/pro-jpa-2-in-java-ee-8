package examples.stateless;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import examples.model.PrintJob;
import examples.model.PrintQueue;

@Stateless
public class PrintService {
    @PersistenceContext(unitName="PrintService")
    protected EntityManager em;

    public PrintQueue createPrinter(String name) {
        if (em.find(PrintQueue.class, name) != null)
            return null;
        PrintQueue q = new PrintQueue();
        q.setName(name);
        em.persist(q);
        return q;
    }

    public PrintJob createPrintJob(int jobId, String queueName) {
        PrintJob job = null;
        PrintQueue q = em.find(PrintQueue.class, queueName);
        if (q != null) {
            job = new PrintJob();
            job.setId(jobId);
            q.addJob(job);
            em.persist(job);
        } 
        return job;
    }

    public PrintJob removePrintJob(int jobId, String queueName) {
        PrintJob job = null;
        PrintQueue q = em.find(PrintQueue.class, queueName);
        if (q != null) {
            Iterator iter = q.getJobs().iterator();
            while (iter.hasNext()) {
                PrintJob current = (PrintJob) iter.next();
                if (current.getId() == jobId) {
                    job = current;
                    iter.remove();
                    job.setQueue(null);
                    em.remove(job);
                }
            }
        }
        return job;
    }

    public List<PrintQueue> listAllPrintQueues() {
        return em.createQuery("SELECT p FROM PrintQueue p", PrintQueue.class).getResultList();
    }

    public List<PrintJob> listAllJobs(String queueName) {
        PrintQueue q = em.find(PrintQueue.class, queueName);
        if (q != null) {
            // trigger loading before returning
            q.getJobs().size();
            return q.getJobs();
        } else {
            return new ArrayList<PrintJob>();
        }
    }
}
