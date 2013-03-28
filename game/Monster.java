package game;

/**
 * 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @author kurt.andres
 * 
 */
public class Monster {
	public static HashMap<String, Integer> monsters = new HashMap<String, Integer>();
	public static HashMap<String, Monster> allMonsters = new HashMap<String, Monster>();

	private String name;
	private int attack;
	private int health;
	private boolean dead = false;

	public Monster() {
		fillMonsterMap();

		Random random = new Random();
		ArrayList<String> keys = new ArrayList<String>(monsters.keySet());
		String randomKey = keys.get(random.nextInt(keys.size()));
		Integer value = monsters.get(randomKey);

		name = randomKey;
		attack = value;
		health = attack * 3;
	}

	public Monster(String s) {
		if (s.equals("Mad Scientist")) {
			name = s;
			attack = 50;
			health = 150;
		}else{
			fillMonsterMap();
			name = s;

			if (monsters.containsKey(s)) {
				attack = (monsters.get(s));
				health = attack * 3;
			} else {
				Random r = new Random(20);
				this.setAttack(r.nextInt());
			}
		}
	}

	public static void addItems(Player p) {
		fillMonsterMap();
		ArrayList<String> a = new ArrayList<String>();
		a.addAll(monsters.keySet());

		for (String s : a) {
			allMonsters.put(s.toLowerCase(), new Monster(s));
		}
	}

	public static void fillMonsterMap() {
		monsters.put("Godzilla", 9);
		monsters.put("Alien", 4);
		monsters.put("Zombie", 2);
		monsters.put("Dr Gray's Evil Clone", 10);
		monsters.put("Cyclops", 7);
		monsters.put("Small (but evil) Kitten", 13);
		monsters.put("Vampire", 12);
		monsters.put("Harpy", 4);
		monsters.put("Confused Duck", 1);
		monsters.put("Mutant Duck", 17);
		monsters.put("Giant", 19);
		monsters.put("Dangling Pointer", 9);
		monsters.put("Evil Lackey", 14);
		monsters.put("Enormous Poodle", 18);
	}

	public int use() {
		return getAttack();
	}

	public String toString() {
		return name;
	}

	public String describe() {
		return "a(n) " + name + " with " + getAttack() + " attack.";
	}

	public String getName() {
		return name;
	}

	public static HashMap<String, Monster> getAllMonsters(Player p) {
		addItems(p);
		return allMonsters;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	/**
	 * @return the health
	 */
	public int getHealth() {

		return health;
	}

	/**
	 * @param health
	 *            the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setDead(boolean b){
		dead = b;
	}
	
	public boolean getDead(){
		return dead;
	}
}
