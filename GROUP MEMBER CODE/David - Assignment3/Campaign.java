import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Campaign {
    private int campaignId;
    private int userId;
    private String campaignName;
    private boolean archived;
    private List<Quest> questEvents = new ArrayList<>();
    private Map<Integer,Permission> shareWith = new HashMap<>();
    private Visibility visibility = Visibility.PUBLIC;

    Campaign(int campaignId, int userId,String campaignName){
        this.campaignId = campaignId;
        this.userId = userId;
        this.campaignName = campaignName;
    }
    void addQuest(String questName,int startTime,Realm realm){
        Quest quest = new Quest(questEvents.size(), questName, startTime,realm);
        questEvents.add(quest);
    }
    void removeQuest(int questId){
        for (Iterator<Quest> it = questEvents.iterator(); it.hasNext(); ) {
            if (it.next().getQuestId() == questId) {
                it.remove();
                break;
            }
        }
        System.out.println("Quest with id: " + questId + " was deleted.");
    }
    public String getCampaignName(){
        return campaignName;
    }
    public void setCampaignName(String newName) {
        campaignName = newName;
    }

    boolean canEdit(int requestingUserId){
        if (requestingUserId == userId) return true;
        Permission p = shareWith.get(requestingUserId);
        return p == Permission.COLLABORATIVE;
    }

    public void share(int targetUserId, Permission level){
        shareWith.put(targetUserId,level);
    }
    public void setArchived(boolean archive) {
        archived = archive;
        System.out.println("Archive status: " + isArchived());

    }

    public boolean isArchived() {
        return archived;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
        System.out.println("Visiblity status: " + getVisibility());
    }

    public int getCampaignID(){
        return campaignId;
    }
    public Quest getQuest(int id){
        for (Quest quest : questEvents) {
            if (quest.getQuestId() == id) {
                return quest;
            }
        }
        return null;
    }
    public void viewQuests(){
        for (Quest quest: questEvents){
            quest.getQuestInfo();
        }
    }

    

    private static void displayTime(int worldMinutes, Realm realm, TimeDisplayPreference pref, String label) {
        if (pref == TimeDisplayPreference.WORLD || pref == TimeDisplayPreference.BOTH) {
            int days = worldMinutes / (24 * 60);
            int hours = (worldMinutes / 60) % 24;
            int mins = worldMinutes % 60;
            System.out.println(label + " (World): Day " + days + ", " + String.format("%02d:%02d", hours, mins));
        }
        if (pref == TimeDisplayPreference.REALM || pref == TimeDisplayPreference.BOTH) {
            String localTime = realm.getLocalTimeDisplay(worldMinutes);
            System.out.println(label + " (Realm): " + localTime);
        }
    }

    public void displayQuestsInRange(int days, TimeDisplayPreference pref) {
        int currentTime = WorldTime.getInstance().getTotalMinutes();
        int rangeStart = currentTime;
        int rangeEnd = currentTime + (days * 24 * 60);
        boolean found = false;
        for (Quest q : questEvents) {
            if (q.getStartTime() >= rangeStart && q.getStartTime() <= rangeEnd) {
                System.out.println("Quest: " + q.getQuestName());
                displayTime(q.getStartTime(), q.getCurrentRealm(), pref, "Start");
                if (q.getEndTime() != null && q.getEndTime() <= rangeEnd) {
                    displayTime(q.getEndTime(), q.getCurrentRealm(), pref, "End");
                }
                System.out.println("Realm: " + q.getCurrentRealm().getName());
                System.out.println("---");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No quests in this time range.");
        }
    }

    public void displayQuests(QuestViewStrategy strategy, TimeDisplayPreference pref){
        System.out.println("\n--- " + strategy.getViewLabel() + " (World Clock) ---");
        if (strategy.isUnlimited()) {
            viewQuests();
        } else {
            displayQuestsInRange(strategy.getRangeInDays(), pref);
        }
    }

//    REMOVED FOR STRATEGY PATTERN AI PATTERN
//    public void displayQuestsByDay(TimeDisplayPreference pref) {
//        System.out.println("\n--- Today's Quests (World Clock) ---");
//        displayQuestsInRange(1, pref);
//    }
//
//    public void displayQuestsByWeek(TimeDisplayPreference pref) {
//        System.out.println("\n--- This Week's Quests (World Clock - 7 Days) ---");
//        displayQuestsInRange(7, pref);
//    }
//
//    public void displayQuestsByMonth(TimeDisplayPreference pref) {
//        System.out.println("\n--- This Month's Quests (World Clock - 30 Days) ---");
//        displayQuestsInRange(30, pref);
//    }
//
//    public void displayQuestsByYear(TimeDisplayPreference pref) {
//        System.out.println("\n--- This Year's Quests (World Clock - 365 Days) ---");
//        displayQuestsInRange(365, pref);
//    }
}
