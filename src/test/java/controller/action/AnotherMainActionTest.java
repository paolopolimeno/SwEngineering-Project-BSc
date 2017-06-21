package controller.action;
import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.actions.AnotherMainAction;
import it.polimi.ingsw.cg30.model.actions.MainAction;

public class AnotherMainActionTest {

	Player player;
	Game game;
	Action action;

	@Before
	public void initializeAction() {

		game = new Game();
		StartGame startGame = new StartGame(game);
		player = new Player(1, "black", game, null);
		game.addPlayer(player);
		startGame.run();
		action = new AnotherMainAction();
		action.setPlayer(player);
	}

	@Test
	public void testPlayerNotEnoughtAssistants() {

		assertEquals(false, action.actionPerformed());
	}

	@Test
	public void testRemoveAssistantsFromPlayer() {

		player.takeAssistant();
		player.takeAssistant();
		player.takeAssistant();

		action.actionPerformed();

		assertEquals(1, player.getAssistants().size());
		assertEquals(8, player.getPossibleActions().size());
		for (Action action : player.getPossibleActions()) {

			assertTrue(action.getClass().getSuperclass() == MainAction.class);
		}

	}

	@Test
	public void testActionsInsertedAreMain() {

		player.takeAssistant();
		player.takeAssistant();
		player.takeAssistant();
		action.actionPerformed();
		for (Action action : player.getPossibleActions()) {

			assertTrue(action.getClass().getSuperclass() == MainAction.class);
		}

	}

}
