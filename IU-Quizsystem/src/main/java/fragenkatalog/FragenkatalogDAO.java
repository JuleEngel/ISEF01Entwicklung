//FragenkatalogDAO
package fragenkatalog;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/** 
* FragenkatalogDAO.java
* Datenbank-Verbindung Fragenkatalog.
* Die Klasse ist eine DAO zur Verbindung mit der Datenbank-Tabelle Fragenkatalog 
* zur Verwaltung von Fragen.
* 
* @author JuleEngel 
* @version 1.0 
* @since 15.02.2024 
*/ 
public class FragenkatalogDAO {
	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("quizsystem");
	
	/**
	 * Lädt eine Liste von Fragenkatalog-Entitäten aus der Datenbank.
	 *
	 * @return Eine Liste von Fragenkatalog-Entitäten
	 */
	public List<Fragenkatalog> loadList() {
	    EntityManager em = emf.createEntityManager();
	    Query q = em.createQuery("select f from Fragenkatalog f");
	    return q.getResultList();
	}

	/**
	 * Löscht eine Fragenkatalog-Entität aus der Datenbank.
	 *
	 * @param fragenkatalog Die zu löschende Fragenkatalog-Entität
	 */
	public void deleteFrage(Fragenkatalog fragenkatalog) {
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction t = em.getTransaction();
	    t.begin();
	    Fragenkatalog entityToDelete = em.find(Fragenkatalog.class, fragenkatalog.getId());
	    if (entityToDelete != null) {
	        em.remove(entityToDelete);
	    }
	    t.commit();
	}

	/**
	 * Aktualisiert eine Fragenkatalog-Entität in der Datenbank.
	 *
	 * @param fragenkatalog Die zu aktualisierende Fragenkatalog-Entität
	 */
	public void updateFrage(Fragenkatalog fragenkatalog) {
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction t = em.getTransaction();
	    t.begin();
	    Fragenkatalog entityToUpdate = em.find(Fragenkatalog.class, fragenkatalog.getId());
	    if (entityToUpdate != null) {
	        em.merge(fragenkatalog);
	    }
	    t.commit();
	}

	/**
	 * Erstellt eine neue Fragenkatalog-Entität in der Datenbank.
	 *
	 * @param neueFrage Die neue Fragenkatalog-Entität
	 */
	public void createFrage(Fragenkatalog neueFrage) {
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction t = em.getTransaction();

	    t.begin();
	    Fragenkatalog neueFrageEntity = new Fragenkatalog();

	    neueFrageEntity.setQuestion(neueFrage.getQuestion());
	    neueFrageEntity.setCorrect_answer(neueFrage.getCorrect_answer());
	    neueFrageEntity.setIncorrect_answer_1(neueFrage.getIncorrect_answer_1());
	    neueFrageEntity.setIncorrect_answer_2(neueFrage.getIncorrect_answer_2());
	    neueFrageEntity.setIncorrect_answer_3(neueFrage.getIncorrect_answer_3());
	    neueFrageEntity.setDifficulty(neueFrage.getDifficulty());
	    neueFrageEntity.setModule_id(neueFrage.getModule_id());
	    neueFrageEntity.setExplanation(neueFrage.getExplanation());
	    neueFrageEntity.setStatus(neueFrage.getStatus());
	    em.persist(neueFrageEntity);
	    t.commit();
	}

	/**
	 * Aktualisiert den Status einer Fragenkatalog-Entität in der Datenbank.
	 *
	 * @param fragenID Die ID der Fragenkatalog-Entität
	 * @param status Der neue Status der Fragenkatalog-Entität
	 */
	public void updateStatus(int fragenID, String status) {
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction t = em.getTransaction();
	    t.begin();
	    Fragenkatalog entityToUpdate = em.find(Fragenkatalog.class, fragenID);
	    if (entityToUpdate != null) {
	        entityToUpdate.setStatus(status);
	    }
	    t.commit();
	}
}
