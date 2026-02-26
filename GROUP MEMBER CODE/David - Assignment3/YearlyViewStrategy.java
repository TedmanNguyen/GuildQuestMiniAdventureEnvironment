public class YearlyViewStrategy implements QuestViewStrategy {
    public int getRangeInDays() { return 365; }
    public String getViewLabel() { return "This Year's Quests"; }
    public boolean isUnlimited() { return false; }
}