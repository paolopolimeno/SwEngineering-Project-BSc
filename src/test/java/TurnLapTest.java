import static org.junit.Assert.*;

import java.awt.Color;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.MarketSellLap;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.TurnLap;
import it.polimi.ingsw.cg30.model.actions.Action;

public class TurnLapTest {

	
	Player player;
	Player player2;
	Game game;
	Action action;
	StartGame startGame;

	@Before
	public void setUp() {

		game = new Game();
		startGame = new StartGame(game);
		
		player = new Player(1,"black", game, null);
		player2 = new Player(2, "black", game, null);
		game.addPlayer(player);
		game.addPlayer(player2);
		startGame.run();
		
	}
@Test(expected= NullPointerException.class)
	
	public void testGameNull(){
	TurnLap tL= new TurnLap(null);
	}

	@Test
	public void TurnPlayer1(){
		game.setGameState(new TurnLap(game));
	}
	@Test
	public void isTurnPlayer2(){
		game.setGameState(new TurnLap(game));
		player.getPossibleActions().clear();
		game.getGameState().update();
		assertTrue(game.getGameState().getCurrentPlayer()==player2);
	}
	@Test
	public void isTurnMarket(){
		game.setGameState(new TurnLap(game));
		player.getPossibleActions().clear();
		game.getGameState().update();
		player2.getPossibleActions().clear();
		game.getGameState().update();
		assertTrue(game.getGameState().getClass()==MarketSellLap.class);
	}
	@Test
	public void player1GetsAction(){
		assertTrue(player.getPossibleActions().size()==9);
	}
}