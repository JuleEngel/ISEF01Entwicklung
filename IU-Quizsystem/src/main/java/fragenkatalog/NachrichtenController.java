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

@SuppressWarnings("serial")
@Named
@SessionScoped
public class NachrichtenController implements Serializable
{
	private String tempNachricht;
	
    @Inject
    private FragenkatalogController fragenkatalogController;
    @Inject
    private NachrichtenListe nachrichtenListe = new NachrichtenListe();
    @Inject
    private LoginController loginController;
    @Inject
    private UserListe userListe;
    @Inject
    private TempVariablen tempVariablen;
    
    private NachrichtenDAO nachrichtenDAO = new NachrichtenDAO();
    private FragenkatalogDAO fragenkatalogDAO = new FragenkatalogDAO();
    

    
    
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
    
    public String getUserReported(Nachrichten nachricht) {
    	User userReported = userListe.getUserFromList(nachricht.getUser_id());
    	int idUserReported = userReported.getId();
    	String nameUserReported = userReported.getUsername();
    	return nameUserReported + "(" + idUserReported + ")";
    }
	 
	 public String reportMessage(Fragenkatalog frage) {
		 User loggedInUser = loginController.getUserLogin();
		 //TODO To be removed
		 if (loggedInUser.getId() != 0) {
			 nachrichtenDAO.createNachricht(frage.getId(), loggedInUser.getId(), tempNachricht);
		 }
		 else {
			 nachrichtenDAO.createNachricht(frage.getId(), 1002, tempNachricht);
		 }
		 
		 frage.setStatus("reported");
		 fragenkatalogDAO.updateFrage(frage);
		 tempNachricht = "";
		 tempVariablen.setTempFrage(null);
		 return "fragenkatalogFrageEingereichtErfolgreich?faces-redirect=true";
	 }
	 
	 public void deleteMessage(Nachrichten nachricht) {
		 nachrichtenDAO.deleteNachricht(nachricht);
	     nachrichtenListe.getNachrichtenListe().remove(nachricht);
	     fragenkatalogController.checkReportFrage();
	 }
	 
	 public void deleteAllMessages(Fragenkatalog frage) {
		 for (Nachrichten nachricht : nachrichtenListe.getNachrichtenListe()) {
			 if (nachricht.getQuestion_id() == frage.getId()) {
				 nachrichtenDAO.deleteNachricht(nachricht);
			 }
		 }
		 nachrichtenListe = new NachrichtenListe();
	 }
	 public void setTempNachricht(String nachricht) {
			tempNachricht = nachricht;
		}
		
	 public String getTempNachricht() {
		return tempNachricht;
	 }

	public void updateNachrichtenListe() {
		nachrichtenListe = new NachrichtenListe();
	}
	 
}
