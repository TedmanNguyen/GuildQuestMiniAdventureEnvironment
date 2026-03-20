package commands;

import java.util.List;
import models.Characters;
import models.Realm;
import ui.GuildQuestUI;
import ui.InputManager;

public class EnterRealmCommand extends Command {

    public EnterRealmCommand(GuildQuestUI ui) {
        super(ui, "Enter a Realm");
    }

    @Override
    public void process() {
        Realm.printRealms();
        int selection = InputManager.getIntInput("Select a Realm ID to enter: ");
        
        if (selection >= 0 && selection < Realm.realms.size()) {
            Realm selected = Realm.realms.get(selection);
            List<Characters> players = ui.getGame().getCharacterManager().getCharacters();
            selected.enter(players);
        } else {
            System.out.println("Invalid Realm ID.");
        }
    }
}
