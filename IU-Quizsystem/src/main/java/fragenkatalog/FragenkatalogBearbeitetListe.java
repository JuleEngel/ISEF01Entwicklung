package fragenkatalog;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@Named
@ApplicationScoped
public class FragenkatalogBearbeitetListe {
	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("quizsystem");
    private FragenkatalogBearbeitetDAO fragenkatalogBearbeitetDAO = new FragenkatalogBearbeitetDAO();
    List<FragenkatalogBearbeitet> fragenkatalogBearbeitetListe = new ArrayList<FragenkatalogBearbeitet>();
    
	public FragenkatalogBearbeitetListe()
    {
        fragenkatalogBearbeitetListe = fragenkatalogBearbeitetDAO.loadList();
    }

    public List<FragenkatalogBearbeitet> getFragenkatalogBearbeitetListe()
    {
        return fragenkatalogBearbeitetListe;
    }
}
