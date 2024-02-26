//FragenkatalogBearbeitet.java
package fragenkatalog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/** 
* FragenkatalogBearbeitet.java
* Klasse für die von Studenten bearbeiteten Versionen von Fragen. 
* Die Klasse enthält inhaltlichen Attribute einer Frage, Konstruktoren, Getter- und Setter.
* Entity bedeutet, dass diese Bean als Entitäts-Objekt für die Datenbank-Übertragung genutzt werden kann.
* 
* @author JuleEngel 
* @version 1.0 
* @since 15.02.2024 
*/ 
@Entity
public class FragenkatalogBearbeitet {
	@Id
	//question_id und user_id sind Foreign Keys auf die Primary Keys der Tabellen fragenkatalog und user
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int question_id;
	private int user_id;
	private String question;
	private String correct_answer;
	private String incorrect_answer_1;
	private String incorrect_answer_2;
	private String incorrect_answer_3;
	private String explanation;
	private String message;
	
	/**
	 * Erstellt einen neuen bearbeiteten Fragenkatalog.
	 */
	public FragenkatalogBearbeitet() {
	}

	/**
	 * Erstellt einen neuen bearbeiteten Fragenkatalog mit den angegebenen Parametern.
	 *
	 * @param id Die ID des bearbeiteten Fragenkatalogs.
	 * @param question_id Die ID der ursprünglichen Frage.
	 * @param user_id Die ID des Benutzers, der die Frage bearbeitet hat.
	 * @param question Die bearbeitete Frage.
	 * @param correct_answer Die korrekte Antwort auf die bearbeitete Frage.
	 * @param incorrect_answer_1 Die erste falsche Antwort auf die bearbeitete Frage.
	 * @param incorrect_answer_2 Die zweite falsche Antwort auf die bearbeitete Frage.
	 * @param incorrect_answer_3 Die dritte falsche Antwort auf die bearbeitete Frage.
	 * @param explanation Die Erklärung zur bearbeiteten Frage.
	 * @param message Die Nachricht zur bearbeiteten Frage.
	 */
	public FragenkatalogBearbeitet(int id, int question_id, int user_id, String question, String correct_answer,
	                               String incorrect_answer_1, String incorrect_answer_2, String incorrect_answer_3,
	                               String explanation, String message) {
	    this.id = id;
	    this.question_id = question_id;
	    this.user_id = user_id;
	    this.question = question;
	    this.correct_answer = correct_answer;
	    this.incorrect_answer_1 = incorrect_answer_1;
	    this.incorrect_answer_2 = incorrect_answer_2;
	    this.incorrect_answer_3 = incorrect_answer_3;
	    this.explanation = explanation;
	    this.message = message;
	}

	/**
	 * Gibt die ID des bearbeiteten Fragenkatalogs zurück.
	 *
	 * @return Die ID des bearbeiteten Fragenkatalogs.
	 */
	public int getId() {
	    return id;
	}

	/**
	 * Setzt die ID des bearbeiteten Fragenkatalogs.
	 *
	 * @param id Die neue ID des bearbeiteten Fragenkatalogs.
	 */
	public void setId(int id) {
	    this.id = id;
	}

	/**
	 * Gibt die ID der ursprünglichen Frage zurück.
	 *
	 * @return Die ID der ursprünglichen Frage.
	 */
	public int getQuestion_id() {
	    return question_id;
	}

	/**
	 * Setzt die ID der ursprünglichen Frage.
	 *
	 * @param question_id Die neue ID der ursprünglichen Frage.
	 */
	public void setQuestion_id(int question_id) {
	    this.question_id = question_id;
	}

	/**
	 * Gibt die ID des Benutzers zurück, der die Frage bearbeitet hat.
	 *
	 * @return Die ID des Benutzers.
	 */
	public int getUser_id() {
	    return user_id;
	}

	/**
	 * Setzt die ID des Benutzers, der die Frage bearbeitet hat.
	 *
	 * @param user_id Die neue ID des Benutzers.
	 */
	public void setUser_id(int user_id) {
	    this.user_id = user_id;
	}

	/**
	 * Gibt die bearbeitete Frage zurück.
	 *
	 * @return Die bearbeitete Frage.
	 */
	public String getQuestion() {
	    return question;
	}

	/**
	 * Setzt die bearbeitete Frage.
	 *
	 * @param question Die neue bearbeitete Frage.
	 */
	public void setQuestion(String question) {
	    this.question = question;
	}

	/**
	 * Gibt die korrekte Antwort auf die bearbeitete Frage zurück.
	 *
	 * @return Die korrekte Antwort.
	 */
	public String getCorrect_answer() {
	    return correct_answer;
	}

	/**
	 * Setzt die korrekte Antwort auf die bearbeitete Frage.
	 *
	 * @param correct_answer Die neue korrekte Antwort.
	 */
	public void setCorrect_answer(String correct_answer) {
	    this.correct_answer = correct_answer;
	}

	/**
	 * Gibt die erste falsche Antwort auf die bearbeitete Frage zurück.
	 *
	 * @return Die erste falsche Antwort.
	 */
	public String getIncorrect_answer_1() {
	    return incorrect_answer_1;
	}

	/**
	 * Setzt die erste falsche Antwort auf die bearbeitete Frage.
	 *
	 * @param incorrect_answer_1 Die neue erste falsche Antwort.
	 */
	public void setIncorrect_answer_1(String incorrect_answer_1) {
	    this.incorrect_answer_1 = incorrect_answer_1;
	}

	/**
	 * Gibt die zweite falsche Antwort auf die bearbeitete Frage zurück.
	 *
	 * @return Die zweite falsche Antwort.
	 */
	public String getIncorrect_answer_2() {
	    return incorrect_answer_2;
	}

	/**
	 * Setzt die zweite falsche Antwort auf die bearbeitete Frage.
	 *
	 * @param incorrect_answer_2 Die neue zweite falsche Antwort.
	 */
	public void setIncorrect_answer_2(String incorrect_answer_2) {
	    this.incorrect_answer_2 = incorrect_answer_2;
	}

	/**
	 * Gibt die dritte falsche Antwort auf die bearbeitete Frage zurück.
	 *
	 * @return Die dritte falsche Antwort.
	 */
	public String getIncorrect_answer_3() {
	    return incorrect_answer_3;
	}

	/**
	 * Setzt die dritte falsche Antwort auf die bearbeitete Frage.
	 *
	 * @param incorrect_answer_3 Die neue dritte falsche Antwort.
	 */
	public void setIncorrect_answer_3(String incorrect_answer_3) {
	    this.incorrect_answer_3 = incorrect_answer_3;
	}

	/**
	 * Gibt die Erklärung zur bearbeiteten Frage zurück.
	 *
	 * @return Die Erklärung zur bearbeiteten Frage.
	 */
	public String getExplanation() {
	    return explanation;
	}

	/**
	 * Setzt die Erklärung zur bearbeiteten Frage.
	 *
	 * @param explanation Die neue Erklärung zur bearbeiteten Frage.
	 */
	public void setExplanation(String explanation) {
	    this.explanation = explanation;
	}

	/**
	 * Gibt die Nachricht zur bearbeiteten Frage zurück.
	 *
	 * @return Die Nachricht zur bearbeiteten Frage.
	 */
	public String getMessage() {
	    return message;
	}

	/**
	 * Setzt die Nachricht zur bearbeiteten Frage.
	 *
	 * @param message Die neue Nachricht zur bearbeiteten Frage.
	 */
	public void setMessage(String message) {
	    this.message = message;
	}
}
