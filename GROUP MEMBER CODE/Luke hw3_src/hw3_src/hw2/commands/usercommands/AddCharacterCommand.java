package hw2.commands.usercommands;

import hw2.user.UserTextUI;

public class AddCharacterCommand extends UserCommand {
	
	public AddCharacterCommand(UserTextUI ui) {
		super(ui);
	}
	
	public void process() {
		ui.getAddCharacterInput();
	}
	
	public String toString() {
		return "Add Character";
	}
}