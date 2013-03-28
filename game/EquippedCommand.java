/**
 * 
 */
package game;

import java.util.Collection;

/**
 * @author josh.l.wright
 *
 */
public class EquippedCommand extends Command {

	
	public EquippedCommand(Player player) {
		super(player);
		name = "equipped";
	}

	@Override
	void run(Room room, String object) {
		System.out.println("Items you have equipped are: ");
		Collection<Item> a = player.inventory1.getEquipped().values();
		for(Item i: a){
			System.out.println("\t"+i.name);
		}

	}

}
