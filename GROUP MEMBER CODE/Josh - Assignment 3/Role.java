package GuildQuest;

// === A3 CHANGES START: Refactoring #2 (Replace Primitive/String with Enum) ===
public enum Role {
    OWNER("owner"),
    COLLABORATOR("collaborator"),
    MEMBER("member"),
    NONE("none");

    private final String label;

    Role(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        // Keeps your printed output identical to before (owner/none/etc.)
        return label;
    }
}
// === A3 CHANGES END: Refactoring #2 (Replace Primitive/String with Enum) ===
