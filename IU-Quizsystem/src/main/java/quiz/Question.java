package quiz;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Question {

	@Id
	private int id;
	private String question;
	private String incorrectAnswer1;
	private String incorrectAnswer2;
	private String incorrectAnswer3;
    private String correctAnswer;
    
    
    public Question(int id, String question, String incorrectAnswer1, String incorrectAnswer2, String incorrectAnswer3,
			String correctAnswer) {
		super();
		this.id = id;
		this.question = question;
		this.incorrectAnswer1 = incorrectAnswer1;
		this.incorrectAnswer2 = incorrectAnswer2;
		this.incorrectAnswer3 = incorrectAnswer3;
		this.correctAnswer = correctAnswer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getIncorrectAnswer1() {
		return incorrectAnswer1;
	}
	public void setIncorrectAnswer1(String incorrectAnswer1) {
		this.incorrectAnswer1 = incorrectAnswer1;
	}
	public String getIncorrectAnswer2() {
		return incorrectAnswer2;
	}
	public void setIncorrectAnswer2(String incorrectAnswer2) {
		this.incorrectAnswer2 = incorrectAnswer2;
	}
	public String getIncorrectAnswer3() {
		return incorrectAnswer3;
	}
	public void setIncorrectAnswer3(String incorrectAnswer3) {
		this.incorrectAnswer3 = incorrectAnswer3;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	
  
    
}
