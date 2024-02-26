//FragenkatalogBearbeitetListe.java
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
* FragenkatalogBearbeitetListe.java
* Klasse zur Erstellung von Listen bearbeiteter Fragen.
* Die Klasse enthält Funktionen, um mithilfe der FragenkatalogBearbeitetDAO die Datensätze aus der Datenbank in eine
* Liste umzuwandeln.
* 
* @author JuleEngel 
* @version 1.0 
* @since 15.02.2024 
*/ 
@Named
@ApplicationScoped
public class FragenkatalogBearbeitetListe {
	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("quizsystem");
    private FragenkatalogBearbeitetDAO fragenkatalogBearbeitetDAO = new FragenkatalogBearbeitetDAO();
    List<FragenkatalogBearbeitet> fragenkatalogBearbeitetListe = new ArrayList<FragenkatalogBearbeitet>();
    
    /**
     * Konstruktor für die Klasse FragenkatalogBearbeitetListe. Lädt die Liste der bearbeiteten Fragenkataloge.
     */
    public FragenkatalogBearbeitetListe() {
        fragenkatalogBearbeitetListe = fragenkatalogBearbeitetDAO.loadList();
    }

    /**
     * Gibt die Liste der bearbeiteten Fragenkataloge zurück.
     *
     * @return Die Liste der bearbeiteten Fragenkataloge.
     */
    public List<FragenkatalogBearbeitet> getFragenkatalogBearbeitetListe() {
        return fragenkatalogBearbeitetListe;
    }
}
