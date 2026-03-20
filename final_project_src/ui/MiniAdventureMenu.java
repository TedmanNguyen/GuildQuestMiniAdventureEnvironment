package ui;

import java.util.List;

import models.Characters;
import models.adventure.MazeGame;
import models.adventure.ReliconGame;

/**
 * Simple menu to select and launch a specific mini-adventure.
 */
public class MiniAdventureMenu implements UserInterface {

    private final List<Characters> players;

    public MiniAdventureMenu(List<Characters> players) {
        this.players = players;
    }

    private void printMenu() {
        System.out.println("\n=== MINI ADVENTURES ===");
        System.out.println("0: Back");
        System.out.println("1: Maze Game");
        System.out.println("2: Relicon Game");
    }

    @Override
    public void commandLoop() {
        while (true) {
            printMenu();
            int selection = InputManager.getIntInput("Select choice: ");

            if (selection == 0) {
                // Return to caller (SelectionMenu)
                break;
            }

            if (players == null || players.isEmpty()) {
                System.out.println("No characters available. Please create characters first.");
                break;
            }

            Characters startingPlayer = players.get(0);

            if (selection == 1) {
                new MazeGame(players).play(startingPlayer);
            } else if (selection == 2) {
                new ReliconGame(players).play(startingPlayer);
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}

