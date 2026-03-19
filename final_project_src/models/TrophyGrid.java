package models;

import models.tiles.Tile;
import models.tiles.TileCharacter;

public class TrophyGrid extends Grid {
    public TrophyGrid(Characters p1, Characters p2) {
        super(15, 30, p1, p2); // Reusing Grid constructor
        populateTrophyText(p1, 2);
        populateTrophyText(p2, 8);
    }

    private void populateTrophyText(Characters p, int startRow) {
        String header = "{{" + p.getCharacterName() + "}}";
        char[] letters = {'E', 'X', 'I', 'T'};
        
        // This logic would ideally set static tiles in the Grid array
        // showing the name and the count for each letter
    }
}
