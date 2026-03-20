package models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Global in-memory activity log for mini-adventure attempts.
 * Records, per character and per game, how many attempts were made
 * and how long each attempt took.
 */
public class ActivityLog {

    private static class Entry {
        private final String gameName;
        private final int characterId;
        private final String characterName;
        private final int durationSeconds;
        private final boolean completed; // true if finished game, false if quit early
        private final int attemptIndex;   // 1-based index per (character, game)

        private Entry(String gameName, int characterId, String characterName,
                      int durationSeconds, boolean completed, int attemptIndex) {
            this.gameName = gameName;
            this.characterId = characterId;
            this.characterName = characterName;
            this.durationSeconds = durationSeconds;
            this.completed = completed;
            this.attemptIndex = attemptIndex;
        }
    }

    // Map from character id to list of that character's attempts
    private static final Map<Integer, List<Entry>> log = new LinkedHashMap<>();

    /**
     * Record a single game attempt for a character.
     */
    public static synchronized void record(String gameName, Characters player,
                                           int durationSeconds, boolean completed) {
        if (player == null) return;

        int id = player.getId();
        String name = player.getCharacterName();

        List<Entry> entries = log.computeIfAbsent(id, k -> new ArrayList<>());
        // Count existing attempts for this game for this character
        int nextIndex = 1;
        for (Entry e : entries) {
            if (e.gameName.equals(gameName)) {
                nextIndex = Math.max(nextIndex, e.attemptIndex + 1);
            }
        }

        entries.add(new Entry(gameName, id, name, durationSeconds, completed, nextIndex));
    }

    /**
     * Print the activity log for the given list of characters.
     */
    public static void printLog(List<Characters> players) {
        System.out.println("\n=== ACTIVITY LOG ===");
        if (players == null || players.isEmpty()) {
            System.out.println("No characters available.");
            return;
        }

        boolean anyRecorded = false;

        for (Characters c : players) {
            List<Entry> entries = log.get(c.getId());
            if (entries == null || entries.isEmpty()) {
                System.out.println("Character " + c.getCharacterName() + " (id " + c.getId() + "): no game attempts yet.");
                continue;
            }
            anyRecorded = true;
            System.out.println("Character " + c.getCharacterName() + " (id " + c.getId() + "):");

            // Group this character's entries by game name, preserving insertion order
            Map<String, List<Entry>> byGame = new LinkedHashMap<>();
            for (Entry e : entries) {
                byGame.computeIfAbsent(e.gameName, k -> new ArrayList<>()).add(e);
            }

            for (Map.Entry<String, List<Entry>> ge : byGame.entrySet()) {
                String gameName = ge.getKey();
                List<Entry> gameEntries = ge.getValue();
                System.out.println("  " + gameName + " - attempts: " + gameEntries.size());
                for (Entry e : gameEntries) {
                    String status = e.completed ? "completed" : "quit";
                    System.out.println("    Attempt " + e.attemptIndex + " (" + status + "): "
                            + e.durationSeconds + " seconds");
                }
            }
        }

        if (!anyRecorded) {
            System.out.println("No game attempts recorded yet.");
        }

        System.out.println();
    }
}

