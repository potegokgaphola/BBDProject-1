package billtracker.login;


public class CurrentUser implements User {
	private static int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
