package login;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class UserDAO {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("quizsystem");
    
    public List<User> loadList() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select u from User u");
        return q.getResultList();
    }
}
