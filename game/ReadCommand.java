package game;
/**
 * 
 */


import java.util.*;
/**
 * @author leanne.miller
 *
 */
public class ReadCommand extends Command {
	
	public ReadCommand(Player p){
		super(p);
		name = "read";
	}

	void run(Room room, String object) {
		if(object== null){
			System.out.println("Read what?");
			Scanner key = new Scanner(System.in);
			object = key.next();
		}
		//Checks to see if the object is a journal page
		if(object.contains("journal page")){
			if(player.inventory1.getBag().containsKey(object)){
				System.out.println("It says: \""  + JournalPage.getClues().get(object).getDescription() + "\"");
			}else{
				System.out.println("Sorry, you do not have a(n) "+ object);
			}
		}else{
			System.out.println("Sorry, you cannot read that item.");
		}
	}

}
