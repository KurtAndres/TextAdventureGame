package game;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
/**
 * @author kurt.andres
 *
 */
public class WonGameJframe {

	public static void winningBanner(WinObject w) {
		System.out.println(chooseEnding(w));
		final JFrame wonGame = new JFrame("Congratulations");
		wonGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		wonGame.setVisible(true);
		wonGame.setTitle("YOU WON!");
		wonGame.setSize(screenSize.width, screenSize.height);
		wonGame.setBackground(Color.RED);
		
		//JButton rightButton = new JButton("");
		JButton closeButton = new JButton("Quit\n\n");
		JButton niceButton = new JButton("You Won!!!!\n");
		JButton newGameButton = new JButton("Play Again");
		JButton printCertificate = new JButton("Print Certificate to Validate Win");
		
		niceButton.setFont(new Font("Courier New", Font.BOLD, 32));
		closeButton.setBackground(Color.gray);
		//rightButton.setBackground(Color.cyan);
		closeButton.setFont(new Font("Courier New", Font.PLAIN, 16));
		newGameButton.setBackground(Color.green);
		newGameButton.setFont(new Font("SERIF", Font.BOLD, 36));
		printCertificate.setBackground(Color.cyan);
		closeButton.setFont(new Font("Courier New", Font.PLAIN, 16));


		closeButton.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent ae ){
				System.out.println("Thanks for Playing, buh-bye!");
				//wonGame.setVisible(false);
				System.exit(0);
			}
		});
		newGameButton.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent ae ){
				System.out.println("Begin New Game");
				//Call a new game here
				wonGame.dispose();
				PlayGame.play();
			}
		});
		printCertificate.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent ae ){
				System.out.println("Certificate printed!");
				//Call print method
				Printer.init();
			}
		});
		wonGame.add(closeButton, BorderLayout.PAGE_END);
		wonGame.add(newGameButton, BorderLayout.CENTER);
		wonGame.add(printCertificate, BorderLayout.WEST);
		wonGame.add(niceButton, BorderLayout.NORTH);
		//wonGame.add(rightButton, BorderLayout.EAST);
	}
	public static void main(String[] args){
		Player p = new Player("fdhjk");
		winningBanner(new WinObject(p, "philosopher's stone"));
	}
	private static String chooseEnding(WinObject w){
		if(w.getName().equals("philosopher's stone")){
			return ("Congratulations!! \nYou have managed to survive all the dangers of the professor's house and come out with the philosopher's stone!" +
					"\nYou soon put it to use making gold and quickly become very rich." +
					"\nYou buy yourself a new house and live in opulence for the rest of your life.");
			
		}else if(w.getName().equals("the cure")){
			return ("Congratulations!! \nYou have managed to survive all the dangers of the professor's house and come out with the Cure!" +
					"\nYou use it to cure yourself and then you patent it and sell it, making yourself millions and winning a Nobel Prize in the process.");
			
		}else if(w.getName().equals("your saga id card")){
			return ("Congratulations!! \nYou have managed to survive all the dangers of the professor's house and come out with your ID card!" +
					"\nYou are now able to get into saga again and you gorge yourself on food." +
					"\nIt also turns out that the professor had entered your ID number into a Bon Appetit raffle and you win millions from it!");
		}else
			return "";
	}
}

