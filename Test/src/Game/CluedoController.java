package Game;

import java.util.ArrayList;

public class CluedoController {

	Board board;
	
	ArrayList<Player> players;
	ArrayList<Card> cards;
	Player currentPlayer;
	
	/*
	 * Initialize game
	 */
	public CluedoController(){
		board = new Board();
		createCards();
		createPlayers();
		
	}
	
	public void createPlayers(){
		
		
	}
	
	
	
	public void start(){
		
		
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
