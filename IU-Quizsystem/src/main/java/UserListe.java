import java.util.ArrayList;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Named
@ApplicationScoped
public class UserListe
{
    private List<User> userListe = new ArrayList<User>();
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("iu-quizsystem");
    private UserDAO userDAO = new UserDAO();
    
    public UserListe()
    {
    	userListe = userDAO.loadList();
    }

    public List<User> getUserListe()
    {
        return userListe;
    }
}