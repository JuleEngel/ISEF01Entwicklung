package quiz;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Named
@ApplicationScoped
public class QuizFragenkatalogListe {
	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("quizsystem");
    private QuizFragenkatalogDAO quizFragenkatalogDAO = new QuizFragenkatalogDAO();
    List<QuizFragenkatalog> quizFragenkatalogListe = new ArrayList<QuizFragenkatalog>();
    
	public QuizFragenkatalogListe()
    {
		quizFragenkatalogListe = quizFragenkatalogDAO.loadList();
    }

    public List<QuizFragenkatalog> getFragenkatalogListe()
    {
        return quizFragenkatalogListe;
    }
}
