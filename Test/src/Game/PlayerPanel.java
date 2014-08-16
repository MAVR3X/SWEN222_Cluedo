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

	JTabbedPane infoTabs;

	public PlayerPanel(Board bd) {
		super();
		board = bd;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(new Color(0, 105, 61));
		g.fillRect(0, 0, 20, 20);

		g.setColor(Color.black);

		Font font = new Font("Arial", Font.BOLD, 16);

		g.setFont(font);
		g.drawString("Current Player", 30, 30);
		drawCards(g);
	}

	private static int CARD_WIDTH = 80;
	private static int CARD_HEIGHT = 120;
	private static int CARD_X = 50;
	private static int CARD_Y = 30;
	private static int CARD_SPACING = 10;

	private void drawCards(Graphics g) {

		if (getCurrentPlayer() != null) {
			if (getCurrentPlayer().hand != null) {

				for (int i = 0; i < getCurrentPlayer().hand.size(); i++) {
					getCurrentPlayer().hand.get(i);

					// Draw Surrounding Box
					g.setColor(Color.GRAY);
					g.draw3DRect(i * CARD_WIDTH + (i * CARD_SPACING) + CARD_X, CARD_Y,
							CARD_WIDTH, CARD_HEIGHT, true);

				}
			}

		}
	}

	private Player getCurrentPlayer() {
		return board.getCurrentPlayerObject();
	}

	private static Image loadImage(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
