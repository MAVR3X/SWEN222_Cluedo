package Game;

import java.util.ArrayList;

public class CluedoController {

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
		int i = 0;
	}

	private void initialise() {
		createPlayers();
		createCards();
		selectSolution();
		allocateCards();
		board.generateTokens();
		
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
		players = interf.getPlayers();
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

	public void start() {

	}
}
