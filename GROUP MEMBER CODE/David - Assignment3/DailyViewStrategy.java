public class DailyViewStrategy implements QuestViewStrategy {
    public int getRangeInDays() { return 1; }
    public String getViewLabel() { return "Today's Quests"; }
    public boolean isUnlimited() { return false; }
}