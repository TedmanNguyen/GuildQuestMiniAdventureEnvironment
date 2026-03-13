package models.tiles;

import models.Characters;
import models.Position;

public class EmptyTile extends Tile {
    private static TileCharacter character = new TileCharacter(' ');

    public EmptyTile(Position position) {
        currentCharacter.updateCharacter(character);
        this.position = position;
    }

    public void stepOn(Characters c) {
        currentCharacter.updateCharacter(c.tileCharacter());
    }

    public void stepOff() {
        currentCharacter.updateCharacter(character);
    }

    public String toString() {
        return currentCharacter.toString();
    }
}