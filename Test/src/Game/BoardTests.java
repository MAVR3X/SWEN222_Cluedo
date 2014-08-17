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
	public void testCluedoController(){
		assertTrue("Controller is nulled ",controller != null);
	}

	@Test
	public void testControllerCardDivision() {
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
	public void testPlayer() {
		// Miss_Scarlet Card
		Card c = new Card(0, 1, 0, 0);
		// Miss_Scarlet player
		Player p = new Player(c, "Bob");
		assertEquals("Player card not equal", c, p.c);
		assertFalse("Player hand is null", p.c == null);

		p.addCard(c);
		assertTrue("Player hand not accepting adds", p.hand.size() == 1);
		assertTrue("Player has no character", p.getCharacter() != null);
		assertTrue("Player has no character",
		p.getCharacter() == Card.Character.values()[1]);
		assertFalse("Player has auto-lost", p.hasLost);
		assertTrue(p.name.equals("Bob"));
	}



	@Test
	public void testCard() {
		Card c = new Card(0, 0, 0, 0);
		// Ensure card is nulled with 0,0,0,0
		assertTrue("Card Type Wrong", c.type == Card.Type.Character);
		assertTrue("Character Type Wrong", c.character == Card.Character.NULL);
		assertTrue("Weapon Type Wrong", c.weaponType == Card.Weapon.NULL);
		assertTrue("Room Type Wrong", c.room == Card.Room.NULL);
		assertTrue("Card image loaded on null", c.cardImage == null);

		// Test gets
		assertTrue("No characters", c.charCount() != 0);
		assertTrue("No weapons", c.wepCount() != 0);
		assertTrue("No rooms", c.roomCount() != 0);

		c = new Card(0, 1, 0, 0);
		assertTrue("Card image not loaded", c.cardImage != null);

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
