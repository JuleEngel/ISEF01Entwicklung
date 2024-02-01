import java.io.Serializable;
import java.util.Map;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class LoginController implements Serializable {
    private User userLogin = new User();
    private String tempName;
    private boolean isLoggedIn = false;
    private static final long INACTIVITY_TIMEOUT =30 * 60 * 1000; //30 Minuten bis zum automatischen Ausloggen
    
    @Inject
    private UserListe userListe = new UserListe();

	public User getUserLogin() {
		return userLogin;
	}
	
	public void postValidateName(ComponentSystemEvent event) throws AbortProcessingException{
		UIInput temp = (UIInput)event.getComponent();
		this.tempName = (String)temp.getValue();
	}
	
	
	public void validateLogin(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		User temp = new User(this.tempName, (String) value);
		for (User tempUserListe : userListe.getUserListe()) {
			if (tempUserListe.equals(temp)) {
				this.userLogin.setId(tempUserListe.getId());
				this.userLogin.setUsername(tempUserListe.getUsername());
				this.userLogin.setEmail(tempUserListe.getEmail());
				this.userLogin.setRole(tempUserListe.getRole());
				this.userLogin.setPlayedgames(tempUserListe.getPlayedgames());
				this.isLoggedIn = true;
				return;
			}
		}
		throw new ValidatorException(new FacesMessage("Falsche Login-Daten!"));
	}
	
	public String login() {
		if (this.isLoggedIn) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        Map<String, Object> sessionMap = facesContext.getExternalContext().getSessionMap();
	        sessionMap.put("lastActivityTime", System.currentTimeMillis());
			return "loginSuccess?faces-redirect=true";
		}
		else {
			return "loginPage?faces-redirect=true";
		}
	}
	
	public String logout() {
	    this.isLoggedIn = false;
	    userLogin = new User();
	    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	    return "logout?faces-redirect=true";
	}
	
	public void checkLoggedIn() {
	    if (!this.isLoggedIn) {
	    	isLoggedIn = false;
	    	userLogin = new User();
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.getExternalContext().invalidateSession();
	        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "loginFailed?faces-redirect=true");
	    }
	    else if (isInactive()) {
	    	isLoggedIn = false;
	    	userLogin = new User();
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.getExternalContext().invalidateSession();
	        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "logout?faces-redirect=true");
	    }
	    else {
	    	updateActivityTimestamp();
	    }
	}
	
	
	public void updateActivityTimestamp() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = facesContext.getExternalContext().getSessionMap();

        if (sessionMap.containsKey("lastActivityTime")) {
            // Benutzeraktivität gefunden, aktualisiere den Zeitstempel
            sessionMap.put("lastActivityTime", System.currentTimeMillis());
        } else {
            // Erster Aufruf, setze den Zeitstempel
            sessionMap.put("lastActivityTime", System.currentTimeMillis());
        }
	}
	
	public boolean isInactive() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String, Object> sessionMap = facesContext.getExternalContext().getSessionMap();
		long lastActivityTime = (Long) sessionMap.getOrDefault("lastActivityTime", System.currentTimeMillis());
        long currentTime = System.currentTimeMillis();
        long inactiveDuration = currentTime - lastActivityTime;
        return inactiveDuration > INACTIVITY_TIMEOUT;
	}
	
	public String formatInput(String input) {
		String shortName = input.split("-")[0];
		StringBuilder formattedName = new StringBuilder();
		formattedName.append(Character.toUpperCase(shortName.charAt(0)));
		formattedName.append(shortName.substring(1));
		return formattedName.toString();
	}
}