//Nachrichten.java
package fragenkatalog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/** 
* Nachrichten.java
* Klasse für die Nachrichten von Studenten. 
* Die Klasse enthält Attribute zur Anlegung von Nachrichten, Konstruktoren, Getter- und Setter.
* Entity bedeutet, dass diese Bean als Entitäts-Objekt für die Datenbank-Übertragung genutzt werden kann.
* 
* @author JuleEngel 
* @version 1.0 
* @since 15.02.2024 
*/ 
@Entity
public class Nachrichten {
	@Id
	//question_id und user_id sind Foreign Keys auf die Primary Keys der Tabellen fragenkatalog und user
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrement
	private int message_id;
	private int user_id;
	private int question_id;
	private String message;
	
	/**
	 * Konstruktor der Nachrichten-Klasse ohne Parameter.
	 */
	public Nachrichten() {
	}

	/**
	 * Konstruktor der Nachrichten-Klasse mit Parametern.
	 *
	 * @param message_id   Die ID der Nachricht
	 * @param user_id      Die ID des Benutzers
	 * @param question_id  Die ID der Frage
	 * @param message      Die Nachricht
	 */
	public Nachrichten(int message_id, int user_id, int question_id, String message) {
	    this.message_id = message_id;
	    this.user_id = user_id;
	    this.question_id = question_id;
	    this.message = message;
	}

	/**
	 * Gibt die ID der Nachricht zurück.
	 *
	 * @return Die ID der Nachricht
	 */
	public int getMessage_id() {
	    return message_id;
	}

	/**
	 * Setzt die ID der Nachricht.
	 *
	 * @param message_id Die ID der Nachricht
	 */
	public void setMessage_id(int message_id) {
	    this.message_id = message_id;
	}

	/**
	 * Gibt die ID des Benutzers zurück.
	 *
	 * @return Die ID des Benutzers
	 */
	public int getUser_id() {
	    return user_id;
	}

	/**
	 * Setzt die ID des Benutzers.
	 *
	 * @param user_id Die ID des Benutzers
	 */
	public void setUser_id(int user_id) {
	    this.user_id = user_id;
	}

	/**
	 * Gibt die ID der Frage zurück.
	 *
	 * @return Die ID der Frage
	 */
	public int getQuestion_id() {
	    return question_id;
	}

	/**
	 * Setzt die ID der Frage.
	 *
	 * @param question_id Die ID der Frage
	 */
	public void setQuestion_id(int question_id) {
	    this.question_id = question_id;
	}

	/**
	 * Gibt die Nachricht zurück.
	 *
	 * @return Die Nachricht
	 */
	public String getMessage() {
	    return message;
	}

	/**
	 * Setzt die Nachricht.
	 *
	 * @param message Die Nachricht
	 */
	public void setMessage(String message) {
	    this.message = message;
	}
}
