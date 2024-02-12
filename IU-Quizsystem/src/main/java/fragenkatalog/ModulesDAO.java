package fragenkatalog;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import login.User;

public class ModulesDAO {
	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("quizsystem");
	
	public List<Modules> loadList() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select m from Modules m");
        return q.getResultList();
    }
}
