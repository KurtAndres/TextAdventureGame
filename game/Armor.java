package game;
/**
 * 
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @author josh.l.wright
 * 
 */
public class Armor extends Item {

	private static HashMap<String, Integer> armors = new HashMap<String, Integer>();

	public Armor(Player p) {
		super(p);
		fillArmorMap();

		Random random = new Random();
		ArrayList<String> keys = new ArrayList<String>(armors.keySet()); // found on Internet after googling
																			// "find random pair in hashmap"
		String randomKey = keys.get(random.nextInt(keys.size())); // ...I understand exactly what it does,
																	// just copied it
		Integer value = armors.get(randomKey);						// http://stackoverflow.com/questions/9919734/selecting-random-key-and-value-sets-from-a-map-in-java
		

		name = randomKey;
		setDefense(value);
	}
	public Armor(Player p, String s) {
		super(p);
		name = s;
		fillArmorMap();
		 
		if (armors.containsKey(s)) {
			this.value = armors.get(s);
		} else {
			Random r = new Random(6);
			this.value = r.nextInt();
		}
	}
	public Armor(Item a){
		super(a.p);
		name = a.name;
		value = a.value;	
	}

	@Override
	public int use() {
		return getDefense();
	}

	@Override
	public String describe() {
		return "Armor: " + name + " gives " + getDefense() + " defense";
	}
	
	public static void fillArmorMap(){
		armors.put("wood breastplate", 3);
		armors.put("wood legplates", 2);
		armors.put("wood gauntlets", 2);
		armors.put("wood helmet", 2);
		armors.put("wood shield", 3);
		armors.put("iron breastplate", 6);
		armors.put("iron legplates", 4);
		armors.put("iron gauntlets", 4);
		armors.put("iron helmet", 4);
		armors.put("iron shield", 6);
		armors.put("magic breastplate", 9);
		armors.put("magic legplates", 6);
		armors.put("magic gauntlets", 3);
		armors.put("magic helmet", 6);
		armors.put("magic shield", 9);
	}
	
	public static void addItems(Player p){
		 fillArmorMap();  
		 ArrayList<String> a = new ArrayList<String>(); 
		 a.addAll(armors.keySet());
		 
		 for(String s: a){ 
			 Item.allItems.put(s, new Armor(p, s)); 
		 } 
	 }

	public int getDefense() {
		return value;
	}

	public void setDefense(int defense) {
		this.value = defense;
	}

	public static HashMap<String, Integer> getArmors() {
		return armors;
	}
	

}
