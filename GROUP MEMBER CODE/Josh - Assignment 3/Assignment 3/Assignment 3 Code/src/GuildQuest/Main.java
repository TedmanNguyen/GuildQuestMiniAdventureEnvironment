package GuildQuest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.LinkedHashMap;

public class Main {

    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static String readLine(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    // === A3 CHANGES START: Command Pattern (Menu Framework) ===
    private static int promptMenu(Scanner sc, String title, LinkedHashMap<Integer, String> labels) {
        System.out.println("\n" + title);
        for (Map.Entry<Integer, String> e : labels.entrySet()) {
            System.out.println(e.getKey() + ". " + e.getValue());
        }
        return readInt(sc, "Choose an option: ");
    }

    private static void runMenuLoop(
            Scanner sc,
            String title,
            LinkedHashMap<Integer, String> labels,
            LinkedHashMap<Integer, Command> actions,
            int backOptionNumber
    ) {
        while (true) {
            int choice = promptMenu(sc, title, labels);
            if (choice == backOptionNumber) return;

            Command cmd = actions.get(choice);
            if (cmd == null) {
                System.out.println("Invalid option.");
            } else {
                cmd.execute();
            }
        }
    }
    // === A3 CHANGES END: Command Pattern (Menu Framework) ===

    private static Campaign findCampaign(CampaignManager cm, int id) {
        for (Campaign c : cm.campaigns) {
            if (c.id == id) return c;
        }
        return null;
    }

    private static void listCharacters(User user) {
        if (user.characters.isEmpty()) {
            System.out.println("(No characters yet)");
            return;
        }
        for (int i = 0; i < user.characters.size(); i++) {
            Character c = user.characters.get(i);
            String current = (user.currentCharacter == c) ? " [current]" : "";
            System.out.println(i + ": " + c + current);
        }
    }

    private static User getOrCreateUser(Map<String, User> usersByName, String username, int[] nextUid) {
        User existing = usersByName.get(username);
        if (existing != null) return existing;

        User created = new User(username, nextUid[0]);
        nextUid[0]++;
        usersByName.put(username, created);
        return created;
    }

    // === A3 CHANGES START: Refactoring #1 (Extract Method) ===

    private static void handleCreateCampaign(
            Scanner sc,
            CampaignManager campaignManager,
            User currentUser
    ) {
        int id = readInt(sc, "Enter campaign ID: ");
        String name = readLine(sc, "Enter campaign name: ");

        // === A3 CHANGES: Factory Pattern (Create Campaign) ===
        Campaign c = GameObjectFactory.createCampaign(id, name);

        campaignManager.addCampaign(c);
        campaignManager.setOwner(id, currentUser);
        System.out.println("Campaign created.");
    }

    private static void handleDeleteCampaign(
            Scanner sc,
            CampaignManager campaignManager,
            User currentUser
    ) {
        int id = readInt(sc, "Enter campaign ID to delete: ");
        // === A3 CHANGES: Refactoring #2 (Replace Primitive/String with Enum) ===
        Role role = campaignManager.getRole(currentUser, id);
        if (role != Role.OWNER) {
            System.out.println("You can only delete campaigns you own.");
            return;
        }

        // === A3 CHANGES START: AI-Assisted Refactoring (Move Method) ===
        // Centralize edit permission policy inside CampaignManager (UI should not own rules).
        if (!campaignManager.canEditCampaign(currentUser, id)) {
            System.out.println("You can only delete campaigns you own.");
            return;
        }
        // === A3 CHANGES END: AI-Assisted Refactoring (Move Method) ===

        boolean removed = campaignManager.removeCampaign(id);
        System.out.println("Deleted: " + removed);
    }

    private static void handleListVisibleCampaigns(
            CampaignManager campaignManager,
            User currentUser
    ) {
        List<Campaign> visible = campaignManager.getVisibleCampaigns(currentUser);
        if (visible.isEmpty()) {
            System.out.println("(No visible campaigns for this user)");
        } else {
            for (Campaign c : visible) {
                // === A3 CHANGES: Refactoring #2 (Replace Primitive/String with Enum) ===
                Role role = campaignManager.getRole(currentUser, c.id);
                System.out.println(c + " (role: " + role + ")");
            }
        }
    }

    private static void handleToggleCampaignVisibility(
            Scanner sc,
            CampaignManager campaignManager,
            User currentUser
    ) {
        int id = readInt(sc, "Enter campaign ID: ");
        Campaign c = findCampaign(campaignManager, id);
        if (c == null) {
            System.out.println("Campaign not found.");
            return;
        }

        // === A3 CHANGES: Refactoring #2 (Replace Primitive/String with Enum) ===
        Role role = campaignManager.getRole(currentUser, id);
        if (role != Role.OWNER) {
            System.out.println("You can only change visibility for campaigns you own.");
            return;
        }

        // === A3 CHANGES START: AI-Assisted Refactoring (Move Method) ===
        // Centralize edit permission policy inside CampaignManager.
        if (!campaignManager.canEditCampaign(currentUser, id)) {
            System.out.println("You can only change visibility for campaigns you own.");
            return;
        }
        // === A3 CHANGES END: AI-Assisted Refactoring (Move Method) ===

        c.publicc = !c.publicc;
        campaignManager.setVisibility(id, c.publicc);
        System.out.println("Campaign " + id + " is now public=" + c.publicc);
    }

    // === A3 CHANGES END: Refactoring #1 (Extract Method) ===


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CampaignManager campaignManager = new CampaignManager();
        QuestManager questManager = new QuestManager();

        // Multiple users stored by username
        Map<String, User> usersByName = new HashMap<>();
        int[] nextUid = new int[]{1};

        System.out.println("\n--- Welcome to GuildQuest ---");
        String initialUsername = readLine(sc, "Enter username: ").trim();
        if (initialUsername.isEmpty()) initialUsername = "Player1";

        // === A3 CHANGES START: Command Pattern (Mutable User Holder for Lambdas) ===
        final User[] currentUserRef = new User[]{ getOrCreateUser(usersByName, initialUsername, nextUid) };
        // === A3 CHANGES END: Command Pattern (Mutable User Holder for Lambdas) ===

        // === A3 CHANGES START: Command Pattern (Main Menu -> Commands) ===
        while (true) {
            String mainTitle = "=== GuildQuest Main Menu (User: " + currentUserRef[0].name + ") ===";

            LinkedHashMap<Integer, String> mainLabels = new LinkedHashMap<>();
            mainLabels.put(1, "Campaign Menu");
            mainLabels.put(2, "Quest Menu");
            mainLabels.put(3, "Character / Inventory Menu");
            mainLabels.put(4, "Switch User");
            mainLabels.put(5, "Exit");

            LinkedHashMap<Integer, Command> mainActions = new LinkedHashMap<>();

            // 1) Campaign Menu
            mainActions.put(1, () -> {
                String title = "--- Campaign Menu (User: " + currentUserRef[0].name + ") ---";
                LinkedHashMap<Integer, String> labels = new LinkedHashMap<>();
                labels.put(1, "Create Campaign");
                labels.put(2, "Delete Campaign");
                labels.put(3, "List My Visible Campaigns");
                labels.put(4, "Toggle Public/Private");
                labels.put(5, "Back");

                LinkedHashMap<Integer, Command> actions = new LinkedHashMap<>();

                // === A3 CHANGES: Refactoring #1 (Extract Method) ===
                actions.put(1, () -> handleCreateCampaign(sc, campaignManager, currentUserRef[0]));

                // === A3 CHANGES: Refactoring #1 (Extract Method) ===
                actions.put(2, () -> handleDeleteCampaign(sc, campaignManager, currentUserRef[0]));

                // === A3 CHANGES: Refactoring #1 (Extract Method) ===
                actions.put(3, () -> handleListVisibleCampaigns(campaignManager, currentUserRef[0]));

                // === A3 CHANGES: Refactoring #1 (Extract Method) ===
                actions.put(4, () -> handleToggleCampaignVisibility(sc, campaignManager, currentUserRef[0]));

                runMenuLoop(sc, title, labels, actions, 5);
            });

            // 2) Quest Menu
            mainActions.put(2, () -> {
                String title = "--- Quest Menu (User: " + currentUserRef[0].name + ") ---";
                LinkedHashMap<Integer, String> labels = new LinkedHashMap<>();
                labels.put(1, "Add Quest Event to Campaign");
                labels.put(2, "List Quest Events in Campaign");
                labels.put(3, "Remove Quest Event from Campaign");
                labels.put(4, "Back");

                LinkedHashMap<Integer, Command> actions = new LinkedHashMap<>();

                actions.put(1, () -> {
                    int campaignId = readInt(sc, "Enter campaign ID: ");

                    Campaign camp = findCampaign(campaignManager, campaignId);
                    if (camp == null) {
                        System.out.println("Campaign not found.");
                        return;
                    }

                    // === A3 CHANGES: Refactoring #2 (Replace Primitive/String with Enum) ===
                    // Kept explicit in UI for clarity / easy tracing during execution.
                    Role role = campaignManager.getRole(currentUserRef[0], campaignId);
                    if (!camp.publicc && role == Role.NONE) {
                        System.out.println("You do not have access to this campaign.");
                        return;
                    }

                    // === A3 CHANGES START: AI-Assisted Refactoring (Move Method) ===
                    // Prefer asking CampaignManager for a viewable campaign (centralized policy + lookup).
                    Campaign viewable = campaignManager.getViewableCampaignOrNull(currentUserRef[0], campaignId);
                    if (viewable == null) {
                        System.out.println("You do not have access to this campaign.");
                        return;
                    }
                    // Use the viewable campaign object from CampaignManager.
                    camp = viewable;

                    // NOTE: Previously we also directly checked canViewCampaign(...) here.
                    // That check is redundant because getViewableCampaignOrNull(...) already uses canViewCampaign(...).
                    // if (!campaignManager.canViewCampaign(currentUserRef[0], campaignId)) {
                    //     System.out.println("You do not have access to this campaign.");
                    //     return;
                    // }
                    // === A3 CHANGES END: AI-Assisted Refactoring (Move Method) ===

                    String name = readLine(sc, "Quest name: ");
                    int start = readInt(sc, "Start time (minutes): ");
                    int end = readInt(sc, "End time (minutes, use -1 if none): ");

                    // === A3 CHANGES: Factory Pattern (Create QuestEvent) ===
                    QuestEvent qe = GameObjectFactory.createQuestEvent(name, start, end, null, currentUserRef[0]);

                    camp.addQuest(qe);
                    questManager.addEvent(qe);
                    System.out.println("Quest event added to campaign " + campaignId + ".");
                });

                actions.put(2, () -> {
                    int campaignId = readInt(sc, "Enter campaign ID: ");

                    Campaign camp = findCampaign(campaignManager, campaignId);
                    if (camp == null) {
                        System.out.println("Campaign not found.");
                        return;
                    }

                    // === A3 CHANGES: Refactoring #2 (Replace Primitive/String with Enum) ===
                    // Kept explicit in UI for clarity / easy tracing during execution.
                    Role role = campaignManager.getRole(currentUserRef[0], campaignId);
                    if (!camp.publicc && role == Role.NONE) {
                        System.out.println("You do not have access to this campaign.");
                        return;
                    }

                    // === A3 CHANGES START: AI-Assisted Refactoring (Move Method) ===
                    // Prefer asking CampaignManager for a viewable campaign (centralized policy + lookup).
                    Campaign viewable = campaignManager.getViewableCampaignOrNull(currentUserRef[0], campaignId);
                    if (viewable == null) {
                        System.out.println("You do not have access to this campaign.");
                        return;
                    }
                    camp = viewable;

                    // NOTE: Redundant if kept in addition to getViewableCampaignOrNull(...)
                    // if (!campaignManager.canViewCampaign(currentUserRef[0], campaignId)) {
                    //     System.out.println("You do not have access to this campaign.");
                    //     return;
                    // }
                    // === A3 CHANGES END: AI-Assisted Refactoring (Move Method) ===

                    if (camp.questEvents.isEmpty()) {
                        System.out.println("(No quest events yet)");
                    } else {
                        for (int i = 0; i < camp.questEvents.size(); i++) {
                            System.out.println(i + ": " + camp.questEvents.get(i));
                        }
                    }
                });

                actions.put(3, () -> {
                    int campaignId = readInt(sc, "Enter campaign ID: ");

                    Campaign camp = findCampaign(campaignManager, campaignId);
                    if (camp == null) {
                        System.out.println("Campaign not found.");
                        return;
                    }

                    // === A3 CHANGES: Refactoring #2 (Replace Primitive/String with Enum) ===
                    // Kept explicit in UI for clarity / easy tracing during execution.
                    Role role = campaignManager.getRole(currentUserRef[0], campaignId);
                    if (!camp.publicc && role == Role.NONE) {
                        System.out.println("You do not have access to this campaign.");
                        return;
                    }

                    // === A3 CHANGES START: AI-Assisted Refactoring (Move Method) ===
                    // Prefer asking CampaignManager for a viewable campaign (centralized policy + lookup).
                    Campaign viewable = campaignManager.getViewableCampaignOrNull(currentUserRef[0], campaignId);
                    if (viewable == null) {
                        System.out.println("You do not have access to this campaign.");
                        return;
                    }
                    camp = viewable;

                    // NOTE: Redundant if kept in addition to getViewableCampaignOrNull(...)
                    // if (!campaignManager.canViewCampaign(currentUserRef[0], campaignId)) {
                    //     System.out.println("You do not have access to this campaign.");
                    //     return;
                    // }
                    // === A3 CHANGES END: AI-Assisted Refactoring (Move Method) ===

                    if (camp.questEvents.isEmpty()) {
                        System.out.println("(No quest events to remove)");
                        return;
                    }

                    int index = readInt(sc, "Enter quest index to remove: ");
                    if (index < 0 || index >= camp.questEvents.size()) {
                        System.out.println("Invalid index.");
                        return;
                    }

                    QuestEvent removed = camp.questEvents.get(index);
                    camp.questEvents.remove(index);

                    int qmIndex = questManager.questEvents.indexOf(removed);
                    if (qmIndex >= 0) questManager.removeEvent(qmIndex);

                    System.out.println("Removed quest: " + removed.name);
                });

                runMenuLoop(sc, title, labels, actions, 4);
            });

            // 3) Character / Inventory Menu
            mainActions.put(3, () -> {
                String title = "--- Character / Inventory Menu (User: " + currentUserRef[0].name + ") ---";
                LinkedHashMap<Integer, String> labels = new LinkedHashMap<>();
                labels.put(1, "Create Character");
                labels.put(2, "List Characters");
                labels.put(3, "Set Current Character");
                labels.put(4, "Add Item to Current Character");
                labels.put(5, "View Current Character Inventory");
                labels.put(6, "Back");

                LinkedHashMap<Integer, Command> actions = new LinkedHashMap<>();

                actions.put(1, () -> {
                    String cname = readLine(sc, "Character name: ");
                    String cclass = readLine(sc, "Class: ");
                    int level = readInt(sc, "Level: ");

                    // === A3 CHANGES: Factory Pattern (Create Character) ===
                    Character character = GameObjectFactory.createCharacter(cname, cclass, level);

                    currentUserRef[0].createCharacter(character);
                    System.out.println("Character created.");
                });

                actions.put(2, () -> listCharacters(currentUserRef[0]));

                actions.put(3, () -> {
                    listCharacters(currentUserRef[0]);
                    int idx = readInt(sc, "Enter character index: ");
                    if (idx < 0 || idx >= currentUserRef[0].characters.size()) {
                        System.out.println("Invalid index.");
                        return;
                    }
                    currentUserRef[0].currentCharacter = currentUserRef[0].characters.get(idx);
                    System.out.println("Current character set to: " + currentUserRef[0].currentCharacter);
                });

                actions.put(4, () -> {
                    if (currentUserRef[0].currentCharacter == null) {
                        System.out.println("No current character. Create one first.");
                        return;
                    }

                    String itemName = readLine(sc, "Item name: ");
                    int rarity = readInt(sc, "Rarity: ");
                    String type = readLine(sc, "Type: ");
                    String desc = readLine(sc, "Description: ");
                    int qty = readInt(sc, "Quantity: ");

                    // === A3 CHANGES: Factory Pattern (Create Item) ===
                    Item item = GameObjectFactory.createItem(itemName, rarity, type, desc, qty);

                    currentUserRef[0].currentCharacter.addItem(item);
                    System.out.println("Item added to " + currentUserRef[0].currentCharacter.name + "'s inventory.");
                });

                actions.put(5, () -> {
                    if (currentUserRef[0].currentCharacter == null) {
                        System.out.println("No current character selected.");
                        return;
                    }
                    System.out.println("Current character: " + currentUserRef[0].currentCharacter);
                    System.out.println("Inventory: " + currentUserRef[0].currentCharacter.inventory);
                });

                runMenuLoop(sc, title, labels, actions, 6);
            });

            int mainChoice = promptMenu(sc, mainTitle, mainLabels);

            if (mainChoice == 5) {
                System.out.println("Exiting GuildQuest.");
                break;
            }

            if (mainChoice == 4) {
                String newUsername = readLine(sc, "Enter username to switch to: ").trim();
                if (newUsername.isEmpty()) {
                    System.out.println("Username cannot be empty.");
                    continue;
                }
                currentUserRef[0] = getOrCreateUser(usersByName, newUsername, nextUid);
                System.out.println("Switched to user: " + currentUserRef[0].name);
                continue;
            }

            Command cmd = mainActions.get(mainChoice);
            if (cmd == null) {
                System.out.println("Invalid option.");
            } else {
                cmd.execute();
            }
        }
        // === A3 CHANGES END: Command Pattern (Main Menu -> Commands) ===

        sc.close();
    }
}