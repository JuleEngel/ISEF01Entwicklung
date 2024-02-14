package fragenkatalog;


import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import login.LoginController;
import login.User;
import login.UserListe;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class FragenkatalogController implements Serializable
{
    private Fragenkatalog neueFrage = new Fragenkatalog();
    private FragenkatalogDAO fragenkatalogDAO = new FragenkatalogDAO();
    private FragenkatalogBearbeitetDAO fragenkatalogBearbeitetDAO = new FragenkatalogBearbeitetDAO();
    private String moduleFilterNew;
    private String moduleFilterReported;
    private String moduleFilterInProgress;
    private String moduleFilterPublished;
    private String tempNachricht;
    
    @Inject
    private TempVariablen tempVariablen;
    @Inject
    private FragenkatalogListe fragenkatalogListe = new FragenkatalogListe();
    @Inject
    private FragenkatalogBearbeitetListe fragenkatalogBearbeitetListe = new FragenkatalogBearbeitetListe();
    @Inject
    private NachrichtenController nachrichtenController;
    @Inject
    private LoginController loginController;
    @Inject
    private UserListe userListe;
    @Inject
    private ModulesController modulesController;
    
    
    
    //Allgemeine Methoden
    public String linkToFragenkatalog() {
    	if (loginController.getUserLogin().getRole().equals("tutor")) {
    		return "/fragenkatalog/fragenkatalogAnzeigeTutor?faces-redirect=true";
    	}
    	else {
    		return "/fragenkatalog/fragenkatalogAnzeigeStudent?faces-redirect=true";
    	}
    }
    
    public List<Fragenkatalog> questionsResults(String filter) {
    	String moduleFilter;
    	if (filter.equals("new")) {
    		moduleFilter = moduleFilterNew;
    	}
    	else if (filter.equals("reported")) {
    		moduleFilter = moduleFilterReported;
    	}
    	else if (filter.equals("in_progress")) {
    		moduleFilter = moduleFilterInProgress;
    	}
    	else if (filter.equals("published")) {
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
                if ((modulesController.getModuleShort(fragenkatalog.getModule_id()).toLowerCase().contains(filterText) 
                				|| modulesController.getModuleLong(fragenkatalog.getModule_id()).toLowerCase().contains(filterText))
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
        	if (modulesController.getModuleShort(fragenkatalog.getModule_id()).toLowerCase().contains(filterText) 
    				|| modulesController.getModuleLong(fragenkatalog.getModule_id()).toLowerCase().contains(filterText)) {
            	if (fragenkatalog.getStatus().equals(filter)) {
                    filteredList.add(fragenkatalog);
                }
            }
        }
        return filteredList;
    }
    
    public String formatDifficulty(int difficulty) {
    	if (difficulty == 1) return "Leicht";
    	else if (difficulty == 2) return "Mittel";
    	else if (difficulty == 3) return "Schwer";
    	else return "";
    }    
    
    public void checkQuestion(FacesContext context, UIComponent comp, Object value) throws ValidatorException{
        String tempQuestion = (String) value;
        for (Fragenkatalog frage : fragenkatalogListe.getFragenkatalogListe()) {
	        if (frage.getQuestion().equals(tempQuestion)) {
	        	throw new ValidatorException(new FacesMessage("Diese Frage existiert bereits!"));
	        }
        }
    }
    
	public void refreshFragenkatalog() {
		 this.fragenkatalogListe = new FragenkatalogListe();
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

	public String getTempNachricht() {
		return tempNachricht;
	}

	public void setTempNachricht(String tempNachricht) {
		this.tempNachricht = tempNachricht;
	}
	
	public String getModule_short(Fragenkatalog frage) {
		return modulesController.getModuleShort(frage.getModule_id());
	}
	
	public String getModule_long(Fragenkatalog frage) {
		return modulesController.getModuleLong(frage.getModule_id());
	}
    
    
	
	
	
    
    //Tutor-Bereich
    public String linkToEditTutor(Fragenkatalog frage) {
    	tempVariablen.setTempFrage(frage);
    	return "fragenkatalogFrageAnpassenTutor?faces-redirect=true";
    }    
    
    public String linkToViewReportedQuestions(Fragenkatalog frage) {
    	tempVariablen.setTempFrage(frage);
    	return "fragenkatalogGemeldeteFragenAnsicht?faces-redirect=true";
    }    
    
    public String linkToCreateTutor() {
    	neueFrage = new Fragenkatalog();
    	return "fragenkatalogFrageErstellenTutor?faces-redirect=true";
    }
    
    public String useReportedFrage(FragenkatalogBearbeitet frage) {
    	//Kopiere Werte von bearbeiteter Frage auf TempFrage
    	tempVariablen.editFrage(frage);
    	//Aktualisiere Frage in der Datenbank
    	fragenkatalogDAO.updateFrage(tempVariablen.getTempFrage());
    	//Aktualisiere Listen
    	nachrichtenController.updateNachrichtenListe();
		fragenkatalogBearbeitetListe = new FragenkatalogBearbeitetListe();
    	//Lösche alle Nachrichten zur Frage und setze sie auf "published"
		fragenkatalogDAO.updateStatus(tempVariablen.getTempFrage().getId(), "published");
		deleteNotifications(tempVariablen.getTempFrage());
		tempVariablen.setTempFrage(null);
    	tempNachricht = "";
    	return "fragenkatalogAnzeigeTutor?faces-redirect=true";
    }   
    
    public void checkTempFrageTutor() {
    	if (tempVariablen.getTempFrage() == null) {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "fragenkatalogAnzeigeTutor?faces-redirect=true");
	    }
    }    
    
	public String deleteFrage(Fragenkatalog frage)  {
		deleteNotifications(frage);
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
		deleteNotifications(frage);
		fragenkatalogDAO.updateStatus(frage.getId(), "in_progress");
		tempVariablen.setTempFrage(null);
        return "fragenkatalogAnzeigeTutor?faces-redirect=true";
    }
	 
	 public String publishFrage(Fragenkatalog frage) {
	 	deleteNotifications(frage);
	 	frage.setStatus("published");
        fragenkatalogDAO.updateFrage(frage);
        tempVariablen.setTempFrage(null);
        return "fragenkatalogAnzeigeTutor?faces-redirect=true";
    }	

	 public List<FragenkatalogBearbeitet> getEditedFragenListeZuFrage (Fragenkatalog frage) {
		 fragenkatalogBearbeitetListe = new FragenkatalogBearbeitetListe();
		 List<FragenkatalogBearbeitet> filteredList = new ArrayList<>();
		 for (FragenkatalogBearbeitet frageBearbeitet : fragenkatalogBearbeitetListe.getFragenkatalogBearbeitetListe()) {
    		if (frageBearbeitet.getQuestion_id() == frage.getId()) {
    			filteredList.add(frageBearbeitet);
    		}
		 }
		 return filteredList;
	 }	 

     public String getUserReported(FragenkatalogBearbeitet frage) {
    	User userReported = userListe.getUserFromList(frage.getUser_id());
    	int idUserReported = userReported.getId();
    	String nameUserReported = userReported.getUsername();
    	return nameUserReported + "(" + idUserReported + ")";
     }

	 public void deleteEditedFrage(FragenkatalogBearbeitet frage) {
		 fragenkatalogBearbeitetDAO.deleteFrage(frage);
		 fragenkatalogBearbeitetListe.getFragenkatalogBearbeitetListe().remove(frage);
		 checkReportFrage();
	 }
	 
	 public void checkReportFrage() {
		 if (tempVariablen.getTempFrage().getStatus().equals("reported")) {
			 if (getEditedFragenListeZuFrage(tempVariablen.getTempFrage()).isEmpty()
					 && nachrichtenController.getNachrichtenListeZuFrage(tempVariablen.getTempFrage()).isEmpty()) {
		    	 fragenkatalogDAO.updateStatus(tempVariablen.getTempFrage().getId(), "published");
		     }
		 }
	 }
   
	 public void deleteNotifications(Fragenkatalog frage) {
		 nachrichtenController.deleteAllMessages(frage);
		 for (FragenkatalogBearbeitet frageBearbeitet : getEditedFragenListeZuFrage(frage)) {
			 fragenkatalogBearbeitetDAO.deleteFrage(frageBearbeitet);
		 }
	 }
	 
	 public String cancelEditTutor() {
		 tempVariablen.setTempFrage(null);
		 return "fragenkatalogAnzeigeTutor?faces-redirect=true";
	 }
	 
	 public String cancelShowReportedTutor() {
		 checkReportFrage();
		 tempVariablen.setTempFrage(null);
		 return "fragenkatalogAnzeigeTutor?faces-redirect=true";
	 }

	 public String cancelCreateTutor() {
		 neueFrage = new Fragenkatalog();
		 return "fragenkatalogAnzeigeTutor?faces-redirect=true";
	 }
    
	 public String createFrageTutor(Fragenkatalog frage, String status) {
		 frage.setStatus(status);
		 fragenkatalogDAO.createFrage(frage);
		 neueFrage = new Fragenkatalog();
		 return "fragenkatalogAnzeigeTutor?faces-redirect=true\"";
	 }
	 
	 
     
    
    
    //Studenten-Bereich
    public String linkToEditStudent(Fragenkatalog frage) {
		tempVariablen.setTempFrage(frage);
		return "fragenkatalogFrageAnpassenStudent?faces-redirect=true";
    }

    public String linkToReport(Fragenkatalog frage) {
    	tempVariablen.setTempFrage(frage);
    	return "fragenkatalogFrageMeldenStudent?faces-redirect=true";
    }
    
    public String linkToCreateStudent() {
    	neueFrage = new Fragenkatalog();
    	return "fragenkatalogFrageErstellenStudent?faces-redirect=true";
    }
    
    public void checkTempFrageStudent() {
    	if (tempVariablen.getTempFrage() == null) {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "fragenkatalogAnzeigeStudent?faces-redirect=true");
	    }
    }
    
	 public String reportEditedFrage(Fragenkatalog frage) {
		 User loggedInUser = loginController.getUserLogin();
		 fragenkatalogBearbeitetDAO.createFrage(loggedInUser.getId(), frage, tempNachricht);
		 fragenkatalogDAO.updateStatus(frage.getId(), "reported");
		 tempNachricht = "";
		 tempVariablen.setTempFrage(null);
		 return "fragenkatalogFrageEingereichtErfolgreich?faces-redirect=true\"";
	 }
    
	 public String cancelEditStudent() {
		 tempVariablen.setTempFrage(null);
		 return "fragenkatalogAnzeigeStudent?faces-redirect=true";
	 }
	 
	 public String cancelCreateStudent() {
		 neueFrage = new Fragenkatalog();
		 return "fragenkatalogAnzeigeStudent?faces-redirect=true";
	 }
    
	 public String createFrageStudent(Fragenkatalog frage) {
		 frage.setDifficulty(0);
		 frage.setStatus("new");
		 fragenkatalogDAO.createFrage(frage);
		 neueFrage = new Fragenkatalog();
		 return "fragenkatalogFrageEingereichtErfolgreich?faces-redirect=true\"";
	 }
}
