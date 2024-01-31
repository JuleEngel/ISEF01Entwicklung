import java.io.Serializable;

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
				this.userLogin.setUsername(tempUserListe.getUsername());
				return;
			}
		}
		throw new ValidatorException(new FacesMessage("Falsche Login-Daten!"));
	}
}