//ModulesListe.java
package fragenkatalog;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.util.ArrayList;

/** 
* ModulesListe.java
* Klasse zur Erstellung von Listen von Modulen.
* Die Klasse enthält Funktionen, um mithilfe der ModulesDAO die Datensätze aus der Datenbank in eine
* Liste umzuwandeln.
* 
* @author JuleEngel 
* @version 1.0 
* @since 15.02.2024 
*/ 
@Named
@ApplicationScoped
public class ModulesListe {
    private ModulesDAO modulesDAO = new ModulesDAO();
    List<Modules> modulesListe = new ArrayList<Modules>();
    
    /**
     * Konstruktor der ModulesListe-Klasse, der die Liste der Module aus der Datenbank lädt.
     */
    public ModulesListe() {
        modulesListe = modulesDAO.loadList();
    }

    /**
     * Gibt die Liste der Module zurück.
     *
     * @return Die Liste der Module
     */
    public List<Modules> getModulesListe() {
        return modulesListe;
    }
}
