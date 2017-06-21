package model.bonus;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.PermissionCard;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.TurnLap;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.actions.AssistantHiring;
import it.polimi.ingsw.cg30.model.actions.BuildEmporium;
import it.polimi.ingsw.cg30.model.actions.CityTokenAction;
import it.polimi.ingsw.cg30.model.actions.FreePermissionCardAction;
import it.polimi.ingsw.cg30.model.actions.RepeatPermissionCardAction;
import it.polimi.ingsw.cg30.model.bonus.Bonus;
import it.polimi.ingsw.cg30.model.bonus.PointsBonus;
import it.polimi.ingsw.cg30.model.bonus.ProsperityBonus;
import it.polimi.ingsw.cg30.model.bonus.RepeatPermissionCardBonus;

public class RepeatPermissionCardBonusTest {

	


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
			startGame.run();
			player = new Player(1, "black", game, null);
			
			game.addPlayer(player);
			startGame.run();
			player.getPossibleActions().clear();
			choice = new ArrayList<>();
			bonus = new RepeatPermissionCardBonus(2);
			
			
			player.getActionsBackup().add(new BuildEmporium(null));
			player.getActionsBackup().add(new AssistantHiring());
			
			bonus.giveBonusToPlayer(player);
			player.getPermissionCards().clear();
			
			
			
			player.takePermissionCard(game.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(0));
			player.takePermissionCard(game.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(0));
			player.takePermissionCard(game.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(0));
			player.takePermissionCard(game.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(0));
			
			player.getPermissionCards().get(0).setUsed(true);
			player.getPermissionCards().get(1).setUsed(true);
			player.getPermissionCards().get(2).setUsed(false);
			player.getPermissionCards().get(3).setUsed(false);
	
	
			player.getPermissionCards().get(0).getBonuses().clear();
			player.getPermissionCards().get(1).getBonuses().clear();
			player.getPermissionCards().get(2).getBonuses().clear();
			player.getPermissionCards().get(3).getBonuses().clear();
			
			
			player.getPermissionCards().get(0).getBonuses().add(new PointsBonus(3));
			player.getPermissionCards().get(0).getBonuses().add(new ProsperityBonus(3));
			
			player.getPermissionCards().get(3).getBonuses().add(new PointsBonus(2));
			player.getPermissionCards().get(3).getBonuses().add(new ProsperityBonus(2));
	
	
}

		@Test
		public void testPlayerHasOnlyOneAction() {

			assertTrue(player.getPossibleActions().size() == 1);

		}
		@Test
		public void testPlayerActionLeftIsRepeatPermissionCardAction() {

			assertTrue(player.getPossibleActions().get(0).getClass() == RepeatPermissionCardAction.class);

		}

		@Test

		public void testWrongChoiceLengthNotWorking() {
			choice.add("1");
			choice.add("2");
			choice.add("3");
			action = new RepeatPermissionCardAction(3, choice);
			action.setPlayer(player);
			assertFalse(action.actionPerformed());

		}
		@Test

		public void testChoiceMismatchLengthNotWorking() {
			choice.add("1");
			choice.add("2");
			choice.add("3");
			action = new RepeatPermissionCardAction(2, choice);
			action.setPlayer(player);
			assertFalse(action.actionPerformed());

		}
		@Test
		public void testOutOfBoundNotWorking() {
			choice.add("5");
			choice.add("2");
			action = new RepeatPermissionCardAction(2, choice);
			action.setPlayer(player);
			assertFalse(action.actionPerformed());

		}
		@Test
		public void testRightLengthWorking() {
			choice.add("1");
			choice.add("2");
			action = new RepeatPermissionCardAction(2, choice);
			action.setPlayer(player);
			assertTrue(action.actionPerformed());

		}
		@Test
		public void testPlayerGetsHisActionsBack() {
			choice.add("1");
			choice.add("2");
		
			action = new RepeatPermissionCardAction(2, choice);
			action.setPlayer(player);
			action.actionPerformed();
			assertTrue(player.getPossibleActions().size()==2);//action FreePermissionCardAction is not removed
			
		}	
		
		
		@Test
		public void testPlayerActuallyGetsBonuses() {
			choice.add("1");
			choice.add("4");
			action = new RepeatPermissionCardAction(2, choice);
			action.setPlayer(player);
			int playerPoints=player.getPointsDisk().getSlot().getCoordinate();
			int playerProsperity=player.getProsperityDisk().getSlot().getCoordinate();
			action.actionPerformed();
			assertTrue((playerPoints==player.getPointsDisk().getSlot().getCoordinate()-5)&&(playerProsperity==player.getProsperityDisk().getSlot().getCoordinate()-5));
			
		}
		@Test
		public void testPlayerActuallyGetsBonuses1() {
			choice.add("1");
			choice.add("2");
			action = new RepeatPermissionCardAction(2, choice);
			action.setPlayer(player);
			int playerPoints=player.getPointsDisk().getSlot().getCoordinate();
			int playerProsperity=player.getProsperityDisk().getSlot().getCoordinate();
			action.actionPerformed();
			assertTrue((playerPoints==player.getPointsDisk().getSlot().getCoordinate()-3)&&(playerProsperity==player.getProsperityDisk().getSlot().getCoordinate()-3));
			
		}
		
		@Test
		
		public void toStringTest(){
			bonus.toString();
		}

		
		
		
		
}