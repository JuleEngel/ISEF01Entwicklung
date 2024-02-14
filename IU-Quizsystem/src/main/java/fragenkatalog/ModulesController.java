package fragenkatalog;


import jakarta.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.inject.Named;

@SuppressWarnings("serial")
@Named
@ApplicationScoped
public class ModulesController implements Serializable
{
    @Inject
    private ModulesListe modulesListe = new ModulesListe();
    
    public List<Modules> getModulesList() {
    	return modulesListe.getModulesListe();
    }
    
    public List<Modules> getModulesByID(int id) {
    	List<Modules> tempList = new ArrayList<>();
    	for (Modules module : modulesListe.getModulesListe()) {
    		if (module.getModule_id() == id) {
    			tempList.add(module);
    		}
    	}
    	return tempList;
    }
    
    public String getModuleShort(int id) {
    	for (Modules module : modulesListe.getModulesListe()) {
    		if (module.getModule_id() == id) {
    			return module.getModule_short();
    		}
    	}
    	return null;
    }
    
    public String getModuleLong(int id) {
    	for (Modules module : modulesListe.getModulesListe()) {
    		if (module.getModule_id() == id) {
    			return module.getModule_long();
    		}
    	}
    	return null;
    }
}
