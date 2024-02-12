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
public class ModulesListe {
	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("quizsystem");
    private ModulesDAO modulesDAO = new ModulesDAO();
    List<Modules> modulesListe = new ArrayList<Modules>();
    
	public ModulesListe()
    {
        modulesListe = modulesDAO.loadList();
    }

    public List<Modules> getModulesListe()
    {
        return modulesListe;
    }
}
