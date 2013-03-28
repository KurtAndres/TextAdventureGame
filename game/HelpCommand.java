package game;
/**
 * 
 */


/**
 * @author josh.l.wright
 * 
 */
public class HelpCommand extends Command {

	public HelpCommand(Player player) {
		super(player);
		name = "help";
	}

	void run(Room room, String object) {
		System.out.println("Commands are: ");
		System.out.println("help \t\t\t\t\t(display commands) "
						+ "\nlook \t\t\t\t\t(descibe what's in the room)" 
						+ "\nnorth/south/east/west/up/down \t\t(move a direction) "
						+ "\npickup [item] \t\t\t\t(pickup an item in the room) " 
						+ "\ninventory \t\t\t\t(displays your inventory) "
						+ "\ndrop [item] \t\t\t\t(removes an item from your inventory)" 
						+ "\neat [item] \t\t\t\t(eat an item)" 
						+ "\nread [item] \t\t\t\t(read an item)"
						+ "\ndescribe [item]/player/monster \t(give info about an item, your player, or the monster in the room))"
						+ "\nunlock [direction] \t\t\t(unlocks a door in the given direction, if you have the key)"
						+ "\nequip [item] \t\t\t\t(equips a weapon or armor to modify your stats)"
						+ "\nunequip [item] \t\t\t\t(unequips a weapon or armor to allow equipping in that spot (1 of each armor, 2 weapons))"
						+ "\nequipped \t\t\t\t(lists all items you have equipped)"
						+ "\nquit \t\t\t\t\t(quit the game)" + "\n");
	}

}
