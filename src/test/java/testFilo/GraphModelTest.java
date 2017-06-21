package testFilo;

import static org.junit.Assert.*;

import java.awt.Color;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.City;
import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.util.GraphModel;

public class GraphModelTest {

	Player player;
	Game game;

	@Before
	public void initializePlayer() {

		game = new Game();
		StartGame startGame = new StartGame(game);
		startGame.run();
		player = new Player(1, "black", game, null);
		player.init();
		game.addPlayer(player);
		
		
		
	}
	
	@Test(expected = NullPointerException.class)
	public void testGraphModel(){
		
		GraphModel graph = new GraphModel(null, null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testBuildGraph(){
		
		game.getGameBoard().getGraph().buildGraph(null, null, null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSetEdges(){
		
		game.getGameBoard().getGraph().setEdges(null, null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetMoneyToPay(){
		
		
		
		City sourceCity = game.getGameBoard().getGraph().getCities().get(0);
		City targetCity = game.getGameBoard().getGraph().getCities().get(9);
		
		assertEquals(4, game.getGameBoard().getGraph().getMoneyToPay(sourceCity, targetCity));
		
		game.getGameBoard().getGraph().getMoneyToPay(null, null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetAllReacheableCities(){
		
		game.getGameBoard().getGraph().getAllReachableCities((SimpleGraph<City, DefaultEdge>) game.getGameBoard().getGraph().getGraph(), game.getGameBoard().getCities().get(0));
		game.getGameBoard().getGraph().getAllReachableCities(null, null);
	}

	
	@Test(expected = NullPointerException.class)
	public void testGetAllEmporiumCities(){
		
		game.getGameBoard().getGraph().getAllEmporiumCities(null, null);
		game.getGameBoard().getGraph().getAllEmporiumCities("black", game.getGameBoard().getCities());
	}
	

	
	@Test
	public void testGetEdges(){
		
		game.getGameBoard().getGraph().getEdges();
	}
}
