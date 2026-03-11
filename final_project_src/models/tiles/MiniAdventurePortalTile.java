package models.tiles;
import models.Characters;
import models.MiniAdventure;

public class MiniAdventurePortalTile extends Tile {
    private static TileCharacter character = new TileCharacter('O');
    private MiniAdventure miniAdventure;

    public MiniAdventurePortalTile(MiniAdventure miniAdventure) {
        this.miniAdventure = miniAdventure;
        currentCharacter = new TileCharacter(character);
    }

    public void stepOn(Characters c) {
        currentCharacter.updateCharacter(c.tileCharacter());
        miniAdventure.launch();
    }

    public void stepOff() {
        currentCharacter.updateCharacter(character);
    }

    public String toString() {
        return currentCharacter.toString();
    }
}