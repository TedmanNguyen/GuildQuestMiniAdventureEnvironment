package models;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ui.GuildQuest;
import models.adventure.MazeGame;
import models.adventure.ReliconGame;

public class Realm implements LocalTimeRule {

    // Extendible set of realms
    public static ArrayList<Realm> realms = new ArrayList<Realm>(Arrays.asList(
            new Realm("Irvine", "A city in California", 1),
            new Realm("Los Angeles", "A city in California", 2),
            new Realm("Laguna Beach", "A city in California", 3)
    ));

    private static int realmCount = 0;


    private int realmId;	// changed from design (String -> int)
    private String name;
    private String description;
    private int timeOffset; // change to timeOffset

    public Realm(String name, String description, int timeOffset) {
        realmId = realmCount++;
        this.name = name;
        this.description = description;
        this.timeOffset = timeOffset;
    }

    public void enter(List<Characters> players) {
        Scanner scanner = new Scanner(System.in);
        Characters active = players.get(0);
        int size = 10;
        
        // Portal positions
        Position portalPos = new Position(size / 2, size / 2);
        char portalChar = 'O';
        if (name.equals("Irvine")) portalChar = 'M';
        else if (name.equals("Los Angeles")) portalChar = 'R';

        while (true) {
            System.out.println("\n--- " + getName() + " ---");
            System.out.println("Local Time: " + getLocalTime().toString());
            
            // Render basic grid with specific portal character
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    Position p = new Position(r, c);
                    boolean playerHere = false;
                    for (Characters pl : players) {
                        if (pl.y == r && pl.x == c) {
                            System.out.print(pl.getId() % 10);
                            playerHere = true;
                            break;
                        }
                    }
                    if (!playerHere) {
                        if (p.equals(portalPos)) System.out.print(portalChar);
                        else System.out.print(".");
                    }
                }
                System.out.println();
            }

            System.out.print(active.getCharacterName() + " (w/a/s/d), Switch (P<id>), or Quit (q): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("q")) break;

            if (input.matches("p\\d+")) {
                int id = Integer.parseInt(input.substring(1));
                Characters found = null;
                for (Characters pl : players) if (pl.getId() == id) found = pl;
                if (found != null) active = found;
            } else if (!input.isEmpty()) {
                char move = input.charAt(0);
                int nextY = active.y;
                int nextX = active.x;
                if (move == 'w') nextY--;
                else if (move == 's') nextY++;
                else if (move == 'a') nextX--;
                else if (move == 'd') nextX++;

                if (nextY >= 0 && nextY < size && nextX >= 0 && nextX < size) {
                    active.y = nextY;
                    active.x = nextX;
                }

                if (active.y == portalPos.getRow() && active.x == portalPos.getCol()) {
                    System.out.println("Entering portal...");
                    if (name.equals("Irvine")) {
                        new MazeGame(players).play(active);
                    } else if (name.equals("Los Angeles")) {
                        new ReliconGame(players).play(active);
                    } else {
                        System.out.println("This portal (" + portalChar + ") is currently under construction.");
                    }
                    // Reset positions after returning from game
                    active.y = 0; active.x = 0;
                }
            }
        }
    }

    public GameTime getLocalTime() { return calculate(); }

    public GameTime calculate() {
        return GuildQuest.getWorldClock().getCurrentTime().applyOffset(timeOffset);
    }
    // added methods
    public String toString() {
        return "#" + realmId + ": " + name + " - " + "\"" + description + "\"" + getLocalTime();
    }

    public String getName() { return name; }
    public static int getNumRealms() { return realms.size(); }

    public static void printRealms() {
        System.out.println("REALMS");
        System.out.println("--------------------");
        for (int i = 0; i < realms.size(); ++i)
            System.out.println(i + ": " + realms.get(i));
        System.out.println();
    }
}