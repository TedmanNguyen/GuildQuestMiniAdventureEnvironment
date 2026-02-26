
package GuildQuest;

import java.util.HashMap;
import java.util.Map;

public class Realm {
    public int id;
    public String name;
    public String description;
    public float[] coordinates;
    public Realm[] adjacentRealms;
    public Clock worldClock;
    public DateRule localDateRule;

    public Map<Realm, Integer> adjacentDict = new HashMap<>();

    public Realm(int id, String name, String description, float[] coordinates, Clock worldClock, DateRule localDateRule) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.coordinates = coordinates;
        this.worldClock = worldClock;
        this.localDateRule = localDateRule;
        this.adjacentRealms = new Realm[0];
    }

    public int getLocalTime() {
        return (worldClock == null) ? 0 : worldClock.time;
    }

    public int getDays() { return localDateRule.getDayConversion(getLocalTime()); }
    public int getHours() { return localDateRule.getHourConversion(getLocalTime()); }
    public int getMinutes() { return localDateRule.getMinuteConversion(getLocalTime()); }

    @Override
    public String toString() { return name + " (Realm " + id + ")"; }
}
