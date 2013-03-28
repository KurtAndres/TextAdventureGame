package game;
/**
 * 
 */


import java.util.*;

/**
 * @author Leanne
 * 
 */
public class InventoryCommand extends Command {

	public InventoryCommand(Player player) {
		super(player);
		name = "inventory";
	}

	void run(Room room, String object) {
		System.out.println("Items in your inventory are: ");
		Collection<Item> a = player.inventory1.getBag().values();
		for(Item i: a){
			System.out.println("\t"+i.name +" x" + i.count);
		}
	}

}
