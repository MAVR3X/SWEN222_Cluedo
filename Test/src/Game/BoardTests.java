package Game;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class BoardTests {

	CluedoController controller;

	@BeforeClass
	public void loadController(){
		controller = new CluedoController();
	}


	@Test
	public void testControllerPublicFields(){

		assertTrue("Cards null", controller.cards != null);
		assertTrue("Board null", controller.board != null);
		assertTrue("Current Player", controller.currentPlayer != null);
		assertTrue("Key Listener null", controller.kl != null);
		assertTrue("Board null", controller.players != null);

	}

	@Test
	public void testCardDivisionPositive(){


	}

}
