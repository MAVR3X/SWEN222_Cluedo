       package Game;

import java.util.ArrayList;
import java.util.Arrays;

import Game.Card.Character;

public class Player {

	ArrayList<Card> hand = new ArrayList<Card>();
	Card c;

	public Player(Card character) {
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
	
	
}
