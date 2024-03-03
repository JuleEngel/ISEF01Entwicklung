//QuizDuo.java
package quiz;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import login.User;


/** 
* QuizDuo.java
* QuizDuo-Klasse. 
* Die Klasse enthält Attribute, die für ein Duo-Quiz benötigt werden, hierunter fallen das Modul, die Schwierigkeit, sowie die
* beiden Spieler, die das Quiz spielen. 
* 
* @author JuleEngel 
* @version 1.0 
* @since 29.02.2024 
*/ 
@Named
@ApplicationScoped
public class QuizDuo{
	private int module_id;
	private int difficulty;
	private User user1;
	private User user2;
	
	/**
	 * Erstellt ein leeres QuizDuo-Objekt.
	 */
	public QuizDuo() {	
	}
	
	/**
	 * Erstellt ein QuizDuo-Objekt mit dem Nutzer, der das Quiz erstellen möchte.
	 *
	 * @param module_id Die ID des Moduls.
	 * @param difficulty Die Schwierigkeitsstufe des Quiz.
	 * @param user1 Der Nutzer, der das Quiz erstellt.
	 */
	public QuizDuo(int module_id, int difficulty, User user1, User user2) {
		this.module_id = module_id;
		this.difficulty = difficulty;
		this.setUser1(user1);
		this.setUser2(user2);
	}
	
	/**
	 * Fügt den zweiten User zum Quiz hinzu.
	 *
	 * @param user Der Benutzer, der zum Quiz hinzugefügt werden soll.
	 */
	public void addUserToQuiz(User user) {
		this.user2 = user;
	}
	
	/**
	 * Gibt die ID des Moduls zurück.
	 *
	 * @return Die ID des Moduls.
	 */
	public int getModule_id() {
	    return module_id;
	}

	/**
	 * Legt die ID des Moduls fest.
	 *
	 * @param module_id Die ID des Moduls.
	 */
	public void setModule_id(int module_id) {
	    this.module_id = module_id;
	}

	/**
	 * Gibt die Schwierigkeitsstufe des Quiz zurück.
	 *
	 * @return Die Schwierigkeitsstufe des Quiz.
	 */
	public int getDifficulty() {
	    return difficulty;
	}

	/**
	 * Legt die Schwierigkeitsstufe des Quiz fest.
	 *
	 * @param difficulty Die Schwierigkeitsstufe des Quiz.
	 */
	public void setDifficulty(int difficulty) {
	    this.difficulty = difficulty;
	}

	/**
	 * Gibt den ersten Benutzer im Quiz zurück.
	 *
	 * @return Der erste Benutzer im Quiz.
	 */
	public User getUser1() {
	    return user1;
	}

	/**
	 * Legt den ersten Benutzer im Quiz fest.
	 *
	 * @param user1 Der erste Benutzer im Quiz.
	 */
	public void setUser1(User user1) {
	    this.user1 = user1;
	}

	/**
	 * Gibt den zweiten Benutzer im Quiz zurück.
	 *
	 * @return Der zweite Benutzer im Quiz.
	 */
	public User getUser2() {
	    return user2;
	}

	/**
	 * Legt den zweiten Benutzer im Quiz fest.
	 *
	 * @param user2 Der zweite Benutzer im Quiz.
	 */
	public void setUser2(User user2) {
	    this.user2 = user2;
	}
}
