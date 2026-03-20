package main;

import ui.GuildQuest;

public class menu {
    public static void main(String[] args) {
        // This retrieves the singleton instance of the game manager
        GuildQuest game = GuildQuest.getInstance();
        
        // This starts the game loop which delegates to the UI command loop
        game.gameLoop();
    }
}