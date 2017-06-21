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
import it.polimi.ingsw.cg30.model.actions.NoFastAction;

public class NoFastActionTest {

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
	public void testActionPerformed() {
		Action action = new NoFastAction();
		action.setPlayer(player);
		assertEquals(true, action.actionPerformed() && player.getPossibleActions().size()==4 && player.getPossibleActions().get(0).getClass().getSuperclass()==MainAction.class);
		
	}
}
