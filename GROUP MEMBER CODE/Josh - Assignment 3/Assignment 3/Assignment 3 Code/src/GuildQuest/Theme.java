
package GuildQuest;


// UML says "Not defined by specification" so provided basic implementation that I think would apply.

public class Theme {
    private final String name;

    public Theme(String name) { this.name = name; }

    public String getName() { return name; }

    @Override
    public String toString() { return name; }
}
