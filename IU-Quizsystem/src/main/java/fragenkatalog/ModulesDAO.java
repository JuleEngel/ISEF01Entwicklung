//ModulesDAO.java
package fragenkatalog;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import login.User;

/** 
* ModulesDAO.java
* Datenbank-Verbindung Modules.
* Die Klasse ist eine DAO zur Verbindung mit der Datenbank-Tabelle Modules 
* zur Verwaltung von Modulen.
* 
* @author JuleEngel 
* @version 1.0 
* @since 15.02.2024 
*/ 
public class ModulesDAO {
	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("quizsystem");
	
	/**
	 * Lädt eine Liste aller Module aus der Datenbank.
	 *
	 * @return Eine Liste aller Module aus der Datenbank
	 */
	public List<Modules> loadList() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select m from Modules m");
        return q.getResultList();
    }
}
