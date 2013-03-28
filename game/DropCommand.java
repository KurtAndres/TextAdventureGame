package game;
/**
 * 
 */


/**
 * @author leanne.miller
 *
 */
public class DropCommand extends Command {
	
	public DropCommand(Player p){
		super(p);
		
	}

	@Override
	void run(Room room, String object) {
		player.inventory1.drop(object.toLowerCase(), room);
	}

}
