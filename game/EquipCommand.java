/**
 * 
 */
package game;

/**
 * @author josh.l.wright
 *
 */
public class EquipCommand extends Command {
	
	public EquipCommand(Player player) {
		super(player);
		name = "equip";				
	}

	@Override
	void run(Room room, String object) {
		player.inventory1.equip(object.toLowerCase());
	}
}
