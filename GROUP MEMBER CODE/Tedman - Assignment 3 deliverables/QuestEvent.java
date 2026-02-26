import java.util.Objects;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

public class QuestEvent implements UniqueID {
    private final String questID;
    private String title;
    private Campaign owner;
    private Realm realm;
    private List<Character> participants = new ArrayList<>();  

    public QuestEvent(Campaign campaign) {
        this.owner = campaign;
        this.questID = "quest-" + UUID.randomUUID().toString().substring(0, 8);
    }
    
    public QuestEvent(Campaign campaign, String title) {
        this(campaign);
        this.title = title;
    }

    @Override
    public String generateID() {
        return questID;
    }
    public String getQuestID() {
        return questID;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Campaign getOwner() {
        return owner;
    }
    public void setOwner(Campaign campaign) {
        this.owner = campaign;
    }

    public void addParticipant(Character character) {
        if (!participants.contains(character)) {
            participants.add(character);
        }
    }
    public void removeParticipant(String characterName) {
        participants.removeIf(character -> character.getName().equals(characterName));  // Fixed: use .equals()
    }
    
    public List<Character> getParticipants() {
        return new ArrayList<>(participants);
    }

    public void addRealm(Realm realm) {
        this.realm = realm;
    }
    public Realm getRealm() {
        return realm;
    }
    
    public void viewParticipants() {
        if (participants.isEmpty()) {
            System.out.println("No participants in this quest event");
        } else {
            System.out.println("Participants:");
            for (Character character : participants) {
                System.out.println("  - " + character.getName() + " (" + character.getRole() + ")");
            }
        }
    }

    // Implement equals and hashCode based on questID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestEvent)) return false;
        QuestEvent that = (QuestEvent) o;
        return questID.equals(that.questID);
    }
    @Override
    public int hashCode() {
        return Objects.hash(questID);
    }
    @Override
    public String toString() {
        return "QuestEvent{questID='" + questID + "', title='" + title + "'}";
    }
}