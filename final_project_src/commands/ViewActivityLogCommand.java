package commands;

import java.util.List;

import models.ActivityLog;
import models.Characters;
import ui.GuildQuest;
import ui.GuildQuestUI;

/**
 * Command to display the activity log for all current characters.
 */
public class ViewActivityLogCommand extends Command {

    public ViewActivityLogCommand(GuildQuestUI ui) {
        super(ui, "View Activity Log");
    }

    @Override
    public void process() {
        GuildQuest game = ui.getGame();
        List<Characters> players = game.getCharacterManager().getCharacters();
        ActivityLog.printLog(players);
    }
}

