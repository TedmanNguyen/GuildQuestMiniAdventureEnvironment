
package GuildQuest;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    public List<Item> contents = new ArrayList<>();

    public Item addItem(Item item) {
        for (Item it : contents) {
            if (it.name.equals(item.name) && it.type.equals(item.type) && it.rarity == item.rarity) {
                it.quantity += item.quantity;
                return it;
            }
        }
        contents.add(item);
        return item;
    }

    public boolean removeItem(Item item) {
        return contents.remove(item);
    }

    public boolean updateItem(Item item) {
        int idx = contents.indexOf(item);
        if (idx >= 0) {
            contents.set(idx, item);
            return true;
        }
        return false;
    }

    @Override
    public String toString() { return contents.toString(); }
}
