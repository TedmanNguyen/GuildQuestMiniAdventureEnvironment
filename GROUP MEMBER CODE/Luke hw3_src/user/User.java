package hw2.user;

import java.util.ArrayList;

import hw2.Campaign;
import hw2.PermissionLevel;
import hw2.Realm;
import hw2.Settings;
import hw2.VisabilityStatus;
import hw2.commands.UserCommand;
import hw2.guildquest.GuildQuest;
import hw2.ui.UserInterface;

import java.util.Scanner;

public abstract class User {
	private static int userCount = 0;
	private int userId;	// changed from design (String -> int)
	private String username;
	private String email;
	// Every instance variable after this is new
	CampaignManager campaignManager;
	CharacterManager characterManager;
	private Settings userSettings;
	UserInterface ui;
	
	public User(String username, String email) {
		this.username = username;
		this.email = email;
		userId = userCount++;
		campaignManager = new CampaignManager();
		characterManager = new CharacterManager();
		userSettings = new Settings();
		ui = createUI();
	}
	
	protected abstract UserInterface createUI();
	
	public void createCampaign(String name, VisabilityStatus visability) {
		campaignManager.createCampaign(name, visability);
	}
	
	public void removeCampaign(Integer id) {
		campaignManager.removeCampaign(id);
	}
	
	public void shareCampaign(Campaign c, User u, PermissionLevel p) {
		// will likely need to change the design to support this
		return;
	}
	
	public void createCharacter(String name, String heroClass) {
		characterManager.createCharacter(name, heroClass);
	}
	
	public void changeCharacter(int index) {
		characterManager.changeCharacter(index);
	}
	
	// Every method after this is new (not in original design)
//	public void addCharacter(String )
	public String toString() {
		return "#" + userId + ": " + username + " - " + email;
	}
	
	void enterRealm(Realm r) {
		userSettings.setCurrentRealm(r);
	}
	
	// getters and setters
	public String getUsername() { return username; }
	public void setUsername(String newUsername) { username = newUsername; }
	public String getEmail() { return email; }
	public void setEmail(String newEmail) { email = newEmail; }
	public Settings getSettings() { return userSettings; }
	
	public void userLoop() {
		ui.commandLoop();
	}
}