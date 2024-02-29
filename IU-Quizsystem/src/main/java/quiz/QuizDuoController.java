//QuizDuoController.java
package quiz;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import fragenkatalog.Fragenkatalog;
import fragenkatalog.FragenkatalogController;
import fragenkatalog.FragenkatalogListe;
import fragenkatalog.Modules;
import fragenkatalog.ModulesController;
import fragenkatalog.NachrichtenController;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.component.UISelectOne;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import login.User;
import login.UserListe;

/** 
* QuizDuoController.java
* Funktionen zur Verwaltung von Duo-Quizzen
* Die Klasse QuizDuoController.java enthält Funktionen zur Erstellung, Verwaltung und Bearbeitung von Duo-Quizzen. Hierunter fällt das
* Anlegen von temporären Variablen (z.B für Fragen, Antworten), von Variablen zur Überprüfung des Status vom Quiz, Methoden zur Validierung
* von Einstellungen und Antworten und der Interaktion mit dem User. Zudem wird hierbei der Dummy implementiert und die Funktionen zur Erstellung und
* von Quizzen erstellt.
* 
* @author JuleEngel 
* @version 1.0 
* @since 29.02.2024 
*/ 
@SuppressWarnings("serial")
@Named
@SessionScoped
public class QuizDuoController implements Serializable {
	@Inject
	ModulesController modulesController;
	@Inject
	QuizController quizController;
	@Inject
	FragenkatalogListe fragenkatalogListe;
	@Inject
	UserListe userListe;
	@Inject 
	QuizDuoList quizDuoList;
	@Inject
	FragenkatalogController fragenkatalogController;
	@Inject
	NachrichtenController nachrichtenController;
	
	//Allgemeine Einstellungen
	private Modules module;
	private int module_id;
	private int difficulty;
	private int tempModule;
	private String showModule;
	private String showDifficulty;
	private String showSecondPlayer = "Dummy"; //FOR-DUMMY
	private User dummy = new User(-1, "dummy", "dummymail", "malksjdbo231j324bnsad09nas", "dummy", 0); //FOR-DUMMY
	private boolean quizActive = false;
	private boolean wantExplanation = false;
	private boolean solved = false;
	private boolean finished = false;
	private boolean nextPlayerFinished = false;
	private String createdQuiz;
	private boolean ingame;
	private String resultUser;
	private int dummyScore = 0; //FOR-DUMMY
	private boolean questionReported = false;

	//Temporäre Variablen zu Fragen
	private Fragenkatalog tempQuestion;
	private String tempAnswer1;
	private String tempAnswer2;
	private String tempAnswer3;
	private String tempAnswer4;
	private String choosedAnswer;
	private String solution = "";
	private int correctAnswers;
	//Temporäre Liste der Fragen
	private List<String> answersToTempQuestion = new ArrayList<>();
	private List<Fragenkatalog> randomQuestionList = new ArrayList<>();

	
	
	//Validierungsmethoden Einstellungen:
	
	/**
	 * Führt die Validierung nach dem Auswählen des Moduls durch, indem das UIInput-Objekt aus dem Event 
	 * extrahiert und das Modul gesetzt wird.
	 * 
	 * @param event Das ComponentSystemEvent-Objekt.
	 * @throws AbortProcessingException Wenn die Verarbeitung abgebrochen werden soll.
	 */
	public void postValidateModule(ComponentSystemEvent event) throws AbortProcessingException{
	    UIInput temp = (UIInput)event.getComponent();
	    this.tempModule = (int)temp.getValue();
	}

