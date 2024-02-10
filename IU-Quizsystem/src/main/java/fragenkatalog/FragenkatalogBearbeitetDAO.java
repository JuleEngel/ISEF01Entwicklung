package fragenkatalog;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FragenkatalogBearbeitetDAO {
	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("quizsystem");
	
	public List<FragenkatalogBearbeitet> loadList() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select f from FragenkatalogBearbeitet f");
        return q.getResultList();
    }
    
    public void deleteFrage(FragenkatalogBearbeitet fragenkatalogBearbeitet) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        	FragenkatalogBearbeitet entityToDelete = em.find(FragenkatalogBearbeitet.class, fragenkatalogBearbeitet.getId());
            if(entityToDelete != null) {
                em.remove(entityToDelete);
            }
        t.commit();
    }
    
    public void createFrage(int userID, Fragenkatalog neueFrage, String tempNachricht) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        
        t.begin();
        	FragenkatalogBearbeitet neueFrageEntity = new FragenkatalogBearbeitet();
        	
        	int questionId = neueFrage.getId();
            neueFrageEntity.setQuestion_id(questionId);
            neueFrageEntity.setUser_id(userID);
            neueFrageEntity.setQuestion(neueFrage.getQuestion());
            neueFrageEntity.setCorrect_answer(neueFrage.getCorrect_answer());
            neueFrageEntity.setIncorrect_answer_1(neueFrage.getIncorrect_answer_1());
            neueFrageEntity.setIncorrect_answer_2(neueFrage.getIncorrect_answer_2());
            neueFrageEntity.setIncorrect_answer_3(neueFrage.getIncorrect_answer_3());
            neueFrageEntity.setExplanation(neueFrage.getExplanation());
            neueFrageEntity.setMessage(tempNachricht);
            em.persist(neueFrageEntity);
        t.commit();
    }
}
