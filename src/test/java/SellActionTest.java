

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.PermissionCard;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.actions.market.SellAction;
import it.polimi.ingsw.cg30.model.actions.market.Thing;

public class SellActionTest {
	
	Game game;
	StartGame start; 
	Player seller;
	
	@Before
	
	public void setUp(){
		 game = new Game();
		 start = new StartGame(game);
		 seller = new Player(1, "red", game, null);
		 game.addPlayer(seller);
		 start.run();
	}
	@Test
	public void testActionPerformed() {
		
		SellAction sl = new SellAction();
		seller.addPossibleActions(sl);
		seller.getPermissionCards().add(new PermissionCard());
		//Thing thing1 = new Thing(seller.getPermissionCards().get(0), 2, seller);
		//Thing thing2 = new Thing(seller.getPermissionCards().get(0), 2, seller);
		Thing thing3 = new Thing(2, seller.getAssistants().get(0), seller.getPlayerNumber());
		Thing thing4 = new Thing(2, seller.getPoliticCards().get(0), seller.getPlayerNumber());
		Thing thing5 = new Thing(2, seller.getPermissionCards().get(0), seller.getPlayerNumber());
		//sl.addThingforSale(thing1);
		//sl.addThingforSale(thing2);
		sl.addThingforSale(thing3);
		sl.addThingforSale(thing4);
		sl.addThingforSale(thing5);
		sl.setPlayer(seller);
		
		assertTrue(sl.actionPerformed());
		
	}

	@Test (expected=NullPointerException.class)
	public void CanNotAddThingForSaleNull(){
		
		SellAction sl = new SellAction();
		sl.addThingforSale(null);
	}
	
	@Test (expected=NullPointerException.class)
	
	public void CanNotBuyPermissionCardIDontHave(){
		
		PermissionCard p1= new PermissionCard();
		p1.setUsed(false);
		SellAction sl = new SellAction();
		sl.addThingforSale(new Thing(2, p1, seller.getPlayerNumber()));
		assertFalse(sl.actionPerformed());
	}
	
	
}
