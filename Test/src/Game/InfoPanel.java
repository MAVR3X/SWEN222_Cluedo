package Game;

import java.awt.Image;

import javax.swing.JPanel;

public class InfoPanel extends JPanel {

	Board board;
	
	private static final Image one = CluedoCanvas.loadImage("Dice1.png");
	private static final Image two = CluedoCanvas.loadImage("Dice2.png");
	private static final Image three = CluedoCanvas.loadImage("Dice3.png");
	private static final Image four = CluedoCanvas.loadImage("Dice4.png");
	private static final Image five = CluedoCanvas.loadImage("Dice5.png");
	private static final Image six = CluedoCanvas.loadImage("Dice6.png");
	
	private static final Image[] squares = {
		one,
		one,
		two,
		three,
		four,
		five,
		six
	};
	
	int dice = 1;
	
	
	
	
	public InfoPanel(Board bd) {
		super();
		board = bd;
	}
	
}
