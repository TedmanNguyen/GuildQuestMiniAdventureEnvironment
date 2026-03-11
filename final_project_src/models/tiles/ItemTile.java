package models.tiles;

import models.Characters;
import models.Item;
import models.Position;

public class ItemTile extends Tile {
    private Item item;

    public ItemTile(Position position, Item item) {
        this.item = item;
        this.position = position;
        currentCharacter = new TileCharacter(item.tileCharacter());
    }

    public void stepOn(Characters c) {
        if (item != null)       // null check makes sure user doesnt pick up item twice
            c.getInventory().addItem(item);
        item = null;            // set item to null after being picked up
        currentCharacter.updateCharacter(c.tileCharacter());
    }

    public void stepOff() {
        currentCharacter = new TileCharacter(' ');     // blank once the item is gone
    }
}