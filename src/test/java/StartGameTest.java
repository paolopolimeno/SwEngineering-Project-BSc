import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Balcony;
import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.actions.Action;

public class StartGameTest {

	Player player,player2;
	Game game;
	Action action;
	StartGame startGame;
	ArrayList<String> choice;
	Balcony balcony;
	
	@Before
	public void setUp() {

		game = new Game();
		startGame = new StartGame(game);
		startGame.run();
		player = new Player(1, "black", game, null);
		player2 = new Player(2,"black", game, null);
		player.init();
		player2.init();
		game.addPlayer(player);
		game.addPlayer(player2);

	}
	
	
	@Test
	
	public void testGetCurrentPlayer(){
		assertTrue(startGame.getCurrentPlayer()==null);
	}
	
	@Test
	public void testtNextPlayer(){
		startGame.nextPlayer();
	}
	
	@Test(expected= NullPointerException.class)
	
	public void testStartGameGameNull(){
	StartGame sG= new StartGame(null);
	
	}
}