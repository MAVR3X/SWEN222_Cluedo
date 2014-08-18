package Game;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import Game.Card.Character;
import Game.Card.Room;
import Game.Card.Weapon;
import Tokens.Token;

/**
 * Controller of MVC Design Pattern;
 *
 * Holds game state information, requests data from model(Board) and draws with
 * View (GameInterface) *
 */
public class CluedoController {

	Board board;
	GameInterface interf;

	ArrayList<Player> players;
	ArrayList<Card> cards;

	Player currentPlayer;
	int playerSteps = 0;
	int playerRoll = -1;

	private static Card solutionCharacter;
	private static Card solutionWeapon;
	private static Card solutionRoom;


	public CluedoController() {

	}

	private void initialise() {

		this.playerRoll = -1;
		this.playerSteps = 0;
		createCards();
		createPlayers();
		currentPlayer = players.get(0);
		board.generateTokens(cards);
		selectSolution();
		allocateCards();
		interf.redraw();
		interf.addKeyListener(kl);
		interf.repaint();

	}

	/**
	 * Allocate cards to players, call display for remaining cards.
	 *
	 * @author isaac
	 */
	private void allocateCards() {
		// Verify there are enough cards left
		while (cards.size() >= players.size()) {
			for (int p = 0; p < players.size(); p++) {
				int cardNum = (int) (Math.random() * (cards.size() - 1));
				players.get(p).addCard(cards.remove(cardNum));
			}
		}

		if (cards.size() != 0) {
			interf.displayCards(cards);
		}

	}

	/**
	 * Using number of possible cards of each type select 3 of each type and
	 * remove from list and add to solution fields.
	 *
	 * Dynamic - Handles adding new cards of any type
	 *
	 * @Author: Isaac
	 */
	private void selectSolution() {
		int characters = Card.charCount() - 1;
		int weapons = Card.wepCount() - 1;
		int rooms = Card.roomCount() - 1;

		// select character
		int selector = (int) (Math.random() * characters);
		solutionCharacter = cards.remove(selector);

		// weapon
		selector = (int) (Math.random() * weapons);
		solutionWeapon = cards.remove(characters - 1 + selector);

		// room
		selector = (int) (Math.random() * rooms);
		solutionRoom = cards.remove(characters + weapons - 2 + selector);

		// System.out.println("Solution = " + solutionCharacter.character +
		// " : "
		// + solutionWeapon.weaponType + " : " + solutionRoom.room);
		// System.out.println("");
	}

	/**
	 * Create the list of players
	 *
	 */
	public void createPlayers() {
		players = new ArrayList<Player>();
		players = interf.getPlayers(cards);
	}

	/**
	 * Create deck by creating an instance of each card type.
	 *
	 * Dynamic to allow for the addition of new cards of any type.
	 *
	 * @Author: Isaac
	 */
	private void createCards() {

		int characters = Card.charCount();
		int weapons = Card.wepCount();
		int rooms = Card.roomCount();

		cards = new ArrayList<Card>(); // 21 cards

		// Loop through each type
		for (int i = 0; i < 3; i++) {
			Card c;
			if (i == 0) {
				// NULL + 6 Chars
				for (int y = 1; y < characters; y++) {
					c = new Card(i, y, 0, 0);
					cards.add(c);
				}
			}
			if (i == 1) {
				// NULL + 6 Weapons
				for (int y = 1; y < weapons; y++) {
					c = new Card(i, 0, y, 0);
					cards.add(c);
				}
			}
			if (i == 2) {
				// NULL + 9 Rooms
				for (int y = 1; y < rooms; y++) {
					c = new Card(i, 0, 0, y);
					cards.add(c);
				}
			}

		}

	}

	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	public static final int LEFT = 4;

	KeyListener kl = new KeyListener() {
		public void keyPressed(KeyEvent e) {

			// if (playerRoll == -1) {
			// JOptionPane.showMessageDialog(null,
			// "Please roll the dice before starting your turn");
			// return;
			// }

			Point currentPosition = board.findTokenPosition(currentPlayer
					.getCharacter());

			int code = e.getKeyCode();
			int newStep = 0;
			if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT) {
				newStep += board.moveToken(currentPlayer.c, RIGHT);
			} else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT) {
				newStep += board.moveToken(currentPlayer.c, LEFT);
			} else if (code == KeyEvent.VK_UP) {
				newStep += board.moveToken(currentPlayer.c, UP);
			} else if (code == KeyEvent.VK_DOWN) {
				newStep += board.moveToken(currentPlayer.c, DOWN);
			}

