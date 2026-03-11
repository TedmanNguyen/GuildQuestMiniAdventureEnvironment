package models.tiles;
import models.Characters;
import models.MiniAdventure;

public class MiniAdventurePortalTile extends Tile {
    private static String character = "O";
    private MiniAdventure miniAdventure;

    public MiniAdventurePortalTile(MiniAdventure miniAdventure) {
        this.miniAdventure = miniAdventure;
        currentCharacter = character;
    }

    public void stepOn(Characters c) {
        miniAdventure.launch();
    }

    public void stepOff() {
        currentCharacter = character;
    }

    public String toString() {
        return currentCharacter;
    }
}