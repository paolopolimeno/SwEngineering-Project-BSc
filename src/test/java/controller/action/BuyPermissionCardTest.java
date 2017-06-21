package controller.action;

import static org.junit.Assert.*;
import java.awt.Color;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Balcony;
import it.polimi.ingsw.cg30.model.Counselor;
import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.PoliticCard;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.TurnLap;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.actions.BuyPermissionCard;
import it.polimi.ingsw.cg30.model.util.Util;

public class BuyPermissionCardTest {

	
	Player player;
	Game game;
	Action action;
	StartGame startGame;
	ArrayList<String> choice;
	Balcony balcony;
	
	@Before
	public void setUp() {

		game = new Game();
		startGame = new StartGame(game);
	
		player = new Player(1, "black", game, null);
		game.addPlayer(player);
		startGame.run();
		game.setGameState(new TurnLap(game));
		choice = new ArrayList<>();
		
		balcony= game.getGameBoard().getBalconies().get(2);
		balcony.addCounselor(new Counselor("black"));
		balcony.addCounselor(new Counselor("white"));
		balcony.addCounselor(new Counselor("pink"));
		balcony.addCounselor(new Counselor("red"));
		
		balcony.getRegion().getShowedPermissionCards().get(0).getBonuses().clear();
		balcony.getRegion().getShowedPermissionCards().get(1).getBonuses().clear();
		
		player.getPoliticCards().clear();
		player.getPoliticCards().add(new PoliticCard("black"));
		player.getPoliticCards().add(new PoliticCard("white"));
		player.getPoliticCards().add(new PoliticCard("pink"));
		player.getPoliticCards().add(new PoliticCard("red"));
		player.getPoliticCards().add(new PoliticCard("yellow"));
		
		
		
		
	}
	
	@Test
	
	public 	void testNotValidPoliticCardNotWorking(){
		
		choice.add("5");
		choice.add("1");
		choice.add("END");
		choice.add("3");
		choice.add("1");
		action= new BuyPermissionCard(choice);
		action.setPlayer(player);
		assertFalse(action.actionPerformed());	
	}
	@Test
	
public 	void testValidPoliticCardWorking(){
		
		choice.add("4");
		choice.add("1");
		choice.add("END");
		choice.add("3");
		choice.add("1");
		action= new BuyPermissionCard(choice);
		action.setPlayer(player);
		assertTrue(action.actionPerformed());	
	}
	@Test
	
public 	void testNoIllegalPermissionCard(){
		
		choice.add("4");
		choice.add("1");
		choice.add("END");
		choice.add("3");
		choice.add("3");
		action= new BuyPermissionCard(choice);
		action.setPlayer(player);
		assertFalse(action.actionPerformed());	
	}
	@Test
public 	void testMoneyPaidIs7(){
		
		choice.add("4");
		choice.add("1");
		choice.add("END");
		choice.add("3");
		choice.add("1");
		action= new BuyPermissionCard(choice);
		action.setPlayer(player);

		int moneyPlayer= player.getProsperityDisk().getSlot().getCoordinate();
		action.actionPerformed();
		assertTrue(moneyPlayer-player.getProsperityDisk().getSlot().getCoordinate()==7);	
	}
	
	
	@Test
	public 	void testMoneyPaidIs10(){
			
			choice.add("4");
			
			choice.add("END");
			choice.add("3");
			choice.add("1");
			action= new BuyPermissionCard(choice);
			action.setPlayer(player);
			
			int moneyPlayer= player.getProsperityDisk().getSlot().getCoordinate();
			action.actionPerformed();
		
			assertTrue(moneyPlayer-player.getProsperityDisk().getSlot().getCoordinate()==10);	
		}
	@Test
	public 	void testMoneyPaidIs4(){
			
			choice.add("4");
			choice.add("1");
			choice.add("2");
			choice.add("END");
			choice.add("3");
			choice.add("1");
			action= new BuyPermissionCard(choice);
			action.setPlayer(player);
			
			int moneyPlayer= player.getProsperityDisk().getSlot().getCoordinate();
			action.actionPerformed();

			assertTrue(moneyPlayer-player.getProsperityDisk().getSlot().getCoordinate()==4);	
		}
		
	
	@Test
	public 	void testMoneyPaidIs0(){
			
			choice.add("4");
			choice.add("1");
			choice.add("2");
			choice.add("3");
		
			choice.add("3");
			choice.add("1");
			action= new BuyPermissionCard(choice);
			action.setPlayer(player);
			int moneyPlayer= player.getProsperityDisk().getSlot().getCoordinate();
			action.actionPerformed();
			assertTrue(moneyPlayer-player.getProsperityDisk().getSlot().getCoordinate()==0);	
		}
	
	@Test
	public 	void testNoPoliticCardNotWorking(){
			
			choice.add("END");
		
			choice.add("3");
			choice.add("1");
			action= new BuyPermissionCard(choice);
			action.setPlayer(player);
			assertFalse(action.actionPerformed());
	
		}
	@Test
	public 	void testNotMatchingCards(){

			choice.add("5");
			choice.add("1");
			choice.add("2");
			choice.add("3");
		
			choice.add("3");
			choice.add("1");
			action= new BuyPermissionCard(choice);
			action.setPlayer(player);
			assertFalse(action.actionPerformed());
	
		}
	@Test
	public 	void testTooMuchToPay(){
			
			player.moveProsperityDisk(-9);
			choice.add("4");
			choice.add("END");
	
			choice.add("3");
			choice.add("1");
			action= new BuyPermissionCard(choice);
			action.setPlayer(player);
			assertFalse(action.actionPerformed());
	
		}
	@Test
	public 	void testEndWhereCantGoNotWork(){
			
			choice.add("4");
			choice.add("1");
			choice.add("2");
			choice.add("3");
			choice.add("END");
			choice.add("1");
			choice.add("1");
			action= new BuyPermissionCard(choice);
			action.setPlayer(player);
			
			int moneyPlayer= player.getProsperityDisk().getSlot().getCoordinate();
			
			assertFalse(action.actionPerformed());	
		}
		
	@Test
	
	public void testWithDuplicateCardsNotWorking()
	{
	
	player.getPoliticCards().clear();
	player.getPoliticCards().add(new PoliticCard("pink"));
	player.getPoliticCards().add(new PoliticCard("JOLLY"));
	player.getPoliticCards().add(new PoliticCard("JOLLY"));
	player.getPoliticCards().add(new PoliticCard("red"));

	choice.add("4");
	choice.add("3");
	choice.add("3");
	choice.add("3");
	choice.add("3");
	choice.add("1");
	action= new BuyPermissionCard(choice);
	action.setPlayer(player);
	assertFalse(action.actionPerformed());	
	}
}
