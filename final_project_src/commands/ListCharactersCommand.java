package commands;
import ui.GuildQuestUI;

public class ListCharactersCommand extends Command {
    public ListCharactersCommand(GuildQuestUI ui) {
        super(ui, "List Characters");
    }

    @Override
    public void process() {
        ui.getGame().getCharacterManager().printCharacters();
    }
}
