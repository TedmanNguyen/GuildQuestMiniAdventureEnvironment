package models.tiles;

import models.Characters;
import models.Item;
import models.Position;

public class ItemTile extends Tile {
    private Item item;
    private Position position;

    public ItemTile(Position position, Item item) {
        this.item = item;
        this.position = position;
        currentCharacter = item.toString();
    }

    public void stepOn(Characters c) {
        if (item != null)       // null check makes sure user doesnt pick up item twice
            c.getInventory().addItem(item);
        item = null;
        currentCharacter = c.toString();
    }

    public void stepOff() {
        currentCharacter = " ";     // blank once the item is gone
    }
}