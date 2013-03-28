package game;
/**
 * 
 */


/**
 * @author leanne.miller
 *
 */
public class WinObject extends Item{
	
	public WinObject(Player p, String s){
		super(p);
		name = s;
	}

	@Override
	public int use() {
		return 0;
	}

	@Override
	public String describe() {
		return "\n\tYour deepest desire.\n";
	}
	
	public String getName(){
		return name;
	}

}
