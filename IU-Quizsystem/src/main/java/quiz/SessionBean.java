package quiz;

import java.util.ArrayList;
import java.util.List;

public class SessionBean {

    private List<Question> questions;
    private Player player1;
    private Player player2;
    private GameMode gameMode;
    private int currentQuestionIndex;
    
    public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}

	public int getCurrentQuestionIndex() {
		return currentQuestionIndex;
	}

	public void setCurrentQuestionIndex(int currentQuestionIndex) {
		this.currentQuestionIndex = currentQuestionIndex;
	}

	

    public void startGame() {
        // Initialize questions
        questions = new ArrayList<>();
        // Initialize players
        if (gameMode == GameMode.SOLO) {
            player1 = new Player();
        } else if (gameMode == GameMode.DUO) {
            player1 = new Player();
            player2 = new Player();
        }
        // Start with first question
        currentQuestionIndex = 0;
    }

    public void nextQuestion() {
        // Check if all questions answered
        if (currentQuestionIndex == questions.size() - 1) {
            // End game
            // Show results
        } else {
            // Increment question index
            currentQuestionIndex++;
        }
    }
}
