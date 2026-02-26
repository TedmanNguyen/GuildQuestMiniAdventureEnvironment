
package GuildQuest;

import java.util.*;

public class WorldManager {
    public Clock clock;
    public Realm[] realms;

    public WorldManager(Clock clock, Realm[] realms) {
        this.clock = clock;
        this.realms = realms;
    }

    // Future change and red in the UML so did not implement
    public boolean isAdjacent(Realm a, Realm b) {
        return true;
    }

    // Future change and red in the UML so did not implement
    public Realm[] getTravelPath(Realm start, Realm goal) {
        return new Realm[0];
    }

    // Future change and red in the UML so did not implement
    public int getTravelTime(Realm a, Realm b) {
        return 0;
    }

}
