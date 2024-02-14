package quiz;

import fragenkatalog.FragenkatalogListe;
import jakarta.inject.Inject;

public class main {

	@Inject
	static
	QuizController quizController; // = new QuizController();
	@Inject
	FragenkatalogListe fragenkatalogListe;
	
	public static void main(String[] args) {
		
		Quiz testQuiz = new Quiz("Testing", 2);
		testQuiz.getQuestionList();
		quizController.starteSoloQuiz(testQuiz);
	}

}

