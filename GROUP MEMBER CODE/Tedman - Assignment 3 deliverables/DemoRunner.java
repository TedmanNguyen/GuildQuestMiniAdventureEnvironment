public class DemoRunner {
    
    public static void runDemo() {
        System.out.println("=== GuildQuest Demo ===\n");
        
        GuildQuest guildQuest = GuildQuest.getInstance();
        
        // USER 1: Fantasy DM with full campaign
        System.out.println("--- User 1: Fantasy Campaign ---");
        User sarah = new User("sarah_dm");
        guildQuest.addUser(sarah);
        
        Campaign fantasy = new Campaign(sarah, "Dragon Quest");
        sarah.addCampaign(fantasy);
        
        Character warrior = new Character(sarah, "Thorin", "Warrior", 10);
        Character wizard = new Character(sarah, "Elara", "Wizard", 8);
        sarah.addCharacter(warrior);
        sarah.addCharacter(wizard);
        
        Realm mountain = new Realm(sarah, "Dragon Peak");
        sarah.addRealm(mountain);
        
        QuestEvent quest1 = new QuestEvent(fantasy, "Slay the Dragon");
        quest1.addRealm(mountain);
        quest1.addParticipant(warrior);
        quest1.addParticipant(wizard);
        fantasy.addQuestEvent(quest1);
        
        displayUser(sarah);
        
        // USER 2: Sci-Fi GM
        System.out.println("\n--- User 2: Sci-Fi Campaign ---");
        User alex = new User("alex_gm");
        guildQuest.addUser(alex);
        
        Campaign scifi = new Campaign(alex, "Starship Odyssey");
        alex.addCampaign(scifi);
        
        Character captain = new Character(alex, "Nova", "Captain", 12);
        alex.addCharacter(captain);
        
        Realm station = new Realm(alex, "Space Station");
        alex.addRealm(station);
        
        QuestEvent quest2 = new QuestEvent(scifi, "Rescue Mission");
        quest2.addRealm(station);
        quest2.addParticipant(captain);
        scifi.addQuestEvent(quest2);
        
        displayUser(alex);
        
        // USER 3: Multi-campaign player
        System.out.println("\n--- User 3: Multiple Campaigns ---");
        User mike = new User("mike_player");
        guildQuest.addUser(mike);
        
        Campaign camp1 = new Campaign(mike, "Weekend Warriors");
        Campaign camp2 = new Campaign(mike, "Mystery Manor");
        mike.addCampaign(camp1);
        mike.addCampaign(camp2);
        
        Character fighter = new Character(mike, "Brutus", "Fighter", 5);
        Character detective = new Character(mike, "Holmes", "Detective", 6);
        mike.addCharacter(fighter);
        mike.addCharacter(detective);
        
        displayUser(mike);
        
        // System Statistics
        System.out.println("\n=== System Statistics ===");
        System.out.println("Total Users: " + guildQuest.getAllUsers().size());
        System.out.println("Users: sarah_dm, alex_gm, mike_player");
        
        // Demonstrate lookups
        System.out.println("\n=== Lookups ===");
        guildQuest.findUserByUsername("sarah_dm")
            .ifPresent(u -> System.out.println("Found user: " + u.getUsername()));
        
        fantasy.checkEventExists("Slay the Dragon");
        
        // Demonstrate error handling
        System.out.println("\n=== Error Handling ===");
        try {
            User duplicate = new User("sarah_dm");
            guildQuest.addUser(duplicate);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Prevented duplicate: " + e.getMessage());
        }
    }
    
    private static void displayUser(User user) {
        System.out.println("\nUser: " + user.getUsername());
        System.out.print("  Campaigns: ");
        user.viewCampaigns();
        System.out.print("  Characters: ");
        user.viewCharacters();
        System.out.print("  Realms: ");
        user.viewRealm();
    }
}