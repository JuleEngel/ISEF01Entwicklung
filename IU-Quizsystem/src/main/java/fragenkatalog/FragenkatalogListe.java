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
public class FragenkatalogListe {
	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("quizsystem");
    private FragenkatalogDAO fragenkatalogDAO = new FragenkatalogDAO();
    List<Fragenkatalog> fragenkatalogListe = new ArrayList<Fragenkatalog>();
    
	public FragenkatalogListe()
    {
        fragenkatalogListe = fragenkatalogDAO.loadList();
    }

    public List<Fragenkatalog> getFragenkatalogListe()
    {
        return fragenkatalogListe;
    }
}