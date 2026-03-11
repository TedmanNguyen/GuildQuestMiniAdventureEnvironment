package models;
import java.util.ArrayList;
import java.util.Arrays;


import ui.GuildQuest;

// changed from design: Realm implements LocalTimeRule
public class Realm implements LocalTimeRule {

    // Extendible set of realms
    public static ArrayList<Realm> realms = new ArrayList<Realm>(Arrays.asList(
            new Realm("Irvine", "A city in California", 1),
            new Realm("Los Angeles", "A city in California", 2),
            new Realm("Laguna Beach", "A city in California", 3)
    ));

    private static int realmCount = 0;


    private int realmId;	// changed from design (String -> int)
    private String name;
    private String description;
    private int timeOffset; // change to timeOffset

    public Realm(String name, String description, int timeOffset) {
        realmId = realmCount++;
        this.name = name;
        this.description = description;
        this.timeOffset = timeOffset;
    }

    public GameTime getLocalTime() { return calculate(); }

    public GameTime calculate() {
        return GuildQuest.worldClock.getCurrentTime().applyOffset(timeOffset);
    }
    // added methods
    public String toString() {
        return "#" + realmId + ": " + name + " - " + "\"" + description + "\"" + getLocalTime();
    }

    public String getName() { return name; }
    public static int getNumRealms() { return realms.size(); }

    public static void printRealms() {
        System.out.println("REALMS");
        System.out.println("--------------------");
        for (int i = 0; i < realms.size(); ++i)
            System.out.println(i + ": " + realms.get(i));
        System.out.println();
    }
}