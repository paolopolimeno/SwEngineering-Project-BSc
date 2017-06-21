package model.bonus;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.TurnLap;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.bonus.AssistantBonus;
import it.polimi.ingsw.cg30.model.bonus.Bonus;

public class BonusTest {

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
		game.setGameState(new TurnLap(game));
		choice = new ArrayList<>();
		
	}
	
	@Test (expected=NullPointerException.class)
	
	public void testPlayerNull(){
		
		Bonus bonus=new AssistantBonus(3);
		bonus.giveBonusToPlayer(null);
		
	}
	
	
	@Test
	
	public void testHashCode(){
		
		Bonus bonus=new AssistantBonus(3);
		int hC=bonus.hashCode();
		assertTrue(hC==34);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	
	public void noMinusBonus(){
		Bonus bonus= new AssistantBonus(-3);		
	}
	
	@Test
	
	public void sameBonusIsEqual(){
		
		Bonus b1=new AssistantBonus(2);
		Bonus b2=b1;
		assertTrue(b1.equals(b2));
		
	}
	
	@Test
	
	public void nullNotEqual(){
	Bonus b1= new AssistantBonus(2);
	assertFalse(b1.equals(null));
	
	}
	
	
	
	
	
	
	
	
	
	
	
}
