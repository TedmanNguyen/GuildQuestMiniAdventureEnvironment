
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class User {
    private int userID;
    private String userName;
    private int currentRealmId;
    private Settings userSetting;
    private List<Characters> characters;
    private Map<Integer, Permission> campaignPermissions;
    private List<Campaign> myCampaigns;
    private List<Campaign> sharedCampaigns;
    private List<Quest> sharedQuests;
    private Map<Integer, Permission> questPermissions;
    User(String userName, int userID){
        this.userName = userName;
        this.userID = userID;
        characters = new ArrayList<>();
        myCampaigns = new ArrayList<>();
        userSetting = new Settings();
        sharedQuests = new ArrayList<>();
        questPermissions = new HashMap<>();
        campaignPermissions = new HashMap<>();
    }
    public String getUserName() {
        return userName;
    }

    //    **REMOVED FOR REFACTOR**
//    void createCampaign(String name){
//        Campaign newCampaign = new Campaign(myCampaigns.size(),userID,name);
//        myCampaigns.add(newCampaign);
//    }
//    replaced with add campaign
    void addCampaign(String name, CampaignFactory factory){
        int newId = myCampaigns.size();
        Campaign newCampaign = factory.createCampaign(newId, this.userID,name);
        myCampaigns.add(newCampaign);
    }
    void deleteCampaign(String campaignName){
        for (Iterator<Campaign> it = myCampaigns.iterator(); it.hasNext(); ) {
            if (it.next().getCampaignName().equals(campaignName)) {
                it.remove();
                break;
            }
        }
        System.out.println("Campaign with id: "+ campaignName + " was deleted.");
    }

    public Settings getUserSettings() {
        return userSetting;
    }
//    move method
    public void viewSettings() {
        userSetting.viewSettings();
    }

    Campaign getCampaign(int id){
        for (Campaign campaign : myCampaigns) {
            if (campaign.getCampaignID() == id) {
                return campaign;
            }
        }
        return null;
    }

    void receiveSharedCampaign(Campaign campaign, Permission level) {
        if (!sharedCampaigns.contains(campaign)) {
            sharedCampaigns.add(campaign);
        }
        campaignPermissions.put(campaign.getCampaignID(), level);
    }

    void shareQuest(Quest quest, User targetUser, Permission level) {
        targetUser.receiveSharedQuest(quest, level);
    }

    void addCharacter(Characters character){
        characters.add(character);
    }

    private void receiveSharedQuest(Quest quest, Permission level) {
        if (!sharedQuests.contains(quest)){
            sharedQuests.add(quest);
        }
        questPermissions.put(quest.getQuestId(), level);
    }

    public List<Characters> getCharacters(){
        return characters;
    }
    public void getUserInfo(){
        System.out.println("Your name name and id: " + userName + " " + userID);
    }
    public int getUserId(){
        return userID;
    }

    public void displayInfo() {
        getUserInfo();
        System.out.println("Characters: " + characters.size());
        System.out.println("My Campaigns: " + myCampaigns.size());
    }

    public void displayCharacters() {
        if (characters.isEmpty()) {
            System.out.println("No characters.");
            return;
        }
        for (Characters c : characters) {
            c.getCharacterInfo();
            System.out.println("---");
        }
    }

    public void displayCampaigns() {
        if (myCampaigns.isEmpty()) {
            System.out.println("No campaigns.");
            return;
        }
        for (Campaign c : myCampaigns) {
            System.out.println(" (ID: " + c.getCampaignID() + ")" + "Campaign: " + c.getCampaignName() + " Archived: " + c.isArchived() + " Visibility : " + c.getVisibility());
        }
    }

}