			// Dice not yet rolled
			if (playerRoll == -1) {
				if (newStep == -1) { // within Room
					interf.redraw();
				} else if (newStep == 6) {// Passage used
					playerRoll = 0;
					interf.redraw();
				}else if(newStep == 0){
					JOptionPane.showMessageDialog(null,
							"Please roll the dice before starting your turn");
				} else {
					// Move player back
					JOptionPane.showMessageDialog(null,
							"Please roll the dice before starting your turn");
					Point newPosition = board.findTokenPosition(currentPlayer
							.getCharacter());
					board.moveToken(newPosition, currentPosition);
				}
			}
			else {
				if(newStep == -1){ // with in room
					interf.redraw();
				}
				else if(newStep == 0){
					JOptionPane.showMessageDialog(null,
							"You are out of moves or the move is invalid ");
				}
				else if (newStep == 2) { //from doorway to room
					currentPlayer.canSuggest = true;
					playerRoll = 0;
					interf.redraw();

				}
				else if(newStep == 3){ // from room to doorway
					if(playerRoll == 0){
						// Move player back
						JOptionPane.showMessageDialog(null,
								"You are out of moves or the move is invalid ");
						Point newPosition = board.findTokenPosition(currentPlayer
								.getCharacter());
						board.moveToken(newPosition, currentPosition);
					}
					else{
						// Moved successfully
						playerSteps += newStep;
						interf.redraw();
					}
				}
				else if ((playerSteps + newStep) > playerRoll) { // out of moves
					// Move player back
					JOptionPane.showMessageDialog(null,
							"You are out of moves or the move is invalid ");
					Point newPosition = board.findTokenPosition(currentPlayer
							.getCharacter());
					board.moveToken(newPosition, currentPosition);
				}
				else {
					// Moved successfully
					playerSteps += newStep;
					interf.redraw();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}
	};

	/**
	 * Complete turn of current player, find next valid player and set to
	 * current player. Reset required variables to limit player movement
	 */
	public void turnComplete() {
		currentPlayer.canAcuse = true;

		interf.requestFocus();

		// Build array of remaining players
		ArrayList<Player> remainingPlayers = new ArrayList<Player>();
		for (Player p : players) {
			if (!p.hasLost || currentPlayer == p) {
				remainingPlayers.add(p);
			}
		}

		// Find new current player
		for (int i = 0; i < remainingPlayers.size(); i++) {
			Player p = remainingPlayers.get(i);
			if (p.equals(currentPlayer)) {

				if (i < remainingPlayers.size() - 1) {
					i++;
					currentPlayer = remainingPlayers.get(i);
					break;
				} else {
					currentPlayer = remainingPlayers.get(0);
					break;
				}
			}

		}

		playerRoll = -1;
		playerSteps = 0;
	}

	/**
	 * Set playerRoll to integer iff a roll has not already changed the roll
	 * from the default -1 value.
	 *
	 * This value limits the move distance.
	 *
	 * @param integer
	 *            to assign to
	 */
	public boolean diceRoll(int i) {

		Point point = board.findToken(currentPlayer.c);
		if(board.getTokens()[point.x][point.y].wasMoved()){
			board.getTokens()[point.x][point.y].setMoved(false);
		}

		currentPlayer.canAcuse = false;
		if (playerRoll != -1) {
			JOptionPane.showMessageDialog(null,
					"You can only use a secret passage or roll the dice once per turn");
			return false;
		}
		// System.out.println("Rolled a " + i);

		playerRoll = i;
		interf.requestFocus();
		return true;
	}

	/**
	 * Determine room, weapon type and person from user. Calls makeSuggestion()
	 * to verify suggestion
	 */
	public void makeSuggestion() {


		Point p = board.findToken(currentPlayer.c);
		if(board.getTokens()[p.x][p.y].wasMoved()){
			currentPlayer.canSuggest = true;
			board.getTokens()[p.x][p.y].setMoved(false);
		}
		if(!currentPlayer.canSuggest){
			JOptionPane.showMessageDialog(null,
					"You can only make a suggestion when you enter a new room");
		return;
		}


		currentPlayer.canSuggest = false;
		interf.requestFocus();

		Card.Room room;
		Card.Weapon weapon;
		Card.Character character;

		// Ensure player is in room
		Point playerPos = board.findToken(board.getCurrentPlayerObject().c);
		int posType = board.paths[playerPos.x][playerPos.y];

		// System.out.println("Player At" + playerPos + " Type: " + posType);
		if (posType <= 0 || posType >= 10) {

			JOptionPane.showMessageDialog(null,
					"You must be in a room to make a suggestion");
			return;
		}

		// Get Weapon
		room = board.getRoom(posType);

		Game.Card.Weapon[] wepPosibilities = Arrays.copyOfRange(
				Card.Weapon.values(), 1, Card.Weapon.values().length);

		weapon = (Card.Weapon) JOptionPane.showInputDialog(interf,
				"Please select the weapon:", "Suggestion Submission",
				JOptionPane.PLAIN_MESSAGE,
				UIManager.getIcon("OptionPane.informationIcon"),
				wepPosibilities, wepPosibilities[0]);

		Game.Card.Character[] carPosibilities = Arrays.copyOfRange(
				Card.Character.values(), 1, Card.Character.values().length);

		character = (Card.Character) JOptionPane.showInputDialog(interf,
				"Please select the murderer:", "Suggestion Submission",
				JOptionPane.PLAIN_MESSAGE,
				UIManager.getIcon("OptionPane.informationIcon"),
				carPosibilities, carPosibilities[0]);

		if (room == null || character == null || weapon == null) {
			return;
		}

		if (currentPlayer.c.character != character) {
			moveCharacterTo(room, character);
		}
		makeSuggestion(room, character, weapon);
		playerRoll = 0;

	}

