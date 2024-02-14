//LoginController.java
package login;
import java.io.Serializable;
import java.util.Map;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/** 
* LoginController.java
* Login-Funktionen incl. Inaktivitätsprüfung.
* Die Klasse LoginController.java enthält Funktionen zur temporären Erstellung von Usern, Login-Validierung, Login, Logout,
* Inaktivitätsprüfung und Formatierung.
* 
* @author JuleEngel 
* @version 1.0 
* @since 01.02.2024 
*/ 
@SuppressWarnings("serial")
@Named
@SessionScoped
public class LoginController implements Serializable {
    private User userLogin = new User(); //User der Nutzereingaben zum Login.
    private String tempName; //Temporäre Sicherung Nutzername.
    private boolean isLoggedIn = false; //Login-Sicherung.
    private static final long INACTIVITY_TIMEOUT = 30 * 60 * 1000; //30 Minuten bis zum automatischen Ausloggen
    
    @Inject
    private UserListe userListe = new UserListe();

    /** 
    * Aufruf User-Datei. 
    * @return User-Objekt anhand der Nutzereingaben. 
    */ 
	public User getUserLogin() {
		return userLogin;
	}
	
	/**
	 * Diese Methode wird nach der Validierung des Namensfelds aufgerufen.
	 * Sie extrahiert den eingegebenen Namen aus dem übergebenen ComponentSystemEvent,
	 * aktualisiert das temporäre Name-Attribut und ermöglicht weitere Verarbeitungsschritte.
	 * 
	 * @param event Das ComponentSystemEvent, das Informationen über die Komponente enthält, auf der die Validierung stattgefunden hat.
	 * @throws AbortProcessingException Wenn die Verarbeitung abgebrochen werden soll. Dies kann beispielsweise bei einer Validierungsverletzung der Fall sein.
	 */
	public void postValidateName(ComponentSystemEvent event) throws AbortProcessingException{
		UIInput temp = (UIInput)event.getComponent();
		this.tempName = (String)temp.getValue();
	}
	
	/**
	 * Diese Methode validiert die eingegebenen Login-Daten.
	 * Sie erstellt ein temporäres User-Objekt mit dem temporären Namen und dem eingegebenen Passwort.
	 * Anschließend wird überprüft, ob ein entsprechendes Benutzerobjekt in der Benutzerliste vorhanden ist.
	 * Wenn eine Übereinstimmung gefunden wird, werden die Benutzerdaten aktualisiert, der Benutzer als eingeloggt markiert
	 * und die Methode kehrt zurück.
	 * Andernfalls wird eine ValidatorException mit einer entsprechenden Fehlermeldung ausgelöst.
	 *
	 * @param context   Der FacesContext, der Informationen über das aktuelle JSF-Anwendungsverhalten enthält.
	 * @param component Die UI-Komponente, die validiert wird (hier nicht direkt verwendet).
	 * @param value     Der eingegebene Wert, der in diesem Fall das Passwort darstellt.
	 * @throws ValidatorException Wenn die Validierung fehlschlägt, wird diese Ausnahme mit einer Fehlermeldung ausgelöst.
	 *                            Dies geschieht, wenn keine Übereinstimmung für die eingegebenen Login-Daten gefunden wird.
	 */
	public void validateLogin(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		User temp = new User(this.tempName, (String) value);
		for (User tempUserListe : userListe.getUserListe()) {
			if (tempUserListe.equals(temp)) {
				this.userLogin.setId(tempUserListe.getId());
				this.userLogin.setUsername(tempUserListe.getUsername());
				this.userLogin.setEmail(tempUserListe.getEmail());
				this.userLogin.setRole(tempUserListe.getRole());
				this.userLogin.setPlayedgames(tempUserListe.getPlayedgames());
				this.isLoggedIn = true;
				return;
			}
		}
		throw new ValidatorException(new FacesMessage("Falsche Login-Daten!"));
	}
	
