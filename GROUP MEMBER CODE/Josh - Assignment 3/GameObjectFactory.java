package GuildQuest;

/**
 * === A3 CHANGES: Factory Pattern ===
 * Centralizes creation of core domain objects so Main does not directly call constructors.
 */
public class GameObjectFactory {

    public static Campaign createCampaign(int id, String name) {
        return new Campaign(id, name);
    }

    public static QuestEvent createQuestEvent(String name, int start, int end, Realm realm, User user) {
        return new QuestEvent(name, start, end, realm, user);
    }

    public static Character createCharacter(String name, String classType, int level) {
        return new Character(name, classType, level);
    }

    public static Item createItem(String name, int rarity, String type, String description, int quantity) {
        return new Item(name, rarity, type, description, quantity);
    }
}
