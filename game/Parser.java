package game;


/**
 * Parser.java
 * 
 * Class to interpret the user's commands
 *
 * @author Thomas VanDrunen
 * Wheaton College, CS 245, Spring 2007
 * Lab 5
 * Feb 8, 2007
 */

import java.util.*;

public class Parser {

	/**
	 * For user input from the keyboard.
	 */
	static Scanner keyboard = new Scanner(System.in);
	private static boolean newRoom = true;
	private static boolean seen = false;

	/**
	 * Let the user make one "turn" at this game. Give the user a description of
	 * the room, prompt for a command, and interpret the command.
	 * 
	 * @param game
	 *            A reference to the object representing the game.
	 */
	public static void executeTurn(Game game, HashMap<String, Command> commands, Player p) {
		// The room that the user is in.
		Room room = Game.getCurrentRoom();
		
		if(Game.getCurrentRoom().getDescription().equals("Backyard")){
			if(seen == false){
				System.out.println("You have made it outside the house to the backyard only to find the Mad Scientist waiting for you!! " +
					"\nHe is determined to stop you from getting away!\n");
				seen = true;
			}
			if(Game.getCurrentRoom().getMonster() == null){
					Game.finishGame(p);
			}
		}
		

		// Stores the size of the String so that we can print it w/o brackets
		int size = room.getItems().values().toString().length();
		if (newRoom) { System.out.println(p.getName() + " is in the " + room.getDescription() + returnMonster(game) + ".");
			if ((!room.getItems().values().isEmpty())) {
				System.out.println("The item(s) in this room are "+ room.getItems().values().toString().substring(1, size - 1) + ".");
			} else {
				System.out.println("There are no items in this room.");
			}
		}
		newRoom = false;
		System.out.print("Enter command--> ");
		String[] input;
		String kb;
		String object = null;
		String command;
		
		kb = keyboard.nextLine().toLowerCase(); // user's command
		
		input = kb.split(" ");
		command = input[0];
		
		if (input.length > 1){
			object = kb.substring(input[0].length()+1);
		}
		
		/*String[] input;
		input = (keyboard.nextLine().toLowerCase()).split(" "); // user's command
		
		String command = input[0];
		String object = null;
		if (input.length > 1) {
			object = input[1];
		}	*/	

		//Performs the command
		if (room.isDirection(command)) {
			Room nextRoom = (Room) Game.getCurrentRoom().getRooms().get(command);
			if (nextRoom == null)
				System.out.println("There is no door in that direction.");
			
			else if(Game.getCurrentRoom().getDoors().get(command).isLocked()){
				System.out.println("Sorry, that door is locked.");

			}else {
				game.setCurrentRoom(nextRoom);
				newRoom = true;
			}

		} else if (commands.containsKey(command)) {
			
			commands.get(command).run(room, object);

		} else
			System.out.println("I do not know how to " + command + ".");

	}
	
	public static String returnMonster(Game game){
		Room room = Game.getCurrentRoom();
		if (room.getMonster() == null) {
			return " alone";
		} else {
			return " with " + room.getMonster().getName();
		}

	}
}
