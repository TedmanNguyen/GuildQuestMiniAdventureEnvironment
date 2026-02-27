import java.util.*;

// --- PATTERN 3 (AI): OBSERVER PATTERN ---
interface TimeObserver {
    void onDayAdvanced(int newDay);
}

// --- pattern 1: singleton World Clock (Updated with Observer) ---
class WorldClock {
    private static WorldClock instance;
    private int days = 1;
    private List<TimeObserver> observers = new ArrayList<>();

    private WorldClock() {}

    public static WorldClock getInstance() {
        if (instance == null) instance = new WorldClock();
        return instance;
    }

    public void addObserver(TimeObserver o) { observers.add(o); }

    public int getDays() { return days; }

    public void advanceDay() {
        days++;
        // Notify all observers that time has passed
        for (TimeObserver o : observers) {
            o.onDayAdvanced(days);
        }
    }
}

// refactor 3 (AI suggested): replace primitive
// motivation: fix "Primitive Obsession" by encapsulating day/hour logic.
class EventTime {
    private final int day;
    private final int hour;

    public EventTime(int day, int hour) {
        if (hour < 0 || hour > 23) throw new IllegalArgumentException("Invalid hour");
        this.day = day;
        this.hour = hour;
    }

    public WorldClockTime toWorldClockTime() {
        return new WorldClockTime(day, hour, 0);
    }
}

// refactor 2: extract class
class SettingsManager {
    public boolean showBothTimes = false;
}

// --- DATA LAYER ---

class WorldClockTime {
    int days, hours, minutes;
    public WorldClockTime(int days, int hours, int minutes) {
        this.days = days; this.hours = hours; this.minutes = minutes;
    }

    public WorldClockTime addOffset(int hourOffset) {
        int totalHours = this.hours + hourOffset;
        int dayOverflow = totalHours / 24;
        int finalHour = totalHours % 24;
        if (finalHour < 0) { finalHour += 24; dayOverflow--; }
        return new WorldClockTime(this.days + dayOverflow, finalHour, this.minutes);
    }

    @Override
    public String toString() { return String.format("Day %d, %02d:%02d", days, hours, minutes); }
}

class Realm {
    String name;
    int timeOffset;
    public Realm(String name, int timeOffset) { this.name = name; this.timeOffset = timeOffset; }

    // refactor 1: move method
    public String getFormattedLocalTime(WorldClockTime worldTime) {
        return worldTime.addOffset(this.timeOffset).toString();
    }
}

// CAMPAIGN implements TimeObserver to react to clock changes
class Campaign implements TimeObserver {
    String title;
    List<QuestEvent> events = new ArrayList<>();
    public Campaign(String title) { this.title = title; }

    @Override
    public void onDayAdvanced(int newDay) {
        // Example reaction: Check if events are today
        System.out.println("[System Update] Campaign '" + title + "' is checking schedule for Day " + newDay);
    }
}

class QuestEvent {
    String title;
    WorldClockTime startTime;
    Realm realm;

    public QuestEvent(String title, WorldClockTime start, Realm realm) {
        this.title = title; this.startTime = start; this.realm = realm;
    }
}

// pattern 2: factory method (updated with EventTime object)
class EventFactory {
    public static QuestEvent createEvent(String title, EventTime time, Realm realm) {
        return new QuestEvent(title, time.toWorldClockTime(), realm);
    }
}

class User {
    String username;
    List<Campaign> campaigns = new ArrayList<>();
    SettingsManager settings = new SettingsManager();
    public User(String username) { this.username = username; }
}

// --- MAIN APPLICATION ---

public class GuildQuestApp {
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser;
    private static List<Realm> worldRealms = new ArrayList<>();

    public static void main(String[] args) {
        setupWorld();
        System.out.print("Enter username: ");
        currentUser = new User(scanner.nextLine());

        while (true) {
            printMenu();
            String choice = scanner.nextLine();
            if (choice.equals("1")) createCampaign();
            else if (choice.equals("2")) addEventToCampaign();
            else if (choice.equals("3")) viewCampaigns();
            else if (choice.equals("4")) { //  option to test observer
                WorldClock.getInstance().advanceDay();
            }
            else if (choice.equals("5")) toggleTimeSetting();
            else if (choice.equals("6")) break;
        }
    }

    private static void setupWorld() {
        worldRealms.add(new Realm("Super Mario", 0));
        worldRealms.add(new Realm("Sonic", 9));
        worldRealms.add(new Realm("Power Rangers", -5));
    }

    private static void printMenu() {
        System.out.println("\n--- Main Menu (Day: " + WorldClock.getInstance().getDays() + ") ---");
        System.out.println("1. Create Campaign | 2. Add Event | 3. View Timelines | 4. Advance Day (Test Observer) | 5. Toggle Time | 6. Exit");
        System.out.print("Choice: ");
    }

    private static void createCampaign() {
        System.out.print("Campaign Title: ");
        Campaign newCamp = new Campaign(scanner.nextLine());
        // Register the new campaign as an observer of the clock
        WorldClock.getInstance().addObserver(newCamp);
        currentUser.campaigns.add(newCamp);
    }

    private static void addEventToCampaign() {
        if (currentUser.campaigns.isEmpty()) return;
        try {
            System.out.print("Event Title: "); String title = scanner.nextLine();
            System.out.print("Day: "); int d = Integer.parseInt(scanner.nextLine());
            System.out.print("Hour (0-23): "); int h = Integer.parseInt(scanner.nextLine());

            // using the refactored EventTime object
            EventTime time = new EventTime(d, h);
            QuestEvent event = EventFactory.createEvent(title, time, worldRealms.get(0));
            currentUser.campaigns.get(0).events.add(event);
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    private static void viewCampaigns() {
        for (Campaign c : currentUser.campaigns) {
            System.out.println("\n[CAMPAIGN: " + c.title + "]");
            for (QuestEvent e : c.events) {
                System.out.println("  - " + e.title + " | World: " + e.startTime);
            }
        }
    }

    private static void toggleTimeSetting() {
        currentUser.settings.showBothTimes = !currentUser.settings.showBothTimes;
    }
}