package Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Game.Card.Character;

public class GameInterface extends javax.swing.JFrame implements
		ActionListener, MouseListener {
	private static int FRAME_WIDTH = 600;
	private static int FRAME_HEIGHT = 800;
	
	private CluedoCanvas canvas;

	public GameInterface() {
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setResizable(false);
		setVisible(true);
		
		setLayout(new BorderLayout());
		addMenu();
		addCanvas();
		add(canvas, BorderLayout.CENTER);	
		addInterface();

	}

	
	private void addInterface() {
		JPanel gui = new JPanel();
		gui.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		gui.setBackground(Color.BLACK);
		this.add(gui);
		
	}

	
	private static int CANVAS_WIDTH = FRAME_WIDTH;
	private static int CANVAS_HEIGHT = 500;
	
	/**
	 * Create canvas to draw UI components on the panel.
	 */
	private void addCanvas() {
		canvas = new CluedoCanvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		canvas.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		canvas.setBackground(Color.WHITE);
		this.add(canvas);
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
	 * Override of standard actionperformed to allow for jmenu items to prompt custom actions.
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
	 * @return num of players
	 * @Author: Isaac
	 */
	public int getPlayerCount() {
		//Prompt for number of players:
		String[] posibilities = {"2","3","4","5","6"};
		return Integer.valueOf((String) JOptionPane.showInputDialog(this, "please select number of players:", "Player Count", JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.informationIcon"), posibilities, "1"));
	}

/**
 * Take input from JOptionPane for player character
 * 
 * Dynamic - Can support additon of new characters
 * @return Card.Character selected by user
 * @Author: Isaac
 */
	public Card.Character newPlayer() {
		//Get list of possiblites & shave off NULL
		Card.Character[] posibilities = Arrays.copyOfRange(Card.Character.values(), 1, Card.Character.values().length);
		return (Card.Character) JOptionPane.showInputDialog(this, "please select your desired character", "Character Selection", JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.informationIcon"), posibilities, Card.Character.Colonel_Mustard);
	}

}
