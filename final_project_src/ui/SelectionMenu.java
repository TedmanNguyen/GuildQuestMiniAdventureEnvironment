package ui;

import java.util.ArrayList;
import java.util.Arrays;

import commands.Command;
import commands.EnterRealmCommand;

public class SelectionMenu implements UserInterface {

    private ArrayList<Command> commands;
    private GuildQuestUI ui;
    
    public SelectionMenu(GuildQuestUI ui) {
        this.ui = ui;
        commands = new ArrayList<>(Arrays.asList(new Command[] {
            new EnterRealmCommand(ui)
        }));
    }

    private void printCommands() {
        System.out.println("\n=== SELECT A REALM ===");
        System.out.println("0: Quit");
        for (int i = 0; i < commands.size(); ++i)
            System.out.printf("%d: %s\n", i + 1, commands.get(i));
    }

    public void commandLoop() {
        while (true) {
            printCommands();
            int selection = InputManager.getIntInput("Select choice: ");
            if (selection == 0) break;
            if (selection > 0 && selection <= commands.size()) {
                commands.get(selection - 1).process();
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}
