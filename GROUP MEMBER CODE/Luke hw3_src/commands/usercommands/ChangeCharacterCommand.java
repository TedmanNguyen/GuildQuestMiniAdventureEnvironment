package hw2.commands.usercommands;

import hw2.user.UserTextUI;

public class ChangeCharacterCommand extends UserCommand {
	
	public ChangeCharacterCommand(UserTextUI ui) {
		super(ui);
	}
	
	public void process() {
		ui.getChangeCharacterInput();
	}
	
	public String toString() {
		return "Change Character";
	}
}