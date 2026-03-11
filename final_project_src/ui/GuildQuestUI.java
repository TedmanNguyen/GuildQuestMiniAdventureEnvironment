package ui;
import java.util.ArrayList;

import commands.AddCharacterCommand;
import commands.Command;
import commands.ListCharactersCommand;
import commands.SelectRealmCommand;

public class GuildQuestUI implements UserInterface {
    private GuildQuest game;
    private ArrayList<Command> commands;
    private CharacterInputHandler characterInputHandler;

    public GuildQuestUI(GuildQuest game) {
        this.game = game;
        characterInputHandler = new CharacterInputHandler(game);
        commands = new ArrayList<>();
        commands.add(new AddCharacterCommand(this));
        commands.add(new SelectRealmCommand(this));
        commands.add(new ListCharactersCommand(this));
    }

    @Override
    public void commandLoop() {
        boolean running = true;
        while (running) {
            printCommands();
            int choice = getChoice();
            if (choice == 0) {
                running = false;
            } else if (choice > 0 && choice <= commands.size()) {
                commands.get(choice - 1).process();
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void printCommands() {
        System.out.println("\n=== GUILDQUEST MAIN MENU ===");
        for (int i = 0; i < commands.size(); i++) {
            System.out.println((i + 1) + ". " + commands.get(i));
        }
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private int getChoice() {
        try {
            return InputManager.getIntInput("");
        } catch (Exception e) {
            return -1;
        }
    }

    public CharacterInputHandler getCharacterInputHandler() {
        return characterInputHandler;
    }

    public GuildQuest getGame() {
        return game;
    }
}
