package Game;

import java.awt.Point;
import java.util.ArrayList;

import Tokens.Token;

public class Board {

	public static final int BOARD_HEIGHT = 26;
	public static final int BOARD_WIDTH = 27;
	Token[][] tokens;
	Door[][] doors;

	public Board() {
		tokens = new Token[BOARD_HEIGHT][BOARD_WIDTH];
		doors = new Door[BOARD_HEIGHT][BOARD_WIDTH];
	}

	public void generateTokens() {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * Find a tokens position from a card
	 * @param c Card of token
	 * @return X/Y of token
	 */
	Point findToken(Card c){
		for(int x = 0; x < BOARD_WIDTH; x++){
			for(int y = 0; y < BOARD_HEIGHT; y++){
				if(tokens[x][y].isCard(c)){
					return new Point(x,y);
				}
			}
		}
		
	
		return null;
	}

}
