package game;
/**
 * 
 */


/**
 * @author josh.l.wright
 * 
 */
public class ExitCommand extends Command {

	public ExitCommand(Player player) {
		super(player);
		name = "quit";
	}

	void run(Room room, String object) {
		System.out.println("You manage to find an open window and you decide to cut your loses and leave.");
		die();
		System.exit(4657);
	}
	
	public static void die(){
		if(Game.getWinObject().getName().equals("philosopher's stone")){
			System.out.println("However, you soon go crazy after coming so close to owning the Philosopher's Stone and are committed to an insane asylum until you die.");
		}else if(Game.getWinObject().getName().equals("the cure")){
			System.out.println("However, without your the Cure there is no way to cure your illness and you shortly die.");
		}else if(Game.getWinObject().getName().equals("your saga id card")){
			System.out.println("However, without your ID card you still can't get into saga and you shortly die of hunger.");
		}
	}

}
