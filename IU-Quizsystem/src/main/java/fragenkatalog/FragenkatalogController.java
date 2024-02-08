package fragenkatalog;


import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class FragenkatalogController implements Serializable
{
    private Fragenkatalog neueFrage = new Fragenkatalog();
    private FragenkatalogDAO fragenkatalogDAO = new FragenkatalogDAO();
    private String moduleFilterNew;
    private String moduleFilterReported;
    private String moduleFilterInProgress;
    private String moduleFilterPublished;
    
    @Inject
    private TempVariablen tempVariablen;
    @Inject
    private FragenkatalogListe fragenkatalogListe = new FragenkatalogListe();
    @Inject
    private NachrichtenController nachrichtenController;
    
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
    	
    	if (filter.equals("published")) {
            if (moduleFilter == null || moduleFilter.trim().isEmpty()) {
                List<Fragenkatalog> filteredList = new ArrayList<>();

                for (Fragenkatalog fragenkatalog : fragenkatalogListe.getFragenkatalogListe()) {
                    if (fragenkatalog.getStatus().equals("published") || fragenkatalog.getStatus().equals("reported")) {
                        filteredList.add(fragenkatalog);
                    }
                }
                return filteredList;
            }

            String filterText = moduleFilter.toLowerCase();
            List<Fragenkatalog> filteredList = new ArrayList<>();

            for (Fragenkatalog fragenkatalog : fragenkatalogListe.getFragenkatalogListe()) {
                if ((fragenkatalog.getModule().toLowerCase().contains(filterText) || fragenkatalog.getModule_short().toLowerCase().contains(filterText))
                        && (fragenkatalog.getStatus().equals("published") || 
                        		(fragenkatalog.getStatus().equals("reported")))) {
                    filteredList.add(fragenkatalog);
                }
            }
            return filteredList;
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
    
    public String linkToEditTutor(Fragenkatalog frage) {
    	tempVariablen.setTempFrage(frage);
    	return "fragenkatalogFrageAnpassenTutor?faces-redirect=true";
    }
    
    public String linkToEditStudent(Fragenkatalog frage) {
    	tempVariablen.setTempFrage(frage);
    	return "fragenkatalogFrageAnpassenStudent?faces-redirect=true";
    }
    
    public String linkToReport(Fragenkatalog frage) {
    	tempVariablen.setTempFrage(frage);
    	return "fragenkatalogFrageMeldenStudent?faces-redirect=true";
    }
    
    public String linkToViewReportedQuestions(Fragenkatalog frage) {
    	tempVariablen.setTempFrage(frage);
    	return "fragenkatalogGemeldeteFragenAnsicht?faces-redirect=true";
    }
    
    public String formatDifficulty(int difficulty) {
    	if (difficulty == 1) return "Leicht";
    	else if (difficulty == 2) return "Mittel";
    	else if (difficulty == 3) return "Schwer";
    	else return "Keine Angabe";
    }
    
    public void checkTempFrageTutor() {
    	if (tempVariablen.getTempFrage() == null) {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "fragenkatalogAnzeigeTutor?faces-redirect=true");
	    }
    }
    
    public void checkTempFrageStudent() {
    	if (tempVariablen.getTempFrage() == null) {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "fragenkatalogAnzeigeStudent?faces-redirect=true");
	    }
    }
    
	 public String deleteFrage(Fragenkatalog frage)  {
        fragenkatalogDAO.deleteFrage(frage);
        fragenkatalogListe.getFragenkatalogListe().remove(frage);
        tempVariablen.setTempFrage(null);
        return "fragenkatalogAnzeigeTutor?faces-redirect=true";
    }
	 
	 public String updateFrage(Fragenkatalog frage) {
		frage.setStatus("in_progress");
        fragenkatalogDAO.updateFrage(frage);
        tempVariablen.setTempFrage(null);
        return "fragenkatalogAnzeigeTutor?faces-redirect=true";
    }
	 
	 public String setInProgressFrage(Fragenkatalog frage) {
			frage.setStatus("in_progress");
			deleteNotifications(frage);
			tempVariablen.setTempFrage(null);
	        return "fragenkatalogAnzeigeTutor?faces-redirect=true";
	    }
	 
	 public String publishFrage(Fragenkatalog frage) {
		 	frage.setStatus("published");
	        fragenkatalogDAO.updateFrage(frage);
	        tempVariablen.setTempFrage(null);
	        return "fragenkatalogAnzeigeTutor?faces-redirect=true";
	    }
	 
	 public String reportEditedFrage(Fragenkatalog frage) {
		 //TODO Neue Tabelle in SQL
		 //return "fragenkatalogFrageEingereichtErfolgreich?faces-redirect=true";
		 return "fragenkatalogFrageEingereichtErfolgreich?faces-redirect=true\"";
	 }
	 
	 public String createNewFrage(Fragenkatalog frage) {
		 //TODO Neue Fragen
		 //return "fragenkatalogFrageEingereichtErfolgreich?faces-redirect=true";
		 return "fragenkatalogFrageEingereichtErfolgreich?faces-redirect=true\"";
	 }
	 
	 public String deleteNotifications(Fragenkatalog frage) {
		 nachrichtenController.deleteAllMessages(frage);
		 //TODO Lösche Vorschläge zur Bearbeitung
		 frage.setStatus("published");
		 tempVariablen.setTempFrage(null);
		 return "fragenkatalogAnzeigeTutor?faces-redirect=true";
	 }
	 
	 public String cancelEditTutor() {
		 tempVariablen.setTempFrage(null);
		 return "fragenkatalogAnzeigeTutor?faces-redirect=true";
	 }
	 
	 public String cancelEditStudent() {
		 tempVariablen.setTempFrage(null);
		 return "fragenkatalogAnzeigeStudent?faces-redirect=true";
	 }
    
    public Fragenkatalog getNeueFrage() {

    	return this.neueFrage;
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
	
	public void setTempFrage(Fragenkatalog frage) {
		tempVariablen.setTempFrage(frage);
	}
	
	public Fragenkatalog getTempFrage() {
		return tempVariablen.getTempFrage();
	}
}
