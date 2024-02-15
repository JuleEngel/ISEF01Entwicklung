//TempVariablen.java
package fragenkatalog;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

/** 
* TempVariablen.java
* Klasse für die temporären Variablen. 
* Die Klasse enthält temporäre Fragen, die zum Bearbeiten / Melden von bereits existierenden Fragen überschrieben werden.
* 
* @author JuleEngel 
* @version 1.0 
* @since 15.02.2024 
*/ 
@SuppressWarnings("serial")
@Named
@SessionScoped
public class TempVariablen implements Serializable {
    private Fragenkatalog tempFrage;
    
    /**
     * Gibt die temporäre Frage zurück.
     *
     * @return Die temporäre Frage
     */
    public Fragenkatalog getTempFrage() {
        return tempFrage;
    }

    /**
     * Legt die temporäre Frage fest.
     *
     * @param tempFrage Die temporäre Frage, die festgelegt werden soll
     */
    public void setTempFrage(Fragenkatalog tempFrage) {
        this.tempFrage = tempFrage;
    }

    /**
     * Bearbeitet die temporäre Frage mit den Werten aus der bearbeiteten Frage.
     *
     * @param frage Die bearbeitete Frage, deren Werte auf die temporäre Frage übertragen werden sollen
     */
    public void editFrage(FragenkatalogBearbeitet frage) {
        tempFrage.setQuestion(frage.getQuestion());
        tempFrage.setCorrect_answer(frage.getCorrect_answer());
        tempFrage.setIncorrect_answer_1(frage.getIncorrect_answer_1());
        tempFrage.setIncorrect_answer_2(frage.getIncorrect_answer_2());
        tempFrage.setIncorrect_answer_3(frage.getIncorrect_answer_3());
        tempFrage.setExplanation(frage.getExplanation());
    }
}