
import static org.junit.Assert.*;


import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.actions.AssistantHiring;
import it.polimi.ingsw.cg30.model.actions.BuildEmporium;
import it.polimi.ingsw.cg30.model.actions.CityTokenAction;
import it.polimi.ingsw.cg30.model.bonus.Bonus;
import it.polimi.ingsw.cg30.model.bonus.CityTokenBonus;
import it.polimi.ingsw.cg30.model.bonus.NobilityBonus;
import it.polimi.ingsw.cg30.model.bonus.PointsBonus;


public class CityTokenBonusTest {
	Player player;
	Game game;
	Bonus bonus;
	StartGame startGame;
	ArrayList<String> choice;
	Action action;

	@Before
	public void setUp() {

		game = new Game();
		startGame = new StartGame(game);

		player = new Player(1, "black", game, null);
		game.addPlayer(player);
		startGame.run();
		choice = new ArrayList<>();
		bonus = new CityTokenBonus(3);
		player.getPossibleActions().clear();

		
		player.getActionsBackup().add(new BuildEmporium(null));
		player.getActionsBackup().add(new AssistantHiring());
		
		bonus.giveBonusToPlayer(player);
		game.getGameBoard().getCities().get(0).buildEmporium(player.getEmporiums().get(0));
		game.getGameBoard().getCities().get(1).buildEmporium(player.getEmporiums().get(0));
		game.getGameBoard().getCities().get(2).buildEmporium(player.getEmporiums().get(0));
		game.getGameBoard().getCities().get(0).getCityBonuses().clear();
		game.getGameBoard().getCities().get(1).getCityBonuses().clear();
		game.getGameBoard().getCities().get(2).getCityBonuses().clear();
		game.getGameBoard().getCities().get(0).getCityBonuses().add(new PointsBonus(2));
		game.getGameBoard().getCities().get(1).getCityBonuses().add(new PointsBonus(2));
		game.getGameBoard().getCities().get(2).getCityBonuses().add(new PointsBonus(2));

	}

	@Test
	public void testPlayerHasOnlyOneAction() {

		assertTrue(player.getPossibleActions().size() == 1);

	}

	@Test
	public void testPlayerActionLeftIsCityTokenAction() {

		assertTrue(player.getPossibleActions().get(0).getClass() == CityTokenAction.class);

	}

	@Test

	public void testWrongChoiceLengthNotWorking() {
		choice.add("1");
		choice.add("2");
		action = new CityTokenAction(2, choice);
		action.setPlayer(player);
		assertFalse(action.actionPerformed());

	}

	@Test
	public void testChoiceLengthMismatchNotWorking() {
		choice.add("1");
		choice.add("2");
		action = new CityTokenAction(3, choice);
		action.setPlayer(player);
		assertFalse(action.actionPerformed());

	}

	@Test
	public void testChoicesmatchNotWorking() {
		choice.add("1");
		choice.add("2");
		action = new CityTokenAction(3, choice);
		action.setPlayer(player);
		assertFalse(action.actionPerformed());

	}

	@Test
	public void testNotWorkingIfNobilityBonus() {
		game.getGameBoard().getCities().get(1).getCityBonuses().add(new NobilityBonus(2));
		choice.add("1");
		choice.add("2");
		choice.add("3");
		action = new CityTokenAction(3, choice);
		action.setPlayer(player);
		assertFalse(action.actionPerformed());
	}

	
	@Test
	public void testNotValidCity() {

		choice.add("1");
		choice.add("0");
		choice.add("3");
		action = new CityTokenAction(3, choice);
		action.setPlayer(player);
		assertFalse(action.actionPerformed());
	}
	@Test
	public void testNotWorkingIfPlayerHasNotBuilt() {

		choice.add("1");
		choice.add("2");
		choice.add("4");
		action = new CityTokenAction(3, choice);
		action.setPlayer(player);
		assertFalse(action.actionPerformed());
	}
	

	@Test
	public void testRightChoiceLengthWorking() {
		choice.add("1");
		choice.add("2");
		choice.add("3");
		action = new CityTokenAction(3, choice);
		action.setPlayer(player);
		assertTrue(action.actionPerformed());
	}

	@Test
	public void testDuplicateNotWorking() {
		choice.add("1");
		choice.add("1");
		choice.add("3");
		action = new CityTokenAction(3, choice);
		action.setPlayer(player);
		assertFalse(action.actionPerformed());
	}
	
	@Test
	public void testPlayerGetsHisActionsBack() {
		choice.add("1");
		choice.add("2");
		choice.add("3");
		action = new CityTokenAction(3, choice);
		action.setPlayer(player);
		action.actionPerformed();
		assertTrue(player.getPossibleActions().size()==2);//action CityTokenAction is not removed
		System.out.println(player.getPossibleActions().size());
	}	
	
	@Test
	public void testPlayerBackUpIsEmptied() {
		choice.add("1");
		choice.add("2");
		choice.add("3");
		action = new CityTokenAction(3, choice);
		action.setPlayer(player);
		action.actionPerformed();
		assertTrue(player.getActionsBackup().size()==0);
		
	}	
	
	@Test
	
	public void testPlayerGetsBonuses(){
	
	
		choice.add("1");
		choice.add("2");
		choice.add("3");
		action = new CityTokenAction(3, choice);
		action.setPlayer(player);
		int pointsNumber=player.getPointsDisk().getSlot().getCoordinate();
		action.actionPerformed();
		assertTrue(player.getPointsDisk().getSlot().getCoordinate()==pointsNumber+6);
		
	}
		
	@Test
	
	public void toStringTest(){
		bonus.toString();
	}
}
