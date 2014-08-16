package Game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Horizontal JPanel containing currentPlayer's hand, with overshadow when not
 * moused over.
 *
 */
public class PlayerPanel extends JPanel {

	Board board;

	Boolean setVisible = false;
	Boolean suggestionPressed = false;
	Boolean accusationPressed = false;

	JTabbedPane infoTabs;

	public PlayerPanel(Board bd) {
		super();
		board = bd;
	}

	private Image arrow = CluedoCanvas.loadImage("arrow.png");

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		drawCards(g);
		addButton(g);

		if (setVisible) {

		} else {

			//Draw Background
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.BLACK);

			// g.setColor(Color.gray);
			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 6 * 0.1f));

			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 1.0f));

			g.fillRect(0, 0, this.getWidth(), 15);

			g2d.setColor(Color.gray);
			g.fillRect(0, 15, this.getWidth(), 1);

			g.drawImage(arrow, (this.getWidth() / 2) - 5, 2, 20, 10, null, null);

			Font font = new Font("Arial", Font.BOLD, 11);

			g.setFont(font);
			g.setColor(Color.WHITE);
			if (getCurrentPlayer() != null) {
				g.drawString("Current Player "
						+ getCurrentPlayer().getCharacter(), 30, 11);
			}

		}

	}

	private Image sp = CluedoCanvas.loadImage("suggestionPressed.png");
	private Image s = CluedoCanvas.loadImage("suggestion.png");
	private Image ap = CluedoCanvas.loadImage("accusationPressed.png");
	private Image a = CluedoCanvas.loadImage("accusation.png");

	private void addButton(Graphics g) {

		if (suggestionPressed) {
			g.drawImage(sp, 600, 40, 150, 35, null, null);
		} else {
			g.drawImage(s, 600, 40, 150, 35, null, null);
		}
		if (accusationPressed) {
			g.drawImage(ap, 600, 90, 150, 35, null, null);
		} else {
			g.drawImage(a, 600, 90, 150, 35, null, null);
		}

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

					if (setVisible) {
						Card c = getCurrentPlayer().hand.get(i);

						// Draw Surrounding Box
						g.setColor(Color.GRAY);
						g.draw3DRect(i * CARD_WIDTH + (i * CARD_SPACING)
								+ CARD_X, CARD_Y, CARD_WIDTH, CARD_HEIGHT, true);
						g.drawImage(c.cardImage, i * CARD_WIDTH
								+ (i * CARD_SPACING) + CARD_X, CARD_Y, null,
								null);
					} else {
						g.setColor(Color.GRAY);
						g.draw3DRect(i * CARD_WIDTH + (i * CARD_SPACING)
								+ CARD_X, CARD_Y, CARD_WIDTH, CARD_HEIGHT, true);
						g.drawImage(back, i * CARD_WIDTH + (i * CARD_SPACING)
								+ CARD_X, CARD_Y, null, null);
					}

				}
			}

		}
	}

	private Player getCurrentPlayer() {
		return board.getCurrentPlayerObject();
	}

}
