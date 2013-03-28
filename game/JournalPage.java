package game;
/**
 * 
 */


import java.util.*;
/**
 * @author leanne.miller
 *
 */
public class JournalPage extends Item {
	private static ArrayList<String> descriptions = new ArrayList<String>();
	private static HashMap<String, JournalPage> clues = new HashMap<String, JournalPage>();
	private String description;
	private int pgNum;
	
	public JournalPage(Player p, String s, int pgNum){
		super(p);
		this.pgNum = pgNum;
		name = s;
		description = descriptions.get(pgNum);
	}

	@Override
	public int use() {
		
		return 0;
	}

	@Override
	public String describe() {
		return "   A page from the professor's journal!";
	}
	
	public static void fillDescriptions(){
		descriptions.add("Five moves are needed to navigate the House of Mirrors.");
		descriptions.add("Four leaf clovers should be the first thing you look for.");
		descriptions.add("Divide the clover by 2 then multiply by three to know that you have made the correct second move.");
		descriptions.add("Two clovers will give you your third room. Head for some sun.");
		descriptions.add("After being doubly lucky, head for the frontier.");
		descriptions.add("When looking for a secret lab you would be wise to go down river.");
		descriptions.add("The professor's favorite place is his tower.");
	}
	
	public static void fillClues(Player p){
		fillDescriptions();
		//Clues to help navigate the House of Mirrors
		clues.put("journal page 0", new JournalPage(p, descriptions.get(0), 0));
		clues.put("journal page 1", new JournalPage(p, descriptions.get(1), 1));
		clues.put("journal page 2", new JournalPage(p, descriptions.get(2), 2));
		clues.put("journal page 3", new JournalPage(p, descriptions.get(3), 3));
		clues.put("journal page 4", new JournalPage(p, descriptions.get(4), 4));
		clues.put("journal page 5", new JournalPage(p, descriptions.get(5), 5));
		
		//Clue to help find the item
		clues.put("journal page 6", new JournalPage(p, descriptions.get(6), 6));
	}
	
	public static void addItems(Player p){
		 fillClues(p);  
		 ArrayList<String> a = new ArrayList<String>(); 
		 a.addAll(clues.keySet());
		 
		 int i = -1;
		 for(String s: a){ 
			 i++;
			 Item.allItems.put(s, new JournalPage(p, descriptions.get(i), i)); 
		 } 
	 }
	
	public static HashMap<String, JournalPage> getClues(){
		return clues;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public int getPgNum(){
		return pgNum;
	}

}
