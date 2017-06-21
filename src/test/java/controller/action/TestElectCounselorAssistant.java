package controller.action;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Assistant;
import it.polimi.ingsw.cg30.model.Counselor;
import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.actions.ElectCounselor;
import it.polimi.ingsw.cg30.model.actions.ElectCounselorAssistant;
import junit.framework.TestCase;

public class TestElectCounselorAssistant  {

	Action action;
	Player giocatore1;
	ArrayList<String> choice = new ArrayList<>();
	StartGame startGame;
	Game game;

	@Before
	public void setUp() {

		game = new Game();
		startGame = new StartGame(game);
		startGame.run();
		giocatore1 = new Player(1, "black", game, null);
		game.addPlayer(giocatore1);
		startGame.run();

	}
	
	@Test
	public void testControlPassingInvalidCounselorElectCounselorAssistant() {

		choice.add("0");
		choice.add("1");
		action = new ElectCounselorAssistant(choice);
		action.setPlayer(giocatore1);
		assertFalse(action.actionPerformed());

	}
	@Test
	public void testControlNoActionWithoutAsssistantElectCounselorAssistant() {

		giocatore1.setAssistants(new ArrayList<Assistant>());

		choice.add("1");
		choice.add("1");
		action = new ElectCounselorAssistant(choice);
		action.setPlayer(giocatore1);
		assertFalse(action.actionPerformed());

	}
	@Test
	public void testControlActionWithAsssistantElectCounselorAssistant() {

		ArrayList<Assistant> assistants = new ArrayList<Assistant>();
		assistants.add(new Assistant());
		giocatore1.setAssistants(assistants);
		choice.add("1");
		choice.add("1");
		action = new ElectCounselorAssistant(choice);
		action.setPlayer(giocatore1);
		assertTrue(action.actionPerformed());

	}
	@Test
	public void testControlPassingNullBalconyElectCounselorAssistant() {

		choice.add("1");
		choice.add(null);
		action = new ElectCounselorAssistant(choice);
		action.setPlayer(giocatore1);
		assertFalse(action.actionPerformed());

	}
	@Test
	public void testControlPassingNullCounselorElectCounselorAssistant() {

		choice.add(null);
		choice.add("1");
		action = new ElectCounselorAssistant(choice);
		action.setPlayer(giocatore1);
		assertFalse(action.actionPerformed());

	}
	@Test
	public void testControlCounselorisMovedinBalconyElectCounselorAssistant() {

		Counselor counselorAdded = game.getGameBoard().getCounselors().get(0);

		choice.add("1");
		choice.add("2");
		action = new ElectCounselorAssistant(choice);
		action.setPlayer(giocatore1);
		action.actionPerformed();

		Iterator<Counselor> i = game.getGameBoard().getBalconies().get(1).getCounselors().iterator();
		Counselor counselorBalcony1 = i.next();
		counselorBalcony1 = i.next();
		counselorBalcony1 = i.next();
		counselorBalcony1 = i.next();
		assertEquals(counselorAdded, counselorBalcony1);
	}
	@Test
	public void testControlCounselorisRemovedfromGameBoardElectCounselorAsssitant() {

		Counselor counselorAdded = game.getGameBoard().getCounselors().get(0);

		choice.add("1");
		choice.add("2");
		action = new ElectCounselorAssistant(choice);
		action.setPlayer(giocatore1);
		action.actionPerformed();

		assertFalse(game.getGameBoard().getCounselors().contains(counselorAdded));
	}

}