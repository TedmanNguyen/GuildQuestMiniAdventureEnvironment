package models.adventure;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import models.Characters;
import models.Position;

public class MazeGame {
    private static final int SIZE = 10;
    private final Random random;
    private final List<Characters> players;

    public MazeGame(List<Characters> players) {
        this(players, new Random());
    }

    public MazeGame(List<Characters> players, Random random) {
        this.random = random;
        this.players = players;
    }

    public void play(Characters startingPlayer) {
        Position spawn = new Position(SIZE / 2, SIZE / 2);
        Position exit = randomExit();
        Set<Position> path = generatePath(spawn, exit);
        Set<Position> visited = new HashSet<>();
        visited.add(spawn);

        // ensure all players start at spawn for this game instance
        for (Characters p : players) {
            p.x = spawn.getCol();
            p.y = spawn.getRow();
        }

        Characters active = startingPlayer;
        Position activePos = spawn;
        active.x = spawn.getCol();
        active.y = spawn.getRow();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Entering Maze. Find the invisible path to the exit (E). 'q' to leave. Use P<id> to switch player.");

        while (running) {
            render(activePos, visited, active.getId(), exit);
            System.out.print("Move (w/a/s/d), switch P<id>, or quit: ");
            String line = scanner.nextLine();
            if (line.isEmpty()) continue;
            if (line.equalsIgnoreCase("q") || line.equalsIgnoreCase("quit")) {
                System.out.println("Leaving Maze. Back to menu.");
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
                System.out.println("You hit the boundary and got sent back to spawn.\n");
                activePos = spawn;
                visited.add(spawn); // keep showing prior path after respawn
                continue;
            }

            if (!path.contains(next)) {
                System.out.println("You hit an invisible wall! Returning to spawn...\n");
                activePos = spawn;
                visited.add(spawn); // retain trail of explored tiles
                continue;
            }

            activePos = next;
            visited.add(activePos);
            active.x = activePos.getCol();
            active.y = activePos.getRow();

            if (activePos.equals(exit)) {
                render(activePos, visited, active.getId(), exit);
                System.out.println("You escaped the maze! Exit was at " + exit + "\n");
                running = false;
            }
        }
    }

    private boolean inBounds(Position pos) {
        return pos.getRow() >= 0 && pos.getRow() < SIZE && pos.getCol() >= 0 && pos.getCol() < SIZE;
    }

    private Position randomExit() {
        int side = random.nextInt(4);
        return switch (side) {
            case 0 -> new Position(0, random.nextInt(SIZE));          // top
            case 1 -> new Position(SIZE - 1, random.nextInt(SIZE));   // bottom
            case 2 -> new Position(random.nextInt(SIZE), 0);          // left
            default -> new Position(random.nextInt(SIZE), SIZE - 1);  // right
        };
    }

    private Set<Position> generatePath(Position start, Position exit) {
        Set<Position> path = new HashSet<>();
        path.add(start);
        Position current = start;

        // simple random-biased walk toward exit until reached
        while (!current.equals(exit)) {
            int dRow = Integer.compare(exit.getRow(), current.getRow());
            int dCol = Integer.compare(exit.getCol(), current.getCol());

            // 70% move toward exit, 30% random perpendicular
            if (random.nextInt(10) < 7) {
                if (random.nextBoolean() && dRow != 0 || dCol == 0) {
                    current = current.translate(dRow, 0);
                } else {
                    current = current.translate(0, dCol);
                }
            } else {
                // random perpendicular wiggle
                if (random.nextBoolean()) {
                    current = current.translate(0, random.nextBoolean() ? 1 : -1);
                } else {
                    current = current.translate(random.nextBoolean() ? 1 : -1, 0);
                }
            }

            // keep within bounds
            int r = Math.max(0, Math.min(SIZE - 1, current.getRow()));
            int c = Math.max(0, Math.min(SIZE - 1, current.getCol()));
            current = new Position(r, c);
            path.add(current);
        }

        return path;
    }

    private void render(Position player, Set<Position> visited, int playerId, Position exit) {
        System.out.println("\nMAZE");
        for (int r = 0; r < SIZE; r++) {
            StringBuilder row = new StringBuilder();
            for (int c = 0; c < SIZE; c++) {
                Position p = new Position(r, c);
                if (player.equals(p)) {
                    row.append(Character.forDigit(playerId % 10, 10));
                } else if (p.equals(exit)) {
                    row.append('E');
                } else if (visited.contains(p)) {
                    row.append('.');
                } else {
                    row.append(' ');
                }
            }
            System.out.println(row);
        }
    }
}
