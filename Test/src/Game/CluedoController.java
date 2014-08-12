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
		interf = new GameInterface();
		initialise();
		int i = 0;
	}

	private void initialise() {
		createPlayers();
		createCards();
		selectSolution();
	}

	/**
	 * Using number of possible cards of each type select
	 * 3 of each type and remove from list and add to solution fields.
	 */
	private void selectSolution() {
		int characters = Card.Character.values().length - 1;
		int weapons = Card.Weapon.values().length - 1;
		int rooms = Card.Room.values().length - 1;

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
	 * Create players by prompting for player count + player names for each player
	 * Loop until valid names are selected
	 */
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

			// Success, add new player
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
