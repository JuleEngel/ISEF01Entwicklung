package quiz;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class QuizFragenkatalogDAO {
	
	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("quizsystem");
	

	public List<QuizFragenkatalog> loadList() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select q from QuizFragenkatalog q");
        return q.getResultList();
    }
    
    

}
