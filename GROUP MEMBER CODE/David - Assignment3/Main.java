import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
//    REMOVED FOR FACADE PATTERN
    //    private static final UserManager userManager = new UserManager();
//    private static User currentUser = null;
//    private static final WorldTime worldTime = WorldTime.getInstance();
//    private static final List<Realm> realmDatabase = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static RPGManagerFacade facade = new RPGManagerFacade();

    private static Menu campaignMenu = new CampaignMenu(facade,scanner);
    private static Menu questMenu = new QuestMenu(facade,scanner);
    private static Menu mainMenu = new MainMenu(facade,scanner);
    private static Menu settingsMenu = new SettingsMenu(facade,scanner);

    public static void main(String[] args) {
        while(facade.getCurrentUser() == null){
            authentication();
        }
        mainMenu();
    }
    private static void authentication(){
        System.out.println("Please enter # | 1. Login | 2. Create account");
        String choice = scanner.nextLine();

        switch (choice){
            case "1" -> login();
            case "2" -> createAccount();
        }
    }
//    EDITTED FOR FACADE PATTERN
//    private static void login(){
//        System.out.println("Enter username: ");
//        String user = scanner.nextLine();
//        if (userManager.getUser(user) == null){
//            System.out.println("No user in database");
//        }
//        else{
//            currentUser = userManager.getUser(user);
//        }
//    }
//
//    updated login function for facade pattern
    private static void login(){
        System.out.println("Enter username: ");
        String user = scanner.nextLine();
        if(!facade.login(user)){
            System.out.println("Login failed.");
        }
    }
    private static void createAccount(){
        System.out.println("Enter username: ");
        String user = scanner.nextLine();
        facade.createAccount(user);
    }
    private static void mainMenu() {
        mainMenu.display();
        switch (scanner.nextLine()) {
            case "1" -> campaignMenu();
            case "2" -> questMenu();
            case "3" -> settingsMenu();
            case "4" -> facade.setCurrentUser(null);
        }
    }

//    REMOVED FOR FACADE PATTERN
//    private static void initializeData(){
//        TimeRule defaultTimeRule = new TimeRule(0,1);
//        Realm dragonCityDefaultRealm = new Realm(1,"Dragon City",defaultTimeRule);
//        Realm smurfCityDefaultRealm = new Realm(1,"Smurf City",defaultTimeRule);
//        Realm fortniteCityDefaultRealm = new Realm(1,"Fortnite City",defaultTimeRule);
//        realmDatabase.add(dragonCityDefaultRealm);
//        realmDatabase.add(smurfCityDefaultRealm);
//        realmDatabase.add(fortniteCityDefaultRealm);
//        currentUser = userManager.createUser("demo");
//        currentUser.addCampaign("NukeTown");
//        currentUser.getCampaign(0).addQuest("DemoQuest", 0, fortniteCityDefaultRealm);
//        currentUser.getUserSettings().setCurrentRealm(fortniteCityDefaultRealm);
//        currentUser = null;
//    }

    private static void campaignMenu(){
//        removed for extract class Refactoring moved to campaignmenu class
//        System.out.println("\n\n\n\n\n\n--- Campaign Menu ---");
//        facade.printWorldTime();
//        System.out.println("1. Add Campaign | 2. Remove Campaign | 3. Update Campaign  | 4. View Campaigns");
        campaignMenu.display();
        switch (scanner.nextLine()){
            case "1" -> addCampaign();
            case "2" -> removeCampaign();
            case "3" -> updateCampaign();
            case "4" -> facade.getCurrentUser().displayCampaigns();
        }
        mainMenu();
    }