	/**
	 * Überprüft, ob Fragen für das ausgewählte Schwierigkeitsniveau vorhanden sind oder bereits ein Quiz läuft.
	 * 
	 * @param context Der FacesContext.
	 * @param component Die UIComponent.
	 * @param value Der Wert des Schwierigkeitsniveaus.
	 * @return true, wenn genügend Fragen vorhanden sind, sonst false.
	 * @throws ValidatorException Wenn die Validierung fehlschlägt.
	 */
	public boolean checkEnoughQuestions(FacesContext context, UIComponent component, Object value) throws ValidatorException{
    	this.difficulty = (int) value;
    	List<Fragenkatalog> tempList = getQuestionList();
    	if(this.quizActive) {
    		throw new ValidatorException(new FacesMessage("Es läuft bereits ein aktives Quiz!"));
    	}
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
	
	/**
	 * Überprüft, ob genügend Fragen im Fragenkatalog für das aktuelle Modul und die Schwierigkeitsstufe vorhanden sind.
	 * Es werden nur Fragen mit dem Status "published" oder "reported" berücksichtigt.
	 * Die Methode gibt true zurück, wenn mindestens 5 Fragen gefunden werden, andernfalls false.
	 *
	 * @return true, wenn genügend Fragen vorhanden sind, sonst false.
	 */
	public boolean checkEnoughQuestionsDummy() {
		List<Fragenkatalog> tempListe = new ArrayList<Fragenkatalog>();
		if (this.module_id != 0) {
			for (Fragenkatalog fragenkatalog : fragenkatalogListe.getFragenkatalogListe()) {
				if (fragenkatalog.getDifficulty() == difficulty && fragenkatalog.getModule_id() == this.module_id 
							&& (fragenkatalog.getStatus().equals("published") || fragenkatalog.getStatus().equals("reported"))){
					tempListe.add(fragenkatalog);
				}
			}
		}
		if (tempListe.size() < 5) {
    		return false;
    	}
		return true;
	}
	
	
	
	//Methoden um die Funktion Duo-Quiz
	
	/**
	 * Tritt einem Spieler-Quiz bei und erstellt ein Dummy-Quiz für den aktuellen Benutzer.
	 * Das Quiz wird aktiviert und ein Dummy-Quiz wird generiert, indem zufällig ein Modul und eine Schwierigkeitsstufe ausgewählt werden.
	 * Die Methode überprüft, ob genügend Fragen für das ausgewählte Modul und die Schwierigkeitsstufe vorhanden sind.
	 * Wenn genügend Fragen vorhanden sind, wird das Dummy-Quiz erstellt und dem QuizDuoList hinzugefügt.
	 * Das Modul und die Schwierigkeitsstufe werden entsprechend gesetzt, und die Variable 'createdQuiz' wird auf 'dummy' gesetzt.
	 * Die Methode kehrt zur DuoQuizSucheNachQuiz-Seite zurück.
	 *
	 * @return Die Navigationsregel für die DuoQuizSucheNachQuiz-Seite.
	 */
	public String joinPlayerQuiz() {
		this.quizActive = true;
		//FOR-DUMMY
		Random random = new Random();
		this.module_id = random.nextInt(3) + 1;
		this.difficulty = random.nextInt(3) + 1;
		while (!checkEnoughQuestionsDummy()) {
			this.module_id = random.nextInt(3) + 1;
			this.difficulty = random.nextInt(3) + 1;
		}
		QuizDuo createdDummyQuiz = new QuizDuo(this.module_id, this.difficulty, this.dummy);
		quizDuoList.addQuiz(createdDummyQuiz);
		this.setShowModule(modulesController.getModuleLong(module_id));
		this.setShowDifficulty(fragenkatalogController.formatDifficulty(difficulty));
		this.createdQuiz = "dummy"; //FOR-DUMMY
		return "duoQuizSearchForQuiz?faces-redirect=true";
	}
	
	/**
	 * Erstellt ein Spieler-Quiz für den aktuellen Benutzer.
	 * Das Quiz wird aktiviert und für das ausgewählte Modul und die Schwierigkeitsstufe erstellt.
	 * Das erstellte Quiz wird dem QuizDuoList hinzugefügt.
	 * Das Modul und die Schwierigkeitsstufe werden entsprechend gesetzt, und die Variable 'createdQuiz' wird auf 'user1' gesetzt.
	 * Die Methode kehrt zur DuoQuizWartenAufSpieler-Seite zurück.
	 *
	 * @return Die Navigationsregel für die DuoQuizWartenAufSpieler-Seite.
	 */
	public String createPlayerQuiz() {
		this.quizActive = true;
		QuizDuo createdQuiz = new QuizDuo(this.module_id, this.difficulty, quizController.getUserLogin());
		quizDuoList.addQuiz(createdQuiz);
		this.setShowModule(modulesController.getModuleLong(module_id));
		this.setShowDifficulty(fragenkatalogController.formatDifficulty(difficulty));
		this.createdQuiz = "user1";
		return "duoQuizWaitForPlayers?faces-redirect=true";
	}
	
	/**
	 * Startet das Quiz und bereitet es für das Spiel vor.
	 * Setzt das Quiz als aktiv und den Benutzer als im Spiel.
	 * Ermittelt das QuizDuo basierend auf dem erstellten Quiztyp ("user1" oder "dummy").
	 * Fügt den aktuellen Benutzer zum QuizDuo hinzu und entfernt das QuizDuo aus der QuizDuoList.
	 * Setzt das Modul und die Schwierigkeitsstufe des Quiz entsprechend.
	 * Holt zufällige Fragen für das Quiz und bereitet das Quiz vor, um die erste Frage anzuzeigen.
	 * Setzt verschiedene Spielvariablen zurück, wie z.B. den nächsten Spieler, das Ergebnis und die Anzahl der korrekten Antworten.
	 * Die Methode leitet zur DuoQuiz-Seite weiter, um das Spiel zu starten.
	 *
	 * @return Die Navigationsregel für die DuoQuiz-Seite.
	 */
	public String startQuiz() {
		this.quizActive = true;
		this.ingame = true;
		if (this.createdQuiz.equals("user1")) {
			QuizDuo quizDuo = quizDuoList.getQuizFromUser(quizController.getUserLogin());
			quizDuo.addUserToQuiz(this.dummy);
			this.difficulty = quizDuo.getDifficulty();
			this.module_id = quizDuo.getModule_id();
			quizDuoList.removeQuiz(quizDuo);
		}
		else if (this.createdQuiz.equals("dummy")){
			QuizDuo quizDuo = quizDuoList.getQuizFromUser(this.dummy);
			quizDuo.addUserToQuiz(quizController.getUserLogin());
			this.difficulty = quizDuo.getDifficulty();
			this.module_id = quizDuo.getModule_id();
			quizDuoList.removeQuiz(quizDuo);
		}
		else {
			System.out.println("Fehler in der Spielersuche");
		}
		this.createdQuiz = "";
		this.module = modulesController.getModuleByID(module_id);
		this.randomQuestionList = getRandomQuestionListFromQuestionList();
		this.nextPlayerFinished = false;
		this.resultUser = "";
		this.dummyScore = 0;
		this.correctAnswers = 0;
		setQuestion();
		return "duoQuiz?faces-redirect=true";
	}
	

	//Allgemeine Quiz-Funktionen
	
	/**
	 * Ruft die Liste der Fragen für das ausgewählte Modul und Schwierigkeitsniveau ab.
	 * 
	 * @return Die Liste der Fragen für das ausgewählte Modul und Schwierigkeitsniveau.
	 */
	public List<Fragenkatalog> getQuestionList() {
		List<Fragenkatalog> tempListe = new ArrayList<>();
		if (this.module_id != 0) {
			for (Fragenkatalog fragenkatalog : fragenkatalogListe.getFragenkatalogListe()) {
				if (fragenkatalog.getDifficulty() == difficulty && fragenkatalog.getModule_id() == this.module_id 
							&& (fragenkatalog.getStatus().equals("published") || fragenkatalog.getStatus().equals("reported"))){
					tempListe.add(fragenkatalog);
				}
			}
		}
    	return tempListe;
    }
	
	/**
	 * Erzeugt eine zufällige Liste von Fragen aus der Liste der verfügbaren Fragen.
	 * 
	 * @return Eine zufällige Liste von Fragen.
	 */
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
    
    /**
     * Leitet zur Anzeige für die Quizergebnisse weiter.
     * 
     * @return Der Umleitpfad zur Solo-Ergebnisseite des Quiz.
     */
	public String showResults() {
		return "/quiz/duoResults?faces-redirect=true";
	}
	
	/**
	 * Setzt alle Quizvariablen zurück, um das Quiz auf den Ausgangszustand zurückzusetzen.
	 */
	public void reset() {
		try {
			QuizDuo tempQuiz = quizDuoList.getQuizFromUser(quizController.getUserLogin());
			quizDuoList.removeQuiz(tempQuiz);
		} catch (Exception e) {
		}
		this.ingame = false;
		this.nextPlayerFinished = false;
		this.resultUser = "";
		this.createdQuiz = "";
		this.showModule = "";
		this.showDifficulty = "";
		this.setQuizActive(false);
		this.tempQuestion = new Fragenkatalog();
		this.answersToTempQuestion = new ArrayList<>();
		this.tempAnswer1 = "";
		this.tempAnswer2 = "";
		this.tempAnswer3 = "";
		this.tempAnswer4 = "";
		this.module = new Modules();
		this.module_id = 0;
		this.difficulty = 0;
		this.choosedAnswer = "";
		this.setSolution("");
		this.randomQuestionList = new ArrayList<>();
		this.solved = false;
		this.wantExplanation = false;
		this.finished = false;
		this.correctAnswers = 0;
		this.dummyScore = 0;
		this.questionReported = false;
	}
	
	/**
	 * Überprüft, ob das Quiz aktiv ist. Wenn nicht, wird das Quiz zurückgesetzt und der 
	 * Benutzer zur Hauptseite für Studenten weitergeleitet.
	 */
	public void checkIsQuizActive() {
	    if (!quizActive) {
	    	reset();
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "/mainpage/indexStudent?faces-redirect=true");
	    }
	}
	
