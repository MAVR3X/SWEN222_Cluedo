package Tokens;



import Game.Card;

public class PlayerToken implements Token{

	Card.Character c;
	
	public PlayerToken(Card c){
		this.c = c.character;
	}

	public Game.Card.Character getCharacter(){
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


}
