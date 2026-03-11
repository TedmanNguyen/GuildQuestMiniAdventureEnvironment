package models.tiles;

import models.Characters;
import models.Position;

public abstract class Tile {

    protected TileCharacter currentCharacter;  // the character representation of the tile, appears on the grid (i.e., an empty tile would be " ")
    protected Position position;          // the position of the tile on the grid

    /**
    * @param c the character stepping on this tile
    * This method triggers the code that runs when a Character steps on a tile, which varies for each tile
    * for example, if this were a MiniAdventurePortalTile, the stepOn method would launch the MiniAdventure.
    * In most cases, this method will also update currentCharacter to be c.tileCharacter().
    */
    public abstract void stepOn(Characters c);
    
    /**
    * This method triggers the code that runs when a Character steps off a tile. In most cases,
    * this will change currentCharacter back to the original character associated with the Tile.
    */
    public abstract void stepOff();
}