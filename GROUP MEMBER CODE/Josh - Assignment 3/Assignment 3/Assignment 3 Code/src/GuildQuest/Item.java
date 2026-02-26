
package GuildQuest;

public class Item {
    public String name;
    public int rarity;
    public String type;
    public String description;
    public int quantity;

    public Item(String name, int rarity, String type, String description, int quantity) {
        this.name = name;
        this.rarity = rarity;
        this.type = type;
        this.description = description;
        this.quantity = quantity;
    }

    public void use() {
        if (quantity > 0) quantity--;
    }

    @Override
    public String toString() {
        return name + " x" + quantity + " (rarity " + rarity + ")";
    }
}
