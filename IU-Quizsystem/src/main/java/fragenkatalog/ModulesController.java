package fragenkatalog;


import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import java.io.Serializable;

import jakarta.inject.Inject;
import jakarta.inject.Named;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ModulesController implements Serializable
{
    @Inject
    private ModulesListe modulesListe = new ModulesListe();
    @Inject
    private FragenkatalogController fragenkatalogController;
    
    public void checkModule(FacesContext context, UIComponent comp, Object value) throws ValidatorException{
        String module_short = (String) value;
        for (Modules modules : modulesListe.getModulesListe()) {
	        if (modules.getModule_short().equals(module_short)) {
	        	String module_long = modules.getModule_long();
	        	fragenkatalogController.getNeueFrage().setModule_short(modules.getModule_short());
	            fragenkatalogController.getNeueFrage().setModule(module_long);
	            return;
	        }
        }
        throw new ValidatorException(new FacesMessage("Bitte geben Sie ein existierendes Modulkürzel ein!"));
    }
	 
}
