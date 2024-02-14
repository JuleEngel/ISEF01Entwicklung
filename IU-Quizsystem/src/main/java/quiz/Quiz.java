package quiz;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import fragenkatalog.Fragenkatalog;
import fragenkatalog.FragenkatalogListe;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import login.User;

//Java-Klasse zur Verwaltung von Fragen und Antworten
@Entity
@SessionScoped
public class Quiz {
	
	private List <Fragenkatalog> randomQuestionList;
	private FragenkatalogListe fragenkatalogListe = new FragenkatalogListe();
	private int score;
	private String module;
	private int difficulty;
	private User user1;
	private User user2;
	
	public Quiz(String module, int difficulty) {
		this.module = module;
		this.difficulty = difficulty;
	}
	

    public List<Fragenkatalog> getQuestionList() {
		List<Fragenkatalog> randomList = new ArrayList<>();
		for (Fragenkatalog frage : fragenkatalogListe.getFragenkatalogListe()) {
			if (frage.getModule_short().equals(module)) {
				if (frage.getDifficulty() == difficulty) {
					randomList.add(frage);
				}
			}
		}
		return randomList;
	}
    
    public Fragenkatalog getRandomQuestion() {
    	Fragenkatalog randomQuestion = new Fragenkatalog();
    	return randomQuestion;
    }
    
	public String getModule() {
		return module;
	}



	public void setModule(String module) {
		this.module = module;
	}



	public int getDifficulty() {
		return difficulty;
	}



	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}



	public User getUser1() {
		return user1;
	}



	public void setUser1(User user1) {
		this.user1 = user1;
	}



	public User getUser2() {
		return user2;
	}



	public void setUser2(User user2) {
		this.user2 = user2;
	}



	public void setFragenkatalogListe(FragenkatalogListe fragenkatalogListe) {
		this.fragenkatalogListe = fragenkatalogListe;
	}



	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	

}
