package hw2.commands.usercommands;

import hw2.user.UserTextUI;

public class EnterRealmCommand extends UserCommand {
	
	public EnterRealmCommand(UserTextUI ui) {
		super(ui);
	}
	
	public void process() {
		ui.getEnterRealmInput();
	}
	
	public String toString() {
		return "Enter Realm";
	}
}