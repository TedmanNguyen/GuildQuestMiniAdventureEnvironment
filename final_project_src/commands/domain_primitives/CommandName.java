package commands.primitive_domains;

/**
 * Primitive domain class that validates the names for commands
 */

public class CommandName {
    String name;

    public CommandName(String name) {
        if (name.isBlank())
            throw new IllegalArgumentException("Commands must have a non-blank name.");
        this.name = name;
    }

    public String toString() {return name; }
}
