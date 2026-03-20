package commands;

import java.util.List;

import models.Characters;
import ui.GuildQuest;
import ui.GuildQuestUI;
import ui.MiniAdventureMenu;

public class LaunchMiniAdventureCommand extends Command {

    public LaunchMiniAdventureCommand(GuildQuestUI ui) {
        super(ui, "Launch a Mini Adventure");
    }

    @Override
    public void process() {
        // Get the current game's characters
        GuildQuest game = ui.getGame();
        List<Characters> players = game.getCharacterManager().getCharacters();

        if (players.isEmpty()) {
            System.out.println("No characters available. Please create characters first.");
            return;
        }

        // Show a simple menu to choose and launch a specific mini-adventure
        MiniAdventureMenu miniMenu = new MiniAdventureMenu(players);
        miniMenu.commandLoop();
    }
}