//    moved over for facade pattern
//    private static void printRealmTime(){
//        Realm realm = currentUser.getUserSettings().getCurrentRealm();
//        System.out.println(realm.getName() + " time: " + realm.getLocalTimeDisplay(campaignCount));
//    }
    private static void questMenu(){

//        extract class refactor moved over to its own class
//        System.out.println("\n\n\n\n\n\n--- Quest Menu ---");
//        facade.printWorldTime();
//        System.out.println("1. Add quest | 2. Remove quest | 3. Update Quest | 4. View Quests");
//        String choice = scanner.nextLine();
//        facade.getCurrentUser().displayCampaigns();

        questMenu.display();
        String choice = scanner.nextLine();
        switch (choice){
            case "1" -> addQuest();
            case "2" -> removeQuest();
            case "3" -> updateQuest();
            case "4" -> viewQuests();
        }
        mainMenu();
    }
    private static void updateQuest(){
        Campaign chosenCampaign = selectCampaign();
        chosenCampaign.viewQuests();
        System.out.println("Enter quest id");
        int questId = Integer.parseInt(scanner.nextLine());
        Quest chosenQuest = chosenCampaign.getQuest(questId);
        System.out.println("1. Change quest Name | 2. change start time | 3. change end time | 4. change realm | 5. Main Menu ");
        String choice = scanner.nextLine();
        switch(choice){
            case "1" -> changeQuestName(chosenQuest);
            case "2" -> chosenQuest.setStartTime(selectTime());
            case "3" -> chosenQuest.setEndTime(selectTime());
            case "4" -> changeQuestRealm(chosenQuest);
            case "5" -> mainMenu();
        }
    }
    private static int selectTime(){
        System.out.println("Please enter time to update quest.");
        int questTime = Integer.parseInt(scanner.nextLine());
        return questTime;
    }
    
    private static void changeQuestRealm(Quest quest){
        facade.selectRealm();
        System.out.println("Enter which realm you would like to choose for your quest");
        int realmSelection = Integer.parseInt(scanner.nextLine());
        facade.changeQuestRealm(quest,realmSelection);
//        moved logic to facade
//        Realm foundRealm = realmDatabase.get(realmSelection);
//        quest.changeRealm(foundRealm);
    }
    
    private static void changeQuestName(Quest quest){
        System.out.println("Please enter name to update quest.");
        String questName = scanner.nextLine();
        quest.setQuestName(questName);
    }
    private static void removeQuest(){
        Campaign chosenCampaign = selectCampaign();
        chosenCampaign.viewQuests();
        System.out.println("enter quest id to remove");
        int questId= Integer.parseInt(scanner.nextLine());
        chosenCampaign.removeQuest(questId);

    }
    private static void viewQuests(){
        Campaign chosenCampaign = selectCampaign();
        viewQuestsByTimeline(chosenCampaign);
    }

    private static void viewQuestsByTimeline(Campaign campaign) {
        while (true) {
            System.out.println("\n--- Quest Timeline Views ---");
            System.out.println("1. View Today's Quests | 2. View This Week's Quests");
            System.out.println("3. View This Month's Quests | 4. View This Year's Quests");
            System.out.println("5. View All Quests | 6. Back to Quest Menu");
            String choice = scanner.nextLine();

            QuestViewStrategy strategy = switch (choice) {
                case "1" -> new DailyViewStrategy();
                case "2" -> new WeeklyViewStrategy();
                case "3" -> new MonthlyViewStrategy();
                case "4" -> new YearlyViewStrategy();
                case "5" -> new AllQuestStrategy();
                default -> null;
            };
            if (choice.equals("5")){campaign.viewQuests();}
            if (strategy != null && !choice.equals("5")) {
                TimeDisplayPreference timeDisplay = facade.getCurrentUser().getUserSettings().getTimeDisplayPreference();
                campaign.displayQuests(strategy, timeDisplay);
            }

//          Replaced for strategy pattern
//            switch (choice) {
//                case "1" -> campaign.displayQuestsByDay(timeDisplay);
//                case "2" -> campaign.displayQuestsByWeek(timeDisplay);
//                case "3" -> campaign.displayQuestsByMonth(timeDisplay);
//                case "4" -> campaign.displayQuestsByYear(timeDisplay);
//                case "5" -> campaign.viewQuests();
//                case "6" -> {
//                    return;
//                }
//            }
        }
    }
    private static Campaign selectCampaign(){
        System.out.println("Enter Campaign Id");
        int campaignId = Integer.parseInt(scanner.nextLine());
        Campaign chosenCampaign = facade.getCampaign(campaignId);
        return chosenCampaign;
    }
    private static void addQuest(){
        System.out.println("What campaign would you like to add a quest in?");
        Campaign chosenCampaign = selectCampaign();
        System.out.println("\n\n Enter quest name.");
        String questName = scanner.nextLine();
        System.out.println("\n\n Enter quest start time.");
        int startTime = Integer.parseInt(scanner.nextLine());
        facade.selectRealm();
        System.out.println("Enter which realm you would like to choose for your quest");
        int realmSelection = Integer.parseInt(scanner.nextLine());
        Realm foundRealm = facade.getRealm(realmSelection);
        chosenCampaign.addQuest(questName, startTime, foundRealm);
    }

