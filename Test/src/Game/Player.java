       package Game;

import java.util.ArrayList;

import Game.Card.Character;

/**
 * Stores player hand, character card and name
 */
public class Player {

	ArrayList<Card> hand = new ArrayList<Card>();
	Card c;
	public String name = "Sir Mixalot";
	public boolean hasLost = false;;

	public Player(Card character, String name) {
		this.name = name;
		this.c = character;
	}

	public boolean addCard(Card card) {

		for (Card c : hand) {
			if (c.equals(card)) {
				return false;
			}
		}
		return hand.add(card);
	}

	public boolean hasCard(Card card) {
		for (Card c : hand) {
			if (c.equals(card)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Player [hand=" + hand + ", character=" + c.character + "]";
	}


	public Character getCharacter(){
		return c.character;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (c == null) {
			if (other.c != null)
				return false;
		} else if (!c.equals(other.c))
			return false;
		return true;
	}

	public String getName(){
		return name;
	}

}
