package GuildQuest;

import java.util.ArrayList;
import java.util.List;

// === A3 CHANGES START: AI-Assisted Design Pattern (Observer Pattern) ===
// User is an Observer: it receives notifications through NotificationListener.
// This keeps notification delivery decoupled from CampaignManager logic.
public class User implements NotificationListener {
// === A3 CHANGES END: AI-Assisted Design Pattern (Observer Pattern) ===

    public String name;
    public boolean showGlobalTime;
    public boolean showLocalTime;
    public Theme theme;
    public Realm location;
    public Character currentCharacter;
    public List<Character> characters = new ArrayList<>();

    // Future change in original UML
    public int uid;
    public String sessionToken;

    public User(String name, int uid) {
        this.name = name;
        this.uid = uid;
        this.showGlobalTime = true;
        this.showLocalTime = false;
        this.theme = new Theme("classic");
        this.location = null;
    }

    public void createCharacter(Character character) {
        characters.add(character);
        if (currentCharacter == null) currentCharacter = character;
    }

    public void deleteCharacter(int index) {
        if (index < 0 || index >= characters.size()) return;
        Character removed = characters.remove(index);
        if (removed == currentCharacter) currentCharacter = characters.isEmpty() ? null : characters.get(0);
    }

    // Future change in original UML
    public int travelTo(Realm realm) {
        this.location = realm;
        return (realm == null) ? -1 : realm.id;
    }

    // Future change in original UML
    public void login(String passwordHash, String token) {
        // token provided by AuthenticationService
        this.sessionToken = token;
    }

    public void logout() {
        this.sessionToken = null;
    }

    // Future change in original UML
    @Override // === A3 CHANGES: AI-Assisted Design Pattern (Observer Pattern) ===
    public void notify(String message) {
        System.out.println("[Notify " + name + "]: " + message);
    }

    @Override
    public String toString() { return name + " (uid=" + uid + ")"; }
}