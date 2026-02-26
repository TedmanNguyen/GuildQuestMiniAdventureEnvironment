package hw2.guildquest;

import java.util.ArrayList;
import hw2.user.User;
import hw2.user.UserWithTextUI;

public class UserManager {
	private static UserManager instance;
	ArrayList<User> users;
	User currentUser;
	
	private UserManager() {
		users = new ArrayList<User>();
		currentUser = null;
	}
	
	static UserManager getInstance() {
		if (instance == null)
			instance = new UserManager();
		return instance;
	}
	
	void createUser(String username, String email) {
		users.add(new UserWithTextUI(username, email));
	}
	
	void removeUser(int index) {
		if (users.isEmpty() || index > users.size())
			return;
		
		users.remove(index);
	}
	
	void selectUser(int index) {
		currentUser = users.get(index);
	}
	
	boolean isEmpty() {
		return users.isEmpty();
	}
	
	int numUsers() {
		return users.size();
	}
	
	User getUser(int index) {
		return users.get(index);
	}
}
