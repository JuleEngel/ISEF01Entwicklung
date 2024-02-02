//UserDAO.java
package login;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/** 
* UserDAO.java
* Datenbank-Verbindung User.
* Die Klasse ist eine DAO zur Verbindung mit der Datenbank quizsystem zur Erstellung einer Nutzerliste.
*  
* 
* @author JuleEngel 
* @version 1.0 
* @since 29.01.2024 
*/ 
public class UserDAO {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("quizsystem");
    
    /**
     * Lädt eine Liste aller Benutzer aus der Datenbank.
     *
     * @return Eine Liste aller Benutzer.
     */
    public List<User> loadList() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select u from User u");
        return q.getResultList();
    }
}
