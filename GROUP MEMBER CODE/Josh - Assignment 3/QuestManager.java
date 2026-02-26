package GuildQuest;

import java.util.*;

public class QuestManager {

    public List<QuestEvent> questEvents = new ArrayList<>();

    public Map<QuestEvent, Set<User>> shared = new HashMap<>();

    public void addEvent(QuestEvent questEvent) {
        if (questEvent == null) return;
        questEvents.add(questEvent);
    }

    public boolean removeEvent(int index) {
        if (index < 0 || index >= questEvents.size()) return false;

        QuestEvent target = questEvents.get(index);

        blastNotify(target, "Quest " + target.name + " has been removed.");

        questEvents.remove(index);
        shared.remove(target);
        return true;
    }

    public boolean updateEvent(int index, QuestEvent updated) {
        if (updated == null) return false;
        if (index < 0 || index >= questEvents.size()) return false;

        QuestEvent existing = questEvents.get(index);

        existing.name = updated.name;
        existing.startTime = updated.startTime;
        existing.endTime = updated.endTime;
        existing.realm = updated.realm;
        existing.characterLog = updated.characterLog;
        existing.characters = updated.characters;

        return true;
    }

    public void shareQuest(User user, QuestEvent questEvent) {
        if (user == null || questEvent == null) return;
        shared.computeIfAbsent(questEvent, k -> new HashSet<>()).add(user);
        quietNotify(user, "Quest " + questEvent.name + " has been shared with you.");
    }

    public void unshareQuest(User user, QuestEvent questEvent) {
        if (user == null || questEvent == null) return;
        Set<User> s = shared.get(questEvent);
        if (s != null) s.remove(user);
    }

    public List<QuestEvent> getVisibleQuests(User user) {
        List<QuestEvent> vis = new ArrayList<>();
        for (QuestEvent e : questEvents) {
            if (e != null && e.user == user) {
                vis.add(e);
                continue;
            }
            Set<User> s = shared.get(e);
            if (s != null && s.contains(user)) vis.add(e);
        }
        return vis;
    }

  // Future change and red in the UML so did not implement
    public void blastNotify(QuestEvent questEvent, String message) {

    }

    // Future change and red in the UML so did not implement
    public void quietNotify(User user, String message) {
        if (user != null) user.notify(message);
    }
}