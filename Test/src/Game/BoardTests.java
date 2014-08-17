package Game;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import Tokens.Token;

/**
 * Loading data from user input for controller. Data will vary but tests take
 * this into account
 *
 * @author blomfiisaa
 *
 */
public class BoardTests {

	static CluedoController controller;

	@BeforeClass
	public static void loadController() {
		controller = new CluedoController();
	}

	@Test
	public void testControllerPublicFields() {
		//Test controller initialisation
		assertTrue("Cards null", controller.cards != null);
		assertTrue("Board null", controller.board != null);
		assertTrue("Current Player", controller.currentPlayer != null);
		assertTrue("Key Listener null", controller.kl != null);
		assertTrue("Board null", controller.players != null);

	}

	@Test
	public void testCardDivisionPositive() {
		int numPlayer = controller.players.size();
		int numCardsPP = (21 - 3) / numPlayer;
		int remainingCards = (21 - 3) % numPlayer;

		// Ensure cards are split properly and handed out properly
		assertTrue("Player hand null", controller.currentPlayer != null);
		assertTrue("Wrong amount of cards",
				controller.currentPlayer.hand.size() == numCardsPP);
		assertTrue("Wrong amount of remainin cards",
				controller.cards.size() == remainingCards);

		// Check hand doesn't have duplicates
		for (Player p : controller.players) {
			for (Card c : p.hand) {
				assertTrue("Players card in remaining cards!",
						!controller.cards.contains(c));

			}
		}

	}


	@Test
	public void testCard(){
		Card c = new Card(0, 0, 0, 0);
		//Ensure card is nulled with 0,0,0,0
		assertTrue("Card Type Wrong", c.type == Card.Type.Character);
		assertTrue("Character Type Wrong", c.character == Card.Character.NULL);
		assertTrue("Weapon Type Wrong", c.weaponType == Card.Weapon.NULL);
		assertTrue("Room Type Wrong", c.room == Card.Room.NULL);


	}

	@Test
	public void testBoardInitialisation(){
		Board b = controller.board;

		assertTrue("Paths are not loaded", b.paths != null);
		assertTrue("Tokens are not loaded", b.getTokens() != null);

		//Check each player has a token
		assertTrue("Players loaded", controller.players != null && controller.players.size() > 0);
		//for(int i = 0; i < Card.Character.values().length){
		//}

		//Test Setting Tokens
		Token[][] tokens = new Token[1][1];
		b.setTokens(tokens);
		assertTrue("Tokens are not set", b.getTokens() == tokens);


	}


}
