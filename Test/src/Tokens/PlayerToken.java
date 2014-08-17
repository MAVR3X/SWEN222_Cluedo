package Tokens;



import Game.Card;

public class PlayerToken implements Token{

	Card.Character c;
	
	public boolean wasMoved = false;
	
	public PlayerToken(Card c){
		this.c = c.character;
	}

	public Card.Character getCharacter(){
		return c;
	}
	
	@Override
	public boolean isCard(Card card) {
		if(card == null){
			return false;
		}
		if(card.character == c){
			return true;
		}
		return false;
	}

	@Override
	public void setMoved(boolean b) {
		wasMoved = b;
		
	}

	@Override
	public boolean wasMoved() {
		return wasMoved;
	}


}
