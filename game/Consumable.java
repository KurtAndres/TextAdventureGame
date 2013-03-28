package game;
/**
 * 
 */


import java.util.*;

/**
 * @author leanne.miller
 * 
 */
public class Consumable extends Item {


	// Map filled in Game Constructor
	private static HashMap<String, Integer> food = new HashMap<String, Integer>();

	public Consumable(Player p) {
		super(p);
		fillFoodMap();

		// Item.allItems.add((Item) food.keySet());

		Random random = new Random();
		ArrayList<String> keys = new ArrayList<String>(food.keySet());
		String randomKey = keys.get(random.nextInt(keys.size()));
		Integer value = food.get(randomKey);

		this.name = randomKey;
		this.value = value;

	}

	public Consumable(Player p, String s) {
		super(p);
		this.name = s;
		if (food.containsKey(s)) {
			this.value = food.get(s);
			
		} else {
			Random r = new Random(4);
			this.value = r.nextInt() + 1;
		}
	}
	public Consumable(Item a){
		super(a.p);
		name = a.name;
		value = a.value;	
	}

	public static void fillFoodMap() {
		food.put("chocolate", 5);
		food.put("sandwich", 4);
		food.put("apple", 3);
		food.put("yogurt", 2);
		food.put("potion", 10);
		food.put("bluepotion", 15);
		food.put("lemonade", 6);
		food.put("soda", 7);
		food.put("cupcake", 2);
		food.put("hamburger", 8);
		food.put("orange", 3);
		food.put("lollipop", 1);
	}
	
	public static void addItems(Player p){
		fillFoodMap();  
		ArrayList<String> a = new ArrayList<String>(); 
		a.addAll(food.keySet());		 
		for(String s: a){ 
			Item.allItems.put(s, new Consumable(p, s)); 
		 }
	}

	public String describe() {
		return "Food: " + name + " gives " + value + " health";

	}

	@Override
	public int use() {
		consume(p);
		return 0;
	}

	public void consume(Player p) {
		p.setHealth(value);
		System.out.println("Your health is now: " + p.getHealth());
	}

	public static void consume(Player p, int i) {

	}

	public static HashMap<String, Integer> getFood() {
		fillFoodMap();
		return food;
	}
}
