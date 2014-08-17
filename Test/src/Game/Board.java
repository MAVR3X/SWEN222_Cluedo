package Game;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Game.Card.Character;
import Game.Card.Room;
import Tokens.PlayerToken;
import Tokens.Token;

/**
 * Model in model view design pattern
 *
 * Contains all current game data and is set by state information from
 * Controller(CluedoController)
 *
 */
public class Board {

	public static final int BOARD_HEIGHT = 27;
	public static final int BOARD_WIDTH = 26;
	private Token[][] tokens;
	private CluedoController controller;

	public Board(CluedoController cc) {
		controller = cc;
		setTokens(new Token[BOARD_WIDTH][BOARD_HEIGHT]);
	}

	/**
	 * Place initial character tokens
	 *
	 * @param cards
	 *            to link to tokens
	 */
	public void generateTokens(ArrayList<Card> cards) {
		for (Card c : cards) {
			switch (c.character) {
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
	 *
	 * @param c
	 *            Card of token
	 * @return X/Y of token
	 */
	public Point findToken(Card c) {
		for (int x = 0; x < BOARD_WIDTH; x++) {
			for (int y = 0; y < BOARD_HEIGHT; y++) {
				if (getTokens()[x][y] != null) {
					if (getTokens()[x][y].isCard(c)) {
						return new Point(x, y);
					}
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

	// Board Token Paths
	// 0 : Hallway paths
	// 1 : Kitchen
	// 10: Kitchen door (Can only be accessed from south)
	// 100:Kitchen passage to Study (Go to point 23, 22)
	// 2 : Ball Room
	// 20: Ball Room door
	// 3 : Conservatory
	// 30: Conservatory door (Can only be accessed from south)
	// 300:Conservatory passage to Lounge (Go to point 1, 21)
	// 4 : Billiard Room
	// 40: Billiard Room door
	// 5 : Library
	// 50: Library door
	// 6 : Study
	// 60: Study door (Can only be accessed from north)
	// 600:Study passage to Kitchen (Go to point 5, 2)
	// 7 : Hall
	// 70: Hall door
	// 8 : Lounge
	// 80: Lounge door (Can only be accessed from north)
	// 800:Lounge passage to Conservatory (Go to point 23, 5)
	// 9 : Dining Room
	// 90: Dining Room door
	//

	int[][] paths = loadBoardImage("boardPaths.txt");

	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	public static final int LEFT = 4;

	public static final Point STUDY = new Point(23, 22);
	public static final Point LOUNGE = new Point(1, 21);
	public static final Point KITCHEN = new Point(5, 2);
	public static final Point CONSERVATORY = new Point(23, 5);

	/**
	 *
	 * @return 0, if not valid move, -1 if moving within a room, 1 if moving in
	 *         hallway, 2 if moved from doorway into room, 3 if moved from room to doorway 6 if used passage
	 */
	public int moveToken(Card c, int direction) {
		
		
		Point currentPos = findToken(c);

		int currentTile = paths[currentPos.x][currentPos.y];
		Point newPos = new Point(currentPos.x, currentPos.y);

		System.out.println(currentPos);

		// Save proposed new position in newPos
		switch (direction) {
		case UP:
			newPos.y = currentPos.y - 1;
			break;
		case DOWN:
			newPos.y = currentPos.y + 1;
			break;
		case RIGHT:
			newPos.x = currentPos.x + 1;
			break;
		case LEFT:
			newPos.x = currentPos.x - 1;
			break;
		}

		if(newPos.x>=26 || newPos.y>=27) return 0;
		
		int newTile = paths[newPos.x][newPos.y];

		if (tokens[newPos.x][newPos.y] != null)
			return 0; // Something already occupying space

		if (currentTile == 0) { // Token is in a hallway

			if (newTile == 0) { // new position is in a hallway
				moveToken(currentPos, newPos);
				return 1;
			} else if (newTile >= 10 && newTile < 100) { // new position is a
															// doorway

				if (newTile == 10 || newTile == 30) { // must enter from south
					if (direction == UP) {
						moveToken(currentPos, newPos);
						return 1;
					}
					return 0; // invalid move
				} else if (newTile == 60 || newTile == 80) { // must enter from
																// north
					if (direction == DOWN) {
						moveToken(currentPos, newPos);
						return 1;
					}
					return 0; // invalid move
				} else { // Normal doorway
					moveToken(currentPos, newPos);
					return 1;
				}
			}
		} else if (currentTile > 0 && currentTile < 10) { // Token is in a room

			if (newTile > 0 && newTile < 10) { // new position is still in room
				moveToken(currentPos, newPos);
				return -1;
			} else if (newTile >= 10 && newTile < 100) { // new position is in
														// doorway
				moveToken(currentPos, newPos);
				return 3;
			} else if (newTile >= 100) { // new position is a passage

				switch (newTile) {
				case 100: // Kitchen passage to Study (Go to point 23, 22)
					newPos = STUDY;
					if (tokens[newPos.x][newPos.y] != null)
						return 0; // Something already occupying space
					moveToken(currentPos, newPos);
					return 6;

				case 300: // Conservatory passage to Lounge (Go to point 1, 21)
					newPos = LOUNGE;
					if (tokens[newPos.x][newPos.y] != null)
						return 0; // Something already occupying space
					moveToken(currentPos, newPos);
					return 6;

				case 600: // Study passage to Kitchen (Go to point 5, 2)
					newPos = KITCHEN;
					if (tokens[newPos.x][newPos.y] != null)
						return 0; // Something already occupying space
					moveToken(currentPos, newPos);
					return 6;

				case 800: // Lounge passage to Conservatory (Go to point 23, 5)
					newPos = CONSERVATORY;
					if (tokens[newPos.x][newPos.y] != null)
						return 0; // Something already occupying space
					moveToken(currentPos, newPos);
					return 6;

				}
			}
		} else { // Token is in a doorway
			if (newTile > 0 && newTile < 10) { // new position is still in room
				moveToken(currentPos, newPos);
				return 2;
			} else { // new position is a hallway

				if (currentTile == 10 || currentTile == 30) { // must go down
					if (direction == DOWN) {
						moveToken(currentPos, newPos);
						return 1;
					}
					return 0; // invalid move
				} else if (currentTile == 60 || currentTile == 80) { // must go
																		// up
					if (direction == UP) {
						moveToken(currentPos, newPos);
						return 1;
					}
					return 0; // invalid move
				} else { // Normal doorway
					moveToken(currentPos, newPos);
					return 1;
				}
			}
		}
		return 0;
	}

	/**
	 * Helper method to move Token from old Point to New Point
	 *
	 * @param oldP
	 * @param newP
	 */
	public void moveToken(Point oldP, Point newP) {
		this.tokens[newP.x][newP.y] = this.tokens[oldP.x][oldP.y];
		this.tokens[oldP.x][oldP.y] = null;
	}

	/**
	 * Loads a 2D array of integers from a file
	 *
	 * @param filename
	 * @return 2D array of int
	 */
	private int[][] loadBoardImage(String filename) {

		int[][] boardImage = new int[26][27];

		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String line;

			for (int y = 0; y < 27; y++) {
				line = br.readLine();
				String[] values = line.split("\t");
				for (int x = 0; x < 26; x++) {

					boardImage[x][y] = Integer.parseInt(values[x]);
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("I/O error: " + e.getMessage());
			System.exit(1);
		}


		return boardImage;
	}

	public void turnComplete() {
		controller.turnComplete();
	}

	public String getCurrentPlayer() {
		if (controller.currentPlayer != null) {
			return controller.currentPlayer.toString();
		} else {
			return "";
		}
	}

	public Player getCurrentPlayerObject() {
		return controller.currentPlayer;
	}

	int dice = 1;

	public int getDice() {
		return dice;
	}

	public void diceRoll(int i) {
		if (controller.diceRoll(i)) {
			dice = i;
		}

	}

	public void makeSuggestion() {
		controller.makeSuggestion();

	}

	/**
	 * Return room type of boardPaths
	 *
	 * @param posType
	 * @return
	 */
	public Room getRoom(int posType) {
		if (posType == 1) {
			return Card.Room.Kitchen;
		}
		if (posType == 2) {
			return Card.Room.Ball_Room;
		}
		if (posType == 3) {
			return Card.Room.Conservatory;
		}
		if (posType == 4) {
			return Card.Room.Billiard_Room;
		}
		if (posType == 5) {
			return Card.Room.Library;
		}
		if (posType == 6) {
			return Card.Room.Study;
		}
		if (posType == 7) {
			return Card.Room.Hall;
		}
		if (posType == 8) {
			return Card.Room.Lounge;
		}
		if (posType == 9) {
			return Card.Room.DiningRoom;
		}
		return Card.Room.NULL;
	}

	public void makeAccusation() {
		controller.makeAccusation();

	}

	/**
	 * Get character's token's x/y coordinates on grid.
	 *
	 * @param character
	 * @return x/y of character's token on board
	 */
	public Point findTokenPosition(Character character) {
		for (int x = 0; x < BOARD_WIDTH; x++) {
			for (int y = 0; y < BOARD_HEIGHT; y++) {
				try {
					PlayerToken token = (PlayerToken) tokens[x][y];
					if (token.getCharacter() == character) {
						return new Point(x, y);
					}

				} catch (Exception e) {

				}

			}
		}
		return null;
	}

	public void start() {
		controller.start();
	}
}
