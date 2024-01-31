public class User {
	private int id;
	private String username;
	private String email;
	private String password;
	private String role;
	private int playedgames;
	
	public User() {
		
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public User(int id, String username, String email, String password, String role, int playedgames) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.playedgames = playedgames;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getPlayedgames() {
		return playedgames;
	}

	public void setPlayedgames(int playedgames) {
		this.playedgames = playedgames;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User anmelder = (User) obj;
			if (anmelder.getEmail().equals(this.email) && anmelder.getPassword().equals(this.password)) {
				return true;
			}
			
		}
		return false;
	}
	
}