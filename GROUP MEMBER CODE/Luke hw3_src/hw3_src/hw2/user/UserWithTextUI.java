package hw2.user;

import hw2.ui.UserInterface;

public class UserWithTextUI extends User {
	
	public UserWithTextUI(String username, String email) {
		super(username, email);
	}
	
	protected UserInterface createUI() {
		return new UserTextUI(this);
	}
}
