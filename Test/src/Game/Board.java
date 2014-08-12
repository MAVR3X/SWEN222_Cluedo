package Game;

import java.util.ArrayList;

import Tokens.Token;

public class Board {

	public static final int BOARD_HEIGHT = 30;
	public static final int BOARD_WIDTH = 20;
	Token[][] board;
	Door[][] doors;

	public Board() {
		board = new Token[BOARD_HEIGHT][BOARD_WIDTH];
		doors = new Door[BOARD_HEIGHT][BOARD_WIDTH];
	}

}
