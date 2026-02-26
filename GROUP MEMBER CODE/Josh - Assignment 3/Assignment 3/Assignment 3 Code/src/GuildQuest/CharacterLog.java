
package GuildQuest;

import java.util.*;

public class CharacterLog {
    private final Map<User, List<Character>> participation = new HashMap<>();

    public void record(User user, Character character) {
        participation.computeIfAbsent(user, k -> new ArrayList<>()).add(character);
    }

    public Map<User, List<Character>> getParticipation() {
        return Collections.unmodifiableMap(participation);
    }
}
