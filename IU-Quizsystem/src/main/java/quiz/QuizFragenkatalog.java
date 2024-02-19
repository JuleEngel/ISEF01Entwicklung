package quiz;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class QuizFragenkatalog {
	@Id
	private int id;
	private String question;
	private String correct_answer;
	private String incorrect_answer_1;
	private String incorrect_answer_2;
	private String incorrect_answer_3;
	private int difficulty;
	private String module_short;
	private String module;
	private String explanation;
	private String status;
	
	public QuizFragenkatalog() {
	}
	
	public QuizFragenkatalog(int id, String question, String correct_answer, String incorrect_answer_1,
            String incorrect_answer_2, String incorrect_answer_3, int difficulty,
            String module_short, String module, String explanation, String status) {
	   this.id = id;
	   this.question = question;
	   this.correct_answer = correct_answer;
	   this.incorrect_answer_1 = incorrect_answer_1;
	   this.incorrect_answer_2 = incorrect_answer_2;
	   this.incorrect_answer_3 = incorrect_answer_3;
	   this.difficulty = difficulty;
	   this.module_short = module_short;
	   this.module = module;
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

	
	public String getModule_short() {
		return module_short;
	}

	public void setModule_short(String module_short) {
		this.module_short = module_short;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
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
