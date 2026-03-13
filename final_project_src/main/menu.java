package main;

import ui.GuildQuest;

public class menu {
    public static void main(String[] args) {
        // Retrieve the singleton instance of the game manager
        GuildQuest game = GuildQuest.getInstance();
        
        // Start the game loop which delegates to the UI command loop
        game.gameLoop();
    }
}