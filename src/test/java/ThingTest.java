import static org.junit.Assert.*;


import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Assistant;
import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.actions.market.Thing;
import it.polimi.ingsw.cg30.model.bonus.Bonus;

public class ThingTest {

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
		
		game.addPlayer(player);
		startGame.run();
		choice = new ArrayList<>();

	}
	
	@Test
	
	public void testHashCode(){
		
		Thing t = new Thing(3,new Assistant(), player.getPlayerNumber());
		t.hashCode();
	}
	
	
	@Test (expected=NullPointerException.class)
	
	public void testNullObject(){
		Thing t = new Thing(3,null,0);

	}
	
	

	@Test 	
	public void testNegativePrice(){
		Thing t = new Thing(-1,new Assistant(), player.getPlayerNumber());
		assertTrue(t.getPrice()==-1);
	}
	
@Test
	public void testEqualIfAssistant(){
		Thing t = new Thing(3,new Assistant(), player.getPlayerNumber());

		Thing t2 = new Thing(3,new Assistant(), player.getPlayerNumber());
		assertTrue(t.equals(t2));
	}

	
@Test
public void testNotEqualIfNullAndNot(){
	Thing t = new Thing(3,new Assistant(), player.getPlayerNumber());
Thing t2=null;
	
	assertFalse(t.equals(t2));

	
}


@Test
public void testNotEqualIfDifferentClass(){
	Thing t = new Thing(3,new Assistant(), player.getPlayerNumber());
Bonus t2=null;
	
	assertFalse(t.equals(t2));

	
}





}
