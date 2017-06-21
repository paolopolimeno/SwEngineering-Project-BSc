package controller.action;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Assistant;
import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.IndicatorDisk;
import it.polimi.ingsw.cg30.model.PermissionCard;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.PoliticCard;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.actions.market.BuyAction;
import it.polimi.ingsw.cg30.model.actions.market.SellAction;
import it.polimi.ingsw.cg30.model.actions.market.Thing;

public class TestBuyAction {

	Player player;
	Player player2;
	Game game;
	BuyAction action;
	StartGame startGame;
	ArrayList<String> choice;

	@Before
	public void setUp() {

		game = new Game();
		startGame = new StartGame(game);
	
		player = new Player(1, "black", game, null);
		player2 = new Player(1, "black", game, null);
	
		game.addPlayer(player);
		game.addPlayer(player2);
		startGame.run();
		choice = new ArrayList<>();
		player.getAssistants().clear();
		player.getPoliticCards().clear();
		player.getPermissionCards().clear();
		player.getPermissionCards().add(new PermissionCard());
		player2.getAssistants().clear();
		player2.getPoliticCards().clear();
		player2.getPermissionCards().clear();
		game.getGameBoard().getAllThingsForSale().clear();
		
		player.getAssistants().add(new Assistant());
		player.getAssistants().add(new Assistant());
		player.getAssistants().add(new Assistant());
		
		player.getPoliticCards().add(new PoliticCard("black"));
		player.getPoliticCards().add(new PoliticCard("red"));
		player.getPoliticCards().add(new PoliticCard("yellow"));
		
		game.getGameBoard().getAllThingsForSale().add(new Thing(4,player.getAssistants().get(0),player.getPlayerNumber()));
		game.getGameBoard().getAllThingsForSale().add(new Thing(3,player.getPoliticCards().get(0),player.getPlayerNumber()));
		game.getGameBoard().getAllThingsForSale().add(new Thing(3,player.getPermissionCards().get(0),player.getPlayerNumber()));
		game.getGameBoard().getAllThingsForSale().add(new Thing(20,player.getPermissionCards().get(0),player.getPlayerNumber()));	
		
	}
	
	@Test
	
	public void player2CanBuyEmpty(){
		
		action= new BuyAction();
		action.setPlayer(player2);
		assertTrue(action.actionPerformed());
	}
	
	@Test
	
	public void player2BuysOnePoliticCard(){
		
		
		
		action= new BuyAction();
		action.setPlayer(player2);
		action.addThingToBuy(new Thing(3, new PoliticCard("black"),player.getPlayerNumber()));
		assertTrue(action.actionPerformed());
		
		
	}
	@Test
	
	public void player2BuysOneAssistant(){
		
		
		
		action= new BuyAction();
		action.setPlayer(player2);
		action.addThingToBuy(new Thing(4,player.getAssistants().get(0),player.getPlayerNumber()));
		assertTrue(action.actionPerformed());
		
		
	}
	
	@Test
	
	public void player2BuysOnePermissionCard(){
		
		
		
		action= new BuyAction();
		action.setPlayer(player2);
		action.addThingToBuy(new Thing(3,player.getPermissionCards().get(0),player.getPlayerNumber()));
		assertTrue(action.actionPerformed());
		
		
	}	
	@Test
	
	public void player2CantBuyTooExpensiveObject(){
		
		
		
		action= new BuyAction();
		action.setPlayer(player2);
		action.addThingToBuy(new Thing(20,player.getPermissionCards().get(0),player.getPlayerNumber()));	
		assertFalse(action.actionPerformed());
		
		
	}	
	
	@Test
	
	public void player2CanNotBuyPoliticCardNotOnTheListDifferentColour(){
		
		
		
		action= new BuyAction();
		action.setPlayer(player2);
		action.addThingToBuy(new Thing(3, new PoliticCard("yellow"),player.getPlayerNumber()));
		assertFalse(action.actionPerformed());
		
		
	}
	@Test
	
	public void player2CanNotBuyPoliticCardNotOnTheListDifferentPrice(){
		
		
		
		action= new BuyAction();
		action.setPlayer(player2);
		action.addThingToBuy(new Thing(2, new PoliticCard("black"),player.getPlayerNumber()));
		assertFalse(action.actionPerformed());
		
		
	}	
	@Test
	
	public void player2CanNotBuyPoliticCardNotOnTheListDifferentPlayerNumber(){
		
		
		
		action= new BuyAction();
		action.setPlayer(player2);
		action.addThingToBuy(new Thing(2, new PoliticCard("black"),player2.getPlayerNumber()));
		assertFalse(action.actionPerformed());
		
		
	}
	@Test
	
	public void player2ActuallyBuysOnePoliticCard(){
		
		
		
		action= new BuyAction();
		action.setPlayer(player2);
		action.addThingToBuy(new Thing(3, new PoliticCard("black"),player.getPlayerNumber()));
		action.actionPerformed();
		assertTrue(player2.getPoliticCards().get(0).getColor()=="black");
		
	}
	
	@Test
	public void playerGetsPoliticCardRemoved(){
		
		
		
		action= new BuyAction();
		action.setPlayer(player2);
		action.addThingToBuy(new Thing(3, new PoliticCard("black"),player.getPlayerNumber()));
		int size= player.getPoliticCards().size();
		action.actionPerformed();
		assertTrue(player.getPoliticCards().size()==size-1);
		
	}
	
	
	
	@Test(expected=NullPointerException.class)
	
	public void CanNotAddNullThinToBuy(){
		action= new BuyAction();
		action.addThingToBuy(null);
		
	}
	
	
	
}
	
	
	
	
	