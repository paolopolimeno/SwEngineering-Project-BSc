import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.IndicatorDisk;
import it.polimi.ingsw.cg30.model.PermissionCard;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.bonus.Bonus;
import it.polimi.ingsw.cg30.model.bonus.PointsBonus;

public class PermissionCardTest {


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
		choice = new ArrayList<>();

	}

	@Test
	
	public void equalsNoWithDifferentClass(){
		
		PermissionCard p1= new PermissionCard();
		Bonus b1= new PointsBonus(2);
		assertFalse(b1.equals(p1));
				
		
	}

	@Test(expected=NullPointerException.class)
	
	public void nullPlayerBonus(){
		
		PermissionCard p1= new PermissionCard();
	p1.useBonuses(null);
				
		
	}
@Test(expected=NullPointerException.class)
	
	public void nullSetRegion(){
		
		PermissionCard p1= new PermissionCard();
	p1.setRegion(null);
				
		
	}

	@Test
	
	public void equalsNoWithNull(){
		
		PermissionCard p1= new PermissionCard();
		assertFalse(p1.equals(null));
		
	}
	
	@Test
	
	public void equalsNoWithNullAndNotNullBonus(){
		
		PermissionCard p1= game.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(0);
		PermissionCard p2= new PermissionCard();
		assertFalse(p1.equals(p2));	
	
	}
	
	
	
	@Test
	
	public void equalsNoWitNullLettersAndNo(){
		
		PermissionCard p1= game.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(0);
	
		p1.getBonuses().clear();
		PermissionCard p2= new PermissionCard();
		assertFalse(p1.equals(p2));	
	}
	
@Test
	
	public void equalsNoWithDifferentLetters(){
		
		PermissionCard p1= game.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(0);
		PermissionCard p2= game.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(1);
		p2.getLetters().add("y");
		assertFalse(p1.equals(p2));	
	}


@Test

public void equalsNoWithDifferentRegion(){

	
	PermissionCard p1= game.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(0);
	PermissionCard p2= game.getGameBoard().getRegions().get(1).getShowedPermissionCards().get(1);
	p1.getBonuses().clear();
	p1.getLetters().clear();
	p2.getBonuses().clear();
	p2.getLetters().clear();
	assertFalse(p1.equals(p2));	
}
	
	
@Test

public void equalsNoWithDifferentUsed(){
	
	PermissionCard p1= game.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(0);
	PermissionCard p2= game.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(1);
	p1.getBonuses().clear();
	p1.getLetters().clear();
	p2.getBonuses().clear();
	p2.getLetters().clear();
	p1.setUsed(true);
	p2.setUsed(false);
	assertFalse(p1.equals(p2));	
}
	
	
	@Test
	
	public void hashCTest(){
		
		PermissionCard p1= game.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(0);
		p1.hashCode();
		
	}
	
	
	
	
	
	
	
}
