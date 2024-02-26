
package quiz;

import jakarta.enterprise.context.ApplicationScoped;
import java.io.Serializable;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import login.LoginController;
import login.User;

@SuppressWarnings("serial")
@Named
@ApplicationScoped
public class QuizController implements Serializable {
	@Inject
	QuizSoloController quizSoloController;
	@Inject
	LoginController loginController;
	
	public String linkToSoloQuizSettings() {
		quizSoloController.reset();
		return "/quiz/soloSettings?faces-redirect=true";
	}
	
	public void refreshSettings() {
		quizSoloController.reset();
	}
	
	public void removeAnyQuizzes() {
		quizSoloController.reset();
	}
	
	public User getUserLogin() {
		return loginController.getUserLogin();
	}
	public String quitFinishedQuiz() {
		quizSoloController.reset();
		return "/mainpage/indexStudent?faces-redirect=true";
	}
	
    public String cancelQuizSolo() {
        quizSoloController.reset();
        return "/mainpage/indexStudent?faces-redirect=true";
    }
}