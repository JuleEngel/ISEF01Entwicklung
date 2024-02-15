//Fragenkatalog.java
package fragenkatalog;

import javax.persistence.Entity;
import javax.persistence.Id;


/** 
* Fragenkatalog.java
* Klasse für die einzelnen Fragen. 
* Die Klasse enthält Attribute zur Anlegung einer Frage, Konstruktoren, Getter- und Setter.
* Entity bedeutet, dass diese Bean als Entitäts-Objekt für die Datenbank-Übertragung genutzt werden kann.
* 
* @author JuleEngel 
* @version 1.0 
* @since 15.02.2024 
*/ 
@Entity
public class Fragenkatalog {
	@Id
	private int id;
	private String question;
	private String correct_answer;
	private String incorrect_answer_1;
	private String incorrect_answer_2;
	private String incorrect_answer_3;
	private int difficulty;
	private int module_id;
	private String explanation;
	private String status;
	
	/**
	 * Erstellt einen neuen Fragenkatalog.
	 */
	public Fragenkatalog() {
	}

	/**
	 * Erstellt einen neuen Fragenkatalog mit den angegebenen Parametern.
	 *
	 * @param id Die ID der Frage.
	 * @param question Die Frage.
	 * @param correct_answer Die korrekte Antwort auf die Frage.
	 * @param incorrect_answer_1 Die erste falsche Antwort auf die Frage.
	 * @param incorrect_answer_2 Die zweite falsche Antwort auf die Frage.
	 * @param incorrect_answer_3 Die dritte falsche Antwort auf die Frage.
	 * @param difficulty Die Schwierigkeit der Frage.
	 * @param module_id Die ID des Moduls, zu dem die Frage gehört.
	 * @param explanation Die Erklärung zur Frage.
	 * @param status Der Status der Frage.
	 */
	public Fragenkatalog(int id, String question, String correct_answer, String incorrect_answer_1,
	                     String incorrect_answer_2, String incorrect_answer_3, int difficulty, int module_id,
	                     String explanation, String status) {
	    this.id = id;
	    this.question = question;
	    this.correct_answer = correct_answer;
	    this.incorrect_answer_1 = incorrect_answer_1;
	    this.incorrect_answer_2 = incorrect_answer_2;
	    this.incorrect_answer_3 = incorrect_answer_3;
	    this.difficulty = difficulty;
	    this.module_id = module_id;
	    this.explanation = explanation;
	    this.status = status;
	}

	/**
	 * Gibt die ID der Frage zurück.
	 *
	 * @return Die ID der Frage.
	 */
	public int getId() {
	    return id;
	}

	/**
	 * Setzt die ID der Frage.
	 *
	 * @param id Die neue ID der Frage.
	 */
	public void setId(int id) {
	    this.id = id;
	}

	/**
	 * Gibt die Frage zurück.
	 *
	 * @return Die Frage.
	 */
	public String getQuestion() {
	    return question;
	}

	/**
	 * Setzt die Frage.
	 *
	 * @param question Die neue Frage.
	 */
	public void setQuestion(String question) {
	    this.question = question;
	}

	/**
	 * Gibt die korrekte Antwort auf die Frage zurück.
	 *
	 * @return Die korrekte Antwort.
	 */
	public String getCorrect_answer() {
	    return correct_answer;
	}

	/**
	 * Setzt die korrekte Antwort auf die Frage.
	 *
	 * @param correct_answer Die neue korrekte Antwort.
	 */
	public void setCorrect_answer(String correct_answer) {
	    this.correct_answer = correct_answer;
	}

	/**
	 * Gibt die erste falsche Antwort auf die Frage zurück.
	 *
	 * @return Die erste falsche Antwort.
	 */
	public String getIncorrect_answer_1() {
	    return incorrect_answer_1;
	}

	/**
	 * Setzt die erste falsche Antwort auf die Frage.
	 *
	 * @param incorrect_answer_1 Die neue erste falsche Antwort.
	 */
	public void setIncorrect_answer_1(String incorrect_answer_1) {
	    this.incorrect_answer_1 = incorrect_answer_1;
	}

	/**
	 * Gibt die zweite falsche Antwort auf die Frage zurück.
	 *
	 * @return Die zweite falsche Antwort.
	 */
	public String getIncorrect_answer_2() {
	    return incorrect_answer_2;
	}

	/**
	 * Setzt die zweite falsche Antwort auf die Frage.
	 *
	 * @param incorrect_answer_2 Die neue zweite falsche Antwort.
	 */
	public void setIncorrect_answer_2(String incorrect_answer_2) {
	    this.incorrect_answer_2 = incorrect_answer_2;
	}

	/**
	 * Gibt die dritte falsche Antwort auf die Frage zurück.
	 *
	 * @return Die dritte falsche Antwort.
	 */
	public String getIncorrect_answer_3() {
	    return incorrect_answer_3;
	}

	/**
	 * Setzt die dritte falsche Antwort auf die Frage.
	 *
	 * @param incorrect_answer_3 Die neue dritte falsche Antwort.
	 */
	public void setIncorrect_answer_3(String incorrect_answer_3) {
	    this.incorrect_answer_3 = incorrect_answer_3;
	}

	/**
	 * Gibt die Schwierigkeit der Frage zurück.
	 *
	 * @return Die Schwierigkeit der Frage.
	 */
	public int getDifficulty() {
	    return difficulty;
	}

	/**
	 * Setzt die Schwierigkeit der Frage.
	 *
	 * @param difficulty Die neue Schwierigkeit der Frage.
	 */
	public void setDifficulty(int difficulty) {
	    this.difficulty = difficulty;
	}

	/**
	 * Gibt die ID des Moduls zurück, zu dem die Frage gehört.
	 *
	 * @return Die ID des Moduls.
	 */
	public int getModule_id() {
	    return module_id;
	}

	/**
	 * Setzt die ID des Moduls, zu dem die Frage gehört.
	 *
	 * @param module_id Die neue ID des Moduls.
	 */
	public void setModule_id(int module_id) {
	    this.module_id = module_id;
	}

	/**
	 * Gibt die Erklärung zur Frage zurück.
	 *
	 * @return Die Erklärung zur Frage.
	 */
	public String getExplanation() {
	    return explanation;
	}

	/**
	 * Setzt die Erklärung zur Frage.
	 *
	 * @param explanation Die neue Erklärung zur Frage.
	 */
	public void setExplanation(String explanation) {
	    this.explanation = explanation;
	}

	/**
	 * Gibt den Status der Frage zurück.
	 *
	 * @return Der Status der Frage.
	 */
	public String getStatus() {
	    return status;
	}

	/**
	 * Setzt den Status der Frage.
	 *
	 * @param status Der neue Status der Frage.
	 */
	public void setStatus(String status) {
	    this.status = status;
	}
}
