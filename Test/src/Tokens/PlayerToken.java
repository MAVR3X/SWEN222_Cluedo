package Tokens;


import Game.Card;

public class PlayerToken implements Token{

	Card.Character c;
	
	public PlayerToken(){
		
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
