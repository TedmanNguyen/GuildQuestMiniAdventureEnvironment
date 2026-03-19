package ui;

import java.util.ArrayList;
import java.util.Arrays;

import commands.Command;
import commands.EnterRealmCommand;
import commands.LaunchMiniAdventureCommand;

public class SelectionMenu implements UserInterface {

    private ArrayList<Command> commands;
    
    public SelectionMenu() {
        commands = new ArrayList<>(Arrays.asList(new Command[] {
            new EnterRealmCommand(null),
            new LaunchMiniAdventureCommand(null)
        }));
    }

    private void printCommands() {
        System.out.println("=== SELECT A REALM OR MINI ADVENTURE ===");
        for (int i = 0; i < commands.size(); ++i)
            System.out.printf("%d: %s\n", i, commands.get(i));
        // System.out.print("Select choice: ");
    }

    public void commandLoop() {
        printCommands();
        int selection;
        do {
            selection = InputManager.getIntInput("Select choice: ");
        } while (selection < 0 || selection >= commands.size());
        commands.get(selection).process();
    }
}
