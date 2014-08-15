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
}
