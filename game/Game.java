package game;


import java.util.*;

/**
 * Game.java
 * 
 * Class to model the game as a collection of rooms. The rooms are organized as
 * a graph, and the Room objects are nodes in the graph.
 * 
 * @author Thomas VanDrunen Wheaton College, CS 245, Spring 2007 Lab 5 Feb 8,
 *         2007 
 *         & Leanne Miller
 */

public class Game {
	private static Player p;
	Scanner s = new Scanner(System.in);
	private static HashMap<String, Command> commands = new HashMap<String, Command>();
	private boolean over;
	private static WinObject w;

	public static HashMap<String, Command> getCommands() {
		return commands;
	}

	/**
	 * The current room the user is in. This serves two purposes-- it is our
	 * only permanent connection to the rooms in this game (the other rooms are
	 * reachable by traversing this room's "doors"-- and it maintains the state
	 * by representing the user's current location.
	 */
	private static Room currentRoom;

	public Player getPlayer() {
		return p;
	}

	public void fillCommands() {
		commands.put("quit", new ExitCommand(p));
		commands.put("look", new LookCommand(p));
		commands.put("help", new HelpCommand(p));
		commands.put("pickup", new PickupCommand(p));
		commands.put("describe", new DescribeCommand(p));
		commands.put("unlock", new UnlockCommand(p));
		commands.put("inventory", new InventoryCommand(p));
		commands.put("eat", new EatCommand(p));
		commands.put("drop", new DropCommand(p));
		commands.put("attack", new AttackCommand(p));
		commands.put("read", new ReadCommand(p));
		commands.put("equip", new EquipCommand(p));
		commands.put("unequip", new UnequipCommand(p));
		commands.put("equipped", new EquippedCommand(p));
	}

	//Constructor to set up the game.
	public Game() {
		intro();
		
		JournalPage.fillClues(p);
		createMap();
		
		fillCommands();
		Item.fillAllItems(p);
		Monster.addItems(p);
		over = false;
	}
	
	private static void intro(){
		Scanner s = new Scanner(System.in);
		
		System.out.print("Welcome to the game! \n" );
		System.out.print("What do you want your character's name to be? ");
		String name = s.nextLine();
		p = new Player(name);
		
		setWinObject();
		if(w.getName().equals("philosopher's stone")){
			System.out.println("\nThe house of a mad scientist sits at the end of your street." +
					"\nYou have heard that he was an alchemist and you think that he might have suceeded in creating the philosopher's stone." +
					"\nDespite warnings against entering the house, you have decided to search it anyway." +
					"\nAs soon as you walked in the front door you heard it lock behind you." +
					"\nYour only hope is that he has left behind a key to the back door, but don't forget to find the stone!");
			
		}else if(w.getName().equals("the cure")){
			System.out.println("\nYou are a very compassionate person who cares greatly for humanity. \nAnd yourself." +
					"\nYou are deathly ill with an unknown disease that no doctor is able to diagnose." +
					"\nYou have heard that a mad scientist who lives near you may have found the Cure for all disease, but that he is too selfish to share it with the world." +
					"\nYou have decided that this is unacceptable and that you must take matters into your own hands and steal his Cure." +
					"\nFor the good of humanity, of course." +
					"\nDespite warnings against entering his house, you have decided to search it anyway." +
					"\nAs soon as you walked in the front door you heard it lock behind you." +
					"\nYour only hope is that he has left behind a key to the back door, but don't forget the Cure!");
			
		}else if(w.getName().equals("your saga id card")){
			System.out.println("\nA mad scientist has stolen your ID card and you are no longer able to get into Saga for your meals." +
					"\nAfter a week of going hungry, you have decided to sneak into the mad scientist's house and take back what is yours." +
					"\nHowever, as soon as you walked in the front door you heard it lock behind you." +
					"\nYour only hope is that he has left behind a key to his back door, but don't forget to find your ID card as well!");
		}
		System.out.println("\nUnfortunately, a lot of his old monsters are still hanging around. \nBetter be careful!\n");
		
		System.out.println("\tTo look around the room that you are in, type: look\n"
						+ "\tTo move to a new room, type the direction you wish to move in. Ex: east\n"
						+ "\tTo pickup an item, type: pickup [item]\n"
						+ "\tTo see a list of all available commands, type: help\n");
	}
	
