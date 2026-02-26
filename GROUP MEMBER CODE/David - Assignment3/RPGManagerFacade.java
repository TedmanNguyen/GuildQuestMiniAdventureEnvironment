import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RPGManagerFacade {

    private final UserManager userManager = new UserManager();
    private final List<Realm> realmDataBase = new ArrayList<>();
    private final WorldTime worldTime = WorldTime.getInstance();
    private User currentUser;
    private int campaignCounter = 0;
    public RPGManagerFacade(){
        initializeDefaultData();
    }
    void initializeDefaultData(){
        TimeRule defaultTimeRule = new TimeRule(0,1);
        Realm dragonCityDefaultRealm = new Realm(1,"Dragon City",defaultTimeRule);
        Realm smurfCityDefaultRealm = new Realm(1,"Smurf City",defaultTimeRule);
        Realm fortniteCityDefaultRealm = new Realm(1,"Fortnite City",defaultTimeRule);
        CampaignFactory factory = new DefaultCampaign(1,100,"TestCampaign");
        realmDataBase.add(dragonCityDefaultRealm);
        realmDataBase.add(smurfCityDefaultRealm);
        realmDataBase.add(fortniteCityDefaultRealm);
        currentUser = userManager.createUser("demo");
        currentUser.addCampaign("NukeTown",factory);
        currentUser.getCampaign(0).addQuest("DemoQuest", 0, fortniteCityDefaultRealm);
        currentUser.getUserSettings().setCurrentRealm(fortniteCityDefaultRealm);
        currentUser = null;
    }
    public boolean login(String name){
        User user = userManager.getUser(name);
        if (user != null){
            currentUser = user;
            return true;
        }
            return false;
    }
    public void createAccount(String name){
        this.currentUser = userManager.createUser(name);
    }
    public User getCurrentUser(){
        return currentUser;
    }
    public void setCurrentUser(User user){
        currentUser = user;
    }
    public void getUserTime(){
        if (currentUser.getUserSettings().getTimeDisplayPreference() == TimeDisplayPreference.WORLD){
            printWorldTime();
        }
        else{
            printRealmTime();
        }
    }

    public void printRealmTime(){
        Realm realm = currentUser.getUserSettings().getCurrentRealm();
        long localTime = new Date().getTime();
        System.out.println(realm.getName() + " time: " + realm.getLocalTimeDisplay(localTime));
    }
    public void printWorldTime(){
        System.out.println("World Clock: " + worldTime.getFormattedTime());
    }

    public void getUserSettings(){
        currentUser.viewSettings();
    }

    public void addNewCampaign(String name){
        if (currentUser == null)return;
        int campaignId = campaignCounter++;
        int userId = currentUser.getUserId();
        CampaignFactory factory = new DefaultCampaign(campaignId,userId,name);
        currentUser.addCampaign(name,factory);
    }
    public void removeCampaign(){
        currentUser.displayCampaigns();
    }
    public Campaign getCampaign(int cId){
        return currentUser.getCampaign(cId);
    }
    public void changeCampaignName(Campaign campaign, String name){
        campaign.setCampaignName(name);
        System.out.println("\n\n Updated List");
        currentUser.displayCampaigns();
    }
    public void changeQuestRealm(Quest quest,int realmId){
        Realm foundRealm = realmDataBase.get(realmId);
        quest.changeRealm(foundRealm);
    }
    public void setCampaignArchive(Campaign campaign, int choice){
        switch(choice){
            case 1 -> campaign.setArchived(true);
            case 2 -> campaign.setArchived(false);
        }
    }
    public void changeTheme(String choice){
        Theme theme = choice.equals("1") ? Theme.CLASSIC : Theme.MORDEN;
        currentUser.getUserSettings().setTheme(theme);
    }
    public void changeRealm(int choice){
        Realm foundRealm = realmDataBase.get(choice);
        if (foundRealm != null) {
            currentUser.getUserSettings().setCurrentRealm(foundRealm);
        }
    }
    public void selectRealm(){
        int i = 0;
        for (Realm realm: realmDataBase){
            System.out.println(i++ + "." + realm.getName());
        }
    }
    public Realm getRealm(int realmId){
        return realmDataBase.get(realmId);
    }
}
