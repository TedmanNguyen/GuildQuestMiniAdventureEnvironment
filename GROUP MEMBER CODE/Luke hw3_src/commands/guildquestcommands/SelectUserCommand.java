package hw2.commands.guildquestcommands;

import hw2.guildquest.GuildQuestTextUI;

public class SelectUserCommand extends GuildQuestCommand {
	
	public SelectUserCommand(GuildQuestTextUI ui) {
		super(ui);
	}
	
	public void process() {
		ui.getSelectUserInput();
	}
	
	public String toString() {
		return "Select User";
	}
}
