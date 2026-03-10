package commands;
import ui.GuildQuestUI;

public class SelectRealmCommand extends Command {
    public SelectRealmCommand(GuildQuestUI ui) {
        super(ui, "Select a Realm");
    }

    @Override
    public void process() {
        System.out.println("\n--- Realm Selection ---");
        
        System.out.println("Realm class is missing.");
    }
}
