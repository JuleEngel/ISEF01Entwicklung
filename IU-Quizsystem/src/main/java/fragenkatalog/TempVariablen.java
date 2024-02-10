package fragenkatalog;
import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class TempVariablen implements Serializable {

    private Fragenkatalog tempFrage;
    
    
	public Fragenkatalog getTempFrage() {
		return tempFrage;
	}
	public void setTempFrage(Fragenkatalog tempFrage) {
		this.tempFrage = tempFrage;
	}
	public void editFrage(FragenkatalogBearbeitet frage) {
		tempFrage.setQuestion(frage.getQuestion());
		tempFrage.setCorrect_answer(frage.getCorrect_answer());
		tempFrage.setIncorrect_answer_1(frage.getIncorrect_answer_1());
		tempFrage.setIncorrect_answer_2(frage.getIncorrect_answer_2());
		tempFrage.setIncorrect_answer_3(frage.getIncorrect_answer_3());
		tempFrage.setExplanation(frage.getExplanation());
	}
}