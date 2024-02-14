package fragenkatalog;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fragenkatalog {
	@Id
	private int id;
	private String question;
	private String correct_answer;
	private String incorrect_answer_1;
	private String incorrect_answer_2;
	private String incorrect_answer_3;
	private int difficulty;
	private int module_id;
	private String explanation;
	private String status;
	
	public Fragenkatalog() {
	}
	
	public Fragenkatalog(int id, String question, String correct_answer, String incorrect_answer_1,
            String incorrect_answer_2, String incorrect_answer_3, int difficulty, int module_id, String explanation, String status) {
	   this.id = id;
	   this.question = question;
	   this.correct_answer = correct_answer;
	   this.incorrect_answer_1 = incorrect_answer_1;
	   this.incorrect_answer_2 = incorrect_answer_2;
	   this.incorrect_answer_3 = incorrect_answer_3;
	   this.difficulty = difficulty;
	   this.module_id = module_id;
	   this.explanation = explanation;
	   this.status = status;
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

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	
	public int getModule_id() {
		return module_id;
	}

	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
