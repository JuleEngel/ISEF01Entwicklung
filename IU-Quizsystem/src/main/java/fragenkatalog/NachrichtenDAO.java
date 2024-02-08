package fragenkatalog;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import login.User;

public class NachrichtenDAO {
	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("quizsystem");
	
	public List<Nachrichten> loadList() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select n from Nachrichten n");
        return q.getResultList();
    }
    
    public void deleteNachricht(Nachrichten nachricht) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        	Nachrichten entityToDelete = em.find(Nachrichten.class, nachricht.getMessage_id());
            if(entityToDelete != null) {
                em.remove(entityToDelete);
            }
        t.commit();
    }
    
    public void createNachricht(int fragenID, int userId, String neueNachricht) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        
        t.begin();
        	Nachrichten neueNachrichtEntity = new Nachrichten();

        	neueNachrichtEntity.setUser_id(userId);
        	neueNachrichtEntity.setQuestion_id(fragenID);
        	neueNachrichtEntity.setMessage(neueNachricht);
            em.persist(neueNachrichtEntity);
        t.commit();
    }
}
