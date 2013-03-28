package game;


import java.util.*;

/**
 * Room.java
 * 
 * Class to model a room in the game.
 *
 * @author Thomas VanDrunen
 * Wheaton College, CS 245, Spring 2007
 * Lab 5
 * Feb 8, 2007
 */

public class Room {

	// A list of all the possible directions for doors
	private ArrayList<String> directions;

	// A map containing all the items in the game
	@SuppressWarnings("unused")
	private HashMap<String, Integer> allItems = new HashMap<String, Integer>();

	// A map containing the items in the room
	private HashMap<String, Item> items = new HashMap<String, Item>();

	private Monster monster = null;
 
    //Creates a HashMap with all of the rooms connected to this room
    private HashMap<String, Room> rooms = new HashMap<String, Room>();
    
	//Creates a HashMap with all of the doors connected to this room
    private HashMap<String, Door> doors = new HashMap<String, Door>();
    
    // A description of this room
    private String description;

    /**
     * Constructor.
     * @param description A String describing this room to the user.
     */
    public Room(String description, Player p) { 
    	setDirections();
    	this.description = description; 
    	
		Random random = new Random();
		if (random.nextBoolean()) {

			Item weapon = new Weapon(p);
			Item food = new Consumable(p);
			getItems().put(weapon.getName().toLowerCase(), weapon);
			getItems().put(food.getName().toLowerCase(), food);
		}else{
			Item armor = new Armor(p);
			getItems().put(armor.getName().toLowerCase(), armor);
		}
		if(!description.equals("Backyard") && !description.equals("Back Alley")){
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(3);
			if (randomInt == 2) {
				monster = new Monster();
			}
		}else if(description.equals("Backyard")){
			monster = new Monster("Mad Scientist");
		}
    }
    
    //Puts a key (with name s) in the room
    public void setKey(String s, Player p){
    	Item key = new Key(p, s);
    	items.put(key.getName().toLowerCase(), key);
    }
    
    //Sets a given item to the room
    public void setItem(Item i){
    	items.put(i.getName(), i);
    }
    
    
    //Connects room r to the current room at direction s
    public void setRoom(String s, Room r){
    	rooms.put(s, r);
    	doors.put(s, new Door());
    }
    
    //Connects room r to the current room at direction s and sets whether or not the door is open or locked
    public void setRoom(String s, Room r, Boolean open, Boolean locked, Player p){
    	rooms.put(s, r);
    	doors.put(s, new Door(p, open, locked));
    }
    
  //Connects room r to the current room at direction s and sets whether or not the door is open or locked
    public void setRoom(String s, Room r, Boolean open, Boolean locked, Player p, String key){
    	rooms.put(s, r);
    	doors.put(s, new Door(p, open, locked));
    }
    
    //Locks a given door with the key
    public void lock(String s, Room r, Key k){
    	boolean locked = r.getDoors().get(s).lock(k);
    	if(locked){
    		System.out.println("Sorry, wrong key.");
    	}else
    		System.out.println("Door is now locked.");
    }
    
    //Unlocks a given door with the key, if the key is the right one
    public void unlock(String s, Room r, Key k){
    	r.getDoors().get(s).unlock(k);
    }
    
    //Returns a hashMap of all the rooms connected to the current room
    public HashMap<String, Room> getRooms(){
    	return rooms;
    }
    
    //Returns a hashMap of all the doors connected to the current room
    public HashMap<String, Door> getDoors(){
    	return doors;
    }
    
    //Creates a list of possible door directions so that the parser can check if a command is a valid door option
    public void setDirections(){
    	directions = new ArrayList<String>();
    	directions.add("north");
    	directions.add("south");
    	directions.add("east");
    	directions.add("west");
    	directions.add("up");
    	directions.add("down");
    }
    
    //Checks if a String is a possible direction to move in
    public boolean isDirection(String s){
    	return directions.contains(s);
    }
    
    //Retrieve a description of this room (for the user).
    public String getDescription() { return description; }
    
    public Monster getMonster() { return monster;
}

    //Returns the items in the room
	public HashMap<String, Item> getItems() {
		return items;
	}

	// Modifies the items in the room
	public void setItems(HashMap<String, Item> items) {
		this.items = items;
	}

	public void removeMonster() {
		monster = null;
		
	}

}

