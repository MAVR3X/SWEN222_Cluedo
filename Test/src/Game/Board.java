package Game;

import java.util.ArrayList;

import Tokens.Token;

public class Board {

	private static final int BOARD_HEIGHT = 30;
	private static final int BOARD_WIDTH = 20;
	Token[][] board;
	Door[][] doors;

	CluedoCanvas canvas;

	public Board() {
		board = new Token[BOARD_HEIGHT][BOARD_WIDTH];
		doors = new Door[BOARD_HEIGHT][BOARD_WIDTH];
		canvas = new CluedoCanvas(BOARD_WIDTH, BOARD_HEIGHT);
	}

}
