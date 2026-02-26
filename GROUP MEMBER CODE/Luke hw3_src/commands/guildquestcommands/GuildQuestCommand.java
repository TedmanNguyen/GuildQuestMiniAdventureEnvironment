package hw2.commands.guildquestcommands;

import hw2.commands.Processable;
import hw2.guildquest.GuildQuestTextUI;

public abstract class GuildQuestCommand implements Processable {
	
	GuildQuestTextUI ui;
	
	public GuildQuestCommand(GuildQuestTextUI ui) {
		this.ui = ui;
	}
	
	public abstract void process();
	public abstract String toString();
}
