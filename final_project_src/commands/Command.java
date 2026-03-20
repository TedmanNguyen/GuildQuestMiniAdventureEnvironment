package commands;
import ui.GuildQuestUI;
import commands.primitive_domains.CommandName;

/**
 * This class is used for determining the action that is taken whenever a user selects an option
 * on a menu. Concrete classes are specific commands that appear on menus.
 */

public abstract class Command implements Processable {
    protected GuildQuestUI ui;
    private CommandName name;

    public Command(GuildQuestUI ui, String name) {
        this.ui = ui;
        this.name = new CommandName(name);
    }

    public String toString() {
        return name.toString();
    }

    public abstract void process();
}
