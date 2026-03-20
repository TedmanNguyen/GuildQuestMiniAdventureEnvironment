package ui;
import java.util.ArrayList;

import commands.AddCharacterCommand;
import commands.Command;
import commands.ListCharactersCommand;
import commands.SelectRealmCommand;
import commands.ViewActivityLogCommand;

public class GuildQuestUI implements UserInterface {
    private GuildQuest game;
    private ArrayList<Command> commands;
    private CharacterInputHandler characterInputHandler;
    public GuildQuestUI(GuildQuest game) {
        this.game = game;
        characterInputHandler = new CharacterInputHandler(game);
        commands = new ArrayList<>();
        commands.add(new AddCharacterCommand(this));
        commands.add(new ListCharactersCommand(this));
        commands.add(new ViewActivityLogCommand(this));
    }

    @Override
    public void commandLoop() {

        boolean running = true;
        while (running) {

            printCommands();
            int choice = getChoice();
            if (choice == 0) break;
            if (choice > 0 && choice <= commands.size()) {
                commands.get(choice - 1).process();
            } else {
                System.out.println("Invalid choice.");
            }
            int count = game.getCharacterManager().characterAmount();
            if (count > 1){
                SelectionMenu menu = new SelectionMenu(this);
                menu.commandLoop();
            }
        }
        GuildQuest.getWorldClock().end();
    }

    private void printCommands() {
        System.out.println("\n=== GUILDQUEST MAIN MENU ===");
        System.out.println(" \nPlease create 2 Characters to Begin!\n");
        System.out.println("0. Exit");
        for (int i = 0; i < commands.size(); i++) {
            System.out.println((i + 1) + ". " + commands.get(i));
        }
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