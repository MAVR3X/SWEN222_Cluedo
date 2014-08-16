package Game;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import Game.Card.Character;
import Game.Card.Room;
import Game.Card.Weapon;
import Tokens.Token;

public class CluedoController {

	Board board;
	GameInterface interf;

	ArrayList<Player> players;
	ArrayList<Card> cards;

	Player currentPlayer;
	private int playerSteps = 0;
	int playerRoll = 0;

	private static Card solutionCharacter;
	private static Card solutionWeapon;
	private static Card solutionRoom;

	/*
	 * Initialize game
	 */
	public CluedoController() {
		board = new Board(this);
		interf = new GameInterface("Cluedo", board);
		initialise();
	}

	private void initialise() {

		createCards();
		createPlayers();
		currentPlayer = players.get(0);
		board.generateTokens(cards);
		selectSolution();
		allocateCards();
		interf.redraw();
		interf.addKeyListener(kl);
		interf.repaint();
	}

	/**
	 * Allocate cards to players, call display for remaining cards.
	 *
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

		if (cards.size() != 0) {
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

	KeyListener kl = new KeyListener() {
		public void keyPressed(KeyEvent e) {
			System.out.print(" + User: " + currentPlayer.c.character + "\n");

			int code = e.getKeyCode();
			if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT) {
				playerSteps += board.moveToken(currentPlayer.c, RIGHT);
			} else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT) {
				playerSteps += board.moveToken(currentPlayer.c, LEFT);
			} else if (code == KeyEvent.VK_UP) {
				playerSteps += board.moveToken(currentPlayer.c, UP);
			} else if (code == KeyEvent.VK_DOWN) {
				playerSteps += board.moveToken(currentPlayer.c, DOWN);
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

	public void turnComplete() {
		interf.requestFocus();
		for (int i = 0; i < players.size(); i++) {
			Player p = players.get(i);

			if (p.equals(currentPlayer)) {

				if (i < players.size() - 1) {
					i++;
					currentPlayer = players.get(i);
					break;
				} else {
					currentPlayer = players.get(0);
					break;
				}
			}

		}
		playerSteps = 0;
		System.out.println(currentPlayer.getCharacter());
	}

	public void diceRoll(int i) {
		playerRoll = i;
		interf.requestFocus();
	}

	/**
	 * Prompt user to make suggestion by asking for weapon type and person
	 *
	 */
	public void makeSuggestion() {
		interf.requestFocus();

		System.out.println("Making Suggestion");
		Card.Room room;
		Card.Weapon weapon;
		Card.Character character;

		// Ensure player is in room
		Point playerPos = board.findToken(board.getCurrentPlayerObject().c);
		int posType = board.paths[playerPos.x][playerPos.y];

		System.out.println("Player At" + playerPos + " Type: " + posType);
		if (posType <= 0 || posType >= 10) {

			JOptionPane.showMessageDialog(null,
					"You must be in a room to make a suggestion");
			return;
		}

		// Get Weapon
		room = board.getRoom(posType);

		Game.Card.Weapon[] wepPosibilities = Arrays.copyOfRange(
				Card.Weapon.values(), 1, Card.Weapon.values().length);
		weapon = (Card.Weapon) JOptionPane.showInputDialog(interf,
				"Please select the weapon:", "Suggestion Submission",
				JOptionPane.PLAIN_MESSAGE,
				UIManager.getIcon("OptionPane.informationIcon"),
				wepPosibilities, wepPosibilities[0]);

		Game.Card.Character[] carPosibilities = Arrays.copyOfRange(
				Card.Character.values(), 1, Card.Character.values().length);
		character = (Card.Character) JOptionPane.showInputDialog(interf,
				"Please select the murderer:", "Suggestion Submission",
				JOptionPane.PLAIN_MESSAGE,
				UIManager.getIcon("OptionPane.informationIcon"),
				carPosibilities, carPosibilities[0]);

		System.out.println(character + " : " + weapon + " : " + room);

		makeSuggestion(room, character, weapon);

	}

	/**
	 * Verify the validity of claim. Check against each players' cards and fail
	 * on first instance of wrong card then move to other players.
	 *
	 * @param room
	 * @param character
	 * @param weapon
	 */
	private void makeSuggestion(Room room, Character character, Weapon weapon) {

		// Iterate each player
		int curPlayerPos = players.indexOf(currentPlayer);
		int i = curPlayerPos + 1;

		for (; i < players.size(); i++) {
			Player playerToCheck = players.get(i);
			for (Card card : playerToCheck.hand) {
				if (card.equals(room) || card.equals(character)
						|| card.equals(weapon)) {
					displayCard(card);
				}

			}

		}
	}

	public void displayCard(Card c) {
		JOptionPane.showMessageDialog(interf,
				"Card matches: ");
	}

}
