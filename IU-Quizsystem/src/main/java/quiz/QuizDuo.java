//QuizSoloController.java
package quiz;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import fragenkatalog.Fragenkatalog;
import fragenkatalog.FragenkatalogListe;
import fragenkatalog.Modules;
import fragenkatalog.ModulesController;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.component.UISelectOne;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import login.User;
import login.UserListe;

@Named
@ApplicationScoped
public class QuizDuo{
	
	//Allgemeine Einstellungen
	private int quizID;
	private int module_id;
	private int difficulty;
	private User user1;
	private User user2 = new User(); //Wird hier nicht verwendet, da ein Dummy genutzt wird
	
	
	public QuizDuo() {
		
	}
	
	public QuizDuo(int module_id, int difficulty, User user1) {
		this.module_id = module_id;
		this.difficulty = difficulty;
		this.setUser1(user1);
	}
	
	public int getModule_id() {
		return module_id;
	}
	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public int getQuizID() {
		return quizID;
	}
	public void setQuizID(int quizID) {
		this.quizID = quizID;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

}
