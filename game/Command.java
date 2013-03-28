package game;
/**
 * 
 */


/**
 * @author josh.l.wright
 * 
 */
public abstract class Command {

	protected String name;
	protected Player player; //Delete later, if time

	public Command(Player player) {
		this.player = player;
	}

	abstract void run(Room room, String object);

}
