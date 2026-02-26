
package GuildQuest;

import java.util.ArrayList;
import java.util.List;

public class Campaign {
    public int id;
    public String name;
    public List<QuestEvent> questEvents = new ArrayList<>();
    public boolean publicc; // renamed from original name in UML since public is a reserved word
    public boolean archived;

    public Campaign(int id, String name) {
        this.id = id;
        this.name = name;
        this.publicc = false;
        this.archived = false;
    }

    public void addQuest(QuestEvent questEvent) {
        if (archived || questEvent == null) return;
        questEvents.add(questEvent);
    }
    public void removeQuest(QuestEvent questEvent) {

        questEvents.remove(questEvent);
    }

    @Override
    public String toString() {
        return "Campaign{" + id + ", " + name + ", public=" + publicc + ", archived=" + archived + "}";
    }
}
