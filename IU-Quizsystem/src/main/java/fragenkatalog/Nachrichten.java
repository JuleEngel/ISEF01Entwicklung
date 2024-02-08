package fragenkatalog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Nachrichten {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrement
	private int message_id;
	private int user_id;
	private int question_id;
	private String message;
	
	public Nachrichten() {
	}
	
	public Nachrichten(int message_id, int user_id, int question_id, String message) {
	   this.message_id = message_id;
	   this.user_id = user_id;
	   this.question_id = question_id;
	   this.message = message;
	}

	public int getMessage_id() {
		return message_id;
	}

	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
