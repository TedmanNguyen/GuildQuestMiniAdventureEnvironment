package commands;
import ui.GuildQuestUI;

public class AddCharacterCommand extends Command {
    public AddCharacterCommand(GuildQuestUI ui) {
        super(ui, "Create Character");
    }

    @Override
    public void process() {
        ui.getCharacterInputHandler().getAddCharacterInput();
        System.out.println("Character created successfully!");
    }
}