//UserListe.java
package login;
import java.util.ArrayList;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/** 
* UserListe.java
* Klasse zur Erstellung von Nutzerlisten.
* Die Klasse enthält Funktionen, um mithilfe der UserDAO die Datensätze aus der Datenbank in eine Userliste umzuwandeln.
* 
* @author JuleEngel 
* @version 1.0 
* @since 29.01.2024 
*/ 
@Named
@ApplicationScoped
public class UserListe
{
    private List<User> userListe = new ArrayList<User>();
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("quizsystem");
    private UserDAO userDAO = new UserDAO();
    
    /**
     * Konstruktor für die UserListe-Klasse. Lädt die Liste aller Benutzer aus der Datenbank.
     */
    public UserListe()
    {
    	userListe = userDAO.loadList();
    }

    /**
     * Gibt die Liste aller Benutzer zurück.
     * @return Die Liste aller Benutzer.
     */
    public List<User> getUserListe()
    {
        return userListe;
    }
    
    public User getUserFromList(int id) {
    	for (User user : userListe) {
    		if (user.getId() == id) {
    			return user;
    		}
    	}
    	return null;
    }
    
    public void updatePlayedGames(int userID, int playedGames) {
    	userDAO.updatePlayedGames(userID, playedGames);
    }
}