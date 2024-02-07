package fragenkatalog;


import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import login.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@Named
@SessionScoped
public class FragenkatalogController implements Serializable
{
    private Fragenkatalog neueFrage = new Fragenkatalog();
    private Fragenkatalog tempFrage;
    private FragenkatalogDAO fragenkatalogDAO = new FragenkatalogDAO();
    private String moduleFilterNew;
    private String moduleFilterReported;
    private String moduleFilterInProgress;
    private String moduleFilterPublished;
    
    @Inject
    private FragenkatalogListe fragenkatalogListe = new FragenkatalogListe();
    
    
    public List<Fragenkatalog> questionsResults(String filter) {
    	String moduleFilter;
    	if (filter == "new") {
    		moduleFilter = moduleFilterNew;
    	}
    	else if (filter == "reported") {
    		moduleFilter = moduleFilterReported;
    	}
    	else if (filter == "in_progress") {
    		moduleFilter = moduleFilterInProgress;
    	}
    	else if (filter == "published") {
    		moduleFilter = moduleFilterPublished;
    	}
    	else {
    		moduleFilter = null;
    	}
    	
    	if (moduleFilter == null || moduleFilter.trim().isEmpty()) {
    		List<Fragenkatalog> filteredList = new ArrayList<>();

            for (Fragenkatalog fragenkatalog : fragenkatalogListe.getFragenkatalogListe()) {
                if (fragenkatalog.getStatus().equals(filter)) {
                    filteredList.add(fragenkatalog);
                }
            }
            return filteredList;
        }
    	
    	String filterText = moduleFilter.toLowerCase();
    	List<Fragenkatalog> filteredList = new ArrayList<>();

        for (Fragenkatalog fragenkatalog : fragenkatalogListe.getFragenkatalogListe()) {
        	if (fragenkatalog.getModule().toLowerCase().contains(filterText) 
            		|| fragenkatalog.getModule_short().toLowerCase().contains(filterText)) {
            	if (fragenkatalog.getStatus().equals(filter)) {
                    filteredList.add(fragenkatalog);
                }
            }
        }
        return filteredList;
    }
    
    public String linkToEdit(Fragenkatalog frage) {
    	this.tempFrage = frage;
    	return "fragenkatalogFrageAnpassenTutor?faces-redirect=true";
    }
    
    public String formatDifficulty(int difficulty) {
    	if (difficulty == 1) return "Leicht";
    	else if (difficulty == 2) return "Mittel";
    	else if (difficulty == 3) return "Schwer";
    	else return "Keine Angabe";
    }
    
    public void checkTempFrage() {
    	if (this.tempFrage == null) {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "fragenkatalogAnzeigeTutor?faces-redirect=true");
	    }
    }
    
	 public String deleteFrage(Fragenkatalog frage)  {
        fragenkatalogDAO.deleteFrage(frage);
        fragenkatalogListe.getFragenkatalogListe().remove(frage);
        return "fragenkatalogAnzeigeTutor?faces-redirect=true";
    }
	 
	 public String updateFrage(Fragenkatalog frage) {
		if (frage.getStatus().equals("new") || frage.getStatus().equals("reported")) {
			frage.setStatus("in_progress");
		}
        fragenkatalogDAO.updateFrage(frage);
        return "fragenkatalogAnzeigeTutor?faces-redirect=true";
    }
	 
	 public String publishFrage(Fragenkatalog frage) {
		 	frage.setStatus("published");
	        fragenkatalogDAO.updateFrage(frage);
	        return "fragenkatalogAnzeigeTutor?faces-redirect=true";
	    }
	 
	 public String cancelEdit() {
		 return "fragenkatalogAnzeigeTutor?faces-redirect=true";
	 }
    
    public Fragenkatalog getNeueFrage() {

    	return this.neueFrage;
    }
    
    public Fragenkatalog getTempFrage() {

    	return this.tempFrage;
    }

	public String getModuleFilterNew() {
		return moduleFilterNew;
	}

	public void setModuleFilterNew(String moduleFilterNew) {
		this.moduleFilterNew = moduleFilterNew;
	}

	public String getModuleFilterReported() {
		return moduleFilterReported;
	}

	public void setModuleFilterReported(String moduleFilterReported) {
		this.moduleFilterReported = moduleFilterReported;
	}

	public String getModuleFilterInProgress() {
		return moduleFilterInProgress;
	}

	public void setModuleFilterInProgress(String moduleFilterInProgress) {
		this.moduleFilterInProgress = moduleFilterInProgress;
	}

	public String getModuleFilterPublished() {
		return moduleFilterPublished;
	}

	public void setModuleFilterPublished(String moduleFilterPublished) {
		this.moduleFilterPublished = moduleFilterPublished;
	}
	
	
    
    
    /*
    public void createFrage()  {
        if (fragenkatalog.getFragenkatalog().contains(platzhalter)) {
            emissionenListe.getEmissionenListe().remove(platzhalter);
            maxIndex = 0;
            isEmpty = false;
            index = 0;
        }
        else {
            maxIndex += 1;
        }
        
        emissionDAO.createEmission(neueEmission);
        
        emissionenListe.getEmissionenListe().add(neueEmission);
        neueEmission = new Emission();

    }
    
    public void updateEmission(Emission emission) {
        emissionDAO.updateEmission(emission);
    }
    
    
    public void validateEmission(FacesContext context, UIComponent comp, Object value) throws ValidatorException{
        String regex = "^[0-9,]+$";
        String emission = (String) value;
        if (!emission.matches(regex)) {
            throw new ValidatorException(new FacesMessage("Bitte geben Sie eine korrekte Emission ein!"));
        }
    }
    
    public void validateCountry(FacesContext context, UIComponent comp, Object value) throws ValidatorException{
        String regex = "^[a-zA-z]+$";
        String name = (String) value;
        for(Emission existierendeEmission : emissionenListe.getEmissionenListe()) {
        	if(existierendeEmission.getCountry().equalsIgnoreCase(name)) {
        		throw new ValidatorException(new FacesMessage("Das Land existriert bereits!"));
        	}
        }
        if (!name.matches(regex)) {
            throw new ValidatorException(new FacesMessage("Bitte geben Sie einen korrekten Ländernamen ein!"));
        }
    }
    */
}
