package commands;
import ui.GuildQuestUI;

public abstract class Command implements Processable {
    protected GuildQuestUI ui;
    private String name;

    public Command(GuildQuestUI ui, String name) {
        this.ui = ui;
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public abstract void process();
}
