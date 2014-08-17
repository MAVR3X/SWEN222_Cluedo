package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class InfoPanel extends JPanel {

	Board board;

	

	public InfoPanel(Board bd) {
		super();
		board = bd;
	}

	private static int CARD_WIDTH = 80;
	private static int CARD_HEIGHT = 120;
	private static int CARD_X = 55;
	private static int CARD_Y = 30;
	private static int CARD_SPACING = 10;

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		
		// Draw label
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 300, 15);
		
		g.setColor(Color.GRAY);
		g.fillRect(0, 15, 300, 1);
		
		Font font = new Font("Display", Font.BOLD, 11);
		g.setFont(font);
		
		g.setColor(Color.WHITE);
		g.drawString("Undealt Cards", 10, 12);
		
		
		
		// Draw cards
		if (!cards.isEmpty()) {

			for (int i = 0; i < cards.size(); i++) {

				Card c = cards.get(i);

				// Draw Surrounding Box
				g.draw3DRect(CARD_X+1, i * CARD_HEIGHT + (i * CARD_SPACING)
						+ CARD_Y-1, CARD_WIDTH, CARD_HEIGHT, true);

				g.drawImage(c.cardImage, CARD_X, i * CARD_HEIGHT + (i * CARD_SPACING)
						+ CARD_Y, null, null);
			}
		}

	}

	ArrayList<Card> cards = new ArrayList<Card>();

	public void setCards(ArrayList<Card> extraCards) {
		cards = extraCards;

	}

}
