
package GuildQuest;

public class Character {
    public String name;
    public String classType; // renamed to classType since class is reserved word
    public int level;
    public Inventory inventory;

    public Character(String name, String classType, int level) {
        this.name = name;
        this.classType = classType;
        this.level = level;
        this.inventory = new Inventory();
    }

    public void levelUp() { level++; }

    public Item addItem(Item item) {
        if (item == null) return null;
        return inventory.addItem(item);
    }
    public boolean removeItem(Item item) { return inventory.removeItem(item); }

    public boolean useItem(Item item) {
        if (!inventory.contents.contains(item)) return false;
        item.use();
        return true;
    }

    @Override
    public String toString() { return name + " (" + classType + " lvl " + level + ")"; }
}
