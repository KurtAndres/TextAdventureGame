package game;
/**
 * 
 */


import java.util.*;

/**
 * @author josh.l.wright
 * 
 */
public abstract class Item implements Cloneable{
	protected Player p;
	protected int count =1;
	protected int value;
	public Item(Player p) {
		this.p = p;
	}
	public Item(Player p, int count){
		this.p = p;
		this.count = count;
	}
	
	protected String name;

	protected static HashMap<String, Item> allItems = new HashMap<String, Item>();

	public abstract int use();

	public abstract String describe();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public static HashMap<String, Item> getItems(Player p) {
		return allItems;
	}
	
	//Called in Game constructor
	public static void fillAllItems(Player p){
		Weapon.addItems(p);
		Armor.addItems(p);
		Consumable.addItems(p);
		Key.addKeys(p);
		JournalPage.addItems(p);
		
	}

}
