package game;
/**
 * 
 */


/**
 * @author josh.l.wright
 * 
 */
public class LookCommand extends Command {

	public LookCommand(Player player) {
		super(player);
		name = "look";
	}

	void run(Room room, String object) {

		//Stores the size of the keyset so that the keyset can be printed without surrounding brackets
		int size = room.getRooms().keySet().toString().length(); 
		
		//Stores the size of the string so that we can print it w/o brackets
		int s = room.getItems().values().toString().length(); 

		System.out.println("You're in " + room.getDescription() + ". It has "
				+ room.getRooms().size() + " doors: "
				+ room.getRooms().keySet().toString().substring(1, size - 1)
				+ ".");
		System.out.println("The item(s) in this room are: "
				+ room.getItems().values().toString().substring(1, s - 1));
		if(room.getMonster() != null){
			System.out.println("The monster in this room is: " + room.getMonster().getName());
		}else
			System.out.println("There is no monster in this room.");
	}

}
