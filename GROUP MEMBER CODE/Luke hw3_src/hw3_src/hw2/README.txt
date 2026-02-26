Java Version: 25.1.3 (latest)

The program has multiple input-command areas:

MAIN MENU
This area is entered automatically upon executing the program. There are multiple commands, each labeled
with a number. This number corresponds to the command the user wishes to perform. For example, the main menu
may look like

MAIN MENU - World Clock (0:0:0)
--------------------
0: Select User
1: Add User
2: Remove User

The program will prompt the user to enter a number corresponding to a command, looping until a valid command is chosen. (Note: on the main menu, the Select User and Remove User commands cannot be entered until a user has been added via the Add User command).

SELECT USERS
Upon entering the number associated with the Select User command, a menu that looks like this will appear:

USERS
--------------------
0: #0: exampleuser - example@uci.edu

Similarly to the main menu, enter the number associated with the user relative to the menu. This is the number on the far left, not the number proceeding the #. The # is just the ID of the user, it is not used for selection in this case.

USER MENU
This menu is entered after the program user adds and selects a user they want to use. The command menu will look like this:

{USERNAME} - World Clock (0.0.0) - {currentRealm.Name} (0.0.0)
--------------------
0: Add Campaign
1: Edit Campaign
2: Enter Realm
3: Add Character
4: Change Character
5: Edit Settings
6: Exit to Main Menu

This menu functions identically to the main menu, entering the number of the command the user wants to execute.
Entering the number 6 will bring you back to the main menu.

EDIT CAMPAIGN MENU
This menu functions identically to the menu for selecting a user. Upon entering the number for executing the command, the list of all existing campaigns pops up, and the user enters the number of the campaign they wish to
edit, which then prompts them to enter a new name for the campaign and new visibility status (public or private).

ENTER REALM MENU
This menu functions identically to the menu for selecting a user. Upon entering the number for executing the command, the list of all existing realms pops up, and the user enters the number of the realm they wish to
enter. This changes the name of the realm on the user menu as well.

CHANGE CHARACTER MENU
This menu functions identically to the menu for selecting a user. Upon entering the number for executing the command, the list of all existing characters associated with the user pops up, and the user enters the number of the character they wish to use.

Design Patterns Used in Refactoring
------------------------------------
1. Strategy (for the UI)
2. Facade (UserManager, CampaignManager, CharacterManager)
3. Singleton (GuildQuest, UserManager)