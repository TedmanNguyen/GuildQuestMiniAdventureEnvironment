package models;

import models.tiles.TileCharacter;

public class Item {
    private static char characterId = 'a';

    private TileCharacter tileCharacter;

    public Item(){
        tileCharacter = new TileCharacter(characterId++);
    }

    public String toString()
    {
        return tileCharacter.toString();
    }

    public TileCharacter tileCharacter() { return tileCharacter; }
}
