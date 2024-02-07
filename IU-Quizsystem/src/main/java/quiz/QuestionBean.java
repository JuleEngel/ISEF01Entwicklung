package quiz;

public class QuestionBean {

    private QuizSession quizSession;

    public QuestionBean() {
    }

    public QuestionBean(QuizSession quizSession) {
        this.quizSession = quizSession;
    }

    public Question getCurrentQuestion() {
        return quizSession.getCurrentQuestion();
    }

    public void submitAnswer() {
        // Check if answer is correct    	
    	String selectedAnswer = Integer.toString(quizSession.getPlayer1().getSelectedAnswer());
    	if (selectedAnswer == quizSession.getCurrentQuestion().getCorrectAnswer()) {
            quizSession.getPlayer1().setScore(quizSession.getPlayer1().getScore() + 1);
        }
    	String selectedAnswer2 = Integer.toString(quizSession.getPlayer2().getSelectedAnswer());
        if (quizSession.getGameMode() == GameMode.DUO && selectedAnswer2 == quizSession.getCurrentQuestion().getCorrectAnswer()) {
            quizSession.getPlayer2().setScore(quizSession.getPlayer2().getScore() + 1);
        }
    }
}
    
