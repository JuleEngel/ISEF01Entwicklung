//NachrichtenListe.java
package fragenkatalog;

import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.util.ArrayList;

/** 
* NachrichtenListe.java
* Klasse zur Erstellung von Listen von Nachrichten.
* Die Klasse enthält Funktionen, um mithilfe der NachrichtenDAO die Datensätze aus der Datenbank in eine
* Liste umzuwandeln.
* 
* @author JuleEngel 
* @version 1.0 
* @since 15.02.2024 
*/ 
@Named
@ApplicationScoped
public class NachrichtenListe {
    private NachrichtenDAO nachrichtenDAO = new NachrichtenDAO();
    List<Nachrichten> nachrichtenListe = new ArrayList<Nachrichten>();
    
    /**
     * Initialisiert eine neue Instanz von NachrichtenListe und lädt die Liste der Nachrichten aus der Datenbank.
     */
    public NachrichtenListe() {
        nachrichtenListe = nachrichtenDAO.loadList();
    }

    /**
     * Gibt die Liste der Nachrichten zurück.
     *
     * @return Die Liste der Nachrichten
     */
    public List<Nachrichten> getNachrichtenListe() {
        return nachrichtenListe;
    }
}