package quiz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fragenkatalog.Fragenkatalog;
import fragenkatalog.FragenkatalogListe;
import fragenkatalog.Modules;
import fragenkatalog.ModulesController;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import login.User;


//Java-Klasse zur Verwaltung von Fragen und Antworten
@SuppressWarnings("serial")
@Named
@SessionScoped
public class QuizSoloController implements Serializable {
	
	@Inject
	ModulesController modulesController;
	@Inject
	QuizController quizController;
	@Inject
	FragenkatalogListe fragenkatalogListe;
	
	private int score;
	private Modules module;
	private int module_id;
	private int difficulty;
	private User user;
	private boolean quizActive = false;
	private int tempModule;
	private Fragenkatalog tempQuestion;
	private String tempAnswer1;
	private String tempAnswer2;
	private String tempAnswer3;
	private String tempAnswer4;
	private String choosedAnswer;
	private String solution = "blubber";
	private boolean solved = false;
	private List<String> answersToTempQuestion;
	private List<Fragenkatalog> randomQuestionList;

	
	public String startQuiz() {
		this.module = modulesController.getModuleByID(module_id);
		this.setQuizActive(true);
		this.score = 0;
		this.user = quizController.getUserLogin();
		this.randomQuestionList = getRandomQuestionListFromQuestionList();
		setQuestion();
		return "soloQuiz?faces-redirect=true";
	}
	
