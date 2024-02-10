package fragenkatalog;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FragenkatalogDAO {
	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("quizsystem");
	
	public List<Fragenkatalog> loadList() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select f from Fragenkatalog f");
        return q.getResultList();
    }
    
    public void deleteFrage(Fragenkatalog fragenkatalog) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        	Fragenkatalog entityToDelete = em.find(Fragenkatalog.class, fragenkatalog.getId());
            if(entityToDelete != null) {
                em.remove(entityToDelete);
            }
        t.commit();
    }
    
    public void updateFrage(Fragenkatalog fragenkatalog) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        Fragenkatalog entityToUpdate = em.find(Fragenkatalog.class, fragenkatalog.getId());
            if(entityToUpdate != null) {
                em.merge(fragenkatalog);
            }
        t.commit();
    }
    
    public void createFrage(Fragenkatalog neueFrage) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        
        t.begin();
        	Fragenkatalog neueFrageEntity = new Fragenkatalog();

            neueFrageEntity.setId(neueFrage.getId());
            neueFrageEntity.setQuestion(neueFrage.getQuestion());
            neueFrageEntity.setCorrect_answer(neueFrage.getCorrect_answer());
            neueFrageEntity.setIncorrect_answer_1(neueFrage.getIncorrect_answer_1());
            neueFrageEntity.setIncorrect_answer_2(neueFrage.getIncorrect_answer_2());
            neueFrageEntity.setIncorrect_answer_3(neueFrage.getIncorrect_answer_3());
            neueFrageEntity.setDifficulty(neueFrage.getDifficulty());
            neueFrageEntity.setModule(neueFrage.getModule());
            neueFrageEntity.setExplanation(neueFrage.getExplanation());
            neueFrageEntity.setStatus(neueFrage.getStatus());
            em.persist(neueFrageEntity);
        t.commit();
    }

	public void updateStatus(int fragenID, String status) {
		EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        Fragenkatalog entityToUpdate = em.find(Fragenkatalog.class, fragenID);
            if(entityToUpdate != null) {
            	entityToUpdate.setStatus(status);
            }
        t.commit();
	}
}
