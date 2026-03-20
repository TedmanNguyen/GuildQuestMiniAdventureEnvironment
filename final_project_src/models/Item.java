package models;
import models.tiles.TileCharacter;

public class Item {
    private TileCharacter tileCharacter;

    // Accepts letter from any type of Relic-hunt game
    public Item(char letter) {
        this.tileCharacter = new TileCharacter(letter);
    }

    public String toString() { return tileCharacter.toString(); }
    public TileCharacter tileCharacter() { return tileCharacter; }
}