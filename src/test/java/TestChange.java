import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.change.Change;
import it.polimi.ingsw.cg30.model.change.GameBoardChange;

public class TestChange {

	Player player;
	Game game;
	Action action;
	StartGame startGame;
	ArrayList<String> choice;

	@Before
	public void setUp() {

		game = new Game();
		startGame = new StartGame(game);
		startGame.run();
		player = new Player(1, "black", game, null);
		player.init();
		game.addPlayer(player);
	}
	
	/*@Test
	public void testPlayerGetReceiver(){
		Change c= new GameBoardChange(game.getGameBoard(),null,player);
		assertTrue(c.getReceiver()==player);
		
	}
	@Test
	public void testPlayerSetReceiver(){
		Change c= new GameBoardChange(game.getGameBoard(),null,null);
		c.setReceiver(player);
		
	}
	@Test(expected=NullPointerException.class)
	public void testPlayerSetReceiverNull(){
		Change c= new GameBoardChange(game.getGameBoard(),null,null);
		c.setReceiver(null);
		
	}
	
	@Test
	public void testUUID(){
		Change c= new GameBoardChange(game.getGameBoard(),null,player);
		assertTrue(c.getUUID()==null);
		
	}*/
	
	
	
	
	
	
}
