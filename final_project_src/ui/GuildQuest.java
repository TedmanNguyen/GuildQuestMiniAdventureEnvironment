package ui;

import java.util.ArrayList;

public class GuildQuest {
    private static GuildQuest instance = null;
    private CharacterManager characterManager;
    private UserInterface ui;

    private GuildQuest() {
        characterManager = new CharacterManager();
        ui = createUI();
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
    }
}
