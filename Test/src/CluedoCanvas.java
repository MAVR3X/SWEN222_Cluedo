import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class CluedoCanvas extends JPanel{

	private static int BOARD_HEIGHT = 600;
	private static int WINDOW_WIDTH;
	private static int WINDOW_HEIGHT;
	
	public CluedoCanvas(int width, int height){
		super();
		WINDOW_WIDTH  = width;
		WINDOW_HEIGHT = height;
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);

	//Basic frame coloring
	//drawBackgrounds(g);
	
	}
	
	private static int DICE_SIZE = 40;
	private static int CARD_HEIGHT = 50;
	private static int CARD_WIDTH = 30;
	private static int CARD_X = 300;
	private static int NAME_HEIGHT = 30;
	
	/**
	 * Draw backgrounds of interface sections
	 * @param g graphics item to draw on
	 */
	private void drawBackgrounds(Graphics g) {
		//Draw Board square
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WINDOW_WIDTH, BOARD_HEIGHT);
		
		//Interface square
		g.setColor(new Color(50,50,50));
		g.fillRect(0, BOARD_HEIGHT, WINDOW_WIDTH, BOARD_HEIGHT);
		
		
		//Draw dice area
		g.setColor(Color.WHITE);
		g.fillRect(DICE_SIZE,  BOARD_HEIGHT + DICE_SIZE, DICE_SIZE * 5, DICE_SIZE * 2);
		
		//Play name section
		g.setColor(new Color(25,25,25));
		g.fillRect(0,  BOARD_HEIGHT - (NAME_HEIGHT/ 2), WINDOW_WIDTH, NAME_HEIGHT);
		
		//Cards
		g.setColor(Color.WHITE);
		int cardSectionHeight = BOARD_HEIGHT + ((WINDOW_HEIGHT - BOARD_HEIGHT) / 2) - (CARD_HEIGHT / 2); 
		g.fillRect(CARD_X, cardSectionHeight, CARD_WIDTH * 5, CARD_HEIGHT + 5);
		
		
	}
	
}
