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
public class QuizDuoList{
	private List<QuizDuo> quizDuoList = new ArrayList<QuizDuo>();

	
	public void addQuiz(QuizDuo newQuiz) {
		quizDuoList.add(newQuiz);
	}
	
	public void removeQuiz(QuizDuo quizToDelete) {
		for (QuizDuo quizDuo : quizDuoList) {
			if (quizDuo.equals(quizToDelete)) {
				quizDuoList.remove(quizDuo);
			}
		}
	}
	
	public QuizDuo getQuizFromUser(User user) {
		for (QuizDuo quiz : getQuizDuoList()) {
			if (quiz.getUser1().equals(user) ) {
				return quiz;
			}
		}
		return new QuizDuo();
	}
	
	public List<QuizDuo> getQuizDuoList() {
		return quizDuoList;
	}

	public void setQuizDuoList(List<QuizDuo> quizDuoList) {
		this.quizDuoList = quizDuoList;
	}
}
