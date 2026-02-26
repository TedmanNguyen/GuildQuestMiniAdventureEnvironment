public class Item {
    private int itemId;
    private String name;
    private Rarity rarity;
    private String description;

    public Item(int itemId, String name, Rarity rarity, String description){
        this.itemId = itemId;
        this.name = name;
        this.rarity = rarity;
        this.description = description;
    }

    void getItemInfo(){
        System.out.println("Item info: " + name + description);
    }
}
