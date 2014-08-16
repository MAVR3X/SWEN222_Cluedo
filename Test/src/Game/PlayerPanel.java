package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

public class PlayerPanel extends JPanel {

	Board board;

	Boolean setVisible = false;

	JTabbedPane infoTabs;

	public PlayerPanel(Board bd) {
		super();
		board = bd;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		
		drawCards(g);
		
		

		if (setVisible) {
			
			addButton(g);
		} else {
			//g.setColor();
			
			Font font = new Font("Arial", Font.BOLD, 16);

			g.setFont(font);
			g.setColor(Color.WHITE);
			if (getCurrentPlayer() != null) {
				g.drawString("Current Player "
						+ getCurrentPlayer().getCharacter(), 30, 30);
			}

		}

	}

	private void addButton(Graphics g) {
		//System.out.println("yup");
		g.setColor(Color.black);
		g.fillRect(600, 20, 150, 35);

	}

	private static int CARD_WIDTH = 80;
	private static int CARD_HEIGHT = 120;
	private static int CARD_X = 50;
	private static int CARD_Y = 20;
	private static int CARD_SPACING = 10;

	private Image back = CluedoCanvas.loadImage("back.png");
	
	
	
	private void drawCards(Graphics g) {

		if (getCurrentPlayer() != null) {
			if (getCurrentPlayer().hand != null) {

				for (int i = 0; i < getCurrentPlayer().hand.size(); i++) {
					
					if(setVisible){
						Card c = getCurrentPlayer().hand.get(i);

						// Draw Surrounding Box
						g.setColor(Color.GRAY);
						g.draw3DRect(i * CARD_WIDTH + (i * CARD_SPACING) + CARD_X,
								CARD_Y, CARD_WIDTH, CARD_HEIGHT, true);
						g.drawImage(c.cardImage, i * CARD_WIDTH
								+ (i * CARD_SPACING) + CARD_X, CARD_Y, null, null);
					}
					else{
						g.setColor(Color.GRAY);
						g.draw3DRect(i * CARD_WIDTH + (i * CARD_SPACING) + CARD_X,
								CARD_Y, CARD_WIDTH, CARD_HEIGHT, true);
						g.drawImage(back, i * CARD_WIDTH
								+ (i * CARD_SPACING) + CARD_X, CARD_Y, null, null);
					}
					

				}
			}

		}
	}

	private Player getCurrentPlayer() {
		return board.getCurrentPlayerObject();
	}

}