	public void checkChoosedAnswer() {
		if (choosedAnswer.equals(tempQuestion.getCorrect_answer())) {
			score += 10;
			this.solution = "Richtig";
		}
		else {
			this.solution = "Falsch";
		}
		this.solved = true;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "soloQuiz?faces-redirect=true");
		System.out.println(solution);
	}
	
	public void postValidateModule(ComponentSystemEvent event) throws AbortProcessingException{
		UIInput temp = (UIInput)event.getComponent();
		this.tempModule = (int)temp.getValue();
	}
	
	public boolean checkEnoughQuestions(FacesContext context, UIComponent component, Object value) throws ValidatorException{
    	this.difficulty = (int) value;
    	List<Fragenkatalog> tempList = getQuestionList();
    	if (this.tempModule == 0) {
    		reset();
    		throw new ValidatorException(new FacesMessage("Bitte wähle zunächst ein Modul aus!"));
    	}
		if (tempList.size() < 5) {
    		reset();
    		throw new ValidatorException(new FacesMessage("Dieses Modul hat derzeit nicht genug Fragen!"));
    	}
		this.module_id = this.tempModule;
    	return true;
	}

	public List<Fragenkatalog> getQuestionList() {
		List<Fragenkatalog> tempListe = new ArrayList<>();
		if (this.tempModule != 0) {
			for (Fragenkatalog fragenkatalog : fragenkatalogListe.getFragenkatalogListe()) {
				if (fragenkatalog.getDifficulty() == difficulty && fragenkatalog.getModule_id() == tempModule 
							&& (fragenkatalog.getStatus().equals("published") || fragenkatalog.getStatus().equals("reported"))){
					tempListe.add(fragenkatalog);
				}
			}
		}
    	return tempListe;
    }
	
    public List<Fragenkatalog> getRandomQuestionListFromQuestionList() {
    	List<Fragenkatalog> questionList = getQuestionList();
    	List<Fragenkatalog> selectedQuestions = new ArrayList<>();
    	List<Fragenkatalog> tempList = new ArrayList<>(questionList);
    	
    	Random random = new Random();
    	int n = Math.min(5, questionList.size()); //Nur fünf Elemente nutzen
    	
    	for (int i = 0; i < n; i++) {
    		int randomIndex = random.nextInt(tempList.size()); //Random Index
    		selectedQuestions.add(tempList.get(randomIndex)); //Füge die Frage zur Fragenliste hinzu
    		tempList.remove(randomIndex); //Entferne Frage mit Index, um Duplikate zu verhindern
    	}
    	return selectedQuestions;
    }
	
	public void setQuestion() {
		tempQuestion = randomQuestionList.get(0);
		this.answersToTempQuestion.add(tempQuestion.getCorrect_answer());
		this.answersToTempQuestion.add(tempQuestion.getIncorrect_answer_1());
		this.answersToTempQuestion.add(tempQuestion.getIncorrect_answer_2());
		this.answersToTempQuestion.add(tempQuestion.getIncorrect_answer_3());
		randomQuestionList.remove(0);
		setAnswer();
	}
	
	public void setAnswer() {
    	Random random = new Random();
    	int randomInt = random.nextInt(answersToTempQuestion.size());
    	this.tempAnswer1 = answersToTempQuestion.get(randomInt);
    	answersToTempQuestion.remove(randomInt);
    	randomInt = random.nextInt(answersToTempQuestion.size());
    	this.tempAnswer2 = answersToTempQuestion.get(randomInt);
    	answersToTempQuestion.remove(randomInt);
    	randomInt = random.nextInt(answersToTempQuestion.size());
    	this.tempAnswer3 = answersToTempQuestion.get(randomInt);
    	answersToTempQuestion.remove(randomInt);
    	this.tempAnswer4 = answersToTempQuestion.get(0);
    	answersToTempQuestion.remove(0);
	}
	
	public void reset() {
		this.setQuizActive(false);
		this.score = 0;
		this.tempQuestion = new Fragenkatalog();
		this.answersToTempQuestion = new ArrayList<>();
		this.tempAnswer1 = "";
		this.tempAnswer2 = "";
		this.tempAnswer3 = "";
		this.tempAnswer4 = "";
		this.module = new Modules();
		this.module_id = 0;
		this.difficulty = 0;
		this.user = new User();
		this.choosedAnswer = "";
		this.solution = "";
		this.randomQuestionList = new ArrayList<>();
		this.solved = false;
	}

	public void checkIsQuizActive() {
	    if (!quizActive) {
	    	reset();
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "/mainpage/indexStudent?faces-redirect=true");
	    }
	}
	
	public void resetAnswerView() {
		this.choosedAnswer = "";
	}
	
	public void updateChoosedAnswer(String answer) {
		this.choosedAnswer = answer;
		System.out.println(choosedAnswer);
	}
	
	public int getModule_id() {
		return module_id;
	}

	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}

	public Modules getModule() {
		return module;
	}

	public void setModule(Modules module) {
		this.module = module;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


	public void setQuizActive(boolean quizActive) {
		this.quizActive = quizActive;
	}

	public List<Fragenkatalog> getRandomQuestionList() {
		return randomQuestionList;
	}

	public void setRandomQuestionList(List<Fragenkatalog> randomQuestionList) {
		this.randomQuestionList = randomQuestionList;
	}

	public Fragenkatalog getTempQuestion() {
		return tempQuestion;
	}

	public void setTempQuestion(Fragenkatalog tempQuestion) {
		this.tempQuestion = tempQuestion;
	}

	public List<String> getAnswersToTempQuestion() {
		return answersToTempQuestion;
	}

	public void setAnswersToTempQuestion(List<String> answersToTempQuestion) {
		this.answersToTempQuestion = answersToTempQuestion;
	}

	public String getTempAnswer1() {
		return tempAnswer1;
	}

	public void setTempAnswer1(String tempAnswer1) {
		this.tempAnswer1 = tempAnswer1;
	}

	public String getTempAnswer2() {
		return tempAnswer2;
	}

	public void setTempAnswer2(String tempAnswer2) {
		this.tempAnswer2 = tempAnswer2;
	}

	public String getTempAnswer3() {
		return tempAnswer3;
	}

	public void setTempAnswer3(String tempAnswer3) {
		this.tempAnswer3 = tempAnswer3;
	}

	public String getTempAnswer4() {
		return tempAnswer4;
	}

	public void setTempAnswer4(String tempAnswer4) {
		this.tempAnswer4 = tempAnswer4;
	}

	public String getChoosedAnswer() {
		return choosedAnswer;
	}

	public void setChoosedAnswer(String choosedAnswer) {
		this.choosedAnswer = choosedAnswer;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	

}
