package hw2;

import hw2.guildquest.GuildQuest;

public class Main {
	public static void main(String[] args) {
		GuildQuest game = GuildQuest.getInstance();
		GuildQuest.worldClock.start();
		game.gameLoop();
	}
}
