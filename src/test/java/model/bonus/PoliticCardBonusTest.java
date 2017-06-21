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
import it.polimi.ingsw.cg30.model.bonus.Bonus;
import it.polimi.ingsw.cg30.model.bonus.PoliticCardBonus;

public class PoliticCardBonusTest {

	Bonus bonus;
	Player player;
	StartGame startGame;
	Game game;

	@Before
	public void setUp() {

		game = new Game();
		startGame = new StartGame(game);
		startGame.run();
		player = new Player(1, "black", game, null);
		player.init();
		game.setGameState(new TurnLap(game));
		game.addPlayer(player);	
		bonus = new PoliticCardBonus(3);
		
	}
	
	@Test
	public void testBonusAmountEqualsTheAmountOfTheConstructor() {
		assertEquals(true, 3 == bonus.getNumberOfBonus());
	}
	@Test
	public void testToString(){
		assertEquals(true, bonus.toString().equals("Politic"));
	}
	
	
	@Test
	public void testPlayerIncreasePoliticCards(){		
		int oldNumberOfPoliticCards = player.getPoliticCards().size();
		bonus.giveBonusToPlayer(player);
		assertTrue(player.getPoliticCards().size()==oldNumberOfPoliticCards+3);
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
