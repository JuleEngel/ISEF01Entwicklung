//FragenkatalogListe.java
package fragenkatalog;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.util.ArrayList;

/** 
* FragenkatalogListe.java
* Klasse zur Erstellung von Listen aller Fragen.
* Die Klasse enthält Funktionen, um mithilfe der FragenkatalogDAO die Datensätze aus der Datenbank in eine
* Liste umzuwandeln.
* 
* @author JuleEngel 
* @version 1.0 
* @since 15.02.2024 
*/ 
@Named
@ApplicationScoped
public class FragenkatalogListe {
    private FragenkatalogDAO fragenkatalogDAO = new FragenkatalogDAO();
    List<Fragenkatalog> fragenkatalogListe = new ArrayList<Fragenkatalog>();
    
    /**
     * Erstellt eine neue Instanz von FragenkatalogListe und lädt die Liste der Fragenkataloge aus der Datenbank.
     */
    public FragenkatalogListe() {
        fragenkatalogListe = fragenkatalogDAO.loadList();
    }

    /**
     * Gibt die Liste der Fragenkataloge zurück.
     *
     * @return Die Liste der Fragenkataloge
     */
    public List<Fragenkatalog> getFragenkatalogListe() {
        return fragenkatalogListe;
    }
}