	/**
	 * Verify the validity of claim. Check against each players' cards and fail
	 * on first instance of wrong card then move to other players.
	 *
	 * @param room
	 * @param character
	 * @param weapon
	 */
	private void makeSuggestion(Room room, Character character, Weapon weapon) {

		// Iterate each player
		boolean isValid = true;
		int curPlayerPos = players.indexOf(currentPlayer);
		int i = curPlayerPos + 1;

		// Check players with indexes higher than current player for matching
		// cards
		for (; i < players.size(); i++) {

			Player playerToCheck = players.get(i);

			for (Card card : playerToCheck.hand) {

				if (card.room.equals(room) || card.character.equals(character)
						|| card.weaponType.equals(weapon)) {
					displayConflictingCard(playerToCheck, card);
					isValid = false;
					return;
				}

			}
		}

		i = 0;
		// Check players with lower indexes
		for (; i < curPlayerPos; i++) {

			Player playerToCheck = players.get(i);

			for (Card card : playerToCheck.hand) {

				if (card.room.equals(room) || card.character.equals(character)
						|| card.weaponType.equals(weapon)) {
					displayConflictingCard(playerToCheck, card);
					isValid = false;
					return;
				}

			}
		}

		if (!isValid) {
			System.out.println("Not Valid");

		} else {
			JOptionPane.showMessageDialog(null, character + "in the " + room
					+ " with the " + weapon
					+ " was not refuted by any players.");
		}

	}

	/**
	 * Create accusation on confirmation by currentPlayer. Determine room,
	 * weapon and character from player and call makeAccusation()
	 */
	public void makeAccusation() {
		if(!currentPlayer.canAcuse){
			JOptionPane.showMessageDialog(null,
					"You can only make an accusation at the start of your turn.");
		return;
		}

		Card.Room room;
		Card.Weapon weapon;
		Card.Character character;

		// Verify they want to accuse
		if (!confirmAccusation()) {
			return;
		}

		// Get Room
		Game.Card.Room[] roomPosibilities = Arrays.copyOfRange(
				Card.Room.values(), 1, Card.Room.values().length);
		room = (Card.Room) JOptionPane.showInputDialog(interf,
				"Please select the room:", "Accusation Submission",
				JOptionPane.PLAIN_MESSAGE,
				UIManager.getIcon("OptionPane.informationIcon"),
				roomPosibilities, roomPosibilities[0]);

		// Get weapon
		Game.Card.Weapon[] wepPosibilities = Arrays.copyOfRange(
				Card.Weapon.values(), 1, Card.Weapon.values().length);
		weapon = (Card.Weapon) JOptionPane.showInputDialog(interf,
				"Please select the weapon:", "Suggestion Submission",
				JOptionPane.PLAIN_MESSAGE,
				UIManager.getIcon("OptionPane.informationIcon"),
				wepPosibilities, wepPosibilities[0]);

		// Get Character
		Game.Card.Character[] carPosibilities = Arrays.copyOfRange(
				Card.Character.values(), 1, Card.Character.values().length);
		character = (Card.Character) JOptionPane.showInputDialog(interf,
				"Please select the murderer:", "Suggestion Submission",
				JOptionPane.PLAIN_MESSAGE,
				UIManager.getIcon("OptionPane.informationIcon"),
				carPosibilities, carPosibilities[0]);

		if (room == null || character == null || weapon == null) {
			return;
		}

		makeAccusation(room, character, weapon);

	}

