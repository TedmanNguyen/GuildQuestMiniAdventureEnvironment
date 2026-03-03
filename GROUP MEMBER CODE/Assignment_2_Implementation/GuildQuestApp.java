import java.util.*;

//data layer

class WorldClockTime {
    int days, hours, minutes;

    public WorldClockTime(int days, int hours, int minutes) {
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
    }

    public WorldClockTime addOffset(int hourOffset) {
        int totalHours = this.hours + hourOffset;
        int dayOverflow = 0;
        if (totalHours >= 24) {
            dayOverflow = totalHours / 24;
            totalHours %= 24;
        } else if (totalHours < 0) {
            dayOverflow = (totalHours / 24) - 1;
            totalHours = 24 + (totalHours % 24);
        }
        return new WorldClockTime(this.days + dayOverflow, totalHours, this.minutes);
    }

    @Override
    public String toString() {
        return String.format("Day %d, %02d:%02d", days, hours, minutes);
    }
}

class Realm {
    String name;
    int timeOffset;

    public Realm(String name, int timeOffset) {
        this.name = name;
        this.timeOffset = timeOffset;
    }
}

class Item {
    String name;
    String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

class Character {
    String name;
    String charClass;
    int level;
    List<Item> inventory = new ArrayList<>();

    public Character(String name, String charClass, int level) {
        this.name = name;
        this.charClass = charClass;
        this.level = level;
    }
}

class QuestEvent {
    String title;
    WorldClockTime startTime;
    Realm realm;

    public QuestEvent(String title, WorldClockTime start, Realm realm) {
        this.title = title;
        this.startTime = start;
        this.realm = realm;
    }
}

class Campaign {
    String title;
    boolean isPublic = false;
    List<QuestEvent> events = new ArrayList<>();

    public Campaign(String title) {
        this.title = title;
    }
}

class User {
    String username;
    List<Campaign> campaigns = new ArrayList<>();
    List<Character> characters = new ArrayList<>();
    boolean showBothTimes = false;

    public User(String username) {
        this.username = username;
    }
}

// main

public class GuildQuestApp {
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser;
    private static List<Realm> worldRealms = new ArrayList<>();

    public static void main(String[] args) {
        setupWorld();
        System.out.println("--- Welcome to GuildQuest ---");
        System.out.print("Enter username: ");
        currentUser = new User(scanner.nextLine());

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": createCampaign(); break;
                case "2": addEventToCampaign(); break;
                case "3": viewCampaigns(); break;
                case "4": createCharacter(); break;
                case "5": toggleTimeSetting(); break;
                case "6":
                    System.out.println("Exiting GuildQuest. Adventure awaits!");
                    running = false;
                    break;
                default: System.out.println("Invalid choice. Please pick 1-6.");
            }
        }
    }

    private static void setupWorld() {
        worldRealms.add(new Realm("Super Mario", 0));
        worldRealms.add(new Realm("Sonic", 9));
        worldRealms.add(new Realm("Power Rangers", -5));
    }

    private static void printMenu() {
        System.out.println("\n--- Main Menu (User: " + currentUser.username + ") ---");
        System.out.println("1. Create Campaign");
        System.out.println("2. Add Quest Event");
        System.out.println("3. View Campaigns & Timelines");
        System.out.println("4. Create Character");
        System.out.println("5. Toggle Time Setting (World vs Both)");
        System.out.println("6. Exit");
        System.out.print("Choice: ");
    }

    private static void createCampaign() {
        System.out.print("Campaign Title: ");
        String title = scanner.nextLine();
        if (title.trim().isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }
        currentUser.campaigns.add(new Campaign(title));
        System.out.println("Campaign '" + title + "' created!");
    }

    private static void addEventToCampaign() {
        if (currentUser.campaigns.isEmpty()) {
            System.out.println("Error: Create a campaign first!");
            return;
        }

        try {
            System.out.println("Select Campaign Index:");
            for (int i = 0; i < currentUser.campaigns.size(); i++) {
                System.out.println(i + ". " + currentUser.campaigns.get(i).title);
            }
            int campIdx = Integer.parseInt(scanner.nextLine());

            if (campIdx < 0 || campIdx >= currentUser.campaigns.size()) {
                System.out.println("Invalid campaign selection.");
                return;
            }

            System.out.print("Event Title: ");
            String title = scanner.nextLine();

            System.out.print("Start Day (Number): ");
            int d = Integer.parseInt(scanner.nextLine());

            System.out.print("Start Hour (0-23): ");
            int h = Integer.parseInt(scanner.nextLine());

            System.out.println("Select Realm Index:");
            for (int i = 0; i < worldRealms.size(); i++) {
                System.out.println(i + ". " + worldRealms.get(i).name + " (UTC " + (worldRealms.get(i).timeOffset >= 0 ? "+" : "") + worldRealms.get(i).timeOffset + ")");
            }
            int realmIdx = Integer.parseInt(scanner.nextLine());

            if (realmIdx < 0 || realmIdx >= worldRealms.size()) {
                System.out.println("Invalid realm selection.");
                return;
            }

            QuestEvent event = new QuestEvent(title, new WorldClockTime(d, h, 0), worldRealms.get(realmIdx));
            currentUser.campaigns.get(campIdx).events.add(event);
            System.out.println("Event '" + title + "' successfully added to " + currentUser.campaigns.get(campIdx).title + "!");

        } catch (NumberFormatException e) {
            System.out.println("Input Error: You must enter a numeric value for IDs and times.");
        }
    }

    private static void viewCampaigns() {
        if (currentUser.campaigns.isEmpty()) {
            System.out.println("No campaigns found.");
            return;
        }

        for (Campaign c : currentUser.campaigns) {
            System.out.println("\n[CAMPAIGN: " + c.title.toUpperCase() + "]");
            if (c.events.isEmpty()) {
                System.out.println("  (No quest events scheduled)");
            } else {
                for (QuestEvent e : c.events) {
                    System.out.print("  - " + e.title + " | World: " + e.startTime);
                    if (currentUser.showBothTimes) {
                        System.out.print(" | Local (" + e.realm.name + "): " + e.startTime.addOffset(e.realm.timeOffset));
                    }
                    System.out.println();
                }
            }
        }
    }

    private static void createCharacter() {
        System.out.print("Character Name: ");
        String name = scanner.nextLine();
        System.out.print("Class (e.g., Mage, Warrior): ");
        String cls = scanner.nextLine();

        currentUser.characters.add(new Character(name, cls, 1));
        System.out.println("Character " + name + " (Level 1 " + cls + ") has entered the world!");
    }

    private static void toggleTimeSetting() {
        currentUser.showBothTimes = !currentUser.showBothTimes;
        System.out.println("Time Display Mode: " + (currentUser.showBothTimes ? "WORLD & LOCAL" : "WORLD ONLY"));
    }
}