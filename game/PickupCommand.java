package game;
/**
 * 
 */


import java.util.HashMap;
import java.util.Scanner;

/**
 * @author josh.l.wright
 * 
 */
public class PickupCommand extends Command {

	public PickupCommand(Player player) {
		super(player);
		name = "pickup";
	}

	@Override
	void run(Room room, String object) {
		Scanner s = new Scanner(System.in);
		if (object == null) {
			System.out.println("What item would you like to pick up?");
			object = s.nextLine();																																																																																																																																																																																																	
		}
		String ob1;
		String ob2;
		if(object.contains("and")){
			int i = object.indexOf("and");
			ob1 = object.substring(0, i-1);
			ob2 = object.substring(i+4);
			
			pickup(room, ob1);
			pickup(room, ob2);
		}else{
			pickup(room, object);
		}
		

	}
	
	private void pickup(Room room, String object){
		boolean success = player.inventory1.pickup(object.toLowerCase(), room);
		if(success){
			HashMap<String, Item> roomInventory = room.getItems();
			roomInventory.remove(object);
			room.setItems(roomInventory);
			int size = room.getItems().values().toString().length();
			if ((!room.getItems().values().isEmpty())) {
				System.out.println("The item(s) in this room are "+ room.getItems().values().toString().substring(1, size - 1) + ".");
			} else {
				System.out.println("There are no items in this room.");
			}
		}else;
	}
	

}
