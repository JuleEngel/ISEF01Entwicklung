//FragenkatalogController.java
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


/** 
* FragenkatalogController.java
* Methoden, die der Verwaltung des Fragenkatalogs dienen.
* Die Klasse FragenkatalogController.java enthält Funktionen zur Verwaltung von Fragen und zur korrekten / individuellen
* Darstellung auf den xhtml-Seiten.
* 
* @author JuleEngel 
* @version 1.0 
* @since 15.02.2024 
*/ 
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
    
    /**
     * Generiert den Link zur Seite des Fragenkatalogs basierend auf der Benutzerrolle.
     *
     * @return Der Link zur Seite des Fragenkatalogs.
     */
    public String linkToFragenkatalog() {
    	if (loginController.getUserLogin().getRole().equals("tutor")) {
    		return "/fragenkatalog/fragenkatalogAnzeigeTutor?faces-redirect=true";
    	}
    	else {
    		return "/fragenkatalog/fragenkatalogAnzeigeStudent?faces-redirect=true";
    	}
    }
    
    /**
     * Liefert eine Liste von Fragenkatalogen basierend auf dem Filter.
     * 
     * @param filter Der Status-Filter, der angewendet werden soll ("new", "reported", "in_progress" oder "published").
     * @return Eine Liste von Fragenkatalogen, die dem Filter entsprechen.
     */
    public List<Fragenkatalog> questionsResults(String filter) {
        String moduleFilter;
        // Bestimmt den ModulFilter basierend auf dem übergebenen Status-Filter.
        if (filter.equals("new")) {
            moduleFilter = moduleFilterNew;
        } else if (filter.equals("reported")) {
            moduleFilter = moduleFilterReported;
        } else if (filter.equals("in_progress")) {
            moduleFilter = moduleFilterInProgress;
        } else if (filter.equals("published")) {
            moduleFilter = moduleFilterPublished;
        } else {
            moduleFilter = null;
        }
        
        // Fall: Der Filter ist "published".
        if (filter.equals("published")) {
            // Wenn das Modulfilter leer ist oder nicht definiert ist, werden alle veröffentlichten und gemeldeten Fragen zurückgegeben.
            if (moduleFilter == null || moduleFilter.trim().isEmpty()) {
                List<Fragenkatalog> filteredList = new ArrayList<>();
                // Durchläuft die Liste der Fragenkataloge.
                for (Fragenkatalog fragenkatalog : fragenkatalogListe.getFragenkatalogListe()) {
                    // Wenn der Status "published" oder "reported" ist, wird die Frage zur gefilterten Liste hinzugefügt.
                    if (fragenkatalog.getStatus().equals("published") || fragenkatalog.getStatus().equals("reported")) {
                        filteredList.add(fragenkatalog);
                    }
                }
                return filteredList;
            }
            // Wenn ein Modulfilter definiert ist, werden Fragen basierend auf dem Modulfilter zurückgegeben.
            String filterText = moduleFilter.toLowerCase();
            List<Fragenkatalog> filteredList = new ArrayList<>();
            // Durchläuft die Liste der Fragenkataloge.
            for (Fragenkatalog fragenkatalog : fragenkatalogListe.getFragenkatalogListe()) {
                // Wenn das Modul der Frage im Modulfilter enthalten ist und der Status "published" oder "reported" ist, wird die Frage zur gefilterten Liste hinzugefügt.
                if ((modulesController.getModuleShort(fragenkatalog.getModule_id()).toLowerCase().contains(filterText) 
                        || modulesController.getModuleLong(fragenkatalog.getModule_id()).toLowerCase().contains(filterText))
                        && (fragenkatalog.getStatus().equals("published") || fragenkatalog.getStatus().equals("reported"))) {
                    filteredList.add(fragenkatalog);
                }
            }
            return filteredList;
        } 
        
        // Fall: Der Filter ist nicht "published".
        if (moduleFilter == null || moduleFilter.trim().isEmpty()) {
            List<Fragenkatalog> filteredList = new ArrayList<>();
            // Durchläuft die Liste der Fragenkataloge.
            for (Fragenkatalog fragenkatalog : fragenkatalogListe.getFragenkatalogListe()) {
                // Wenn der Status der Frage dem Filter entspricht, wird die Frage zur gefilterten Liste hinzugefügt.
                if (fragenkatalog.getStatus().equals(filter)) {
                    filteredList.add(fragenkatalog);
                }
            }
            return filteredList;
        }
        
        // Wenn ein Modulfilter definiert ist, werden Fragen basierend auf dem Modulfilter und dem Statusfilter zurückgegeben.
        String filterText = moduleFilter.toLowerCase();
        List<Fragenkatalog> filteredList = new ArrayList<>();
        // Durchläuft die Liste der Fragenkataloge.
        for (Fragenkatalog fragenkatalog : fragenkatalogListe.getFragenkatalogListe()) {
            // Wenn das Modul der Frage im Modulfilter enthalten ist und der Status der Frage dem Filter entspricht, wird die Frage zur gefilterten Liste hinzugefügt.
            if (modulesController.getModuleShort(fragenkatalog.getModule_id()).toLowerCase().contains(filterText) 
                    || modulesController.getModuleLong(fragenkatalog.getModule_id()).toLowerCase().contains(filterText)) {
                if (fragenkatalog.getStatus().equals(filter)) {
                    filteredList.add(fragenkatalog);
                }
            }
        }
        return filteredList;
    }

    
    /**
     * Formatiert die Schwierigkeit einer Frage basierend auf dem Schwierigkeitsgrad.
     * 
     * @param difficulty Der Schwierigkeitsgrad der Frage.
     * @return Eine Zeichenfolge, die den Schwierigkeitsgrad darstellt ("Leicht", "Mittel", "Schwer") oder eine leere Zeichenfolge, wenn der Schwierigkeitsgrad ungültig ist.
     */
    public String formatDifficulty(int difficulty) {
        if (difficulty == 1) return "Leicht";
        else if (difficulty == 2) return "Mittel";
        else if (difficulty == 3) return "Schwer";
        else return "";
    }    

    /**
     * Überprüft, ob eine Frage bereits im Fragenkatalog vorhanden ist.
     * 
     * @param context Der FacesContext.
     * @param comp Die UIComponent.
     * @param value Der Wert der Frage, die überprüft werden soll.
     * @throws ValidatorException Wenn die Frage bereits existiert, wird eine ValidatorException ausgelöst.
     */
    public void checkQuestion(FacesContext context, UIComponent comp, Object value) throws ValidatorException {
        String tempQuestion = (String) value;
        // Durchläuft die Liste der Fragenkataloge.
        for (Fragenkatalog frage : fragenkatalogListe.getFragenkatalogListe()) {
            // Überprüft, ob die Frage bereits existiert.
            if (frage.getQuestion().equals(tempQuestion)) {
                throw new ValidatorException(new FacesMessage("Diese Frage existiert bereits!"));
            }
        }
    }
    
    /**
     * Aktualisiert die Liste der Fragen im Fragenkatalog.
     */
    public void refreshFragenkatalog() {
        this.fragenkatalogListe = new FragenkatalogListe();
    }

    /**
     * Gibt die neue Frage zurück.
     * 
     * @return Die neue Frage.
     */
    public Fragenkatalog getNeueFrage() {
        return this.neueFrage;
    }

    /**
     * Gibt den Modulfilter für neue Fragen zurück.
     * 
     * @return Der Modulfilter für neue Fragen.
     */
    public String getModuleFilterNew() {
        return moduleFilterNew;
    }

    /**
     * Setzt den Modulfilter für neue Fragen.
     * 
     * @param moduleFilterNew Der Modulfilter für neue Fragen.
     */
    public void setModuleFilterNew(String moduleFilterNew) {
        this.moduleFilterNew = moduleFilterNew;
    }

    /**
     * Gibt den Modulfilter für gemeldete Fragen zurück.
     * 
     * @return Der Modulfilter für gemeldete Fragen.
     */
    public String getModuleFilterReported() {
        return moduleFilterReported;
    }

    /**
     * Setzt den Modulfilter für gemeldete Fragen.
     * 
     * @param moduleFilterReported Der Modulfilter für gemeldete Fragen.
     */
    public void setModuleFilterReported(String moduleFilterReported) {
        this.moduleFilterReported = moduleFilterReported;
    }

    /**
     * Gibt den Modulfilter für Fragen in Bearbeitung zurück.
     * 
     * @return Der Modulfilter für Fragen in Bearbeitung.
     */
    public String getModuleFilterInProgress() {
        return moduleFilterInProgress;
    }

    /**
     * Setzt den Modulfilter für Fragen in Bearbeitung.
     * 
     * @param moduleFilterInProgress Der Modulfilter für Fragen in Bearbeitung.
     */
    public void setModuleFilterInProgress(String moduleFilterInProgress) {
        this.moduleFilterInProgress = moduleFilterInProgress;
    }

    /**
     * Gibt den Modulfilter für veröffentlichte Fragen zurück.
     * 
     * @return Der Modulfilter für veröffentlichte Fragen.
     */
    public String getModuleFilterPublished() {
        return moduleFilterPublished;
    }

    /**
     * Setzt den Modulfilter für veröffentlichte Fragen.
     * 
     * @param moduleFilterPublished Der Modulfilter für veröffentlichte Fragen.
     */
    public void setModuleFilterPublished(String moduleFilterPublished) {
        this.moduleFilterPublished = moduleFilterPublished;
    }

    /**
     * Setzt die temporäre Frage.
     * 
     * @param frage Die temporäre Frage.
     */
    public void setTempFrage(Fragenkatalog frage) {
        tempVariablen.setTempFrage(frage);
    }

    /**
     * Gibt die temporäre Frage zurück.
     * 
     * @return Die temporäre Frage.
     */
    public Fragenkatalog getTempFrage() {
        return tempVariablen.getTempFrage();
    }

    /**
     * Gibt die temporäre Nachricht zurück.
     * 
     * @return Die temporäre Nachricht.
     */
    public String getTempNachricht() {
        return tempNachricht;
    }

    /**
     * Setzt die temporäre Nachricht.
     * 
     * @param tempNachricht Die temporäre Nachricht.
     */
    public void setTempNachricht(String tempNachricht) {
        this.tempNachricht = tempNachricht;
    }

    /**
     * Gibt das Modulkürzel des Moduls einer Frage zurück.
     * 
     * @param frage Die Frage, für die das Modulkürzel des Moduls abgerufen wird.
     * @return das Modulkürzel des Moduls.
     */
    public String getModule_short(Fragenkatalog frage) {
        return modulesController.getModuleShort(frage.getModule_id());
    }

    /**
     * Gibt den Modulnamen des Moduls einer Frage zurück.
     * 
     * @param frage Die Frage, für die der Modulnamen des Moduls abgerufen wird.
     * @return Der Modulnamen des Moduls.
     */
    public String getModule_long(Fragenkatalog frage) {
        return modulesController.getModuleLong(frage.getModule_id());
    }




	
    
    //Tutor-Bereich
    
    /**
     * Erzeugt einen Link zum Bearbeiten einer Frage für Tutoren.
     * Setzt die temporäre Frage und leitet zur Seite für die Bearbeitung einer Frage weiter.
     * 
     * @param frage Die zu bearbeitende Frage.
     * @return Der Link zur Bearbeitungsseite.
     */
    public String linkToEditTutor(Fragenkatalog frage) {
    	tempVariablen.setTempFrage(frage);
    	return "fragenkatalogFrageAnpassenTutor?faces-redirect=true";
    }    
    
    /**
     * Erzeugt einen Link zur Ansicht gemeldeter Fragen für Tutoren.
     * Setzt die temporäre Frage und leitet zur Seite für die Ansicht gemeldeter Fragen weiter.
     * 
     * @param frage Die zu betrachtende Frage.
     * @return Der Link zur Seite mit den gemeldeten Fragen.
     */
    public String linkToViewReportedQuestions(Fragenkatalog frage) {
        tempVariablen.setTempFrage(frage);
        return "fragenkatalogGemeldeteFragenAnsicht?faces-redirect=true";
    }

    /**
     * Erzeugt einen Link zum Erstellen einer neuen Frage für Tutoren.
     * Erstellt eine neue Frage und leitet zur Seite für die Frageerstellung weiter.
     * 
     * @return Der Link zur Frageerstellungsseite.
     */
    public String linkToCreateTutor() {
        neueFrage = new Fragenkatalog();
        return "fragenkatalogFrageErstellenTutor?faces-redirect=true";
    }

    /**
     * Verwendet eine gemeldete Frage.
     * Kopiert die Werte der bearbeiteten Frage auf die temporäre Frage.
     * Aktualisiert die Frage in der Datenbank.
     * Aktualisiert die Listen und löscht alle Nachrichten zur Frage und setzt sie auf "published".
     * Setzt die temporäre Frage wieder zurück
     * 
     * @param frage Die bearbeitete Frage.
     * @return Der Link zur Tutoransicht des Fragenkatalogs.
     */
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
    
    /**
     * Überprüft, ob eine temporäre Frage für Tutoren vorhanden ist.
     * Falls nicht, leitet es den Benutzer zur Tutoransicht des Fragenkatalogs weiter.
     */
    public void checkTempFrageTutor() {
        if (tempVariablen.getTempFrage() == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "fragenkatalogAnzeigeTutor?faces-redirect=true");
        }
    }

    /**
     * Löscht eine Frage aus dem Fragenkatalog für Tutoren.
     * Löscht auch alle zugehörigen Benachrichtigungen und setzt die temporäre Frage auf null.
     * 
     * @param frage Die zu löschende Frage.
     * @return Der Link zur Tutoransicht des Fragenkatalogs.
     */
    public String deleteFrage(Fragenkatalog frage)  {
        deleteNotifications(frage);
        fragenkatalogDAO.deleteFrage(frage);
        fragenkatalogListe.getFragenkatalogListe().remove(frage);
        tempVariablen.setTempFrage(null);
        return "fragenkatalogAnzeigeTutor?faces-redirect=true";
    }

    /**
     * Setzt den Status einer Frage auf "in_progress" und aktualisiert die Frage.
     * Setzt auch die temporäre Frage auf null.
     * 
     * @param frage Die zu aktualisierende Frage.
     * @return Der Link zur Tutoransicht des Fragenkatalogs.
     */
    public String updateFrage(Fragenkatalog frage) {
        frage.setStatus("in_progress");
        fragenkatalogDAO.updateFrage(frage);
        tempVariablen.setTempFrage(null);
        return "fragenkatalogAnzeigeTutor?faces-redirect=true";
    }

    /**
     * Setzt den Status einer Frage auf "in_progress".
     * Löscht auch alle zugehörigen Benachrichtigungen und setzt die temporäre Frage auf null.
     * 
     * @param frage Die zu aktualisierende Frage.
     * @return Der Link zur Tutoransicht des Fragenkatalogs.
     */
    public String setInProgressFrage(Fragenkatalog frage) {
        deleteNotifications(frage);
        fragenkatalogDAO.updateStatus(frage.getId(), "in_progress");
        tempVariablen.setTempFrage(null);
        return "fragenkatalogAnzeigeTutor?faces-redirect=true";
    }

    /**
     * Veröffentlicht eine Frage im Fragenkatalog.
     * Löscht auch alle zugehörigen Benachrichtigungen und setzt die temporäre Frage auf null.
     * 
     * @param frage Die zu veröffentlichende Frage.
     * @return Der Link zur Tutoransicht des Fragenkatalogs.
     */
    public String publishFrage(Fragenkatalog frage) {
        deleteNotifications(frage);
        frage.setStatus("published");
        fragenkatalogDAO.updateFrage(frage);
        tempVariablen.setTempFrage(null);
        return "fragenkatalogAnzeigeTutor?faces-redirect=true";
    }

    /**
     * Gibt eine Liste von durch Studenten bearbeitete Fragen zurück, die zu einer bestimmten Frage gehören.
     * 
     * @param frage Die Frage, zu der die bearbeiteten Fragen gefiltert werden sollen.
     * @return Eine Liste von bearbeiteten Fragen, die zur angegebenen Frage gehören.
     */
    public List<FragenkatalogBearbeitet> getEditedFragenListeZuFrage(Fragenkatalog frage) {
        fragenkatalogBearbeitetListe = new FragenkatalogBearbeitetListe();
        List<FragenkatalogBearbeitet> filteredList = new ArrayList<>();
        for (FragenkatalogBearbeitet frageBearbeitet : fragenkatalogBearbeitetListe.getFragenkatalogBearbeitetListe()) {
            if (frageBearbeitet.getQuestion_id() == frage.getId()) {
                filteredList.add(frageBearbeitet);
            }
        }
        return filteredList;
    }

    /**
     * Gibt den Benutzer zurück, der eine Frage gemeldet hat.
     * 
     * @param frage Die bearbeitete Frage, für die der Benutzer zurückgegeben werden soll.
     * @return Der Benutzer, der die Frage gemeldet hat.
     */
    public String getUserReported(FragenkatalogBearbeitet frage) {
        User userReported = userListe.getUserFromList(frage.getUser_id());
        int idUserReported = userReported.getId();
        String nameUserReported = userReported.getUsername();
        return nameUserReported + "(" + idUserReported + ")";
    }

    /**
     * Löscht eine bearbeitete Frage aus der Datenbank und der Liste der bearbeiteten Fragen.
     * Überprüft dann den Status der zugehörigen Frage, um festzustellen, ob sie veröffentlicht werden kann.
     * 
     * @param frage Die zu löschende bearbeitete Frage.
     */
    public void deleteEditedFrage(FragenkatalogBearbeitet frage) {
        fragenkatalogBearbeitetDAO.deleteFrage(frage);
        fragenkatalogBearbeitetListe.getFragenkatalogBearbeitetListe().remove(frage);
        checkReportFrage();
    }

    /**
     * Überprüft den Status einer gemeldeten Frage und veröffentlicht sie, wenn keine bearbeiteten Fragen oder Nachrichten dazu vorhanden sind.
     */
    public void checkReportFrage() {
        if (tempVariablen.getTempFrage().getStatus().equals("reported")) {
            if (getEditedFragenListeZuFrage(tempVariablen.getTempFrage()).isEmpty()
                    && nachrichtenController.getNachrichtenListeZuFrage(tempVariablen.getTempFrage()).isEmpty()) {
                fragenkatalogDAO.updateStatus(tempVariablen.getTempFrage().getId(), "published");
            }
        }
    }
   
    /**
     * Löscht alle Benachrichtigungen und bearbeiteten Fragen zu einer bestimmten Frage.
     * 
     * @param frage Die Frage, für die alle Benachrichtigungen und bearbeiteten Fragen gelöscht werden sollen.
     */
    public void deleteNotifications(Fragenkatalog frage) {
        nachrichtenController.deleteAllMessages(frage);
        for (FragenkatalogBearbeitet frageBearbeitet : getEditedFragenListeZuFrage(frage)) {
            fragenkatalogBearbeitetDAO.deleteFrage(frageBearbeitet);
        }
    }

    /**
     * Bricht den Bearbeitungsmodus einer Frage ab und leitet den Benutzer zur Fragenansicht für Tutoren weiter.
     * 
     * @return Die Navigationsregel für die Weiterleitung zur Fragenansicht für Tutoren.
     */
    public String cancelEditTutor() {
        tempVariablen.setTempFrage(null);
        return "fragenkatalogAnzeigeTutor?faces-redirect=true";
    }

    /**
     * Bricht die Ansicht gemeldeter Fragen für Tutoren ab und überprüft den Status der gemeldeten Frage.
     * 
     * @return Die Navigationsregel für die Weiterleitung zur Fragenansicht für Tutoren.
     */
    public String cancelShowReportedTutor() {
        checkReportFrage();
        tempVariablen.setTempFrage(null);
        return "fragenkatalogAnzeigeTutor?faces-redirect=true";
    }

    /**
     * Bricht den Erstellungsmodus einer Frage für Tutoren ab und leitet den Benutzer zur Fragenansicht für Tutoren weiter.
     * 
     * @return Die Navigationsregel für die Weiterleitung zur Fragenansicht für Tutoren.
     */
    public String cancelCreateTutor() {
        neueFrage = new Fragenkatalog();
        return "fragenkatalogAnzeigeTutor?faces-redirect=true";
    }

    /**
     * Erstellt eine neue Frage für Tutoren und setzt ihren Status. Leitet den Benutzer dann zur Fragenansicht für Tutoren weiter.
     * 
     * @param frage Die neue Frage, die erstellt werden soll.
     * @param status Der Status, der der neuen Frage zugewiesen werden soll.
     * @return Die Navigationsregel für die Weiterleitung zur Fragenansicht für Tutoren.
     */
    public String createFrageTutor(Fragenkatalog frage, String status) {
        frage.setStatus(status);
        fragenkatalogDAO.createFrage(frage);
        neueFrage = new Fragenkatalog();
        return "fragenkatalogAnzeigeTutor?faces-redirect=true\"";
    }

	 
	 
     
    
    
    //Studenten-Bereich
    
    /**
     * Leitet den Benutzer zur Seite zur Bearbeitung einer Frage für Studenten weiter.
     * 
     * @param frage Die zu bearbeitende Frage.
     * @return Die Navigationsregel für die Weiterleitung zur Seite zur Bearbeitung einer Frage für Studenten.
     */
    public String linkToEditStudent(Fragenkatalog frage) {
        tempVariablen.setTempFrage(frage);
        return "fragenkatalogFrageAnpassenStudent?faces-redirect=true";
    }

    /**
     * Leitet den Benutzer zur Seite zum Melden einer Frage für Studenten weiter.
     * 
     * @param frage Die zu meldende Frage.
     * @return Die Navigationsregel für die Weiterleitung zur Seite zum Melden einer Frage für Studenten.
     */
    public String linkToReport(Fragenkatalog frage) {
        tempVariablen.setTempFrage(frage);
        return "fragenkatalogFrageMeldenStudent?faces-redirect=true";
    }

    /**
     * Leitet den Benutzer zur Seite zur Erstellung einer neuen Frage für Studenten weiter.
     * 
     * @return Die Navigationsregel für die Weiterleitung zur Seite zur Erstellung einer neuen Frage für Studenten.
     */
    public String linkToCreateStudent() {
        neueFrage = new Fragenkatalog();
        return "fragenkatalogFrageErstellenStudent?faces-redirect=true";
    }

    /**
     * Überprüft, ob eine temporäre Frage für Studenten vorhanden ist. 
     * Wenn nicht, leitet es den Benutzer zur Fragenansicht für Studenten weiter.
     */
    public void checkTempFrageStudent() {
        if (tempVariablen.getTempFrage() == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "fragenkatalogAnzeigeStudent?faces-redirect=true");
        }
    }

    /**
     * Meldet eine bearbeitete Frage für Studenten und aktualisiert ihren Status sowie die Benachrichtigungen. 
     * Setzt dann die temporäre Frage zurück und leitet den Benutzer zur Erfolgsmeldungsseite weiter.
     * 
     * @param frage Die bearbeitete Frage, die gemeldet werden soll.
     * @return Die Navigationsregel für die Weiterleitung zur Erfolgsmeldungsseite.
     */
    public String reportEditedFrage(Fragenkatalog frage) {
        User loggedInUser = loginController.getUserLogin();
        fragenkatalogBearbeitetDAO.createFrage(loggedInUser.getId(), frage, tempNachricht);
        fragenkatalogDAO.updateStatus(frage.getId(), "reported");
        tempNachricht = "";
        tempVariablen.setTempFrage(null);
        return "fragenkatalogFrageEingereichtErfolgreich?faces-redirect=true\"";
    }

    /**
     * Bricht den Bearbeitungsmodus einer Frage für Studenten ab und leitet den Benutzer zur Fragenansicht für Studenten weiter.
     * 
     * @return Die Navigationsregel für die Weiterleitung zur Fragenansicht für Studenten.
     */
    public String cancelEditStudent() {
        tempVariablen.setTempFrage(null);
        return "fragenkatalogAnzeigeStudent?faces-redirect=true";
    }

    /**
     * Bricht den Erstellungsmodus einer Frage für Studenten ab, setzt die neue Frage zurück und 
     * leitet den Benutzer zur Fragenansicht für Studenten weiter.
     * 
     * @return Die Navigationsregel für die Weiterleitung zur Fragenansicht für Studenten.
     */
    public String cancelCreateStudent() {
        neueFrage = new Fragenkatalog();
        return "fragenkatalogAnzeigeStudent?faces-redirect=true";
    }

    /**
     * Erstellt eine neue Frage für Studenten mit einem Schwierigkeitsgrad von 0 und dem Status "new". 
     * Setzt dann die neue Frage zurück und leitet den Benutzer zur Erfolgsmeldungsseite weiter.
     * 
     * @param frage Die neue Frage, die erstellt werden soll.
     * @return Die Navigationsregel für die Weiterleitung zur Erfolgsmeldungsseite.
     */
    public String createFrageStudent(Fragenkatalog frage) {
        frage.setDifficulty(0);
        frage.setStatus("new");
        fragenkatalogDAO.createFrage(frage);
        neueFrage = new Fragenkatalog();
        return "fragenkatalogFrageEingereichtErfolgreich?faces-redirect=true\"";
    }

}
