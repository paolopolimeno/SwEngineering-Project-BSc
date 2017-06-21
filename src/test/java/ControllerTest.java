import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.controller.Controller;
import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.actions.AssistantHiring;
import it.polimi.ingsw.cg30.model.actions.ElectCounselor;

public class ControllerTest {

	

	Player player,player2;
	Game game;
	StartGame startGame;
	Controller controller;
	Action action;
	ArrayList<String> choice;
	
	@Before
	public void setUp() {

		game = new Game();
		startGame = new StartGame(game);
		controller= new Controller(game);
		player = new Player(1, "black", game, null);
		player2 = new Player(2, "black", game, null);

		game.addPlayer(player);
		game.addPlayer(player2);

		startGame.run();
	}
	
	@Test
	
	public void testControllerActionIsPerformed(){
	
	int size= player.getAssistants().size();
	action= new AssistantHiring();
	action.setPlayer(player);
	controller.update(action);
	assertTrue(player.getAssistants().size()==size+1);
	
	}
	
	@Test
	
	public void testControllerActionIsNotMyTurn(){
	
	int size= player.getAssistants().size();
	game.getGameState().nextPlayer();
	action= new AssistantHiring();
	action.setPlayer(player);
	controller.update(action);
	assertTrue(player.getAssistants().size()==size);
	}
	
	
	
	@Test
	
	public void testControllerActionIsNotPerformedBecauseNotLegit(){
	
	int size= player.getAssistants().size();
	player.getPossibleActions().clear();
	action= new AssistantHiring();
	action.setPlayer(player);
	controller.update(action);
	assertTrue(player.getAssistants().size()==size);
	
	}
	
		
}
