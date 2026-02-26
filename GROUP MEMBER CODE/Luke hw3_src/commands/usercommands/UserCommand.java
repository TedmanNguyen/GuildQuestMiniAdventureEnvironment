package hw2.commands.usercommands;

import hw2.commands.Processable;
import hw2.user.UserTextUI;

public abstract class UserCommand implements Processable {
	UserTextUI ui;
	
	public UserCommand(UserTextUI ui) {
		this.ui = ui;
	}
	
	public abstract void process();
	public abstract String toString();
}
