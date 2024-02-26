//MainController.java
package main;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import login.LoginController;

import java.io.Serializable;

/** 
* MainController.java
* Methoden, die über alle xhtml-Seiten hinweg gehen.
* Die Klasse MainController.java enthält Funktionen zur Navigation zwischen verschiedenen Seiten.
* 
* @author JuleEngel 
* @version 1.0 
* @since 15.02.2024 
*/ 
@SuppressWarnings("serial")
@Named
@ViewScoped
public class MainController implements Serializable
{
    /**
     * Der Login-Controller, der für die Authentifizierung der Benutzer verwendet wird.
     */
	@Inject
    LoginController loginController;

    /**
     * Standardkonstruktor für den Hauptcontroller.
     * Erzeugt eine Instanz des Hauptcontrollers.
     */
    public MainController() {
        loginController = new LoginController();
    }
	
	/**
	 * Generiert einen Link zur Hauptseite basierend auf der Rolle des eingeloggten Benutzers.
	 *
	 * @return Ein String, der den Link zur Hauptseite repräsentiert. Wenn der Benutzer ein Tutor ist, wird "/mainpage/indexTutor?faces-redirect=true" zurückgegeben;
	 * wenn der Benutzer kein Tutor ist, wird "/mainpage/indexStudent?faces-redirect=true" zurückgegeben.
	 */
	public String linkToMainPage() {
        if (loginController.getUserLogin().getRole().equals("tutor")) {
        	return "/mainpage/indexTutor?faces-redirect=true";
        }
        else {
        	return "/mainpage/indexStudent?faces-redirect=true";
        }
    }  
	
	/**
	 * Generiert einen Link zur Login-Seite.
	 *
	 * @return Ein String, der den Link zur Login-Seite repräsentiert.
	 */
	public String linkToLoginPage() {
		return "login?faces-redirect=true";
	}

}