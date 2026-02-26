package hw2.guildquest;

import java.util.ArrayList;

import java.util.Scanner;

import hw2.WorldClock;
import hw2.ui.UserInterface;

public class GuildQuest {
	private static GuildQuest instance = null;
	public static final WorldClock worldClock = new WorldClock();
	UserManager userManager;
	UserInterface ui;
	
	private GuildQuest() {
		userManager = UserManager.getInstance();
		ui = createUI();
	}
	
	protected UserInterface createUI() {
		return new GuildQuestTextUI(this);
	}
	
	public static GuildQuest getInstance() {
		if (instance == null)
			instance = new GuildQuest();
		return instance;
	}
	
	void addUser(String username, String email) {
		userManager.createUser(username, email);
	}
	void removeUser(int i) {
		userManager.removeUser(i);
	}
	
	void selectUser(int i) {
		userManager.selectUser(i);
	}
	
	public void gameLoop() {
		ui.commandLoop();
	}
	
	public GuildQuestTextUI getUI() {
		return (GuildQuestTextUI)ui;
	}
}