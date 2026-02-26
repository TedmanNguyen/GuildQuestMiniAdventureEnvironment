package hw2.commands.guildquestcommands;

import hw2.guildquest.GuildQuestTextUI;

public class AddUserCommand extends GuildQuestCommand {
	
	public AddUserCommand(GuildQuestTextUI ui) {
		super(ui);
	}
	
	public void process() {
		ui.getAddUserInput();
	}
	
	public String toString() {
		return "Add User";
	}
}
