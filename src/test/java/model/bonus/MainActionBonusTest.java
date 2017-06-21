package model.bonus;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.TurnLap;
import it.polimi.ingsw.cg30.model.actions.MainAction;
import it.polimi.ingsw.cg30.model.bonus.Bonus;
import it.polimi.ingsw.cg30.model.bonus.MainActionBonus;

public class MainActionBonusTest {

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
		game.setGameState(new TurnLap(game));
		bonus = new MainActionBonus(3);
		player.getPossibleActions().clear();
	}
	
	@Test
	
	public void useBonusAndPlayerGetsMainActionFilled(){
		
		int numberActionBeforeBonus=player.getPossibleActions().size();
		bonus.giveBonusToPlayer(player);
		assertTrue(player.getPossibleActions().size()==numberActionBeforeBonus+4 && player.getPossibleActions().get(0).getClass().getSuperclass()==MainAction.class
				&&player.getPossibleActions().get(1).getClass().getSuperclass()==MainAction.class&&
				player.getPossibleActions().get(2).getClass().getSuperclass()==MainAction.class&&
				player.getPossibleActions().get(3).getClass().getSuperclass()==MainAction.class);
		
	}
	
	@Test
	
	public void toStringTest(){
		bonus.toString();
	}
	
	
	
	
}
