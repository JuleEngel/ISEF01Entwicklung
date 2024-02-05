package fragenkatalog;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/** 
* Fragenkatalog.java
* Kurze Beschreibung 
* Lange Beschreibung
*  
* 
* @author JuleEngel 
* @version 1.0 
* @since 29.01.2024 
*/ 
@Named
@ApplicationScoped
public class FragenkatalogListe {
	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("quizsystem");
	private int size;
    private boolean isEmpty;
    private FragenkatalogDAO fragenkatalogDAO = new FragenkatalogDAO();
    List<Fragenkatalog> fragenkatalogListe = new ArrayList<Fragenkatalog>();
    
	public FragenkatalogListe()
    {
        fragenkatalogListe = fragenkatalogDAO.loadList();
        size = fragenkatalogListe.size();
        isEmpty = fragenkatalogListe.isEmpty();
    }

    public List<Fragenkatalog> getFragenkatalogListe()
    {
        return fragenkatalogListe;
    }
    
    public int getSize() {
        return size;
    }
    
    public boolean getIsEmpty() {
        return isEmpty;
    }
}
