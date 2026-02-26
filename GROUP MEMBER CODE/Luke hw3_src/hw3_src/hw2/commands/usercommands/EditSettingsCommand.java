package hw2.commands.usercommands;

import hw2.user.UserTextUI;

public class EditSettingsCommand extends UserCommand {
	
	public EditSettingsCommand(UserTextUI ui) {
		super(ui);
	}
	
	public void process() {
		ui.getEditSettingsInput();
	}
	
	public String toString() {
		return "Edit Settings";
	}
}