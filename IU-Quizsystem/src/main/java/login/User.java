//User.java
package login;
import javax.persistence.Entity;
import javax.persistence.Id;

/** 
* User.java
* User-Klasse. 
* Die Klasse enthält Attribute zur Nutzeranlegung des Objektes "User", Konstruktoren, Getter- und Setter sowie eine equals-Methode.
* Entity bedeutet, dass diese Bean als Entitäts-Objekt für die Datenbank-Übertragung genutzt werden kann.
* 
* @author JuleEngel 
* @version 1.0 
* @since 29.01.2024 
*/ 
@Entity
public class User {
	@Id
	private int id;
	private String username;
	private String email;
	private String password;
	private String role;
	private int playedgames;
	
	/**
	 * Standardkonstruktor für die User-Klasse.
	 * Dieser Konstruktor wird verwendet, um eine Instanz der User-Klasse zu erstellen.
	 */
	public User() {
	}
	
	/**
	 * Erzeugt einen neuen Benutzer mit den angegebenen E-Mail-Adresse und Passwort.
	 *
	 * @param email    Die E-Mail-Adresse des Benutzers.
	 * @param password Das Passwort des Benutzers.
	 */
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	/**
	 * Erzeugt einen neuen Benutzer mit den angegebenen Attributen.
	 *
	 * @param id         Die eindeutige ID des Benutzers.
	 * @param username   Der Benutzername des Benutzers.
	 * @param email      Die E-Mail-Adresse des Benutzers.
	 * @param password   Das Passwort des Benutzers.
	 * @param role       Die Rolle des Benutzers.
	 * @param playedgames Die Anzahl der gespielten Spiele des Benutzers.
	 */
	public User(int id, String username, String email, String password, String role, int playedgames) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.playedgames = playedgames;
	}
	
	/**
	 * Gibt die eindeutige ID des Benutzers zurück.
	 * @return Die ID des Benutzers.
	 */
	public int getId() {
	    return id;
	}

	/**
	 * Setzt die eindeutige ID des Benutzers.
	 * @param id Die neue ID des Benutzers.
	 */
	public void setId(int id) {
	    this.id = id;
	}

	/**
	 * Gibt den Benutzernamen des Benutzers zurück.
	 * @return Der Benutzername.
	 */
	public String getUsername() {
	    return username;
	}

	/**
	 * Setzt den Benutzernamen des Benutzers.
	 * @param username Der neue Benutzername.
	 */
	public void setUsername(String username) {
	    this.username = username;
	}

	/**
	 * Gibt die E-Mail-Adresse des Benutzers zurück.
	 * @return Die E-Mail-Adresse.
	 */
	public String getEmail() {
	    return email;
	}

	/**
	 * Setzt die E-Mail-Adresse des Benutzers.
	 * @param email Die neue E-Mail-Adresse.
	 */
	public void setEmail(String email) {
	    this.email = email;
	}

	/**
	 * Gibt das Passwort des Benutzers zurück.
	 * @return Das Passwort.
	 */
	public String getPassword() {
	    return password;
	}

	/**
	 * Setzt das Passwort des Benutzers.
	 * @param password Das neue Passwort.
	 */
	public void setPassword(String password) {
	    this.password = password;
	}

	/**
	 * Gibt die Rolle des Benutzers zurück.
	 * @return Die Rolle des Benutzers.
	 */
	public String getRole() {
	    return role;
	}

	/**
	 * Setzt die Rolle des Benutzers.
	 * @param role Die neue Rolle des Benutzers.
	 */
	public void setRole(String role) {
	    this.role = role;
	}

	/**
	 * Gibt die Anzahl der gespielten Spiele des Benutzers zurück.
	 * @return Die Anzahl der gespielten Spiele.
	 */
	public int getPlayedgames() {
	    return playedgames;
	}

	/**
	 * Setzt die Anzahl der gespielten Spiele des Benutzers.
	 * @param playedgames Die neue Anzahl der gespielten Spiele.
	 */
	public void setPlayedgames(int playedgames) {
	    this.playedgames = playedgames;
	}

	/**
	 * Vergleicht das aktuelle Benutzerobjekt mit einem anderen Objekt auf Gleichheit.
	 * Zwei Benutzer gelten als gleich, wenn ihre E-Mail-Adressen und Passwörter übereinstimmen.
	 *
	 * @param obj Das Objekt, mit dem das Benutzerobjekt verglichen werden soll.
	 * @return true, wenn die Objekte gleich sind, andernfalls false.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User anmelder = (User) obj;
			if (anmelder.getEmail().equals(this.email) && anmelder.getPassword().equals(this.password)) {
				return true;
			}
			
		}
		return false;
	}
	
}