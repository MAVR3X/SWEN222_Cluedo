package Game;

public class Player {

	Card[] hand = new Card[3];

	public Player() {

	}

	public boolean addCard(Card card) {

		for (int i = 0; i < 3; i++) {
			if (hand[i] == null) {
				hand[i] = card;
				return true;
			}
		}
		return false;
	}

	public boolean hasCard(Card card) {
		for (int i = 0; i < 3; i++) {
			if (hand[i] != null) {
				if (hand[i].equals(card)) {
					return true;
				}
			}
		}
		return false;
	}

}
