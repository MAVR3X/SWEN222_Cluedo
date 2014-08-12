package Game;

import java.util.ArrayList;

public class CluedoController {

	Board board;
	GameInterface interf;

	ArrayList<Player> players;
	ArrayList<Card> cards;

	Player currentPlayer;

	/*
	 * Initialize game
	 */
	public CluedoController() {
		board = new Board();
		interf = new GameInterface();
		initialise();

	}

	private void initialise() {
		createPlayers();
		createCards();
	}

	public void createPlayers() {
		
		

	}

	private void createCards() {

		cards = new ArrayList<Card>(); // 21 cards

		for (int i = 0; i < 3; i++) { // For each card type
			Card c;
			if (i == 0) {
				// NULL + 6 Chars
				for (int y = 1; y < 7; y++) {
					c = new Card(i, y, 0, 0);
					cards.add(c);
				}
			}
			if (i == 1) {
				// NULL + 6 Weapons
				for (int y = 1; y < 7; y++) {
					c = new Card(i, 0, y, 0);
					cards.add(c);
				}
			}
			if (i == 2) {
				// NULL + 9 Rooms
				for (int y = 1; y < 10; y++) {
					c = new Card(i, 0, 0, y);
					cards.add(c);
				}
			}

		}

	}

	private void createTokens() {
	}

	public void start() {

	}
}
