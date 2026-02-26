package hw2.commands.usercommands;

import hw2.user.UserTextUI;

public class ExitToMainMenuCommand extends UserCommand {
	
	public ExitToMainMenuCommand(UserTextUI ui) {
		super(ui);
	}
	
	public void process() {
		return;		// does nothing in here
	}
	
	public String toString() {
		return "Exit to Main Menu";
	}
}