package game;


/**
 * PlayGame.java
 * 
 * Program to control the playing of this game.
 * 
 * @author Josh Wright, Leanne Miller, and Kurt Andres
 *         March 27th 2013
 *         CSCI 245: Gray
 */

public class PlayGame {
	

	public static void main(String[] args) {
		play();
	}
	
	public static void play(){
		Game game = new Game(); // reference to the game object

		while (!game.isOver())
			Parser.executeTurn(game, Game.getCommands(), game.getPlayer());
	}

}
