package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JPanel;

public class InfoPanel extends JPanel {

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

	public InfoPanel(Board bd) {
		super();
		board = bd;
	}

	private static int CARD_WIDTH = 80;
	private static int CARD_HEIGHT = 120;
	private static int CARD_X = 55;
	private static int CARD_Y = 200;
	private static int CARD_SPACING = 10;

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		// Get dice roll
		dice = board.getDice();
		// Draw dice
		g.drawImage(diceImage[dice], 45, 30, 100, 100, null, null);

		
		// Draw label
		g.setColor(Color.BLACK);
		g.fillRect(0, 155, 300, 15);
		
		g.setColor(Color.GRAY);
		g.fillRect(0, 170, 300, 1);
		
		Font font = new Font("Display", Font.BOLD, 11);
		g.setFont(font);
		
		g.setColor(Color.WHITE);
		g.drawString("Undealt Cards", 10, 167);
		
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
