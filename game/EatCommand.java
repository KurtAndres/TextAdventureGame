package game;
/**
 * 
 */


import java.util.Scanner;

/**
 * @author Leanne
 * 
 */
public class EatCommand extends Command {

	public EatCommand(Player p) {
		super(p);
		name = "eat";
	}

	@Override
	void run(Room room, String object) {
		if(object== null){
			System.out.println("Eat what?");
			Scanner key = new Scanner(System.in);
			object = key.next();
		}
		if(player.inventory1.eat(object.toLowerCase())){
			System.out.println("Your health is now "+ player.getHealth());
		}

	}

}
