package models.tiles;

import models.Characters;
import models.Position;

public class WallTile extends Tile {
    private static TileCharacter character = new TileCharacter('|');

    public WallTile(Position position) {
        this.position = position;
        currentCharacter = new TileCharacter(character);
    }

    public void stepOn(Characters c) {
        return;
    }

    public void stepOff() {
        return;
    }

    public String toString() {
        return currentCharacter.toString();
    }
}