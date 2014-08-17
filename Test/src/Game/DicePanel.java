package Game;

import java.awt.Graphics;
import java.awt.Image;


import javax.swing.JPanel;

public class DicePanel extends JPanel{
	
	Board board;
	
	private static final Image one = CluedoCanvas.loadImage("Dice1.png");
	private static final Image two = CluedoCanvas.loadImage("Dice2.png");
	private static final Image three = CluedoCanvas.loadImage("Dice3.png");
	private static final Image four = CluedoCanvas.loadImage("Dice4.png");
	private static final Image five = CluedoCanvas.loadImage("Dice5.png");
	private static final Image six = CluedoCanvas.loadImage("Dice6.png");

	private static final Image[] diceImage = { one, one, two, three, four,
			five, six };

	int dice = 1;


	public DicePanel(Board bd) {
		super();
		board = bd;	
		this.setSize(300, 200);
	}
	
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		// Get dice roll
		dice = board.getDice();
		// Draw dice
		g.drawImage(diceImage[dice], 45, 30, 100, 100, null, null);
	}

}