	private static void setWinObject(){
		Scanner s = new Scanner(System.in);
		System.out.print("Which is your character more interested in: Money, Helping Others or Food? ");
		String win = s.nextLine();
		if(win.toLowerCase().equals("money")){
			w = new WinObject(p, "philosopher's stone");
			return;
		}if(win.toLowerCase().equals("helping others")){
			w = new WinObject(p, "the cure");
			return;
		}if(win.toLowerCase().equals("food")){
			w = new WinObject(p, "your saga id card");
			return;
		}else{
			System.out.println("Sorry, that is not a valid interest.");
			setWinObject();
		}
	}


	//Is this game over or not?
	public boolean isOver() {
		if(currentRoom.getDescription().equals("Back Alley") && p.inventory1.getBag().containsKey(w)){
			over = true;
		}
		return over;
	}


	//Move into a different current room.your player,
	@SuppressWarnings("static-access")
	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	public static void finishGame(Player p) {
		if(p.returnwon()){
			WonGameJframe.winningBanner(w);
		}else{
			System.out.println("You defeat the professor and escape.");
			ExitCommand.die();
			System.out.println("\n\nGame Over.");
			System.exit(1024);
		}
		
	}
	
	public static WinObject getWinObject(){
		return w;
	}

	private void createMap() {
		// Create ground floor rooms
		Room home = new Room("Hall", p);
		currentRoom = home;
		Room library = new Room("Library", p);
		Room foyer = new Room("Foyer", p);
		Room study = new Room("Study", p);
		Room parlor = new Room("Parlor", p);
		Room diningRoom = new Room("Dining Room", p);
		Room sunRoom = new Room("Sun Room", p);
		Room kitchen = new Room("Kitchen", p);
		Room backyard = new Room("Backyard", p);
		Room pond = new Room("Pond", p);

		home.setRoom("east", library, false, true, p); //Locks the door
		library.setRoom("west", home);
		library.setItem(new JournalPage(p, "journal page 6", 6));
		home.setRoom("west", foyer);
		foyer.setRoom("east", home);
		foyer.setItem(new JournalPage(p, "journal page 0", 0));
		home.setRoom("south", study);
		study.setRoom("north", home);
		home.setRoom("north", parlor);
		parlor.setRoom("south", home);
		parlor.setRoom("north", diningRoom);
		diningRoom.setRoom("south", parlor);
		diningRoom.setRoom("west", sunRoom);
		sunRoom.setRoom("east", diningRoom);
		sunRoom.setItem(new JournalPage(p, "journal page 1", 1));
		diningRoom.setRoom("east", kitchen);
		kitchen.setRoom("west", diningRoom);
		kitchen.setRoom("east", backyard, false, true, p); //Locks the door
		backyard.setRoom("west", kitchen);
		backyard.setRoom("north", pond);
		pond.setRoom("south", backyard);

		// Create upstairs rooms
		Room landing = new Room("Landing", p);
		Room bedroom1 = new Room("Bedroom1", p);
		Room balcony = new Room("Balcony", p);
		Room walkInCloset = new Room("Walk in Closet", p);
		Room bedroom2 = new Room("Bedroom2", p);
		Room closet = new Room("Closet", p);
		Room stair = new Room("Secret Staircase", p);
		Room turret = new Room("Turret Room", p);

		home.setRoom("up", landing);
		landing.setRoom("down", home);
		landing.setRoom("west", bedroom1);
		bedroom1.setRoom("east", landing);
		bedroom1.setRoom("west", balcony);
		balcony.setRoom("east", bedroom1);
		bedroom1.setRoom("south", walkInCloset);
		walkInCloset.setRoom("north", bedroom1);
		walkInCloset.setItem(new JournalPage(p, "journal page 4", 4));
		landing.setRoom("east", bedroom2);
		bedroom2.setRoom("west", landing);
		bedroom2.setRoom("north", closet);
		closet.setRoom("south", bedroom2);
		closet.setRoom("up", stair);
		stair.setRoom("down", closet);
		stair.setRoom("up", turret, false, true, p);
		turret.setRoom("down", stair);
		turret.setItem(w);

		// Create downstairs rooms
		Room basement = new Room("Basement", p);
		Room tunnel = new Room("Tunnel", p);
		Room stormCellar = new Room("Storm Cellar", p);
		Room storage1 = new Room("Storage1", p);
		Room laundry = new Room("Laundry Room", p);
		Room storage2 = new Room("Storage2", p);
		Room pantry = new Room("Pantry", p);

		home.setRoom("down", basement);
		basement.setRoom("up", home);
		basement.setRoom("east", tunnel);
		tunnel.setRoom("west", basement);
		tunnel.setRoom("east", stormCellar);
		stormCellar.setRoom("west", tunnel);
		stormCellar.setItem(new JournalPage(p, "journal page 3", 3));
		basement.setRoom("west", storage1);
		storage1.setRoom("east", basement);
		basement.setRoom("north", laundry);
		laundry.setRoom("south", basement);
		laundry.setRoom("north", storage2);
		storage2.setRoom("south", laundry);
		storage2.setRoom("east", pantry);
		storage2.setItem(new JournalPage(p, "journal page 5", 5));
		pantry.setRoom("west", storage2);
		pantry.setRoom("up", kitchen);
		kitchen.setRoom("down", pantry);

		// Create house of mirrors
		Room hom1 = new Room("House of Mirrors, Room 1", p);
		Room hom2 = new Room("House of Mirrors, Room 2", p);
		Room hom3 = new Room("House of Mirrors, Room 3", p);
		Room hom4 = new Room("House of Mirrors, Room 4", p);
		Room hom5 = new Room("House of Mirrors, Room 5", p);
		Room hom6 = new Room("House of Mirrors, Room 6", p);
		Room hom7 = new Room("House of Mirrors, Room 7", p);
		Room hom8 = new Room("House of Mirrors, Room 8", p);
		Room hom9 = new Room("House of Mirrors, Room 9", p);
		Room lab = new Room("Secret Laboratory", p);

		basement.setRoom("south", hom1);
		hom1.setRoom("north", basement);
		hom1.setRoom("east", hom4);
		hom1.setRoom("south", hom7);
		hom1.setRoom("west", hom9);
		hom4.setRoom("south", hom6);
		hom4.setRoom("west", hom6);
		hom4.setRoom("east", hom1);
		hom6.setRoom("north", hom2);
		hom6.setRoom("west", hom2);
		hom6.setRoom("south", hom8);
		hom6.setRoom("east", hom1);
		hom8.setRoom("west", hom3);
		hom8.setRoom("south", hom4);
		hom8.setRoom("east", hom7);
		hom8.setRoom("north", hom1);
		hom3.setRoom("west", hom9);
		hom3.setRoom("north", hom6);
		hom3.setRoom("east", hom4);
		hom3.setRoom("south", lab);
		lab.setRoom("north", hom3);
		lab.setKey("key", p);
		hom9.setRoom("north", hom7);
		hom9.setRoom("south", hom1);
		hom9.setRoom("east", hom6);
		hom9.setRoom("west", hom1);
		hom7.setRoom("south", hom1);
		hom7.setRoom("north", hom5);
		hom7.setRoom("east", hom2);
		hom7.setRoom("west", hom1);
		hom7.setItem(new JournalPage(p, "journal page 2", 2));
		hom5.setRoom("east", hom1);
		hom5.setRoom("west", hom9);
		hom5.setRoom("north", hom4);
		hom5.setRoom("south", hom8);
		hom2.setRoom("north", hom5);
		hom2.setRoom("south", hom1);
		hom2.setRoom("east", hom4);
		hom2.setRoom("west", hom1);
	}

	public static Room getCurrentRoom() {
		return currentRoom;
	}

}
