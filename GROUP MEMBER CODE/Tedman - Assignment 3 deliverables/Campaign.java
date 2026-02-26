import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Campaign implements UniqueID, Owner{
    private final String campaignID;
    private String title;
    private User owner;
    private Boolean isArchived;
    private List<QuestEvent> questEventList = new ArrayList<QuestEvent>();

    public Campaign(User user){
        owner = user;
        campaignID = "campaign-" + UUID.randomUUID().toString().substring(0, 8);
        isArchived = false;
    }
    public Campaign(User user, String title) {
        this(user);
        this.title = title;
    }
    @Override
    public String generateID() {
        return campaignID;
    }
    
    public String getCampaignID() {
        return campaignID;
    }

    @Override
    public void addOwnList(Object item){
        if (item instanceof QuestEvent){
            addQuestEvent((QuestEvent) item);
        }
        else{
            throw new IllegalArgumentException("Cannot add " + item.getClass().getName() + " to Campaign");
        }
    }
    @Override
    public boolean removeOwnList(Object item){
        if (item instanceof QuestEvent){
            return questEventList.remove(item);
        }
        else {
            throw new IllegalArgumentException("Cannot remove " + item.getClass().getName() + " from Campaign");
        }
    }
    @Override
    public <T> List<T> getOwnedItems(Class<T> itemClass)  {
        List<T> result = new ArrayList<>();

        if (itemClass == QuestEvent.class) {
            for (QuestEvent event : questEventList) {
                result.add(itemClass.cast(event));
            }
        }
        
        return result;        
    }
    
    public void addQuestEvent(QuestEvent questEvent){
        questEventList.add(questEvent);
    }

    public Campaign getCampaign(String title){
        return this;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setOwner(User user)
    {
        owner = user;
    }
    public User getOwner() {
        return owner;
    }

    /**
     * Find a quest event by title
     * @param title The title to search for
     * @return The quest event if found, null otherwise
     */
    public QuestEvent findEvent(String title) {
        if (questEventList.isEmpty()) {
            return null;
        }
        
        for (QuestEvent quest : questEventList) {
            if (quest.getTitle() != null && quest.getTitle().equals(title)) {  // Fixed: use .equals()
                return quest;
            }
        }
        return null;
    }

    /**
     * Check if an event exists by title
     * @param title The title to search for
     */
    public void checkEventExists(String title) {
        QuestEvent event = findEvent(title);
        if (event != null) {
            System.out.println("Valid Quest: " + event.getQuestID());
        } else {
            System.out.println("Quest Not Found: " + title);
        }
    }

    /**
     * View all quest events attached to this campaign
     */
    public void viewQuestEvents() {
        if (questEventList.isEmpty()) {
            System.out.println("This campaign has no quest events");
        }
        else {
            System.out.println("Quest Events in " + title + ":");
            for (QuestEvent quest : questEventList) {
                System.out.println("  - " + quest.getQuestID() + ": " + quest.getTitle());
            }
        }
    }

    // Implement equals and hashCode based on campaignID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Campaign)) return false;
        Campaign campaign = (Campaign) o;
        return campaignID.equals(campaign.campaignID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(campaignID);
    }
    
    @Override
    public String toString() {
        return "Campaign{campaignID='" + campaignID + "', title='" + title + "'}";
    }
}