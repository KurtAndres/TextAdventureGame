package game;
/**
 * 
 */


/**
 * @author leanne.miller
 * 
 */
public class Key extends Item implements Comparable<Key> {

	public Key(Player p) {
		super(p);
		name = "key";
	}

	public Key(Player p, String s) {
		super(p);
		name = s;
	}

	public int use() {
		Game.getCommands().get("unlock").run(Game.getCurrentRoom(), "");
		return 0;
	}

	public String describe() {
		return name;
	}

	@Override
	public int compareTo(Key k) {
		if (k.name.equals(this.name)) {
			return 0;
		} else
			return -1;
	}
	
	public static void addKeys(Player p){
		String s = "key";
		Item.allItems.put(s, new Key(p, s));
	}

}
