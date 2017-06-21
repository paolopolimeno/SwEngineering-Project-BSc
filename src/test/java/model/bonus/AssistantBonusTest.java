package model.bonus;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Assistant;
import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.bonus.AssistantBonus;
import it.polimi.ingsw.cg30.model.bonus.Bonus;
import it.polimi.ingsw.cg30.model.bonus.PoliticCardBonus;

public class AssistantBonusTest {


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
		game.addPlayer(player);	
		bonus = new PoliticCardBonus(3);
		
	}
	@Test
	public void testBonusAmountEqualsTheAmountOfTheConstructor() {
		 bonus = new AssistantBonus(3);
		
		assertEquals(true, 3 == bonus.getNumberOfBonus());
	}
	
	@Test
	public void testBonusAmountEqualsTheAmountOfTheNullConstructor() {
		 bonus = new AssistantBonus();
		
		assertEquals(true, 0 == bonus.getNumberOfBonus());
	}
	
	@Test
	public void testToString(){
		 bonus = new AssistantBonus(3);
		
		assertEquals(true, bonus.toString().equals("Assistant"));
	}
	
	@Test
	public void testGiveBonusToPlayer(){
		 bonus = new AssistantBonus(3);
		int oldNumberOfAssistants = player.getAssistants().size();
		bonus.giveBonusToPlayer(player);
		
		assertTrue(oldNumberOfAssistants+bonus.getNumberOfBonus() == player.getAssistants().size());
	}

}
