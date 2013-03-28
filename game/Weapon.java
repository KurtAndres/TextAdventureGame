package game;
/**
 * 
 */


import java.util.*;

/**
 * @author josh.l.wright
 * 
 */
public class Weapon extends Item {

	private static HashMap<String, Integer> weapons = new HashMap<String, Integer>();

	public Weapon(Player p) {
		super(p);
		fillMap();

		Random random = new Random();
		ArrayList<String> keys = new ArrayList<String>(weapons.keySet());
		String randomKey = keys.get(random.nextInt(keys.size()));
		Integer value = weapons.get(randomKey);

		this.name = randomKey;
		this.setAttack(value);
	}

	public Weapon(Player p, String s) {
		super(p);
		this.name = s;
		fillMap();
		
		if (weapons.containsKey(s)) { 
			this.value = weapons.get(s);
		} else {
			Random r = new Random(6);
			this.value = r.nextInt();
		}	
	}
	public Weapon(Item a){
		super(a.p);
		this.name = a.name;
		this.value = a.value;
	}

	@Override
	public int use() {
		return getAttack();
	}

	@Override
	public String describe() {
		return "Weapon: " + name + " gives " + getAttack() + " Attack";
	}

	public static void fillMap() {
		weapons.put("plastic sword", 4);
		weapons.put("plastic axe", 3);
		weapons.put("plastic mace", 2);
		weapons.put("plastic dagger", 1);
		weapons.put("steel sword", 8);
		weapons.put("steel axe", 6);
		weapons.put("steel mace", 4);
		weapons.put("steel dagger", 2);
		weapons.put("magic sword", 12);
		weapons.put("magic axe", 9);
		weapons.put("magic mace", 6);
		weapons.put("rubber chicken", 15);
		weapons.put("magic dagger", 3);
	}

	
	 public static void addItems(Player p){
		 fillMap();  
		 ArrayList<String> a = new ArrayList<String>(); 
		 a.addAll(weapons.keySet());
		 
		 for(String s: a){ 
			 Item.allItems.put(s, new Weapon(p, s)); 
		 } 
	 }
	 

	public int getAttack() {
		return value;
	}

	public void setAttack(int attack) {
		this.value = attack;
	}

	public static HashMap<String, Integer> getWeapons() {
		return weapons;
	}

}
