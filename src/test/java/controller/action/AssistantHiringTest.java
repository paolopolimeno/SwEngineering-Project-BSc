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
import it.polimi.ingsw.cg30.model.actions.AssistantHiring;

public class AssistantHiringTest {

	
	Player player;
	Game game;
	Action action;
	
	@Before
	public void initializeAction(){
		
		game = new Game();
		StartGame startGame = new StartGame(game);
		player = new Player(1, "black", game, null);
		game.addPlayer(player);
		startGame.run();
		action = new AssistantHiring();
		action.setPlayer(player);
	}
	
	@Test
	public void testPlayerNotEnoughMoney(){
		
		player.moveProsperityDisk(-9);
		assertEquals(false, action.actionPerformed());
	}
	
	@Test
	public void testPlayerGetsRightAmountOfAssistant(){
		
		player.moveProsperityDisk(20);
		assertEquals(true, action.actionPerformed());
		
		assertEquals(2, player.getAssistants().size());
	}
}
