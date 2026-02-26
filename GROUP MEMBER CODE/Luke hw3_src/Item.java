package hw2;

public class Item {
	
	private String name;
	private String description;
	private String rarity;
	
	public Item(String name, String description, String rarity) {
		this.name = name;
		this.description = description;
		this.rarity = rarity;
	}
	
	//new methods
	public boolean equals(Object o) {
		if (o instanceof Item) {
			Item o2 = (Item)o;
			return name.equals(o2.name) && 
					description.equals(o2.description) &&
					rarity.equals(o2.rarity);
		}
		return false;
	}
	
	public String toString() {
		return name + " : " + description + " : " + rarity.toUpperCase();
	}
}