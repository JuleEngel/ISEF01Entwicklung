//QuizController.java
package quiz;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import login.LoginController;
import login.User;

/** 
* QuizController.java
* Funktionen zur Verwaltung von Quizzen (Solo und Quo).
* Die Klasse QuizController.java enthält Funktionen zur Verlinkung der einzelnen Seiten, zum Zurücksetzen von Einstellungen und Quizzen.
* 
* @author JuleEngel 
* @version 1.0 
* @since 24.02.2024 
*/ 
@SuppressWarnings("serial")
@Named
@ApplicationScoped
public class QuizController implements Serializable {
	@Inject
	QuizSoloController quizSoloController;
	@Inject
	LoginController loginController;
	
	/**
	 * Setzt den Quiz-Solo-Controller zurück und leitet zur Seite für die Einstellungen des Solo-Quiz weiter.
	 * 
	 * @return Der Umleitpfad zur Seite für die Einstellungen des Solo-Quiz.
	 */
	public String linkToSoloQuizSettings() {
	    quizSoloController.reset();
	    return "/quiz/soloSettings?faces-redirect=true";
	}

	/**
	 * Aktualisiert die Einstellungen, indem der Quiz-Solo-Controller zurückgesetzt wird.
	 */
	public void refreshSettings() {
	    quizSoloController.reset();
	}

	/**
	 * Entfernt alle vorhandenen Quizze, indem der Quiz-Solo-Controller zurückgesetzt wird.
	 */
	public void removeAnyQuizzes() {
	    quizSoloController.reset();
	}

	/**
	 * Ruft die Anmeldeinformationen des Benutzers ab.
	 * 
	 * @return Die Anmeldeinformationen des Benutzers.
	 */
	public User getUserLogin() {
	    return loginController.getUserLogin();
	}

	/**
	 * Beendet das abgeschlossene Quiz, indem der Quiz-Solo-Controller zurückgesetzt und zur Hauptseite für Studenten weitergeleitet wird.
	 * 
	 * @return Der Umleitpfad zur Hauptseite für Studenten.
	 */
	public String quitFinishedQuiz() {
	    quizSoloController.reset();
	    return "/mainpage/indexStudent?faces-redirect=true";
	}

	/**
	 * Bricht das Solo-Quiz ab, indem der Quiz-Solo-Controller zurückgesetzt und zur Hauptseite für Studenten weitergeleitet wird.
	 * 
	 * @return Der Umleitpfad zur Hauptseite für Studenten.
	 */
	public String cancelQuizSolo() {
	    quizSoloController.reset();
	    return "/mainpage/indexStudent?faces-redirect=true";
	}

}