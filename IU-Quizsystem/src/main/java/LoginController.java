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
    private Nutzer nutzerLogin = new Nutzer();
    private String tempName;
    
    @Inject
    private NutzerListe nutzerListe = new NutzerListe();

	public Nutzer getNutzerLogin() {
		return nutzerLogin;
	}
	
	public void postValidateName(ComponentSystemEvent event) throws AbortProcessingException{
		UIInput temp = (UIInput)event.getComponent();
		this.tempName = (String)temp.getValue();
	}
	
	
	public void validateLogin(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Nutzer temp = new Nutzer(this.tempName, (String) value);
		for (Nutzer tempNutzerListe : nutzerListe.getNutzerListe()) {
			if (tempNutzerListe.equals(temp)) {
				this.nutzerLogin.setNutzerName(tempNutzerListe.getNutzerName());
				return;
			}
		}
		throw new ValidatorException(new FacesMessage("Falsche Login-Daten!"));
	}
}