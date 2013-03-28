package game;
/**
 * 
 */

/**
 * @author josh.l.wright
 * 
 */
public class Player {
	private String name;
	private int attack;
	private int defense;
	private int level;
	private int xp;
	private int health;
	protected Inventory inventory1 = new Inventory(this);
	private boolean hasItem = false;
	public Player(String name) {
		this.name = name;
		attack = 1;
		defense = 0;
		health = 20;
		level = 1;
		xp = 0;
	}

	public void stats() {
		System.out.printf(
				"[%s's Stats]: Attack: %d Defense: %d Health: %d Level: %d XP: %d %n",
				name, attack, defense, health, level, xp);
	}

	public String getName() {
		return name;
	}

	public String setName(String s) {
		name = s;
		return s;
	}

	public int getAttack() {
		return attack;
	}

	public int setAttack(int i) {
		attack += i;
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public int setDefense(int i) {
		defense+=i;
		return defense;
	}

	public int getLevel() {
		return level;
	}

	public int setLevel(int i) {
		level = i;
		return level;
	}

	public int getXP() {
		return xp;
	}

	public int setXP(int i) {
		xp = i;
		return xp;
	}

	public int getHealth() {
		return health;
	}
	public void addHealth(int i){
		health+=i; 
	}

	public int setHealth(int i) {
		if(health >= 50){
			return health;
		}else{
			if(health+i > 50){
				health = 50;
			}else{
				health += i;
			}
			return health;
		}
	}
	public int level(int xp){
		this.xp+=xp;
		if(level < 2 && this.xp>10){
			System.out.println("Level 2! Attack +1, Defense +1, Health +5");
			level=2;
			statInc();
		}if(level < 3 && this.xp>20){
			System.out.println("Level 3! Attack +1, Defense +1, Health +5");
			level=3;
			statInc();
		}if(level < 4 && this.xp>40){
			System.out.println("Level 4! Attack +1, Defense +1, Health +5");
			level=4;
			statInc();
		}if(level < 5 && this.xp>80){
			System.out.println("Level 5! Attack +1, Defense +1, Health +5");
			level=5;
			statInc();
		}if(level < 6 && this.xp>130){
			System.out.println("Level 6! Attack +1, Defense +1, Health +5");
			level=6;
			statInc();
		}if(level < 7 && this.xp>190){
			System.out.println("Level 7! Attack +1, Defense +1, Health +5");
			level=7;
			statInc();
		}if(level < 8 && this.xp>260){
			System.out.println("Level 8! Attack +1, Defense +1, Health +5");
			level=8;
			statInc();
		}if(level < 9 && this.xp>340){
			System.out.println("Level 9! Attack +1, Defense +1, Health +5");
			level=9;
			statInc();
		}if(level < 10 && this.xp>440){
			System.out.println("Level 10! Max Level!! Attack +10, Defense +10, Health +50");
			level=10;
			for(int x=0; x<10;x++) statInc();
		}
		return level;
	}
	private void statInc(){
		attack+=1;
		defense+=1;
		health+=5;
	}

	public void won() {hasItem=true;}
	public void dropWon() {hasItem=false;}
	public boolean returnwon(){return hasItem;}
}
