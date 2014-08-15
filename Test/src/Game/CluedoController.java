package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class CluedoController{

	Board board;
	GameInterface interf;

	ArrayList<Player> players;
	ArrayList<Card> cards;

	Player currentPlayer;

	private static Card solutionCharacter;
	private static Card solutionWeapon;
	private static Card solutionRoom;

	/*
	 * Initialize game
	 */
	public CluedoController() {
		board = new Board();
		interf = new GameInterface("Cluedo", board);
		initialise();
	}

	private void initialise() {
		
		createCards();
		createPlayers();
		board.generateTokens(cards);
		selectSolution();
		allocateCards();
		interf.redraw();
		interf.addKeyListener(kl);
		
	}

	/**
	 * Allocate cards to players, call display for remaining cards. 
	 * @author isaac
	 */
	private void allocateCards() {
		// Verify there are enough cards left
		while (cards.size() >= players.size()) {
			for (int p = 0; p < players.size(); p++) {
				int cardNum = (int) (Math.random() * (cards.size() - 1));
				players.get(p).addCard(cards.remove(cardNum));
			}
		}
		
		if(cards.size() != 0){
			interf.displayCards(cards);	
		}

	}

	/**
	 * Using number of possible cards of each type select 3 of each type and
	 * remove from list and add to solution fields.
	 * 
	 * Dynamic - Handles adding new cards of any type
	 * 
	 * @Author: Isaac
	 */
	private void selectSolution() {
		int characters = Card.charCount() - 1;
		int weapons = Card.wepCount() - 1;
		int rooms = Card.roomCount() - 1;

		// select character
		int selector = (int) (Math.random() * characters);
		solutionCharacter = cards.remove(selector);

		// weapon
		selector = (int) (Math.random() * weapons);
		solutionWeapon = cards.remove(characters - 1 + selector);

		// room
		selector = (int) (Math.random() * rooms);
		solutionRoom = cards.remove(characters + weapons - 2 + selector);

	}

	/**
	 * Create the list of players
	 * 
	 */
	public void createPlayers() {
		players = new ArrayList<Player>();
		players = interf.getPlayers(cards);
	}

	/**
	 * Create deck by creating an instance of each card type.
	 * 
	 * Dynamic to allow for the addition of new cards of any type.
	 * 
	 * @Author: Isaac
	 */
	private void createCards() {

		int characters = Card.charCount();
		int weapons = Card.wepCount();
		int rooms = Card.roomCount();

		cards = new ArrayList<Card>(); // 21 cards

		// Loop through each type
		for (int i = 0; i < 3; i++) {
			Card c;
			if (i == 0) {
				// NULL + 6 Chars
				for (int y = 1; y < characters; y++) {
					c = new Card(i, y, 0, 0);
					cards.add(c);
				}
			}
			if (i == 1) {
				// NULL + 6 Weapons
				for (int y = 1; y < weapons; y++) {
					c = new Card(i, 0, y, 0);
					cards.add(c);
				}
			}
			if (i == 2) {
				// NULL + 9 Rooms
				for (int y = 1; y < rooms; y++) {
					c = new Card(i, 0, 0, y);
					cards.add(c);
				}
			}

		}

	}
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	public static final int LEFT = 4;
	
	
	
	KeyListener kl = new KeyListener(){
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT){			
			board.moveToken(players.get(0).c, RIGHT);
		} else if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT) {
			board.moveToken(players.get(0).c, LEFT);
		} else if(code == KeyEvent.VK_UP) {
			board.moveToken(players.get(0).c, UP);
		} else if(code == KeyEvent.VK_DOWN) {
			board.moveToken(players.get(0).c, DOWN);
		}
		interf.redraw();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		}
	};
}
