//NachrichtenDAO.java
package fragenkatalog;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import login.User;

/** 
* NachrichtenDAO.java
* Datenbank-Verbindung Nachrichten.
* Die Klasse ist eine DAO zur Verbindung mit der Datenbank-Tabelle Nachrichten 
* zur Verwaltung von Nachrichten, die von Studenten erstellt wurden.
* 
* @author JuleEngel 
* @version 1.0 
* @since 15.02.2024 
*/ 
public class NachrichtenDAO {
	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("quizsystem");
	
	/**
	 * Lädt eine Liste von Nachrichten aus der Datenbank.
	 *
	 * @return Die Liste der Nachrichten aus der Datenbank
	 */
	public List<Nachrichten> loadList() {
	    EntityManager em = emf.createEntityManager();
	    Query q = em.createQuery("select n from Nachrichten n");
	    return q.getResultList();
	}

	/**
	 * Löscht eine Nachricht aus der Datenbank.
	 *
	 * @param nachricht Die zu löschende Nachricht
	 */
	public void deleteNachricht(Nachrichten nachricht) {
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction t = em.getTransaction();
	    t.begin();
	    Nachrichten entityToDelete = em.find(Nachrichten.class, nachricht.getMessage_id());
	    if (entityToDelete != null) {
	        em.remove(entityToDelete);
	    }
	    t.commit();
	}

	/**
	 * Erstellt eine neue Nachricht und speichert sie in der Datenbank.
	 *
	 * @param fragenID      Die ID der Frage, zu der die Nachricht gehört
	 * @param userId        Die ID des Benutzers, der die Nachricht erstellt hat
	 * @param neueNachricht Der Inhalt der neuen Nachricht
	 */
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
