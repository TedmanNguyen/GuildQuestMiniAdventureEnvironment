import java.util.Objects;
import java.util.UUID;

public class Character {
    private final String charID;
    private User owner;
    private String name;
    private String role;
    private int level;

    public Character(User owner, String name, String role, int level) {
        this.owner = owner;
        this.charID = "char-" + UUID.randomUUID().toString().substring(0, 8);
        this.name = name;
        this.role = role;
        this.level = level;
    }
    
    public String getCharID() {
        return charID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public String getInformation() {
        return name + " is a " + role + " and is Level: " + level;
    }

    public void setOwner(User user) {
        owner = user;
    }
    public User getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Character)) return false;
        Character character = (Character) o;
        return charID.equals(character.charID);
    }
    @Override
    public int hashCode() {
        return Objects.hash(charID);
    }
    @Override
    public String toString() {
        return "Character{charID='" + charID + "', name='" + name + "', role='" + role + "', level=" + level + "}";
    }
}