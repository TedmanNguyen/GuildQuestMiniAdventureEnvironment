public class MonthlyViewStrategy implements QuestViewStrategy{
    public int getRangeInDays(){
        return 30;
    }
    public String getViewLabel(){
        return "This Month's Quest";
    }
    public boolean isUnlimited() { return false; }
}
