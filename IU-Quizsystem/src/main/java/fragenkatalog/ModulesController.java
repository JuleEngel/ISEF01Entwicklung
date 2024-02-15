//ModulesController.java
package fragenkatalog;

import jakarta.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.inject.Named;

/** 
* ModulesController.java
* Methoden, die der Verwaltung der Module dienen.
* Die Klasse ModulesController.java enthält Funktionen zur korrekten Darstellung auf den xhtml-Seiten.
* 
* @author JuleEngel 
* @version 1.0 
* @since 15.02.2024 
*/ 
@SuppressWarnings("serial")
@Named
@ApplicationScoped
public class ModulesController implements Serializable
{
    @Inject
    private ModulesListe modulesListe = new ModulesListe();
    
    /**
     * Gibt eine Liste aller Module zurück.
     *
     * @return Eine Liste aller Module
     */
    public List<Modules> getModulesList() {
        return modulesListe.getModulesListe();
    }

    /**
     * Gibt eine Liste aller Module mit der angegebenen Modul-ID zurück.
     *
     * @param id Die ID des gesuchten Moduls
     * @return Eine Liste aller Module mit der angegebenen Modul-ID
     */
    public List<Modules> getModulesByID(int id) {
        List<Modules> tempList = new ArrayList<>();
        for (Modules module : modulesListe.getModulesListe()) {
            if (module.getModule_id() == id) {
                tempList.add(module);
            }
        }
        return tempList;
    }

    /**
     * Gibt die Kurzbeschreibung des Moduls mit der angegebenen Modul-ID zurück.
     *
     * @param id Die ID des gesuchten Moduls
     * @return Die Kurzbeschreibung des Moduls mit der angegebenen Modul-ID oder null, falls das Modul nicht gefunden wurde
     */
    public String getModuleShort(int id) {
        for (Modules module : modulesListe.getModulesListe()) {
            if (module.getModule_id() == id) {
                return module.getModule_short();
            }
        }
        return null;
    }

    /**
     * Gibt die Langbeschreibung des Moduls mit der angegebenen Modul-ID zurück.
     *
     * @param id Die ID des gesuchten Moduls
     * @return Die Langbeschreibung des Moduls mit der angegebenen Modul-ID oder null, falls das Modul nicht gefunden wurde
     */
    public String getModuleLong(int id) {
        for (Modules module : modulesListe.getModulesListe()) {
            if (module.getModule_id() == id) {
                return module.getModule_long();
            }
        }
        return null;
    }
}
