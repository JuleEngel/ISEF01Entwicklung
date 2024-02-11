package quiz;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class QuestionDAO {
	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("quizsystem");
	
	public List<Question> loadQuestion() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select a from Question a");
        return q.getResultList();
    }
}
