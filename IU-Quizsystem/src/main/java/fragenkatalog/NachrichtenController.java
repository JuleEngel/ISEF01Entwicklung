//NachrichtenController.java
package fragenkatalog;

import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import login.LoginController;
import login.User;
import login.UserListe;

/** 
* NachrichtenController.java
* Methoden, die der Verwaltung von Nachrichten dienen.
* Die Klasse NachrichtenController.java enthält Funktionen zur Verwaltung von Nachrichten, die Studenten
* zu bereits bestehenden Fragen erstellen können.
* 
* @author JuleEngel 
* @version 1.0 
* @since 15.02.2024 
*/ 
@SuppressWarnings("serial")
@Named
@SessionScoped
public class NachrichtenController implements Serializable
{
	private String tempNachricht;
    @Inject
    private FragenkatalogController fragenkatalogController;
    @Inject
    private LoginController loginController;
    @Inject
    private UserListe userListe;
    @Inject
    private TempVariablen tempVariablen;
    
    private NachrichtenDAO nachrichtenDAO = new NachrichtenDAO();
    private FragenkatalogDAO fragenkatalogDAO = new FragenkatalogDAO();
    private NachrichtenListe nachrichtenListe = new NachrichtenListe();
    private FragenkatalogBearbeitetListe fragenkatalogBearbeitetListe = new FragenkatalogBearbeitetListe();
    private FragenkatalogListe fragenkatalogListe = new FragenkatalogListe();
    private int amountOfMessages;
    
    
    
    /**
     * Gibt eine Liste von Nachrichten zurück, die zu einer bestimmten Frage gehören.
     *
     * @param frage Die Frage, zu der die Nachrichten gehören
     * @return Die Liste der Nachrichten zu der Frage
     */
    public List<Nachrichten> getNachrichtenListeZuFrage(Fragenkatalog frage) {
        nachrichtenListe = new NachrichtenListe();
        List<Nachrichten> filteredList = new ArrayList<>();
        for (Nachrichten nachricht : nachrichtenListe.getNachrichtenListe()) {
            if (nachricht.getQuestion_id() == frage.getId()) {
                filteredList.add(nachricht);
            }
        }
        return filteredList;
    }

    /**
     * Aktualisiert die Gesamtanzahl der Nachrichten zurück.
     *
     */
    public void updateAmountOfMessages() {
        this.amountOfMessages = 0;
        amountOfMessages += nachrichtenListe.getNachrichtenListe().size();
        amountOfMessages  += fragenkatalogBearbeitetListe.getFragenkatalogBearbeitetListe().size();
        for (Fragenkatalog frage : fragenkatalogListe.getFragenkatalogListe()) {
            if (frage.getStatus().equals("new")) {
            	setAmountOfMessages(getAmountOfMessages() + 1);
            }
        }
    }

    /**
     * Gibt den Benutzer zurück, der eine bestimmte Nachricht gemeldet hat.
     *
     * @param nachricht Die gemeldete Nachricht
     * @return Der Benutzer, der die Nachricht gemeldet hat
     */
    public String getUserReported(Nachrichten nachricht) {
        User userReported = userListe.getUserFromList(nachricht.getUser_id());
        int idUserReported = userReported.getId();
        String nameUserReported = userReported.getUsername();
        return nameUserReported + "(" + idUserReported + ")";
    }

    /**
     * Meldet eine Nachricht zu einer Frage.
     *
     * @param frage Die Frage, zu der die Nachricht gehört
     * @return Die Zielseite nach erfolgreichem Melden der Nachricht
     */
    public String reportMessage(Fragenkatalog frage) {
        User loggedInUser = loginController.getUserLogin();
        nachrichtenDAO.createNachricht(frage.getId(), loggedInUser.getId(), tempNachricht);
        frage.setStatus("reported");
        fragenkatalogDAO.updateFrage(frage);
        tempNachricht = "";
        tempVariablen.setTempFrage(null);
        return "fragenkatalogFrageEingereichtErfolgreich?faces-redirect=true";
    }

    /**
     * Löscht eine Nachricht.
     *
     * @param nachricht Die zu löschende Nachricht
     */
    public void deleteMessage(Nachrichten nachricht) {
        nachrichtenDAO.deleteNachricht(nachricht);
        nachrichtenListe.getNachrichtenListe().remove(nachricht);
        updateAmountOfMessages();
        fragenkatalogController.checkReportFrage();
    }

    /**
     * Löscht alle Nachrichten zu einer bestimmten Frage.
     *
     * @param frage Die Frage, zu der die Nachrichten gehören
     */
    public void deleteAllMessages(Fragenkatalog frage) {
        for (Nachrichten nachricht : nachrichtenListe.getNachrichtenListe()) {
            if (nachricht.getQuestion_id() == frage.getId()) {
                nachrichtenDAO.deleteNachricht(nachricht);
            }
        }
        nachrichtenListe = new NachrichtenListe();
        updateAmountOfMessages();
    }

    /**
     * Setzt die temporäre Nachricht.
     *
     * @param nachricht Die temporäre Nachricht
     */
    public void setTempNachricht(String nachricht) {
        tempNachricht = nachricht;
    }

    /**
     * Gibt die temporäre Nachricht zurück.
     *
     * @return Die temporäre Nachricht
     */
    public String getTempNachricht() {
        return tempNachricht;
    }

    /**
     * Aktualisiert die Liste der Nachrichten.
     */
    public void updateNachrichtenListe() {
        nachrichtenListe = new NachrichtenListe();
    }

    /**
     * Gibt die Anzahl an Nachrichten zurück.
     *
     * @return Anzahl an Nachrichten
     */
	public int getAmountOfMessages() {
		return amountOfMessages;
	}

    /**
     * Setzt die Anzahl an Nachrichten.
     *
     * @param Anzahl an Nachrichten
     */
	public void setAmountOfMessages(int amountOfMessages) {
		this.amountOfMessages = amountOfMessages;
	}
}
