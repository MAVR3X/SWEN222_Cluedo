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

public class PlayerPanel extends JPanel{

	Player currentPlayer;
	JTabbedPane infoTabs;


	public PlayerPanel(Player player){
		super();
		currentPlayer = player;

	}


	@Override
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(new Color(0, 105,61));
		g.fillRect(0, 0, 20, 20);

		g.setColor(Color.black);

		Font font = new Font("Arial",Font.BOLD,16);

		g.setFont(font);
		g.drawString("Current Player", 30 , 30);
		drawCards(g);
	}


	private static int CARD_ = 50;
	private static int CARD_SPACING = 10;
	private void drawCards(Graphics g) {


	}


	private static Image loadImage(String string) {
		// TODO Auto-generated method stub
		return null;
	}





}
