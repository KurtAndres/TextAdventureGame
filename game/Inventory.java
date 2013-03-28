/**
 * 
 */
package game;

import java.util.HashMap;

/**
 * @author josh.l.wright
 * 
 */
public class Inventory { // please pass all methods that require strings
							// completely lower case arguments (.toLowerCase())
	private HashMap<String, Item> bag = new HashMap<String, Item>();
	private HashMap<String, Item> equipped = new HashMap<String, Item>();
	private int weapons = 0;
	private boolean head = true;
	private boolean chest = true;
	private boolean shield = true;
	private boolean legs = true;
	private boolean hands = true;
	private Player player;

	public Inventory(Player player) {
		this.player = player;
	}

	public boolean pickup(String item, Room room) {
		if (room.getItems().containsKey(item)) {
			if (searchBag(item) != null) {
				searchBag(item).count++;
				System.out.printf(item + " has been picked up. ");
				return true;
			} else {
				if(room.getItems().get(item) instanceof WinObject) player.won();
				bag.put(item, room.getItems().get(item));
				System.out.printf(item + " has been picked up. ");
				return true;
			}
		} else {
			System.out.println("There is no " + item + " in the room.");
			return false;
		}

	}

	public boolean equip(String item) {
		if (searchEquipped(item) != null) {
			System.out
					.println("You already have that item equipped! (can only have 1 item of each name equipped)");
			return false;
		} else if (searchBag(item) != null) {
			if (capacity(searchBag(item))) {
				equipped.put(item, searchBag(item));
				System.out.println(item + " equipped.");
				addStats(searchBag(item));
				return true;
			} else {
				if (searchBag(item) instanceof Consumable) {
					return false;
				}
				System.out
						.println("You already have the maximum of that type of item equipped. Unequip first.");
				return false;
			}

		} else {
			System.out.println("You don't have that item in your bag!");
			return false;
		}
	}

	private void addStats(Item item) {
		if (item instanceof Armor) {
			player.setDefense(item.value);
		} else { // Weapon
			player.setAttack(item.value);
		}
		player.stats();
	}

	private void subtractStats(Item item) {
		if (item instanceof Armor) {
			player.setDefense(-item.value);
		} else { // Weapon
			player.setAttack(-item.value);
		}
		player.stats();
	}

	public boolean drop(String item, Room room) {
		if (searchBag(item) != null) {
			if (searchBag(item).count > 1) {
				searchBag(item).count--;
				HashMap<String, Item> roomItems = room.getItems();
				Item clone = null;
				if (searchBag(item) instanceof Armor) {
					clone = new Armor(searchBag(item));
				} else if (searchBag(item) instanceof Weapon) {
					clone = new Weapon(searchBag(item));
				} else if (searchBag(item) instanceof Consumable) {
					clone = new Consumable(searchBag(item));
				} else
					;
				roomItems.put(clone.name, clone);
				room.setItems(roomItems);
				if (searchEquipped(item) != null) {
					unequip(item);
				}
				System.out.println(item + " dropped.");
				return true;
			} else {
				HashMap<String, Item> roomItems = room.getItems();
				roomItems.put(item, searchBag(item));
				room.setItems(roomItems);
				if (searchEquipped(item) != null) {
					unequip(item);
				}
				if(searchBag(item) instanceof WinObject) player.dropWon();
				bag.remove(item);
				System.out.println(item + " dropped.");
				return true;
			}
		} else {
			System.out.println("You don't have that item!");
			return false;
		}
	}

	public boolean unequip(String item) {
		if (searchEquipped(item) != null) {
			if (searchEquipped(item) instanceof Weapon) {
				weapons--;
			} else if (item.contains("legplates")) {
				legs = true;
			} else if (item.contains("breastplate")) {
				chest = true;
			} else if (item.contains("gauntlets")) {
				hands = true;
			} else if (item.contains("helmet")) {
				head = true;
			} else
				shield = true;
			equipped.remove(item);
			subtractStats(searchBag(item));
			System.out.println(item + " unequipped.");
			return true;
		} else {
			System.out.println("You don't have that item equipped!");
			return false;
		}
	}

	public boolean eat(String item) {
		if (searchBag(item) != null) {
			if (searchBag(item) instanceof Consumable) {
				player.addHealth(searchBag(item).value);
				if (searchBag(item).count > 1) {
					searchBag(item).count--;
				} else
					bag.remove(item);
				return true;
			} else {
				System.out.println("I seriously doubt you want to eat that. ");
				return false;
			}
		} else {
			System.out.println("You don't have that item! ");
			return false;
		}
	}

	public Item searchBag(String name) {
		Item item = null;
		if (bag.containsKey(name)) {
			item = bag.get(name);
			return item;
		} else
			return null;
	}

	public Item searchEquipped(String name) {
		Item item = null;
		if (equipped.containsKey(name)) {
			item = equipped.get(name);
			return item;
		} else
			return null;
	}

	public boolean capacity(Item item) {
		if (item instanceof Weapon) {
			if (weapons < 2) {
				weapons++;
				return true;
			} else
				return false;

		} else if (item instanceof Armor) {
			if (item.name.contains("breastplate")) {
				if (chest) {
					chest = false;
					return true;
				}
			} else if (item.name.contains("legplates")) {
				if (legs) {
					legs = false;
					return true;
				}
			} else if (item.name.contains("gauntlets")) {
				if (hands) {
					hands = false;
					return true;
				}
			} else if (item.name.contains("helmet")) {
				if (head) {
					head = false;
					return true;
				}
			} else { // shield
				if (shield) {
					shield = false;
					return true;
				}
			}
			return false;
		} else {
			System.out.println("You can't equip that, silly!");
			return false;
		}

	}

	/**
	 * @return the bag
	 */
	public HashMap<String, Item> getBag() {
		return bag;
	}

	/**
	 * @param bag
	 *            the bag to set
	 */
	public void setBag(HashMap<String, Item> bag) {
		this.bag = bag;
	}

	public HashMap<String, Item> getEquipped() {
		return equipped;
	}
}
