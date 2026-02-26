package hw2.user;

import java.util.ArrayList;
import hw2.commands.usercommands.*;
import hw2.guildquest.GuildQuest;
import hw2.ui.*;

public class UserTextUI implements UserInterface {
	private User user;
	private ArrayList<UserCommand> commands;
	private final CampaignInputHandler campaignHandler;
    private final CharacterInputHandler characterHandler;
    private final RealmInputHandler realmHandler;
	
	public UserTextUI(User user) {
		this.user = user;
		campaignHandler = new CampaignInputHandler(user);
		characterHandler = new CharacterInputHandler(user);
		realmHandler = new RealmInputHandler(user);
		commands = new ArrayList<UserCommand>();
		commands.add(new AddCampaignCommand(this));
		commands.add(new EditCampaignCommand(this));
		commands.add(new EnterRealmCommand(this));
		commands.add(new AddCharacterCommand(this));
		commands.add(new ChangeCharacterCommand(this));
		commands.add(new EditSettingsCommand(this));
		commands.add(new ExitToMainMenuCommand(this));
	}
	
	public void commandLoop() {
		boolean inUserMode = true;
		while (inUserMode) {
			printCommands();
			UserCommand command = getUserCommand();
			
			if (command instanceof ExitToMainMenuCommand)
				inUserMode = false;
			command.process();
		}
	}
	
	private void printCommands() {
		System.out.println(user.getUsername().toUpperCase() + " - WorldClock { " +
				GuildQuest.worldClock + " } - " + user.getSettings().getCurrentRealm().getName() +
				" { " + user.getSettings().getCurrentRealm().getLocalTime() + " }");
		System.out.println("--------------------");
		for (int i = 0; i < commands.size(); ++i)
			System.out.printf("%d: %s\n", i, commands.get(i));
	}
	
	private UserCommand getUserCommand() {
		int commandNumber = InputManager.getIndexInput("Enter the number of the corresponding command you wish to execute: ",
				0, commands.size());
		return commands.get(commandNumber);
	}
	
	public void getAddCampaignInput() {		
		campaignHandler.getAddCampaignInput();
	}
	
	public void getEditCampaignInput() {
		campaignHandler.getEditCampaignInput();
	}
	
	public void getEnterRealmInput() {		
		realmHandler.getEnterRealmInput();
	}
	
	public void getAddCharacterInput() {
		characterHandler.getAddCharacterInput();
	}
	
	public void getChangeCharacterInput() {
		characterHandler.getChangeCharacterInput();
	}
	
	public void getEditSettingsInput() {
		System.out.println("Sorry, there is currently no support for editing settings.");
	}
}
