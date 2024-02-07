package quiz;

public class Player {

    private String name;
    private int score;
    private int selectedAnswer;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.selectedAnswer = -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(int selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}
