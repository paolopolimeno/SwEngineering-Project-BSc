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
import it.polimi.ingsw.cg30.model.bonus.Bonus;
import it.polimi.ingsw.cg30.model.bonus.FreePermissionCardBonus;
import it.polimi.ingsw.cg30.model.bonus.PointsBonus;
import it.polimi.ingsw.cg30.model.bonus.ProsperityBonus;

public class FreePermissionCardBonusTest {

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
			player = new Player(1,"black", game, null);

			game.addPlayer(player);
			startGame.run();
			player.getPossibleActions().clear();
			choice = new ArrayList<>();
			bonus = new FreePermissionCardBonus(2);
			
			player.getActionsBackup().add(new BuildEmporium(null));
			player.getActionsBackup().add(new AssistantHiring());
			
			bonus.giveBonusToPlayer(player);
			player.getPermissionCards().clear();
	
			
			game.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(0).getBonuses().clear(); 
			game.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(1).getBonuses().clear();
			game.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(0).getLetters().clear();
			game.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(1).getLetters().clear();
			
			game.getGameBoard().getRegions().get(1).getShowedPermissionCards().get(0).getBonuses().clear(); 
			game.getGameBoard().getRegions().get(1).getShowedPermissionCards().get(1).getBonuses().clear();
			game.getGameBoard().getRegions().get(1).getShowedPermissionCards().get(0).getLetters().clear();
			game.getGameBoard().getRegions().get(1).getShowedPermissionCards().get(1).getLetters().clear();
			
			game.getGameBoard().getRegions().get(2).getShowedPermissionCards().get(0).getBonuses().clear(); 
			game.getGameBoard().getRegions().get(2).getShowedPermissionCards().get(1).getBonuses().clear();
			game.getGameBoard().getRegions().get(2).getShowedPermissionCards().get(0).getLetters().clear();
			game.getGameBoard().getRegions().get(2).getShowedPermissionCards().get(1).getLetters().clear();
			
			
		
			
			game.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(0).getBonuses().add(new PointsBonus(3));
			game.getGameBoard().getRegions().get(1).getShowedPermissionCards().get(0).getBonuses().add(new ProsperityBonus(3));
			game.getGameBoard().getRegions().get(2).getShowedPermissionCards().get(0).getBonuses().add(new PointsBonus(1));
			
			game.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(1).getBonuses().add(new PointsBonus(3));
			game.getGameBoard().getRegions().get(1).getShowedPermissionCards().get(1).getBonuses().add(new ProsperityBonus(3));
			game.getGameBoard().getRegions().get(2).getShowedPermissionCards().get(1).getBonuses().add(new PointsBonus(1));
			
			game.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(0).getLetters().add("a");
			game.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(0).getLetters().add("b");
			game.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(0).getLetters().add("c");
			game.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(0).getLetters().add("d");
			
			
			game.getGameBoard().getRegions().get(1).getShowedPermissionCards().get(0).getLetters().add("f");
			game.getGameBoard().getRegions().get(1).getShowedPermissionCards().get(0).getLetters().add("g");
			game.getGameBoard().getRegions().get(1).getShowedPermissionCards().get(0).getLetters().add("h");
			game.getGameBoard().getRegions().get(1).getShowedPermissionCards().get(0).getLetters().add("i");
			
		}
		
		@Test
		public void testPlayerHasOnlyOneAction() {

			assertTrue(player.getPossibleActions().size() == 1);

		}
		@Test
		public void testPlayerActionLeftIsFreePermissionCardAction() {

			assertTrue(player.getPossibleActions().get(0).getClass() == FreePermissionCardAction.class);

		}


		@Test

		public void testWrongChoiceLengthNotWorking() {
			choice.add("1");
			choice.add("2");
			choice.add("1");
			action = new FreePermissionCardAction(3, choice);
			action.setPlayer(player);
			assertFalse(action.actionPerformed());

		}
		@Test

		public void testRightChoiceLengthWorking() {
			choice.add("1");
			choice.add("2");
			choice.add("1");
			choice.add("2");
			action = new FreePermissionCardAction(2, choice);
			action.setPlayer(player);
			assertTrue(action.actionPerformed());

		}
		
		@Test

		public void testLengthMismatchNotWorking() {
			choice.add("1");
			choice.add("2");
			action = new FreePermissionCardAction(2, choice);
			action.setPlayer(player);
			assertFalse(action.actionPerformed());

		}
		
		@Test
		public void testPlayerGetsHisActionsBack() {
			choice.add("1");
			choice.add("2");
			choice.add("1");
			choice.add("2");
			action = new FreePermissionCardAction(2, choice);
			action.setPlayer(player);
			action.actionPerformed();
			assertTrue(player.getPossibleActions().size()==2);//action FreePermissionCardAction is not removed
			
		}	
		
		
		@Test
		
		public void testNotValidCity(){
			
			choice.add("17");
			choice.add("2");
			choice.add("1");
			choice.add("2");
			action = new FreePermissionCardAction(2, choice);
			action.setPlayer(player);
			assertFalse(action.actionPerformed());		
					
		}

		@Test
		
		public void testNotValidCard(){
			
			choice.add("1");
			choice.add("3");
			choice.add("1");
			choice.add("2");
			action = new FreePermissionCardAction(2, choice);
			action.setPlayer(player);
			assertFalse(action.actionPerformed());		
					
		}

		
		@Test
		
		public void playerGetsPermissionCards(){
			
			choice.add("1");
			choice.add("1");
			choice.add("2");
			choice.add("1");
			action = new FreePermissionCardAction(2, choice);
			action.setPlayer(player);
			action.actionPerformed();
			PermissionCard permissionCard=player.getPermissionCards().get(0);
			PermissionCard permissionCard1=player.getPermissionCards().get(1);
			assertTrue(permissionCard.getLetters().contains("a")&&permissionCard.getLetters().contains("b")&& permissionCard1.getLetters().contains("f")&&permissionCard.getLetters().size()==4&&permissionCard1.getLetters().size()==4);
	
		}
		@Test
		
		public void toStringTest(){
			bonus.toString();
		}
		
}