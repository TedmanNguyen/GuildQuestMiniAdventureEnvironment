package models.tiles;

import models.Characters;

public class SafeTile extends Tile {
    private static String character = " ";

    public SafeTile() {}

    public void stepOn(Characters c) {
        currentCharacter = character;
    }

    public void stepOff() {
        currentCharacter = character;
    }

    public String toString() {
        return currentCharacter;
    }
}