	/**
	 * Diese Methode verarbeitet den Login-Vorgang.
	 * Überprüft, ob der Benutzer bereits eingeloggt ist. Wenn ja, wird die letzte Aktivitätszeit in der Sitzung aktualisiert,
	 * und der Benutzer wird auf die Erfolgsseite weitergeleitet. Andernfalls wird der Benutzer auf die Login-Seite weitergeleitet.
	 *
	 * @return Eine Zielseite, abhängig davon, ob der Benutzer eingeloggt ist oder nicht.
	 *         - "loginSuccess?faces-redirect=true": Erfolgsseite, wenn der Benutzer bereits eingeloggt ist.
	 *         - "loginPage?faces-redirect=true": Login-Seite, wenn der Benutzer nicht eingeloggt ist.
	 */
	public String login() {
		if (this.isLoggedIn) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        Map<String, Object> sessionMap = facesContext.getExternalContext().getSessionMap();
	        sessionMap.put("lastActivityTime", System.currentTimeMillis());
	        if (userLogin.getRole().equals("tutor")) {
	        	return "/mainpage/indexTutor?faces-redirect=true";
	        }
	        else {
	        	return "/mainpage/indexStudent?faces-redirect=true";
	        }
		}
		else {
			return "loginPage?faces-redirect=true";
		}
	}
	
	/**
	 * Diese Methode verarbeitet den Logout-Vorgang.
	 * Setzt den Status des Benutzers auf "nicht eingeloggt", erstellt einen neuen leeren Benutzer und invalidiert die aktuelle Sitzung.
	 * Der Benutzer wird dann auf die Login-Seite weitergeleitet.
	 *
	 * @return Eine Zielseite für die Weiterleitung nach dem erfolgreichen Logout.
	 *         "login/logout?faces-redirect=true": Die Login-Seite mit einem Redirect, um die URL zu aktualisieren.
	 */
	public String logout() {
	    this.isLoggedIn = false;
	    userLogin = new User();
	    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	    return "/login/logout?faces-redirect=true";
	}
	
	/**
	 * Diese Methode überprüft, ob der Benutzer eingeloggt ist, ob seine Sitzung abgelaufen ist oder ob er inaktiv ist.
	 * - Falls der Benutzer nicht eingeloggt ist, wird die Sitzung ungültig gemacht und zur Seite "login/loginFailed" weitergeleitet.
	 * - Falls der Benutzer inaktiv ist oder die Sitzung abgelaufen ist, wird er ausgeloggt, die Sitzung ungültig gemacht und zur Seite "login/logout" weitergeleitet.
	 * - Andernfalls wird der Zeitstempel für die letzte Aktivität aktualisiert.
	 */
	public void checkLoggedInStudent() {
	    if (!this.isLoggedIn) {
	    	isLoggedIn = false;
	    	userLogin = new User();
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.getExternalContext().invalidateSession();
	        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "/login/loginFailed?faces-redirect=true");
	    }
	    else if (isInactive()) {
	    	isLoggedIn = false;
	    	userLogin = new User();
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.getExternalContext().invalidateSession();
	        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "/login/logout?faces-redirect=true");
	    }
	    else {
	    	updateActivityTimestamp();
	    }
	}
	
	/**
	 * Diese Methode überprüft, ob der Benutzer eingeloggt und Tutor ist, ob seine Sitzung abgelaufen ist oder ob er inaktiv ist.
	 * - Falls der Benutzer nicht eingeloggt ist, wird die Sitzung ungültig gemacht und zur Seite "login/loginFailed" weitergeleitet.
	 * - Falls der Benutzer inaktiv ist oder die Sitzung abgelaufen ist, wird er ausgeloggt, die Sitzung ungültig gemacht und zur Seite "login/logout" weitergeleitet.
	 * - Andernfalls wird der Zeitstempel für die letzte Aktivität aktualisiert.
	 */
	public void checkLoggedInTutor() {
	    if (!this.isLoggedIn) {
	    	isLoggedIn = false;
	    	userLogin = new User();
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.getExternalContext().invalidateSession();
	        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "/login/loginFailed?faces-redirect=true");
	    }
	    else if (isInactive()) {
	    	isLoggedIn = false;
	    	userLogin = new User();
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.getExternalContext().invalidateSession();
	        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "/login/logout?faces-redirect=true");
	    }
	    else if (!userLogin.getRole().equals("tutor")) {
	    	FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.getExternalContext().invalidateSession();
	        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "/login/accessDenied?faces-redirect=true");
	    }
	    else {
	    	updateActivityTimestamp();
	    }
	}
	
	/**
	 * Diese Methode aktualisiert den Zeitstempel für die Benutzeraktivität in der Sitzung.
	 * - Falls bereits ein Zeitstempel vorhanden ist, wird er aktualisiert.
	 * - Falls kein Zeitstempel vorhanden ist, wird ein neuer Zeitstempel erstellt und in der Sitzung gespeichert.
	 */
	public void updateActivityTimestamp() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = facesContext.getExternalContext().getSessionMap();

        if (sessionMap.containsKey("lastActivityTime")) {
            // Benutzeraktivität gefunden, aktualisiere den Zeitstempel
            sessionMap.put("lastActivityTime", System.currentTimeMillis());
        } else {
            // Erster Aufruf, setze den Zeitstempel
            sessionMap.put("lastActivityTime", System.currentTimeMillis());
        }
	}
	
	/**
	 * Diese Methode überprüft, ob der Benutzer inaktiv ist, basierend auf dem Zeitstempel der letzten Aktivität.
	 *
	 * @return true, wenn der Benutzer inaktiv ist, andernfalls false.
	 */
	public boolean isInactive() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String, Object> sessionMap = facesContext.getExternalContext().getSessionMap();
		long lastActivityTime = (Long) sessionMap.getOrDefault("lastActivityTime", System.currentTimeMillis());
        long currentTime = System.currentTimeMillis();
        long inactiveDuration = currentTime - lastActivityTime;
        return inactiveDuration > INACTIVITY_TIMEOUT;
	}
	
	/**
	 * Diese Methode formatiert den Eingabestring, indem der erste Teil vor dem Trennungszeichen '-' extrahiert wird.
	 * Das Ergebnis wird dann so umgeformt, dass der erste Buchstabe großgeschrieben ist, gefolgt von Kleinbuchstaben.
	 *
	 * @param input Der Eingabestring, der formatiert werden soll.
	 * @return Der formatierte Name.
	 */
	public String formatInput(String input) {
		String shortName = input.split("-")[0];
		StringBuilder formattedName = new StringBuilder();
		formattedName.append(Character.toUpperCase(shortName.charAt(0)));
		formattedName.append(shortName.substring(1));
		return formattedName.toString();
	}
}