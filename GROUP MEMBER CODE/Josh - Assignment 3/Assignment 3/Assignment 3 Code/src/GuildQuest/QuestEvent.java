
package GuildQuest;

public class QuestEvent {
    public String name;
    public int startTime;
    public int endTime;
    public Realm realm;
    public User user;
    public CharacterLog characterLog;
    public Character[] characters;

    public QuestEvent(String name, int startTime, int endTime, Realm realm, User owner) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.realm = realm;
        this.user = owner;
        this.characterLog = new CharacterLog();
        this.characters = new Character[0];
    }

    public Item grantRewardItems(Item item) {

        for (var entry : characterLog.getParticipation().entrySet()) {
            for (Character c : entry.getValue()) {
                c.addItem(new Item(item.name, item.rarity, item.type, item.description, item.quantity));
            }
        }
        return item;
    }

    public void recordParticipation(User user, Character character) {
        characterLog.record(user, character);
    }

    @Override
    public String toString() {
        return "QuestEvent{" + name + " @ " + (realm==null?"?":realm.name) + ", start=" + startTime + "}";
    }
}
