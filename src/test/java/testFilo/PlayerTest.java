package testFilo;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.NobilitySlot;
import it.polimi.ingsw.cg30.model.PermissionCard;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.PoliticCard;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.actions.BonusAction;
import it.polimi.ingsw.cg30.model.actions.BuildEmporium;
import it.polimi.ingsw.cg30.model.actions.CityTokenAction;
import it.polimi.ingsw.cg30.model.actions.ElectCounselor;
import it.polimi.ingsw.cg30.model.actions.FreePermissionCardAction;
import it.polimi.ingsw.cg30.model.actions.MainAction;
import it.polimi.ingsw.cg30.model.actions.market.BuyAction;
import it.polimi.ingsw.cg30.model.actions.market.SellAction;
import it.polimi.ingsw.cg30.model.bonus.AssistantBonus;
import it.polimi.ingsw.cg30.model.bonus.Bonus;
import it.polimi.ingsw.cg30.model.util.Util;

public class PlayerTest {

	Player player;
	Game game;

	@Before
	public void initializePlayer() {

		game = new Game();
		StartGame startGame = new StartGame(game);
		startGame.run();
		player = new Player(1, "black", game, null);
		player.init();
		game.addPlayer(player);
	}

	@Test(expected = NullPointerException.class)
	public void testPlayer(){
		
		Player player2 = new Player(1, null, null, null);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPlayerIllegal(){
		
		Player player2 = new Player(0, "black", game, null);
	}
	
	@Test
	public void testGetGame() {

		assertEquals(game, player.getGame());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateInitialAssistant() {

		player.createInitialAssistant(1);
		assertEquals(2, player.getAssistants().size());

		player.createInitialAssistant(-3);
		player.createInitialAssistant(0);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testAssistant() {

		player.takeAssistant();
		assertEquals(2, player.getAssistants().size());

		player.removeAssistants(1);
		assertEquals(1, player.getAssistants().size());

		player.removeAssistants(-1);
	}


	@Test(expected = NullPointerException.class)
	public void testTakePermissionCards() {

		PermissionCard selectedPermissionCard = game.getGameBoard().getHill().getShowedPermissionCards().get(0);
		player.takePermissionCard(selectedPermissionCard);
		assertEquals(1, player.getPermissionCards().size());

		player.takePermissionCard(null);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testMoveProsperityDisk() {

		player.moveProsperityDisk(10);

		assertEquals(20, player.getProsperityDisk().getSlot().getCoordinate());

		player.moveProsperityDisk(100);

		assertEquals(20, player.getProsperityDisk().getSlot().getCoordinate());

		player.moveProsperityDisk(-10);
		player.moveProsperityDisk(-15);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testMovePointsDisk() {

		player.movePointsDisk(10);

		assertEquals(10, player.getPointsDisk().getSlot().getCoordinate());

		player.movePointsDisk(100);

		assertEquals(99, player.getPointsDisk().getSlot().getCoordinate());

		player.movePointsDisk(-10);
		player.movePointsDisk(-100);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testMoveNobilityDisk(){
		
		player.moveNobilityDisk(10);
		assertEquals(10, player.getNobilityDisk().getSlot().getCoordinate());
		
		player.moveNobilityDisk(100);
		
		assertEquals(20, player.getNobilityDisk().getSlot().getCoordinate());
		
		player.moveNobilityDisk(-10);
		player.moveNobilityDisk(-100);
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTakePoliticCard() {

		int initAmount = player.getPoliticCards().size();
		player.takePoliticCards(1);
		assertEquals(player.getPoliticCards().size(), initAmount + 1);

		player.takePoliticCards(-3);
	}

	@Test
	public void testGetPlayerNumber() {

		assertEquals(1, player.getPlayerNumber());
	}

	@Test
	public void testGetColor() {

		assertEquals("black", player.getColor());
	}

	@Test
	public void testEmporiums() {

		player.removeEmporium();
		assertEquals(9, player.getEmporiums().size());
	}

	@Test
	public void testGetPossibleActions() {

		assertEquals(0, player.getPossibleActions().size());
	}

	@Test(expected = NullPointerException.class)
	public void testAddPossibleAction() {

		player.addPossibleActions(new ElectCounselor(null));
		assertEquals(1, player.getPossibleActions().size());

		player.addPossibleActions(null);

	}

	

	@Test
	public void testGetBonusCards() {

		player.getBonusCards();
	}

	@Test
	public void testMainAction() {

		player.mainActionFiller();
		assertEquals(4, player.getPossibleActions().size());

		player.mainActionRemover();
		assertEquals(0, player.getPossibleActions().size());

	}

	@Test
	public void testFastAction() {

		player.fastActionFiller();
		assertEquals(5, player.getPossibleActions().size());

		player.fastActionRemover();
		assertEquals(0, player.getPossibleActions().size());
	}
	
	@Test
	public void returnVoidPoliticCards(){
		int i = player.getPoliticCards().size();
		player.returnPoliticCards(null);
		assertTrue(i==player.getPoliticCards().size());
	}

	@Test
	public void testSellActionRemover(){
		Action action = new SellAction();
		player.addPossibleActions(action);
		player.sellActionRemover();
		
		boolean result = false;
		
		for (Action a : player.getPossibleActions()){
			if (a instanceof SellAction){
				result = true;
			}
		}
		assertFalse(result);
	}
	
	@Test
	public void testBuyActionRemover(){
		Action action = new BuyAction();
		player.addPossibleActions(action);
		player.buyActionRemover();
		
		boolean result = false;
		
		for (Action a : player.getPossibleActions()){
			if (a instanceof BuyAction){
				result = true;
			}
		}
		assertFalse(result);
	}
	
	@Test
	public void testSetPossibleAction(){
		player.getPossibleActions().clear();
		ArrayList<Action> actions = new ArrayList<>();
		actions.add(new BuyAction());
		actions.add(new SellAction());
		player.setPossibleActions(actions);
		assertEquals(actions, player.getPossibleActions());
	}

	
	@Test
	
	public void checkBonusYes(){
		player.getPossibleActions().clear();
		player.getPossibleActions().add(new CityTokenAction(1,null));
		assertTrue(player.checkIfHasBonus());
	}
	
@Test
	
	public void checkBonusNo(){
		player.getPossibleActions().clear();
		assertFalse(player.checkIfHasBonus());
	}

	@Test 
	
	public void testBackUp(){
		player.getPossibleActions().clear();
		player.getActionsBackup().clear();
		player.getPossibleActions().add(new CityTokenAction(1,null));
		player.getPossibleActions().add(new BuildEmporium(null));
		player.makeBackUpAndRemove();
		assertTrue(player.getActionsBackup().size()==1 && player.getPossibleActions().size()==1 && player.getActionsBackup().get(0).getClass().getSuperclass()==MainAction.class&&
				player.getPossibleActions().get(0).getClass().getSuperclass()==BonusAction.class);
		
		
	}
	
	
	
	
	
}
