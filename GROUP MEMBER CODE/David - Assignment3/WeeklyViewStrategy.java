public class WeeklyViewStrategy implements QuestViewStrategy {
    public int getRangeInDays() { return 7; }
    public String getViewLabel() { return "This Week's Quests"; }
    public boolean isUnlimited() { return false; }
}