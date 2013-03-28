package game;
/**
 * 
 */


/**
 * @author josh.l.wright, kurt.andres
 * 
 */
public class AttackCommand extends Command {

	public AttackCommand(Player player) {
		super(player);
	}

	@Override
	void run(Room room, String object) {
		if (room.getMonster() == null) System.out.println("You charge forward... but there's no monster! Calm down, you're gonna hurt yourself.");
		else {
			System.out.println("You attack " + room.getMonster().getName()+"!!");
			room.getMonster().setHealth(room.getMonster().getHealth()-player.getAttack());
			if(room.getMonster().getHealth()<=0){
				System.out.println("You do "+ player.getAttack() + " damage to " +room.getMonster().getName()+"! It has 0 health left.");
			}else
				System.out.println("You do "+ player.getAttack() + " damage to " +room.getMonster().getName()+"! It has " + room.getMonster().getHealth()+" health left.");
			if(room.getMonster().getHealth()<=0){
				System.out.println(room.getMonster().getName()+" dies! You gain "+room.getMonster().getAttack()*8 + " experience!");
				player.level(room.getMonster().getAttack()*8);
				room.getMonster().setDead(true);
				room.removeMonster();
			}else{
				if(room.getMonster().getAttack()-player.getDefense()>=0){
					System.out.println(room.getMonster().getName() + " does "+ (room.getMonster().getAttack()-player.getDefense())+" damage to you!");
					player.setHealth(-((room.getMonster().getAttack()-player.getDefense())));
				}else System.out.println("Your defense is too high for "+room.getMonster().getName()+" to do any damage to you!!");
				if(player.getHealth()<1){
					if(room.getMonster().getName().equals("Mad Scientist")){
						String the = "";
						if(Game.getWinObject().getName().equals("philosopher's stone")){the = "the ";}
						System.out.println("\nYou fail to defeat the mad scientist.");
						if(player.inventory1.getBag().containsKey(Game.getWinObject().getName())){
							System.out.println("\nHe overpowers you and takes back " + the + Game.getWinObject() + ". ");
						}else{
							System.out.println("He overcomes you and has your life in his grasp.");
						}
							System.out.println("\n\nSubsequently though, he has an attack of mercy and instead of killing you, lets you go free.");
									ExitCommand.die();
							System.out.println("\n\nGame Over.");
						}else{
							System.out.println("0 health left!!! YOU DIED. Wayyy to go. Game over. ");	
						
					}
					System.exit(1024);
				}else{
					System.out.println("You have "+player.getHealth()+" health left.");
				}
			}
		}
	}
}
