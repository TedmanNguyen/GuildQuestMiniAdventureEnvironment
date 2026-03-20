package ui;

import java.util.List;
import java.util.Scanner;

import models.Characters;
import models.Position;
import models.adventure.MazeGame;
import models.adventure.ReliconGame;

/**
 * Console grid menu: 10x10, WASD movement. 'M' to launch Maze, 'R' to launch Relicon.
 */
public class GridMenu {
    private static final int SIZE = 10;
    private final Position mazePos = new Position(2, 2);
    private final Position relicPos = new Position(2, SIZE - 3);

    private final List<Characters> players;
    private Characters active;
    private Position activePos;

    public GridMenu(List<Characters> players) {
        this.players = players;
        this.active = players.isEmpty() ? null : players.get(0);
        this.activePos = new Position(SIZE / 2, SIZE / 2);
        // initialize positions on players for rendering
        for (int i = 0; i < players.size(); i++) {
            Characters p = players.get(i);
            p.x = activePos.getCol();
            p.y = activePos.getRow();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            render();
            System.out.print("Move (w/a/s/d), switch P<id>, or q to quit: ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            if (line.equalsIgnoreCase("q") || line.equalsIgnoreCase("quit")) break;

            if (line.matches("(?i)p\\d+")) {
                int id = Integer.parseInt(line.substring(1));
                Characters found = players.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
                if (found != null) {
                    active = found;
                    activePos = new Position(active.y, active.x); // sync to current coords
                    System.out.println("Switched to player " + id + "\n");
                } else {
                    System.out.println("No player with id " + id + "\n");
                }
                continue;
            }

            char input = Character.toLowerCase(line.charAt(0));
            Position next = activePos.move(input);
            if (!inBounds(next)) {
                System.out.println("Can't move there.\n");
                continue;
            }
            activePos = next;
            active.x = next.getCol();
            active.y = next.getRow();

            if (activePos.equals(mazePos)) {
                new MazeGame(players).play(active); // pass both players, active starts
                resetPlayers();
            } else if (activePos.equals(relicPos)) {
                new ReliconGame(players).play(active); // pass both players, active starts
                resetPlayers();
            }
        }
    }

    private void resetPlayers() {
        for (Characters p : players) {
            p.x = SIZE / 2;
            p.y = SIZE / 2;
        }
        activePos = new Position(SIZE / 2, SIZE / 2);
    }

    private boolean inBounds(Position pos) {
        return pos.getRow() >= 0 && pos.getRow() < SIZE && pos.getCol() >= 0 && pos.getCol() < SIZE;
    }

    private void render() {
        System.out.println("\nMAIN GRID MENU");
        for (int r = 0; r < SIZE; r++) {
            StringBuilder row = new StringBuilder();
            for (int c = 0; c < SIZE; c++) {
                Position p = new Position(r, c);
                if (p.equals(mazePos)) {
                    row.append('M');
                } else if (p.equals(relicPos)) {
                    row.append('R');
                } else {
                    Characters occupant = playerAt(p);
                    if (occupant != null) {
                        row.append(Character.forDigit(occupant.getId() % 10, 10));
                    } else {
                        row.append('.');
                    }
                }
            }
            System.out.println(row);
        }
        System.out.println("Active player: " + (active != null ? active.getId() : "none"));
    }

    private Characters playerAt(Position pos) {
        for (Characters p : players) {
            if (p.getYPosition() == pos.getRow() && p.getXPosition() == pos.getCol()) {
                return p;
            }
        }
        return null;
    }
}
