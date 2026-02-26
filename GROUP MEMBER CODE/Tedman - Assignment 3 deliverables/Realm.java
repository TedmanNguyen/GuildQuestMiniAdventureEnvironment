import java.util.Objects;
import java.util.UUID;

public class Realm {
    private final String realmID;
    private User owner;
    private String name;
    private String description;

    public Realm(User owner) {
        this.owner = owner;
        this.realmID = "realm-" + UUID.randomUUID().toString().substring(0, 8);
        this.name = "Unknown Realm";
    }

    public Realm(User owner, String name) {
        this.owner = owner;
        this.realmID = "realm-" + UUID.randomUUID().toString().substring(0, 8);
        this.name = name;
    }
    
    public String getRealmID() {
        return realmID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setOwner(User user) {
        this.owner = user;
    }
    public User getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Realm)) return false;
        Realm realm = (Realm) o;
        return realmID.equals(realm.realmID);
    }
    @Override
    public int hashCode() {
        return Objects.hash(realmID);
    }
    @Override
    public String toString() {
        return "Realm{realmID='" + realmID + "', name='" + name + "'}";
    }
}