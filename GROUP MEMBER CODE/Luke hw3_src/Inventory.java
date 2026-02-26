package hw2;

import java.util.ArrayList;

public class Inventory {
	private static int defaultCapacity;
	private int capacity;
	// new member variables
	private ArrayList<Item> items;
	
	public Inventory(int capacity) {
		this.capacity = capacity;
		items = new ArrayList<Item>(capacity);
	}
	
	public void addItem(Item item) {
		if (items.size() < capacity)
			items.add(item);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}
	
	public String toString() {
		String result = "[\n";
		for (Item i : items)
			result += i + "\n";
		return result + "]";
	}
	
	public static int getDefaultCapacity() { return defaultCapacity; }
}