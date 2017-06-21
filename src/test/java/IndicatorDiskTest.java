import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Balcony;
import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.IndicatorDisk;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.actions.Action;

public class IndicatorDiskTest {

	Player player;
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
		player.init();
		game.addPlayer(player);
	}
	@Test
	
	public void testGetPlayer(){
		
		
		IndicatorDisk iD= new IndicatorDisk(player);
		assertTrue(iD.getPlayer()==player);
	}
	
	@Test (expected=NullPointerException.class)
	
	public void testPlayerNull(){

		IndicatorDisk iD= new IndicatorDisk(null);
	}
	
	@Test (expected=NullPointerException.class)
	
	public void testSlotNull(){
		IndicatorDisk iD= new IndicatorDisk(player);
		iD.setSlot(null);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
