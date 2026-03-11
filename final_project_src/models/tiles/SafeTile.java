package models.tiles;

import models.Characters;
import models.Position;

public class SafeTile extends Tile {
    private static TileCharacter beforeSteppedOnCharacter = new TileCharacter(' ');
    private static TileCharacter afterSteppedOffCharacter = new TileCharacter('X');

    public SafeTile(Position position) {
        this.position = position;
        currentCharacter = new TileCharacter(beforeSteppedOnCharacter);
    }

    public void stepOn(Characters c) {
        currentCharacter.updateCharacter(c.tileCharacter());
    }

    public void stepOff() {
        currentCharacter.updateCharacter(afterSteppedOffCharacter);
    }

    public String toString() {
        return currentCharacter.toString();
    }
}