package models.adventure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import models.Characters;
import models.Position;

/**
 * Relicon: 10x10 grid, fog-of-war radius 1, collect E/X/I/T to win. Supports two players visible and switchable via P<id>.
 * Recommendation: Co-Op Gameplay. Switch players upon finding a relic.
 */
public class ReliconGame {
    private static final int SIZE = 10;
    private static final char EMPTY = ' ';
    private final Random random;
    private final List<Characters> players;

    public ReliconGame(List<Characters> players) {
        this(players, new Random());
    }

    public ReliconGame(List<Characters> players, Random random) {
        this.random = random;
        this.players = players;
    }

    public void play(Characters startingPlayer) {
        Position activePos = new Position(startingPlayer.y, startingPlayer.x);
        Characters active = startingPlayer;
        Map<Position, Character> items = spawnItems();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entering Relicon. Collect E, X, I, T. 'q' to leave. Use P<id> to switch player.");

        boolean running = true;
        while (running) {
            render(activePos, items, active.getId());
            System.out.print("Move (w/a/s/d), switch P<id>, or quit: ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            if (line.equalsIgnoreCase("q")) {
                System.out.println("Leaving Relicon. Back to menu.");
                break;
            }

            if (line.matches("(?i)p\\d+")) {
                int id = Integer.parseInt(line.substring(1));
                Characters found = players.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
                if (found != null) {
                    active = found;
                    activePos = new Position(active.y, active.x);
                    System.out.println("Switched to player " + id + "\n");
                } else {
                    System.out.println("No player with id " + id + "\n");
                }
                continue;
            }

            char input = Character.toLowerCase(line.charAt(0));
            Position next = activePos.move(input);
            if (!inBounds(next)) {
                System.out.println("Blocked by boundary.\n");
                continue;
            }

            activePos = next;
            active.x = activePos.getCol();
            active.y = activePos.getRow();

            if (items.remove(activePos) != null) {
                System.out.println("Picked up an item! Remaining: " + items.size());
            }

            if (items.isEmpty()) {
                render(activePos, items, active.getId());
                System.out.println("You collected all items! You win!\n");
                running = false;
            }
        }
    }

    private boolean inBounds(Position pos) {
        return pos.getRow() >= 0 && pos.getRow() < SIZE && pos.getCol() >= 0 && pos.getCol() < SIZE;
    }

    private Map<Position, Character> spawnItems() {
        Map<Position, Character> map = new HashMap<>();
        char[] letters = {'E', 'X', 'I', 'T'};
        int idx = 0;
        while (map.size() < 4) {
            int r = random.nextInt(SIZE);
            int c = random.nextInt(SIZE);
            Position p = new Position(r, c);
            boolean occupied = players.stream().anyMatch(pl -> pl.getYPosition() == r && pl.getXPosition() == c);
            if (occupied) continue;
            map.putIfAbsent(p, letters[idx % letters.length]);
            idx++;
        }
        return map;
    }

    private void render(Position activePos, Map<Position, Character> items, int activeId) {
        System.out.println("\nRELICON");
        for (int r = 0; r < SIZE; r++) {
            StringBuilder row = new StringBuilder();
            for (int c = 0; c < SIZE; c++) {
                Position p = new Position(r, c);
                int dist = Math.max(Math.abs(activePos.getRow() - r), Math.abs(activePos.getCol() - c));
                if (dist > 1) {
                    row.append(' '); // fog
                    continue;
                }
                Characters occupant = playerAt(p);
                if (occupant != null) {
                    row.append(Character.forDigit(occupant.getId() % 10, 10));
                } else if (items.containsKey(p)) {
                    row.append(items.get(p));
                } else {
                    row.append(EMPTY);
                }
            }
            System.out.println(row);
        }
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
