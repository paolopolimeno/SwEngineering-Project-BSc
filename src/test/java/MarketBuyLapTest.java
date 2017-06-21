import static org.junit.Assert.assertTrue;

import java.awt.Color;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.MarketBuyLap;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.TurnLap;
import it.polimi.ingsw.cg30.model.actions.Action;

public class MarketBuyLapTest {
	Player player;
	Player player2;
	Game game;
	Action action;
	StartGame startGame;

	@Before
	public void setUp() {

		game = new Game();
		startGame = new StartGame(game);
		
		player = new Player(1, "black", game, null);
		player2 = new Player(2,"black", game, null);
		game.addPlayer(player);
		game.addPlayer(player2);
	
		startGame.run();
		player.getPossibleActions().clear();
		player2.getPossibleActions().clear();
	}
	@Test
	public void testGetPlayer(){
		game.setGameState(new MarketBuyLap(game));
		game.getGameState().getCurrentPlayer();
	}
	
	@Test
	public void TurnPlayer1(){
		game.setGameState(new MarketBuyLap(game));
	}
	@Test
	public void isTurn(){
		game.setGameState(new MarketBuyLap(game));
		game.getGameState().update();
	}
	
	@Test
	public void isTurnLapAgain(){
		game.setGameState(new MarketBuyLap(game));
		
		player.getPossibleActions().clear();
		player2.getPossibleActions().clear();
		
		game.getGameState().update();
		
		player.getPossibleActions().clear();
		player2.getPossibleActions().clear();
		
		game.getGameState().update();
		assertTrue(game.getGameState().getClass()==TurnLap.class);
	}
}