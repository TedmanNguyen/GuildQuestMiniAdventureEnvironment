package hw2.guildquest;

import java.util.ArrayList;
import hw2.commands.guildquestcommands.*;
import hw2.ui.*;

public class GuildQuestTextUI implements UserInterface {
	private GuildQuest guildQuest;
	private ArrayList<GuildQuestCommand> commands;
	
	public GuildQuestTextUI(GuildQuest g) {
		guildQuest = g;
		commands = new ArrayList<GuildQuestCommand>();
		commands.add(new SelectUserCommand(this));
		commands.add(new AddUserCommand(this));
		commands.add(new RemoveUserCommand(this));
	}
	
	public void commandLoop() {
		while (true) {
			printCommands();
			GuildQuestCommand command = getCommandChoice();
			command.process();
			
			if (guildQuest.userManager.currentUser != null)
				guildQuest.userManager.currentUser.userLoop();
		}
	}
	
	private void printCommands() {
		System.out.println("MAIN MENU - WorldClock { " + GuildQuest.worldClock + " }");
		System.out.println("--------------------");
		for (int i = 0; i < commands.size(); ++i)
			System.out.printf("%d: %s\n", i, commands.get(i));
	}
	
	public void getAddUserInput() {	
		String username = InputManager.getStringInput("Enter a username (no spaces in the name): ", InputManager.stringNoWhiteSpace);
		String email = InputManager.getStringInput("Enter a valid email: ", InputManager.stringNoWhiteSpace);
		guildQuest.addUser(username, email);
	}
	
	public void getRemoveUserInput() {
		if (guildQuest.userManager.isEmpty())
			return;
		
		printUsers();
		int i = InputManager.getIndexInput("Enter the users number: ", 0, guildQuest.userManager.numUsers());
		guildQuest.removeUser(i);
	}
	
	public void getSelectUserInput() {
		if (guildQuest.userManager.isEmpty())
			return;
		
		printUsers();
		int i = InputManager.getIndexInput("Enter the users number: ", 0, guildQuest.userManager.numUsers());
		guildQuest.selectUser(i);
	}
	
	private void printUsers() {
		System.out.println("USERS");
		System.out.println("--------------------");
		for (int i = 0; i < guildQuest.userManager.numUsers(); ++i)
			System.out.println(i + ": " + guildQuest.userManager.getUser(i));
		System.out.println();
	}
	
	private GuildQuestCommand getCommandChoice() {
		int commandNumber = InputManager.getIndexInput("Enter the number of the command you wish to execute: ",
														0, 3);
		return commands.get(commandNumber);
	}
}
