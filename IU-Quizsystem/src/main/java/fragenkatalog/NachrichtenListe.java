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
public class NachrichtenListe {
	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("quizsystem");
    private NachrichtenDAO nachrichtenDAO = new NachrichtenDAO();
    List<Nachrichten> nachrichtenListe = new ArrayList<Nachrichten>();
    
	public NachrichtenListe()
    {
        nachrichtenListe = nachrichtenDAO.loadList();
    }

    public List<Nachrichten> getNachrichtenListe()
    {
        return nachrichtenListe;
    }
}
