package models;

import java.util.Scanner;
import java.util.List;

public class TrophyRealm extends Realm {
    private TrophyGrid grid;
    private List<Characters> players;

    public TrophyRealm(List<Characters> players) {
        // Reuses Realm constructor: Name, Description, and a 5-hour time offset
        super("Hall of Relics", "A trophy room of your achievements.", 5);
        this.players = players;
        this.grid = new TrophyGrid(players.get(0), players.get(1));
    }

    public void enter() {
        Scanner scanner = new Scanner(System.in);
        Characters active = players.get(0);
        
        while (true) {
            //Displaying the realm-specific local time
            System.out.println("\n--- " + getName() + " ---");
            System.out.println("Local Time: " + getLocalTime().toString()); 
            
            grid.displayGrid(); // Reusing Grid's display logic
            
            System.out.print(active.getCharacterName() + " (w/a/s/d), Switch (P<id>), or Quit (q): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("q")) break;

            // Movement from Mini adventure
            if (input.matches("p\\d+")) {
                int id = Integer.parseInt(input.substring(1));
                active = players.stream().filter(p -> p.getId() == id).findFirst().orElse(active);
            } else if (!input.isEmpty()) {
                handleMovement(active, input.charAt(0));
            }
        }
    }

    private void handleMovement(Characters p, char dir) {
        // Implementation uses p.x/p.y and Grid tiles to update position
    }
}