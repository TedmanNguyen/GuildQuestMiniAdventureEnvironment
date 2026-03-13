package models.tiles;

import models.MiniAdventure;
import models.Characters;
import models.Position;

/**
 * This class corresponds to the tile in the Invisible Maze MiniAdventure that is not apart
 * of the path. Stepping on it sends the character back to the beginning / spawn.
 */
public class ResetTile extends Tile {
    private static TileCharacter character = new TileCharacter(' ');
    private MiniAdventure miniAdventure;

    public ResetTile(Position position, MiniAdventure miniAdventure) {
        this.position = position;
        this.miniAdventure = miniAdventure;
        currentCharacter = new TileCharacter(character);
    }

    /**
     * This method will set the Character's position to be the spawn point of the MiniAdventure
     */
    public void stepOn(Characters c) {
        return;
    }

    /**
     * The stepOff method will not have to do anything here because a Character cannot stepOff
     * the tile, as their Position is changed in the stepOn method.
     */
    public void stepOff() {
        return;
    }

    public String toString() {
        return currentCharacter.toString();
    }
}
