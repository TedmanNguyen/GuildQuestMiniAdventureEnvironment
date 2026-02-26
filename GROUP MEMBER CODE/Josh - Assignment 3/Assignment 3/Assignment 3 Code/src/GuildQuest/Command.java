package GuildQuest;

/**
 * === A3 CHANGES: Command Pattern ===
 * Represents one executable menu action.
 */
@FunctionalInterface
public interface Command {
    void execute();
}
