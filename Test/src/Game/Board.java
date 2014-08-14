package Game;

import java.awt.Point;
import java.util.ArrayList;

import Tokens.PlayerToken;
import Tokens.Token;

public class Board {

	public static final int BOARD_HEIGHT = 26;
	public static final int BOARD_WIDTH = 27;
	private Token[][] tokens;
	private Door[][] doors;

	public Board() {
		setTokens(new Token[BOARD_HEIGHT][BOARD_WIDTH]);
		doors = new Door[BOARD_HEIGHT][BOARD_WIDTH];
	}

	
	
	public void generateTokens(ArrayList<Card> cards) {
		for(Card c: cards){
			switch(c.character){
			case Colonel_Mustard:
				getTokens()[1][18] = new PlayerToken(c);
				break;
			case Miss_Scarlett:
				getTokens()[8][25] = new PlayerToken(c);
				break;
			case Mrs_White:
				getTokens()[10][1] = new PlayerToken(c);
				break;
			case The_Reverend_Green:
				getTokens()[15][1] = new PlayerToken(c);
				break;
			case Mrs_Peacock:
				getTokens()[24][7] = new PlayerToken(c);
				break;
			case Professor_Plum:
				getTokens()[24][20] = new PlayerToken(c);
				break;
			default:
				break;
			}
		}
		
	}
	
	
	/**
	 * Find a tokens position from a card
	 * @param c Card of token
	 * @return X/Y of token
	 */
	Point findToken(Card c){
		for(int x = 0; x < BOARD_WIDTH; x++){
			for(int y = 0; y < BOARD_HEIGHT; y++){
				if(getTokens()[x][y].isCard(c)){
					return new Point(x,y);
				}
			}
		}
		
	
		return null;
	}



	public Token[][] getTokens() {
		return tokens;
	}



	public void setTokens(Token[][] tokens) {
		this.tokens = tokens;
	}

}
