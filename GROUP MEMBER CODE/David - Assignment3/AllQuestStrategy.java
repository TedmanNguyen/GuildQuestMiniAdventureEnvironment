public class AllQuestStrategy implements QuestViewStrategy{
    public int getRangeInDays() { return 0; } // Irrelevant when unlimited
    public String getViewLabel() { return "All Quests"; }
    public boolean isUnlimited() { return true; }
}
