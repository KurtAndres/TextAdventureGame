package game;
/**
 * 
 */


import java.util.*;

/**
 * @author leanne.miller
 * 
 */
public class UnlockCommand extends Command {

	public UnlockCommand(Player player) {
		super(player);
		name = "unlock";
	}

	public void run(Room r, String s) {
		Scanner kb = new Scanner(System.in);
		if (s == null) {
			System.out.println("Which door would you like to unlock?");
			s = kb.next();
		}
		if (!r.getDoors().containsKey(s)) {
			System.out.println("Sorry, that is not a door.");
			return;

		}else{
			if(!r.getDoors().get(s).isLocked()){
				System.out.println("That door is not locked.");
			}else {
				Key k = r.getDoors().get(s).getKey();
				if (player.inventory1.getBag().containsKey(k.getName())) {
					r.unlock(s, r, k);
					System.out.println("Door is now unlocked.");
				} else {
					System.out.println("Sorry, you do not have the key.");
				}
			}

		}

	}
}
