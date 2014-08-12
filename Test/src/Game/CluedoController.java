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
		players = new ArrayList<Player>();
		int playerCount = interf.getPlayerCount();
		for (int i = 0; i < playerCount; i++) {
			// Loop till a legitimate user is selected.
			Card.Character player = Card.Character.NULL;
			boolean isValid = false;
			
			while (!isValid) {
				 player = interf.newPlayer();

				// Verify not already used
				isValid = true;
				for (Player p : players) {
					if (p.character.equals(player)) {
						isValid = false;
					}
				}				
			}
			
			//Success, add new player
			players.add(new Player(player));
		
		}

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
