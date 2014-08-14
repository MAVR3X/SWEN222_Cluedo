package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Game.Card.Character;

public class GameInterface extends JFrame implements ActionListener,
		MouseListener {
	private static int FRAME_WIDTH = 800;
	private static int FRAME_HEIGHT = 1000;

	private CluedoCanvas canvas;
	private Board board;

	public GameInterface(String title, Board board) {
		super(title);

		this.board = board;
		
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setResizable(false);
		setVisible(true);

		setLayout(new BorderLayout());
		addMenu();
		addCanvas();
		addInterface();

		JLabel kitchen = new JLabel("Kitchen");
		kitchen.setForeground(Color.black);
		add(kitchen, BorderLayout.CENTER);
		
		// Center window in screen
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();
		setBounds((scrnsize.width - getWidth()) / 2,
				(scrnsize.height - getHeight()) / 2, getWidth(), getHeight());
			
		// Display window
		setVisible(true);		

	}

	private void addInterface() {
		JPanel gui = new JPanel();
		gui.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		gui.setBackground(Color.BLACK);
		this.add(gui, BorderLayout.SOUTH);

	}

	private static int CANVAS_WIDTH = FRAME_WIDTH;
	private static int CANVAS_HEIGHT = 800;

	/**
	 * Create canvas to draw UI components on the panel.
	 */
	private void addCanvas() {
		canvas = new CluedoCanvas(CANVAS_WIDTH, CANVAS_HEIGHT, board);
		canvas.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		canvas.setBackground(Color.WHITE);
		add(canvas, BorderLayout.CENTER);
	}

	/**
	 * Create menu and add options, then assign to this interface's jmenubar.
	 */
	public void addMenu() {
		JMenu fileMenu = new JMenu("File");

		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this);
		fileMenu.add(menuItem);

		JMenu gameMenu = new JMenu("Game");

		JMenuBar menu = new JMenuBar();
		menu.add(fileMenu);
		menu.add(gameMenu);

		this.setJMenuBar(menu);
	}

	/**
	 * Override of standard actionperformed to allow for jmenu items to prompt
	 * custom actions.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {

		// User wishes to exit, prompt for confirmation with
		// JOptionPanel/JDialog
		if (arg0.getActionCommand().equals("Exit")) {

			int input = JOptionPane.showOptionDialog(null,
					"Are you sure you wish to exit?", "Are you a quitter?",
					JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, null, null);
			if (input == JOptionPane.OK_OPTION) {
				System.exit(1);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * Take input from JOptionPane for player count
	 * 
	 * @return num of players
	 * @Author: Isaac
	 */
	public int getPlayerCount() {
		// Prompt for number of players:
		String[] posibilities = { "2", "3", "4", "5", "6" };
		return Integer.valueOf((String) JOptionPane.showInputDialog(this,
				"please select number of players:", "Player Count",
				JOptionPane.PLAIN_MESSAGE,
				UIManager.getIcon("OptionPane.informationIcon"), posibilities,
				"1"));
	}

	/**
	 * Creates an option pane and allows users to select character
	 * 
	 * @return ArrayList of players
	 */
	public ArrayList<Player> getPlayers() {
		ArrayList<Player> players = new ArrayList<Player>(); // The list of
																// players
		int playerCount = getPlayerCount(); // The number of players

		// The number of character options to start with
		Card.Character[] possibilities = Arrays.copyOfRange(
				Card.Character.values(), 1, Card.Character.values().length);

		// For each character chosen the list of available characters is
		// modified to account for the character just selected
		for (int i = 0; i < playerCount; i++) {
			Card.Character[] poss = new Card.Character[possibilities.length - 1];
			Card.Character c = (Card.Character) JOptionPane.showInputDialog(
					this, "please select your desired character",
					"Character Selection", JOptionPane.PLAIN_MESSAGE,
					UIManager.getIcon("OptionPane.informationIcon"),
					possibilities, possibilities[0]);
			players.add(new Player(c));

			int k = 0;
			for (int j = 0; j < possibilities.length; j++) {
				if (c != possibilities[j]) {
					poss[k++] = possibilities[j];
				}
			}
			possibilities = poss;
		}

		return players;
	}

	// TODO Dislay extra cards
	public void displayCards(ArrayList<Card> extraCards) {
		
		
	}

	public void redraw() {
		canvas.repaint();
		
	}

}