//    moved for facade pattern
//    private static void selectRealm(){
//        int i = 0;
//        for (Realm realm: realmDatabase){
//            System.out.println(i++ + "." + realm.getName());
//        }
//    }
    private static void addCampaign(){
        System.out.println("Enter Campaign Name:");
        String name = scanner.nextLine();
        facade.addNewCampaign(name);
//        moved logic to facade + factory method
//        CampaignFactory factory = new DefaultCampaign();
//        currentUser.addCampaign(name,factory);
    }
//    factory method + facade in place
//    private static void addCampaign(){
//        System.out.println("Please enter Campaign Name you would like to create.");
//        String campaignName = scanner.nextLine();
//        currentUser.createCampaign(campaignName);
//        System.out.println(campaignName + " has been added to your campaign list.");
//    }

    private static void removeCampaign(){
        facade.getCurrentUser().displayCampaigns();
        System.out.println("Please enter name of Campaign you would like to remove.");
        String campaignName = scanner.nextLine();
        facade.getCurrentUser().deleteCampaign(campaignName);
    }

    private static void updateCampaign(){
        facade.getCurrentUser().displayCampaigns();
        System.out.println("Please enter the id of which campaign you would like to edit");
        int campaignId = scanner.nextInt();
        scanner.nextLine();
        Campaign modifiedCampaign = facade.getCurrentUser().getCampaign(campaignId);
        System.out.println("1. Change Name | 2. archive | 3. Update Visibility");
        String choice = scanner.nextLine();
        switch(choice){
            case "1" -> changeCampaignName(modifiedCampaign);
            case "2" -> archiveCampaign(modifiedCampaign);
            case "3" -> visibilityCampaign(modifiedCampaign);
        }
        mainMenu();
    }

    private static void changeCampaignName(Campaign campaign){
        System.out.println("Please enter new campaign name");
        String name = scanner.nextLine();
        facade.changeCampaignName(campaign,name);
//        moved logic with facade pattern
//        campaign.setCampaignName(name);
//        System.out.println("\n\n Updated List");
//        currentUser().displayCampaigns();
    }

    private static void archiveCampaign(Campaign campaign){
        System.out.println("Please enter 1 to archive or 2 to unarchive");
        int choice = scanner.nextInt();
        facade.setCampaignArchive(campaign,choice);
//        switch(choice){
//            case 1 -> campaign.setArchived(true);
//            case 2 -> campaign.setArchived(false);
//        }
    }

    private static void visibilityCampaign(Campaign campaign){
        System.out.println("Please enter 1 to make campaign public or 2 to private campaign.");
        int choice = scanner.nextInt();
        switch(choice){
            case 1 -> campaign.setVisibility(Visibility.PUBLIC);
            case 2 -> campaign.setVisibility(Visibility.PRIVATE);   
        }
    }
//    moved for facade pattern
//    private static void printWorldTime(){
//        System.out.println("World Clock: " + worldTime.getFormattedTime());
//    }


    private static void settingsMenu() {
//        extract class refactor
//        System.out.println("\n--- Settings ---");
//        System.out.println("1. Change Theme | 2. Set Current Realm | 3. Set Time Display | 4. View Settings | 5. Back");
        settingsMenu.display();
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> changeTheme();
            case "2" -> setCurrentRealm();
            case "3" -> setTimeDisplay();
            case "4" -> facade.getCurrentUser().viewSettings();
        }
        mainMenu();
    }

    private static void changeTheme() {
        System.out.println("Theme: 1. CLASSIC, 2. MODERN");
        String choice = scanner.nextLine();
        facade.changeTheme(choice);

//        Theme theme = choice.equals("1") ? Theme.CLASSIC : Theme.MORDEN;
//        currentUser.getUserSettings().setTheme(theme);
    }

    private static void setCurrentRealm() {
        facade.selectRealm();
        System.out.println("Enter which Realm you would like to change too. ");
        int realmSelection = Integer.parseInt(scanner.nextLine());
        facade.changeRealm(realmSelection);
//        facade pattern
//        Realm foundRealm = realmDatabase.get(realmSelection);
//
//        if (foundRealm != null) {
//            currentUser.getUserSettings().setCurrentRealm(foundRealm);
//        }
    }

    private static void setTimeDisplay() {
        System.out.println("Time Display: 1. WORLD, 2. REALM, 3. BOTH");
        String choice = scanner.nextLine();
        TimeDisplayPreference pref = switch (choice) {
            case "1" -> TimeDisplayPreference.WORLD;
            case "2" -> TimeDisplayPreference.REALM;
            case "3" -> TimeDisplayPreference.BOTH;
            default -> TimeDisplayPreference.WORLD;
        };
        facade.getCurrentUser().getUserSettings().setTimeDisplayPreference(pref);
    }
}