	/**
	 * Überprüft, ob der Benutzer sich bereits im Spiel befindet.
	 * Wenn der Benutzer bereits im Spiel ist, wird das Spiel zurückgesetzt und er zur Hauptseite für Studenten weitergeleitet.
	 */
	public void checkIsIngame() {
		if (ingame) {
			reset();
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "/mainpage/indexStudent?faces-redirect=true");
		}
	}
	
	/**
	 * Überprüft, ob das Quiz abgeschlossen ist. Wenn das Quiz nicht abgeschlossen ist, wird es zurückgesetzt und der 
	 * Benutzer zur Hauptseite für Studenten weitergeleitet.
	 * Wenn das Quiz abgeschlossen ist, wird die Anzahl der gespielten Spiele des Benutzers aktualisiert und das "finished" Attribut zurückgesetzt.
	 */
	public void checkIsQuizFinished () {
	    this.quizActive = false;
	    //FOR-DUMMY
	    Random random = new Random();
	    this.dummyScore = random.nextInt(5) + 1;
	    if (this.correctAnswers > dummyScore) {
		    this.resultUser = "Du hast das Quiz gewonnen!";
	    }
	    else if (this.correctAnswers < dummyScore) {
	    	this.resultUser = "Du hast das Quiz verloren!";
	    }
	    else {
	    	this.resultUser = "Unentschieden!";
	    }
		if (!finished) {
	    	reset();
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "/mainpage/indexStudent?faces-redirect=true");
	    }
		else {
			quizController.getUserLogin().setPlayedgames(quizController.getUserLogin().getPlayedgames() + 1);
			userListe.updatePlayedGames(quizController.getUserLogin().getId(), quizController.getUserLogin().getPlayedgames());
			this.finished = false;
		}
	}
		
	
		
	//Methoden zu einzelnen Fragen
	
	/**
	 * Überprüft die ausgewählte Antwort des Benutzers. Wenn die Antwort korrekt ist, wird die Anzahl der 
	 * richtigen Antworten erhöht und die Lösung wird auf "Richtig" gesetzt. 
	 * Andernfalls wird die Lösung auf "Falsch" gesetzt. 
	 * Dann wird das Quiz als gelöst markiert und die Seite aktualisiert.
	 */
	public void checkChoosedAnswer() {
		if (choosedAnswer.equals(tempQuestion.getCorrect_answer())) {
			this.correctAnswers += 1;
			this.setSolution("Richtig");
		}
		else {
			this.setSolution("Falsch");
		}
		this.solved = true;
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "duoQuiz?faces-redirect=true");
        new Thread(() -> {
            try {
                Thread.sleep(2000); // Verzögerung von 2 Sekunden
                nextPlayerFinished = true; // Wert ändern nach der Verzögerung
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
	}

	/**
	 * Setzt die Frage für das Quiz fest, indem eine zufällige Frage aus der Liste ausgewählt wird und die Antworten 
	 * darauf gesetzt werden.
	 */
	public void setQuestion() {
		tempQuestion = randomQuestionList.get(0);
		this.answersToTempQuestion.add(tempQuestion.getCorrect_answer());
		this.answersToTempQuestion.add(tempQuestion.getIncorrect_answer_1());
		this.answersToTempQuestion.add(tempQuestion.getIncorrect_answer_2());
		this.answersToTempQuestion.add(tempQuestion.getIncorrect_answer_3());
		randomQuestionList.remove(0);
		setAnswer();
	}
	
	/**
	 * Setzt die Antworten für die aktuelle Frage fest, indem zufällige Antworten aus der Liste ausgewählt werden.
	 */
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
	
	/**
	 * Geht zur nächsten Frage im Quiz über, aktualisiert den Status des Quiz bei Bedarf und aktualisiert die Seite.
	 */
	public void nextQuestion() {
		setQuestion();
		nextPlayerFinished = false;
		if (this.randomQuestionList.isEmpty()) {
			this.finished = true;
		}
		this.wantExplanation = false;
		this.solved = false;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "duoQuiz?faces-redirect=true");
	}
	
	/**
	 * Zeigt die Erklärung zur aktuellen Frage an und aktualisiert die Seite, um die Änderung wirksam zu machen.
	 */
	public void showExplanation() {
		this.wantExplanation = true;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "duoQuiz?faces-redirect=true");
	}
	
	/**
	 * Überprüft, ob der Benutzer eine Erklärung zur aktuellen Frage sehen möchte.
	 * 
	 * @return true, wenn der Benutzer eine Erklärung sehen möchte, sonst false.
	 */
	public boolean checkWantExplanation() {
		if (this.wantExplanation) {
			return true;
		}
		return false;
	}
	
	/**
	 * Überprüft, ob das Quiz bereits gelöst wurde, und setzt die Variable "wantExplanation" entsprechend zurück.
	 */
	public void reloadCheck() {
		if (!this.solved) {
			this.wantExplanation = false;
		}
	}
	
	/**
	 * Behandelt die Änderung der ausgewählten Antwort durch den Benutzer.
	 * 
	 * @param event Das AjaxBehaviorEvent-Objekt.
	 */
	public void handleAnswerChange(AjaxBehaviorEvent event) {
	    UISelectOne selectOne = (UISelectOne) event.getComponent();
	    String selectedAnswer = (String) selectOne.getValue();
	    this.choosedAnswer = selectedAnswer;
	}
	
	/**
	 * Meldet die aktuelle Frage und setzt den Status der Frage als gemeldet.
	 * Die Methode ruft die Methode reportMessage() des NachrichtenControllers auf, um die Frage zu melden.
	 * Danach wird der Status 'questionReported' auf true gesetzt.
	 * Schließlich leitet die Methode zur DuoQuiz-Seite weiter, um fortzufahren.
	 */
	public void reportQuestion() {
		nachrichtenController.reportMessage(this.tempQuestion);
		this.questionReported = true;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "duoQuiz?faces-redirect=true");
	}
	

	//Getter und Setter
	
	/**
	 * Gibt die ID des Moduls zurück.
	 * 
	 * @return Die ID des Moduls.
	 */
	public int getModule_id() {
	    return module_id;
	}

	/**
	 * Legt die ID des Moduls fest.
	 * 
	 * @param module_id Die ID des Moduls.
	 */
	public void setModule_id(int module_id) {
	    this.module_id = module_id;
	}

	/**
	 * Gibt das Modul zurück.
	 * 
	 * @return Das Modul.
	 */
	public Modules getModule() {
	    return module;
	}

	/**
	 * Legt das Modul fest.
	 * 
	 * @param module Das Modul.
	 */
	public void setModule(Modules module) {
	    this.module = module;
	}

	/**
	 * Gibt die Schwierigkeit zurück.
	 * 
	 * @return Die Schwierigkeit.
	 */
	public int getDifficulty() {
	    return difficulty;
	}

	/**
	 * Legt die Schwierigkeit des Quiz fest.
	 * 
	 * @param difficulty Die Schwierigkeit des Quiz.
	 */
	public void setDifficulty(int difficulty) {
	    this.difficulty = difficulty;
	}

	/**
	 * Legt fest, ob das Quiz aktiv ist.
	 * 
	 * @param quizActive true, wenn das Quiz aktiv ist, sonst false.
	 */
	public void setQuizActive(boolean quizActive) {
	    this.quizActive = quizActive;
	}

	/**
	 * Gibt die Liste der zufälligen Fragen zurück.
	 * 
	 * @return Die Liste der zufälligen Fragen.
	 */
	public List<Fragenkatalog> getRandomQuestionList() {
	    return randomQuestionList;
	}

	/**
	 * Legt die Liste der zufälligen Fragen fest.
	 * 
	 * @param randomQuestionList Die Liste der zufälligen Fragen.
	 */
	public void setRandomQuestionList(List<Fragenkatalog> randomQuestionList) {
	    this.randomQuestionList = randomQuestionList;
	}

	/**
	 * Gibt die temporäre Frage zurück.
	 * 
	 * @return Die temporäre Frage.
	 */
	public Fragenkatalog getTempQuestion() {
	    return tempQuestion;
	}

	/**
	 * Legt die temporäre Frage fest.
	 * 
	 * @param tempQuestion Die temporäre Frage.
	 */
	public void setTempQuestion(Fragenkatalog tempQuestion) {
	    this.tempQuestion = tempQuestion;
	}

	/**
	 * Gibt die Liste der Antworten zur temporären Frage zurück.
	 * 
	 * @return Die Liste der Antworten zur temporären Frage.
	 */
	public List<String> getAnswersToTempQuestion() {
	    return answersToTempQuestion;
	}

	/**
	 * Legt die Liste der Antworten zur temporären Frage fest.
	 * 
	 * @param answersToTempQuestion Die Liste der Antworten zur temporären Frage.
	 */
	public void setAnswersToTempQuestion(List<String> answersToTempQuestion) {
	    this.answersToTempQuestion = answersToTempQuestion;
	}

	/**
	 * Gibt die erste temporäre Antwort zurück.
	 * 
	 * @return Die erste temporäre Antwort.
	 */
	public String getTempAnswer1() {
	    return tempAnswer1;
	}

	/**
	 * Legt die erste temporäre Antwort fest.
	 * 
	 * @param tempAnswer1 Die erste temporäre Antwort.
	 */
	public void setTempAnswer1(String tempAnswer1) {
	    this.tempAnswer1 = tempAnswer1;
	}

	/**
	 * Gibt die zweite temporäre Antwort zurück.
	 * 
	 * @return Die zweite temporäre Antwort.
	 */
	public String getTempAnswer2() {
	    return tempAnswer2;
	}

	/**
	 * Legt die zweite temporäre Antwort fest.
	 * 
	 * @param tempAnswer2 Die zweite temporäre Antwort.
	 */
	public void setTempAnswer2(String tempAnswer2) {
	    this.tempAnswer2 = tempAnswer2;
	}

	/**
	 * Gibt die dritte temporäre Antwort zurück.
	 * 
	 * @return Die dritte temporäre Antwort.
	 */
	public String getTempAnswer3() {
	    return tempAnswer3;
	}

	/**
	 * Legt die dritte temporäre Antwort fest.
	 * 
	 * @param tempAnswer3 Die dritte temporäre Antwort.
	 */
	public void setTempAnswer3(String tempAnswer3) {
	    this.tempAnswer3 = tempAnswer3;
	}

	/**
	 * Gibt die vierte temporäre Antwort zurück.
	 * 
	 * @return Die vierte temporäre Antwort.
	 */
	public String getTempAnswer4() {
	    return tempAnswer4;
	}

	/**
	 * Legt die vierte temporäre Antwort fest.
	 * 
	 * @param tempAnswer4 Die vierte temporäre Antwort.
	 */
	public void setTempAnswer4(String tempAnswer4) {
	    this.tempAnswer4 = tempAnswer4;
	}

	/**
	 * Gibt die vom Benutzer gewählte Antwort zurück.
	 * 
	 * @return Die vom Benutzer gewählte Antwort.
	 */
	public String getChoosedAnswer() {
	    return choosedAnswer;
	}

	/**
	 * Gibt die vom Benutzer gewählte Antwort zurück.
	 * 
	 * @return Die vom Benutzer gewählte Antwort.
	 */
	public String returnChoosedAnswer() {
	    return choosedAnswer;
	}

	/**
	 * Legt die vom Benutzer gewählte Antwort fest.
	 * 
	 * @param choosedAnswer Die vom Benutzer gewählte Antwort.
	 */
	public void setChoosedAnswer(String choosedAnswer) {
	    this.choosedAnswer = choosedAnswer;
	}

	/**
	 * Gibt zurück, ob die Frage gelöst wurde oder nicht.
	 * 
	 * @return true, wenn die Frage gelöst wurde, sonst false.
	 */
	public boolean isSolved() {
	    return solved;
	}

	/**
	 * Legt fest, ob die Frage gelöst wurde oder nicht.
	 * 
	 * @param solved true, wenn die Frage gelöst wurde, sonst false.
	 */
	public void setSolved(boolean solved) {
	    this.solved = solved;
	}

	/**
	 * Überprüft, ob der Benutzer eine Erklärung zur aktuellen Frage sehen möchte.
	 * 
	 * @return true, wenn der Benutzer eine Erklärung sehen möchte, sonst false.
	 */
	public boolean isWantExplanation() {
	    return wantExplanation;
	}

	/**
	 * Legt fest, ob der Benutzer eine Erklärung zur aktuellen Frage sehen möchte.
	 * 
	 * @param wantExplanation true, wenn der Benutzer eine Erklärung sehen möchte, sonst false.
	 */
	public void setWantExplanation(boolean wantExplanation) {
	    this.wantExplanation = wantExplanation;
	}

	/**
	 * Überprüft, ob das Quiz abgeschlossen wurde.
	 * 
	 * @return true, wenn das Quiz abgeschlossen wurde, sonst false.
	 */
	public boolean isFinished() {
	    return finished;
	}

	/**
	 * Legt fest, ob das Quiz abgeschlossen wurde.
	 * 
	 * @param finished true, wenn das Quiz abgeschlossen wurde, sonst false.
	 */
	public void setFinished(boolean finished) {
	    this.finished = finished;
	}

	/**
	 * Gibt die Anzahl der korrekten Antworten zurück.
	 * 
	 * @return Die Anzahl der korrekten Antworten.
	 */
	public int getCorrectAnswers() {
	    return correctAnswers;
	}

	/**
	 * Legt die Anzahl der korrekten Antworten fest.
	 * 
	 * @param correctAnswers Die Anzahl der korrekten Antworten.
	 */
	public void setCorrectAnswers(int correctAnswers) {
	    this.correctAnswers = correctAnswers;
	}

	/**
	 * Gibt die Lösung für die aktuelle Frage zurück.
	 * 
	 * @return Die Lösung für die aktuelle Frage.
	 */
	public String getSolution() {
	    return solution;
	}

	/**
	 * Legt die Lösung für die aktuelle Frage fest.
	 * 
	 * @param solution Die Lösung für die aktuelle Frage.
	 */
	public void setSolution(String solution) {
	    this.solution = solution;
	}

	/**
	 * Gibt das angezeigte Modul zurück.
	 *
	 * @return Das angezeigte Modul.
	 */
	public String getShowModule() {
	    return showModule;
	}

	/**
	 * Legt das angezeigte Modul fest.
	 *
	 * @param showModule Das angezeigte Modul.
	 */
	public void setShowModule(String showModule) {
	    this.showModule = showModule;
	}

	/**
	 * Gibt die angezeigte Schwierigkeitsstufe zurück.
	 *
	 * @return Die angezeigte Schwierigkeitsstufe.
	 */
	public String getShowDifficulty() {
	    return showDifficulty;
	}

	/**
	 * Legt die angezeigte Schwierigkeitsstufe fest.
	 *
	 * @param showDifficulty Die angezeigte Schwierigkeitsstufe.
	 */
	public void setShowDifficulty(String showDifficulty) {
	    this.showDifficulty = showDifficulty;
	}

	/**
	 * Gibt den angezeigten zweiten Spieler zurück.
	 *
	 * @return Der angezeigte zweite Spieler.
	 */
	public String getShowSecondPlayer() {
	    return showSecondPlayer;
	}

	/**
	 * Legt den angezeigten zweiten Spieler fest.
	 *
	 * @param showSecondPlayer Der angezeigte zweite Spieler.
	 */
	public void setShowSecondPlayer(String showSecondPlayer) {
	    this.showSecondPlayer = showSecondPlayer;
	}

	/**
	 * Überprüft, ob der nächste Spieler fertig ist.
	 *
	 * @return true, wenn der nächste Spieler fertig ist, sonst false.
	 */
	public boolean isNextPlayerFinished() {
	    return nextPlayerFinished;
	}

	/**
	 * Legt fest, ob der nächste Spieler fertig ist.
	 *
	 * @param nextPlayerFinished true, wenn der nächste Spieler fertig ist, sonst false.
	 */
	public void setNextPlayerFinished(boolean nextPlayerFinished) {
	    this.nextPlayerFinished = nextPlayerFinished;
	}

	/**
	 * Überprüft, ob das Quiz erstellt wurde.
	 *
	 * @return Der Status, ob das Quiz erstellt wurde (true) oder nicht (false).
	 */
	public String isCreatedQuiz() {
	    return createdQuiz;
	}

	/**
	 * Legt fest, ob das Quiz erstellt wurde.
	 *
	 * @param createdQuiz Der Status, ob das Quiz erstellt wurde (true) oder nicht (false).
	 */
	public void setCreatedQuiz(String createdQuiz) {
	    this.createdQuiz = createdQuiz;
	}

	/**
	 * Überprüft, ob sich der Benutzer im Spiel befindet.
	 *
	 * @return true, wenn der Benutzer im Spiel ist, sonst false.
	 */
	public boolean isIngame() {
	    return ingame;
	}

	/**
	 * Legt fest, ob sich der Benutzer im Spiel befindet.
	 *
	 * @param ingame Der Status, ob sich der Benutzer im Spiel befindet (true) oder nicht (false).
	 */
	public void setIngame(boolean ingame) {
	    this.ingame = ingame;
	}

	/**
	 * Gibt das Ergebnis des Benutzers zurück.
	 *
	 * @return Das Ergebnis des Benutzers.
	 */
	public String getResultUser() {
	    return resultUser;
	}

	/**
	 * Legt das Ergebnis des Benutzers fest.
	 *
	 * @param resultUser Das Ergebnis des Benutzers.
	 */
	public void setResultUser(String resultUser) {
	    this.resultUser = resultUser;
	}

	/**
	 * Gibt die Punktzahl des Dummy-Benutzers zurück.
	 *
	 * @return Die Punktzahl des Dummy-Benutzers.
	 */
	public int getDummyScore() {
	    return dummyScore;
	}

	/**
	 * Legt die Punktzahl des Dummy-Benutzers fest.
	 *
	 * @param dummyScore Die Punktzahl des Dummy-Benutzers.
	 */
	public void setDummyScore(int dummyScore) {
	    this.dummyScore = dummyScore;
	}

	/**
	 * Überprüft, ob die Frage gemeldet wurde.
	 *
	 * @return true, wenn die Frage gemeldet wurde, sonst false.
	 */
	public boolean getQuestionReported() {
	    return questionReported;
	}

	/**
	 * Legt fest, ob die Frage gemeldet wurde.
	 *
	 * @param questionReported Der Status, ob die Frage gemeldet wurde (true) oder nicht (false).
	 */
	public void setQuestionReported(boolean questionReported) {
	    this.questionReported = questionReported;
	}
}