	/**
	 * Called by makeAccusation to verify the validity of the accusation. If the
	 * accusation is incorrect the player is removed with playerRemove() or the
	 * player wins.
	 *
	 * @param room
	 * @param character
	 * @param weapon
	 */
	private void makeAccusation(Room room, Character character, Weapon weapon) {

		// Correct
		if (solutionWeapon.weaponType == weapon
				&& solutionCharacter.character == character
				&& solutionRoom.room == room) {
			JOptionPane.showMessageDialog(null, currentPlayer.name
					+ " wins! With the guess: " + character + " in the " + room
					+ " with the " + weapon);
			JOptionPane
					.showMessageDialog(null,
							"Thanks for playing. Credits: Isaac, Mike. Click Game->New Game to play again.");

		} else {
			JOptionPane.showMessageDialog(null, currentPlayer.name
					+ " loses! The guess: " + character + " in the " + room
					+ " with the " + weapon + " was WRONG! ");
			removePlayer();
		}
	}

	private void removePlayer() {
		currentPlayer.hasLost = true;
		turnComplete();

	}

	/**
	 * Prompt user to confirm they wish to accuse
	 *
	 * @return
	 */
	public boolean confirmAccusation() {

		int reply = JOptionPane
				.showConfirmDialog(
						null,
						"Are you sure you want to accuse someone? You will die a painful death if you are incorrect",
						"Confirm Accusation", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Display conflicting card for players to view in pop-up.
	 *
	 * @param Player
	 *            playerToCheck which requested suggestion
	 * @param Card
	 *            c which conflicted the suggestion
	 */
	public void displayConflictingCard(Player playerToCheck, Card c) {
		ImageIcon icon = new ImageIcon();
		icon.setImage(c.cardImage);

		JOptionPane.showMessageDialog(null, playerToCheck.getCharacter()
				+ " has a conflicting card", "Conflicting Card Found",
				JOptionPane.INFORMATION_MESSAGE, icon);
	}

	public static Point kitchenPnt = new Point(3, 5);
	public static Point diningroomPnt = new Point(4, 13);
	public static Point loungePnt = new Point(5, 22);
	public static Point hallPnt = new Point(12, 22);
	public static Point studyPnt = new Point(21, 23);
	public static Point libraryPnt = new Point(21, 17);
	public static Point billiardroomPnt = new Point(21, 11);
	public static Point conservatoryPnt = new Point(21, 4);
	public static Point ballRoomPnt = new Point(12, 5);

	/**
	 * Move character's token to room.
	 *
	 * Takes into account surrounding tokens and moves new position to avoid
	 * conflicts.
	 *
	 * @param room
	 *            to move character to
	 * @param character
	 *            to move to room
	 */
	private void moveCharacterTo(Room room, Character character) {


		// Determine old position of character to transport
		Point oldPos = board.findTokenPosition(character);
		Token token = board.getTokens()[oldPos.x][oldPos.y];
		board.getTokens()[oldPos.x][oldPos.y] = null;

		token.setMoved(true);

		// Find new position in selected room
		Point newPos = new Point(0, 0);
		if (room == Card.Room.Kitchen) {
			newPos = kitchenPnt;
		}
		if (room == Card.Room.DiningRoom) {
			newPos = diningroomPnt;
		}
		if (room == Card.Room.Lounge) {
			newPos = loungePnt;
		}
		if (room == Card.Room.Hall) {
			newPos = hallPnt;
		}
		if (room == Card.Room.Study) {
			newPos = studyPnt;
		}
		if (room == Card.Room.Library) {
			newPos = libraryPnt;
		}
		if (room == Card.Room.Billiard_Room) {
			newPos = billiardroomPnt;
		}
		if (room == Card.Room.Conservatory) {
			newPos = conservatoryPnt;
		}
		if (room == Card.Room.Ball_Room) {
			newPos = ballRoomPnt;
		}

		// Check the placement is valid, move if not
		int modx = 0;
		int mody = 0;
		while (true) {
			if (board.getTokens()[newPos.x + modx][newPos.y + mody] != null) {
				modx++;
			}
			if (board.getTokens()[newPos.x + modx][newPos.y + mody] != null) {
				mody++;
			}

			if (board.getTokens()[newPos.x + modx][newPos.y + mody] == null) {
				board.getTokens()[newPos.x + modx][newPos.y + mody] = token;
				break;
			}
		}
		interf.repaint();

	}

	/**
	 * Get character object from character enum value
	 *
	 * @param character
	 *            enum value
	 * @return Player object corrisponding to enum
	 */
	public Player getCharacter(Character character) {
		for (Player p : players) {
			if (p.c.character == character) {
				return p;
			}
		}
		return null;
	}

	public void start() {
		if (board != null) {
			board = null;
			interf.dispose();
		}
		board = new Board(this);
		interf = new GameInterface("Cluedo", board);
		initialise();
	}

}
