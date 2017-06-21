package model.bonus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.bonus.Bonus;
import it.polimi.ingsw.cg30.model.bonus.NobilityBonus;

public class NobilityBonusTest {


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
		bonus = new NobilityBonus(2);	
	}
	
	@Test
	public void testToString(){
		assertEquals(true, bonus.toString().equals("Nobil"));
	}
	@Test
	public void testGiveBonusToPlayer(){
		
		int oldNobilityPosition = player.getNobilityDisk().getSlot().getCoordinate();
		bonus.giveBonusToPlayer(player);
		
		assertTrue((oldNobilityPosition ==  player.getNobilityDisk().getSlot().getCoordinate()-2));
	}
}