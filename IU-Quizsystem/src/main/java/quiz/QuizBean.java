package quiz;

import java.util.List;

//Java-Klasse zur Verwaltung von Fragen und Antworten
public class QuizBean {

	private List<Question> questions;
	private int score;
	
    public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	
    public QuizBean() {
        // Fragen aus der Datenbank abrufen und initialisieren
    }

    public void checkAnswer(Question question, String selectedAnswer) {
        if (question.getCorrectAnswer().equals(selectedAnswer)) {
            score++;
        }
    }


}
