//QuizDuoList.java
package quiz;
import java.util.ArrayList;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import login.User;


/** 
* QuizDuoList.java
* QuizDuoList-Klasse. 
* Die Klasse enthält eine Liste an Quizzen für das Duo-Spiel, die derzeit offen sind.
* 
* @author JuleEngel 
* @version 1.0 
* @since 29.02.2024 
*/ 
@Named
@ApplicationScoped
public class QuizDuoList{
	private List<QuizDuo> quizDuoList = new ArrayList<QuizDuo>();

	/**
	 * Fügt ein neues QuizDuo zur Liste der QuizDuos hinzu.
	 *
	 * @param newQuiz Das neue QuizDuo, das hinzugefügt werden soll.
	 */
	public void addQuiz(QuizDuo newQuiz) {
		quizDuoList.add(newQuiz);
	}
	
	/**
	 * Entfernt ein QuizDuo aus der Liste der QuizDuos.
	 *
	 * @param quizToDelete Das QuizDuo, das entfernt werden soll.
	 */
	public void removeQuiz(QuizDuo quizToDelete) {
		quizDuoList.remove(quizToDelete);
	}
	
	/**
	 * Gibt das QuizDuo eines bestimmten Benutzers zurück, falls vorhanden.
	 * Wenn kein passendes QuizDuo gefunden wird, wird ein leeres QuizDuo zurückgegeben.
	 *
	 * @param user Der Benutzer, dessen QuizDuo zurückgegeben werden soll.
	 * @return Das QuizDuo des Benutzers oder ein leeres QuizDuo, falls keines gefunden wird.
	 */
	public QuizDuo getQuizFromUser(User user) {
		for (QuizDuo quiz : getQuizDuoList()) {
			if (quiz.getUser1().equals(user) || quiz.getUser2().equals(user) ) {
				return quiz;
			}
		}
		return new QuizDuo();
	}
	
	/**
	 * Gibt die Liste der QuizDuos zurück.
	 *
	 * @return Die Liste der QuizDuos.
	 */
	public List<QuizDuo> getQuizDuoList() {
		return quizDuoList;
	}

	/**
	 * Legt die Liste der QuizDuos fest.
	 *
	 * @param quizDuoList Die Liste der QuizDuos.
	 */
	public void setQuizDuoList(List<QuizDuo> quizDuoList) {
		this.quizDuoList = quizDuoList;
	}
}
