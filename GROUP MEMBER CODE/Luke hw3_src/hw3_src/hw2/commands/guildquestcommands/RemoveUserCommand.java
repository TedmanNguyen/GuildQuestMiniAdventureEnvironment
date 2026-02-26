package hw2.commands.guildquestcommands;

import hw2.guildquest.GuildQuestTextUI;

public class RemoveUserCommand extends GuildQuestCommand {
	
	public RemoveUserCommand(GuildQuestTextUI ui) {
		super(ui);
	}
	
	public void process() {
		ui.getRemoveUserInput();
	}
	
	public String toString() {
		return "Remove User";
	}
}
