package Tokens;

import Game.Card;



public interface Token {

	public boolean wasMoved = false;

	boolean isCard(Card c);

	void setMoved(boolean b);
	boolean wasMoved();

	
}
