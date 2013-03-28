package game;
/**
 * 
 */


import java.util.Scanner;

/**
 * @author josh.l.wright
 * 
 */
public class DescribeCommand extends Command {
	Player p;

	public DescribeCommand(Player player) {
		super(player);
		name = "describe";
		p = player;
	}

	void run(Room room, String object) {
		if (object == null) {
			System.out.println("Describe what? [item], " + Game.getWinObject() + ", player, or monster? ");
			Scanner s = new Scanner(System.in);
			object = s.nextLine();
		}
		if(Item.getItems(player).containsKey(object)){  
			System.out.println(Item.getItems(player).get(object).describe());
			 
		}else if(Game.getWinObject().getName().equals(object.toLowerCase())){
			System.out.println(Game.getWinObject().describe());
		}else if (object.equals("player") || object.equals(player.getName())) {
		
			player.stats();
		}else if (object.equals("monster")) {
			if (room.getMonster() != null)
				System.out.println(room.getMonster().describe());
			else
				System.out.println("There is no monster in the room.");
		}else if(Monster.getAllMonsters(p).containsKey(object.toLowerCase())){
			System.out.println(Monster.getAllMonsters(p).get(object.toLowerCase()).describe());
			
		}else{
			System.out.println("I can only describe [item], " + Game.getWinObject() + ", player, or monster.");
		}
	}

}
