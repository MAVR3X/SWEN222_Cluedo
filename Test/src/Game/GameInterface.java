package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UIManager;


public class GameInterface extends JFrame implements ActionListener,
		MouseListener {
	private static int FRAME_WIDTH = 1000;
	private static int FRAME_HEIGHT = 1000;
	private static int CANVAS_WIDTH = 800;
	private static int CANVAS_HEIGHT = 800;

	private CluedoCanvas canvas;
	private Board board;

	ArrayList<Player> players;

	public GameInterface(String title, Board board) {
		super(title);
		this.board = board;
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setResizable(false);
		setVisible(true);

		setLayout(new BorderLayout());
		addMenu();
		addCanvas();
		addLowerInterface();
		addRightInterface();


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

	private void addRightInterface() {
		JPanel gui = new JPanel();
		gui.setBounds(CANVAS_WIDTH, 0, FRAME_WIDTH - CANVAS_WIDTH, FRAME_HEIGHT);
		gui.setBackground(Color.BLACK);
		gui.setAlignmentX(RIGHT_ALIGNMENT);

		gui.setLayout(new BoxLayout(gui, BoxLayout.Y_AXIS));


		//Next Player Button
		JButton button = new JButton("Turn Complete");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				board.turnComplete();
				repaint();
			}
		});

		button.setAlignmentX(gui.CENTER_ALIGNMENT);
		gui.add(button);
		
		JButton RollDice = new JButton("Roll Dice");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//TODO Roll Dice method;
				repaint();
			}
		});
		
		RollDice.setAlignmentX(gui.CENTER_ALIGNMENT);
		gui.add(RollDice);
		
		this.add(gui);


	}

	PlayerPanel gui;
	
	private void addLowerInterface() {
		gui = new PlayerPanel(board);
		gui.setBounds(0, CANVAS_HEIGHT, CANVAS_WIDTH, FRAME_HEIGHT - CANVAS_HEIGHT);
		gui.setBackground((new Color(0, 105,61)));
		gui.setLayout(null);
		

		gui.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		        gui.setVisible = true;
		        gui.repaint();
		    }
		    public void mouseExited(MouseEvent e) {
		    	gui.setVisible = false;
		    	gui.repaint();
		    }

		    public void mouseClicked(MouseEvent e){
		    	Point loc = e.getLocationOnScreen();
		    	loc.x = loc.x - canvas.getLocationOnScreen().x;
		    	loc.y = loc.y - canvas.getLocationOnScreen().y;
		    	//System.out.printf("x: %d, y: %d\n",loc.x, loc.y);

				if(loc.x > 600 && loc.x < 750){
					if(loc.y > 820 && loc.y < 860){
						gui.suggestionPressed = true;
						gui.repaint();
						board.makeSuggestion();
						gui.suggestionPressed = false;
						gui.repaint();
					}
					if(loc.y > 870 && loc.y < 910){
						gui.accusationPressed = true;
						gui.repaint();
						board.makeAccusation();
						gui.accusationPressed = false;
						gui.repaint();
					}
				}
		    }
		});

		JButton button = new JButton("Make Accusation");
		button.setBounds(20, 20, 40, 15);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int i = (int) (Math.random() * 6);
				board.diceRoll(i);
			}
		});

		JButton bn = new JButton("Roll? Dice");
		bn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int i = (int) (Math.random() * 6);
				board.diceRoll(i);
			}
		});
		gui.add(bn);
		this.add(gui);
		gui.repaint();

	}


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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
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
	 * Uses Jradio buttons to get selection of number of players
	 * @return number of players
	 */
	public int getPlayerCount(){
		
		// Panel to hold the contents
		JPanel pane = new JPanel(new BorderLayout());
		
		
		
		// create a button group to hold the radio buttons
		final ButtonGroup group = new ButtonGroup();
	
		// Create a panel to hold the buttons
		JPanel radioPane = new JPanel(new GridLayout(0,4));
		
		// Create the buttons
		JRadioButton radioButton;
		
		group.add(radioButton = new JRadioButton("3", true));
		radioButton.setActionCommand("3");
		group.add(radioButton);
		radioPane.add(radioButton);
		group.add(radioButton = new JRadioButton("4"));
		radioButton.setActionCommand("4");
		group.add(radioButton);
		radioPane.add(radioButton);
		group.add(radioButton = new JRadioButton("5"));
		radioButton.setActionCommand("5");
		group.add(radioButton);
		radioPane.add(radioButton);
		group.add(radioButton = new JRadioButton("6"));
		radioButton.setActionCommand("6");
		group.add(radioButton);
		radioPane.add(radioButton);
		
		// create a label
		JLabel label = new JLabel("Please select number of players");
			
		// add the label and radio pane to the pane
		pane.add(label, BorderLayout.NORTH);
		pane.add(radioPane, BorderLayout.CENTER);

		// create a dialog to wait for user selection
		String [] options = {"ok"};
		
		int result = JOptionPane.showOptionDialog(null, pane, "New Game",
	            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
	            options, 0);
		
		ButtonModel model = group.getSelection();
		
		return Integer.parseInt(model.getActionCommand());
	}

	
	
	/**
	 * Creates an option pane and allows users to select character
	 *
	 * @param cards
	 *
	 * @return ArrayList of players
	 */
	public ArrayList<Player> getPlayers(ArrayList<Card> cards) {
		players = new ArrayList<Player>(); // The list of
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
			for (Card card : cards) {
				if (card.type == Card.Type.Character) {
					if (card.character == c) {
						players.add(new Player(card));
					}
				}
			}

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
