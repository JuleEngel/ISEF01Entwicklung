//Modules.java
package fragenkatalog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/** 
* Modules.java
* Klasse für die einzelnen Module. 
* Die Klasse enthält Attribute zur Anlegung eines Moduls, Konstruktoren, Getter- und Setter.
* Entity bedeutet, dass diese Bean als Entitäts-Objekt für die Datenbank-Übertragung genutzt werden kann.
* 
* @author JuleEngel 
* @version 1.0 
* @since 15.02.2024 
*/ 
@Entity
public class Modules {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrement
	private int module_id;
	private String module_short;
	private String module_long;
	
	/**
	 * Erstellt eine neue Instanz der Klasse Modules.
	 */
	public Modules() {
	}

	/**
	 * Erstellt eine neue Instanz der Klasse Modules mit den angegebenen Werten für Modul-ID, Modulkürzel und Modulname.
	 *
	 * @param module_id     Die ID des Moduls
	 * @param module_short  Modulkürzel des Moduls
	 * @param module_long   Modulname des Moduls
	 */
	public Modules(int module_id, String module_short, String module_long) {
	   this.module_id = module_id;
	   this.module_short = module_short;
	   this.module_long = module_long;
	}

	/**
	 * Gibt die Modul-ID zurück.
	 *
	 * @return Die Modul-ID
	 */
	public int getModule_id() {
	    return module_id;
	}

	/**
	 * Setzt die Modul-ID.
	 *
	 * @param module_id Die Modul-ID, die gesetzt werden soll
	 */
	public void setModule_id(int module_id) {
	    this.module_id = module_id;
	}

	/**
	 * Gibt das Modulkürzel des Moduls zurück.
	 *
	 * @return Modulkürzel des Moduls
	 */
	public String getModule_short() {
	    return module_short;
	}

	/**
	 * Setzt das Modulkürzel des Moduls.
	 *
	 * @param module_short Das Modulkürzel, das gesetzt werden soll
	 */
	public void setModule_short(String module_short) {
	    this.module_short = module_short;
	}

	/**
	 * Gibt den Modulnamen des Moduls zurück.
	 *
	 * @return Modulname des Moduls
	 */
	public String getModule_long() {
	    return module_long;
	}

	/**
	 * Setzt den Modulnamen des Moduls.
	 *
	 * @param module_long Modulname, der gesetzt werden soll
	 */
	public void setModule_long(String module_long) {
	    this.module_long = module_long;
	}
}
