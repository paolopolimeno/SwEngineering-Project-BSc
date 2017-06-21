package controller.action;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.PermissionCard;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.actions.ChangePermissionCards;

public class ChangePermissionCardsTest {
	
	Player player;
	Game game;
	Action action;
	
	@Before
	public void initGameForTest(){
		game = new Game();
		StartGame startGame = new StartGame(game);
		game.setGameState(startGame);

		player = new Player(1, "black", game, null);
		game.addPlayer(player);
		startGame.run();
		ArrayList<String> choice = new ArrayList<>();
		choice.add("1");
		action = new ChangePermissionCards(choice);
	}
	@Test
	public void actionWithNoAssistants() {
		player.removeAssistants(1000);
		action.setPlayer(player);
		assertEquals(false, action.actionPerformed());
	}
	
	@Test
	public void actionWithNotLegitChoice(){
		player.takeAssistant();
		ArrayList<String> choice2 = new ArrayList<>();
		choice2.add("5");
		action = new ChangePermissionCards(choice2);
		action.setPlayer(player);
		
		assertEquals(false, action.actionPerformed());
	}
	
	@Test
	public void actionReturnsTrueAndPlayerHasOneAssistantLess(){
		player.takeAssistant();
		int oldSize = player.getAssistants().size();
		action.setPlayer(player);

		assertEquals(true, action.actionPerformed());
		assertEquals(true, oldSize == player.getAssistants().size()+1);
	}
	
	@Test
	public void actionReturnsTrueAndTheCardsAreChanged(){
		player.takeAssistant();
		action.setPlayer(player);
		int oldSize = game.getGameBoard().getSea().getDeckPermissionCards().size();
		ArrayList<PermissionCard> oldShowedCards = new ArrayList<>(game.getGameBoard().getSea().getShowedPermissionCards());
		assertEquals(true, action.actionPerformed());
		
		assertEquals(true, oldSize == game.getGameBoard().getSea().getDeckPermissionCards().size());
		assertEquals(false, game.getGameBoard().getSea().getShowedPermissionCards().equals(oldShowedCards));
	}

}
