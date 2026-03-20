package ui;

import java.util.ArrayList;

import models.WorldClock;

public class GuildQuest {
    private static GuildQuest instance = null;
    private static WorldClock worldClock = new WorldClock();
    private CharacterManager characterManager;
    private UserInterface ui;

    private GuildQuest() {
        characterManager = new CharacterManager();
        ui = createUI();
    }

    public static WorldClock getWorldClock() {
        return worldClock;
    }

    protected UserInterface createUI() {
        return new GuildQuestUI(this);
    }

    public static GuildQuest getInstance() {
        if (instance == null) {
            instance = new GuildQuest();
        }
        return instance;
    }

    public CharacterManager getCharacterManager() {
        return characterManager;
    }

    public void gameLoop() {
        ui.commandLoop();
        // AFTER 2 CHARACTERS CREATED COMMANDLOOP WILL END
        SelectionMenu menu = new SelectionMenu((GuildQuestUI) ui);
        menu.commandLoop();
    }
}
