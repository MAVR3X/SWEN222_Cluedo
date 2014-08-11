package Game;

import java.util.ArrayList;

public class Board {

	private static final int BOARD_HEIGHT = 30;
	private static final int BOARD_WIDTH = 20;
	Card[][] board;
	Door[][] doors;
	ArrayList<Card> cards;

	public Board() {
		board = new Card[BOARD_HEIGHT][BOARD_WIDTH];
		doors = new Door[BOARD_HEIGHT][BOARD_WIDTH];
		createCards();
	}

	private void createCards() {
		cards = new ArrayList<Card>(); // 21 cards

		for (int i = 0; i < 3; i++) { // For each card type

			if (i == 0) {
				// NULL + 6 Chars
				for (int y = 1; y < 7; y++) {
					cards.add(new Card(i, y, 0, 0));
				}
			}
			if (i == 1) {
				// NULL + 6 Weapons
				for (int y = 1; y < 7; y++) {
					cards.add(new Card(i, 0, y, 0));
				}
			}
			if (i == 2) {
				// NULL + 9 Rooms
				for (int y = 1; y < 10; y++) {
					cards.add(new Card(i, 0, 0, y));
				}
			}
		}
		int x = 0;

	}

}
