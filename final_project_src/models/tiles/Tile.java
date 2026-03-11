package models.tiles;

import models.Characters;
import models.Position;

public abstract class Tile {

    protected String currentCharacter;
    private Position position;
    public abstract void stepOn(Characters c);
    abstract public void stepOff();
}