public class Nutzer {
	private String nutzerName;
	private String nutzerEmail;
	private String nutzerPasswort;
	
	public Nutzer() {
		
	}
	
	public Nutzer(String email, String passwort) {
		this.nutzerEmail = email;
		this.nutzerPasswort = passwort;
	}
	
	public Nutzer(String name, String email, String passwort) {
		this.nutzerName = name;
		this.nutzerEmail = email;
		this.nutzerPasswort = passwort;
	}
	
	public String getNutzerEmail() {
		return nutzerEmail;
	}
	public String getNutzerPasswort() {
		return nutzerPasswort;
	}
	
	public void setNutzerEmail(String email) {
		this.nutzerEmail = email;
	}

	public void setNutzerPasswort(String passwort) {
		this.nutzerPasswort = passwort;
	}
	
	public String getNutzerName() {
		return nutzerName;
	}

	public void setNutzerName(String name) {
		this.nutzerName = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Nutzer) {
			Nutzer anmelder = (Nutzer) obj;
			if (anmelder.getNutzerEmail().equals(this.nutzerEmail) && anmelder.getNutzerPasswort().equals(this.nutzerPasswort)) {
				return true;
			}
			
		}
		return false;
	}
	
}