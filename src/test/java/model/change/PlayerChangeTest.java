package model.change;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Assistant;
import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.GameBoard;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.change.GameBoardChange;
import it.polimi.ingsw.cg30.model.change.PlayerChange;

public class PlayerChangeTest {

	Player player1, player2, player3, player4;
	Game game;
	
	PlayerChange pC;
	@Before
	public void setUp(){
		
		game = new Game();
		StartGame startGame = new StartGame(game);
		player1 = new Player(1, "black", game, null);
		player2 = new Player(2, "red", game, null);
		player3 = new Player(3, "blue", game, null);
		player4 = new Player(4, "yellow", game, null);
		

		game.addPlayer(player1);
		game.addPlayer(player2);
		game.addPlayer(player3);
		game.addPlayer(player4);
	

		startGame.run();
		player1.getAssistants().clear();
		player2.getAssistants().clear();
		player2.getAssistants().add(new Assistant());
		player2.getAssistants().add(new Assistant());
		player2.getAssistants().add(new Assistant());
		
		
		player2.getAssistants().add(new Assistant());
	
		pC = new PlayerChange(player1,null);	
			
	}
	
	
	
	@Test
	
	public void testPlayerNumber(){
		
		assertTrue(pC.getPlayerNumber()==player1.getPlayerNumber());
	}
	
	
	@Test
	
	public void testGetColor(){
		assertTrue(pC.getColor().equals(player1.getColor()));
	}
	
	@Test
	
	public void testPermissionCard(){
		assertTrue(pC.getPermissionCards().equals(player1.getPermissionCards()));
	}
	
	@Test
	
	public void testAssistants(){
		assertTrue(pC.getAssistants().equals(player1.getAssistants()));
	}
	@Test
	public void testPossibleActions(){
		assertTrue(pC.getPossibleActions().equals(player1.getPossibleActions()));
	}
	
	
	@Test
	public void testBonusCards(){
		assertTrue(pC.getBonusCards().equals(player1.getBonusCards()));
	}
	@Test
	public void testPoliticCards(){
		assertTrue(pC.getPoliticCards().equals(player1.getPoliticCards()));
	}
	@Test
	public void testEmporiums(){
		assertTrue(pC.getEmporiums().equals(player1.getEmporiums()));
	}
	
	
}
