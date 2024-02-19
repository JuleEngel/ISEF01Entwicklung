package quiz;

import jakarta.inject.Inject;

public class main {

	@Inject
	static
	QuizController quizController; // = new QuizController();
	@Inject
	QuizFragenkatalogListe quizFragenkatalogListe;
	
	public static void main(String[] args) {
		
		Quiz testQuiz = new Quiz("testing", 2);
		quizController.starteSoloQuiz(testQuiz);
	}

}

