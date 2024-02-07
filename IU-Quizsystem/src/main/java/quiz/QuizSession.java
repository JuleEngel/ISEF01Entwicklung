package quiz;

import java.util.List;

public class QuizSession {

    private List<Question> questions;
    private Player player1;
    private Player player2;
    private GameMode gameMode;
    private int currentQuestionIndex;

    public QuizSession() {
    }

    public QuizSession(List<Question> questions, GameMode gameMode) {
        this.questions = questions;
        this.gameMode = gameMode;
        if (gameMode == GameMode.SOLO) {
            player1 = new Player();
        } else if (gameMode == GameMode.DUO) {
            player1 = new Player();
            player2 = new Player();
        }
        currentQuestionIndex = 0;
    }

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

    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    public void nextQuestion() {
        currentQuestionIndex++;
    }

    public boolean isFinished() {
        return currentQuestionIndex == questions.size();
    }

    public int calculateScore() {
        int score = 0;
        for (Question question : questions) {
            if (question.getCorrectAnswer().equals(Integer.toString(player1.getSelectedAnswer()))) {
                score++;
            }
            if (gameMode == GameMode.DUO && question.getCorrectAnswer().equals(Integer.toString(player2.getSelectedAnswer()))) {
                score++;
            }
        }
        return score;
    }

}
