package fragenkatalog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FragenkatalogBearbeitet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int question_id;
	private int user_id;
	private String question;
	private String correct_answer;
	private String incorrect_answer_1;
	private String incorrect_answer_2;
	private String incorrect_answer_3;
	private String explanation;
	private String message;
	
	public FragenkatalogBearbeitet() {
	}
	
	public FragenkatalogBearbeitet(int id, int question_id, int user_id, String question, String correct_answer, String incorrect_answer_1,
            String incorrect_answer_2, String incorrect_answer_3, String explanation, String message) {
	   this.id = id;
	   this.question_id = question_id;
	   this.user_id = user_id;
	   this.question = question;
	   this.correct_answer = correct_answer;
	   this.incorrect_answer_1 = incorrect_answer_1;
	   this.incorrect_answer_2 = incorrect_answer_2;
	   this.incorrect_answer_3 = incorrect_answer_3;
	   this.explanation = explanation;
	   this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCorrect_answer() {
		return correct_answer;
	}

	public void setCorrect_answer(String correct_answer) {
		this.correct_answer = correct_answer;
	}

	public String getIncorrect_answer_1() {
		return incorrect_answer_1;
	}

	public void setIncorrect_answer_1(String incorrect_answer_1) {
		this.incorrect_answer_1 = incorrect_answer_1;
	}

	public String getIncorrect_answer_2() {
		return incorrect_answer_2;
	}

	public void setIncorrect_answer_2(String incorrect_answer_2) {
		this.incorrect_answer_2 = incorrect_answer_2;
	}

	public String getIncorrect_answer_3() {
		return incorrect_answer_3;
	}

	public void setIncorrect_answer_3(String incorrect_answer_3) {
		this.incorrect_answer_3 = incorrect_answer_3;
	}


	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
