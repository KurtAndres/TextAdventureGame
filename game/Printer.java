package game;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.print.*;
//Learned code for printing in java from Oracle's Website
//Code for printing used from Oracle Example, Modified Output.
//* Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.

public class Printer implements Printable, ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(this);
		boolean ok = job.printDialog();
		if (ok) {
			try {
				job.print();
			} catch (PrinterException ex) {
				/* The job did not successfully complete */
			}
		}
	}

	@Override
	public int print(Graphics g, PageFormat pf, int page) throws
	PrinterException {

		if (page > 0) { /* We have only one page, and 'page' is zero-based */
			return NO_SUCH_PAGE;
		}

		/* User (0,0) is typically outside the imageable area
		 * translate by the X and Y values in the PageFormat to avoid clipping
		 */
		Graphics2D g2d = (Graphics2D)g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());

		/* Now we perform our rendering */
		g.drawString("Congratulations, You are among the elite few to win:", 85, 100);
		g.drawString("Ghosts of Dr. Gray--THE GAME", 135,150 );
		g.drawString("Sign your name below to validate this official certificate", 79,450 );
		g.drawString("X___________________________________", 120,500 );
		g.drawString("(game produced by Kurt Andres, Leanne Miller, and Josh Wright)", 52,600 );


		/* tell the caller that this page is part of the printed document */
		return PAGE_EXISTS;
	}
	public static void init() {

		UIManager.put("swing.boldMetal", false);
		JFrame f = new JFrame("Printer");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});
		JButton printButton = new JButton("Print Certificate");
		printButton.setBackground(Color.RED);
		printButton.setFont(new Font("COURRIER", Font.BOLD, 18));
		printButton.addActionListener(new Printer());
		f.add("Center", printButton);
		f.pack();
		f.setVisible(true);
	}


}
