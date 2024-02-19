package quiz;

import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class QuizController implements Serializable{
	
	@Inject
	private QuizFragenkatalogListe quizFragenkatalogListe = new QuizFragenkatalogListe();
	
	
	public void starteSoloQuiz(Quiz quiz) {
		System.out.println(quiz.getRandomQuestion());

	}
	
	
	
	/* public void checkAnswer(Fragenkatalog fragenkatalog, String selectedAnswer) {
	        if (fragenkatalog.getCorrect_answer().equals(selectedAnswer)) {
	            score++;
	        }
	    } */

}


/*public int calculateScore() {
    int score = 0;
    for (Question question : questions) {
        if (question.getCorrectAnswer().equals(Integer.toString(player1.getSelectedAnswer()))) {
            score++;
        }
        if (gameMode == GameMode.DUO && question.getCorrectAnswer().equals(Integer.toString(player2.getSelectedAnswer()))) {
            score++;
        }
    }
    return score; */
    

