import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.cg30.model.EndGame;
import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.PermissionCard;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;

public class TestEndGame {

	
	Game game;
	Player player,player2, player3;
	EndGame endGame;
	
	@Test
	
	public void testWinner(){
		
		game= new Game(null,3);
		player = new Player(1, "black", game, null);
		player.getEmporiums().clear();
		player2 = new Player(2, "black", game, null);
		game.addPlayer(player);
		game.addPlayer(player2);
		game.setGameState(new StartGame(game));
		player.init();
		player2.init();
		game.getGameState().run();
		endGame= new EndGame(game,player);
		game.setGameState(endGame);
		endGame.run();
		player2.getPossibleActions().clear();
		endGame.update();
		assertTrue(game.getGameBoard().getWinner().equals(player2));
		
		
	}
	
	@Test
	
	public void testWinner1(){	
		game= new Game(null,3);
		player = new Player(1, "black", game, null);
		player.getEmporiums().clear();
		player2 = new Player(2, "black", game, null);
		game.addPlayer(player);
		game.addPlayer(player2);
		game.setGameState(new StartGame(game));
		player.init();
		player.moveNobilityDisk(1);
		player2.init();
		game.getGameState().run();
		endGame= new EndGame(game,player);
		game.setGameState(endGame);
		endGame.run();
		player2.getPossibleActions().clear();
		endGame.update();
		assertTrue(game.getGameBoard().getWinner().equals(player));

	}
	
	@Test
	
	public void testWinner1With3players(){	
		game= new Game(null,3);
		player = new Player(1, "black", game, null);
		player.getEmporiums().clear();
		player2 = new Player(2, "black", game, null);
		player3 = new Player(3, "black", game, null);
		game.addPlayer(player);
		game.addPlayer(player2);
		game.addPlayer(player3);
		game.setGameState(new StartGame(game));
		player.init();
		player2.init();
		player3.init();
		player3.moveNobilityDisk(1);
		player.moveNobilityDisk(1);
		player.getPermissionCards().add(new PermissionCard());
		game.getGameState().run();
		endGame= new EndGame(game,player);
		game.setGameState(endGame);
		endGame.run();
		player2.getPossibleActions().clear();
		endGame.update();
		player3.getPossibleActions().clear();
		endGame.update();
		assertTrue(game.getGameBoard().getWinner().equals(player));

	}
	

	
	@Test(expected = NullPointerException.class)
	public void testEndGameWithNoGame(){
		game.setGameState(new EndGame(null, null));
	}
	
	@Test
	public void onlineExpected(){
		game= new Game(null,3);
		player = new Player(1, "black", game, null);
		player.getEmporiums().clear();
		player2 = new Player(2, "black", game, null);
		player3 = new Player(3, "black", game, null);
		game.addPlayer(player);
		game.addPlayer(player2);
		game.addPlayer(player3);
		game.setGameState(new StartGame(game));
		player.init();
		player2.init();
		player3.init();
		player3.moveNobilityDisk(1);
		player.moveNobilityDisk(1);
		player.getPermissionCards().add(new PermissionCard());
		game.getGameState().run();
		endGame= new EndGame(game,player);
		game.setGameState(endGame);
		assertTrue(endGame.checkPlayersNotAllOffline());
	}
	
	@Test
	public void offlineExpected(){
		game= new Game(null,3);
		player = new Player(1, "black", game, null);
		player.getEmporiums().clear();
		player2 = new Player(2, "black", game, null);
		player3 = new Player(3, "black", game, null);
		game.addPlayer(player);
		game.addPlayer(player2);
		game.addPlayer(player3);
		game.setGameState(new StartGame(game));
		player.init();
		player2.init();
		player3.init();
		player3.setOffline();
		player2.setOffline();
		player.setOffline();
		player3.moveNobilityDisk(1);
		player.moveNobilityDisk(1);
		player.getPermissionCards().add(new PermissionCard());
		game.getGameState().run();
		endGame= new EndGame(game,player);
		game.setGameState(endGame);
		assertFalse(endGame.checkPlayersNotAllOffline());
	}
	
	
	
	
	
	
	
	
}
