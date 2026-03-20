package commands;

import ui.GuildQuestUI;

public class LaunchMiniAdventureCommand extends Command {

    public LaunchMiniAdventureCommand(GuildQuestUI ui) {
        super(ui, "Launch a Mini Adventure");
    }

    public void process() {
        MiniAdventure.printAdventures();
        System.out.println("Not yet implemented");
    }
}
