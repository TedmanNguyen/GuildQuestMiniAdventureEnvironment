public class Item {
    private static char characterId = 'a';

    public char id;

    public Item(int itemId){
        this.id = characterId++;
    }

    public String toString()
    {
        return "" + this.id;
    }
}
