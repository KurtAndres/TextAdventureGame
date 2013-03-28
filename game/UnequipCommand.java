package game;

public class UnequipCommand extends Command {
	
	public UnequipCommand(Player player) {
		super(player);
		name = "unequip";				
	}

	@Override
	void run(Room room, String object) {
		player.inventory1.unequip(object.toLowerCase());
	}
}