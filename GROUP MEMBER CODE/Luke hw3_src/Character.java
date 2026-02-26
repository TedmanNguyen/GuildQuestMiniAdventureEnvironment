package hw2;

public class Character {
	private static int characterCount = 0;
	private int characterID;
	private String name;
	private String heroClass;
	private int level;
	private Inventory inventory;
	
	public Character(String name, String heroClass) {
		this.name = name;
		this.heroClass = heroClass;
		level = 1;
		characterID = characterCount++;
		inventory = new Inventory(Inventory.getDefaultCapacity());
	}
	
	public void levelUp() { ++level; }
	
	public String toString() {
		return "#" + characterID + ": " + name + " - " + heroClass + " - LVL" + level; 
	}
	
	// setters and getters
	public String getName() { return name; }
	public void setName(String newName) { name = newName; } 
	public String getHeroClass() { return heroClass; }
	public void setHeroClass(String newClass) { heroClass = newClass; }
	public int getLevel() { return level; }
	public void addToInventory(Item i) { inventory.addItem(i); }
}