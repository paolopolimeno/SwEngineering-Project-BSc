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
import it.polimi.ingsw.cg30.model.actions.market.SellAction;
import it.polimi.ingsw.cg30.model.actions.market.Thing;

public class TestSellAction {

	Player player;
	Game game;
	SellAction action;
	StartGame startGame;
	ArrayList<String> choice;

	@Before
	public void setUp() {

		game = new Game();
		startGame = new StartGame(game);
		player = new Player(1, "black", game, null);
		game.addPlayer(player);
		startGame.run();
		choice = new ArrayList<>();
		player.getAssistants().clear();
		player.getPoliticCards().clear();
		game.getGameBoard().getAllThingsForSale().clear();
	}

	@Test

	public void testPlayerCanSellAssistants() {

		player.getAssistants().add(new Assistant());
		player.getAssistants().add(new Assistant());
		player.getAssistants().add(new Assistant());

		action = new SellAction();
		action.setPlayer(player);
		action.addThingforSale(new Thing(1, new Assistant(), player.getPlayerNumber()));
		action.addThingforSale(new Thing(1, new Assistant(), player.getPlayerNumber()));
		action.addThingforSale(new Thing(1, new Assistant(), player.getPlayerNumber()));
		assertTrue(action.actionPerformed());

	}

	@Test

	public void assistantsAreActuallySold() {

		player.getAssistants().add(new Assistant());
		player.getAssistants().add(new Assistant());
		player.getAssistants().add(new Assistant());

		action = new SellAction();
		action.setPlayer(player);
		action.addThingforSale(new Thing(1, new Assistant(), player.getPlayerNumber()));
		action.addThingforSale(new Thing(1, new Assistant(), player.getPlayerNumber()));
		action.addThingforSale(new Thing(1, new Assistant(), player.getPlayerNumber()));
		action.actionPerformed();
		assertTrue(player.getGame().getGameBoard().getAllThingsForSale().size() == 3
				&& player.getGame().getGameBoard().getAllThingsForSale().get(0).getThing() instanceof Assistant);

	}

	@Test

	public void testPlayerCanNotSellMoreAssistantsThanHeHas() {

		player.getAssistants().add(new Assistant());
		player.getAssistants().add(new Assistant());
		player.getAssistants().add(new Assistant());

		action = new SellAction();
		action.setPlayer(player);
		action.addThingforSale(new Thing(1, new Assistant(), player.getPlayerNumber()));
		action.addThingforSale(new Thing(1, new Assistant(), player.getPlayerNumber()));
		action.addThingforSale(new Thing(1, new Assistant(), player.getPlayerNumber()));
		action.addThingforSale(new Thing(1, new Assistant(), player.getPlayerNumber()));
		assertFalse(action.actionPerformed());
	}

	@Test
	public void testPlayerCanNotSellObjectsWhichCantBeSold() {

		action = new SellAction();
		action.setPlayer(player);
		action.addThingforSale(new Thing(1, new IndicatorDisk(player), player.getPlayerNumber()));
		assertFalse(action.actionPerformed());
	}

	@Test

	public void testPlayerCanSellPoliticCardsHeHas() {

		player.getPoliticCards().add(new PoliticCard("red"));
		player.getPoliticCards().add(new PoliticCard("blue"));
		player.getPoliticCards().add(new PoliticCard("green"));

		action = new SellAction();
		action.setPlayer(player);
		action.addThingforSale(new Thing(1, new PoliticCard("red"), player.getPlayerNumber()));
		action.addThingforSale(new Thing(1, new PoliticCard("blue"), player.getPlayerNumber()));
		action.addThingforSale(new Thing(1, new PoliticCard("green"), player.getPlayerNumber()));
		assertTrue(action.actionPerformed());
	}

	@Test

	public void testPlayerCanTSellTwoTimesSameThing() {

		player.getPoliticCards().add(new PoliticCard("red"));
		player.getPoliticCards().add(new PoliticCard("blue"));
		player.getPoliticCards().add(new PoliticCard("green"));

		action = new SellAction();
		action.setPlayer(player);
		action.addThingforSale(new Thing(1, new PoliticCard("red"), player.getPlayerNumber()));
		action.addThingforSale(new Thing(1, new PoliticCard("red"), player.getPlayerNumber()));
		assertFalse(action.actionPerformed());
	}
	@Test

	public void testPlayerCanSellOneOfHisCards() {

		player.getPoliticCards().add(new PoliticCard("red"));
		player.getPoliticCards().add(new PoliticCard("blue"));
		player.getPoliticCards().add(new PoliticCard("green"));

		action = new SellAction();
		action.setPlayer(player);
		action.addThingforSale(new Thing(1, new PoliticCard("red"), player.getPlayerNumber()));
		
		assertTrue(action.actionPerformed());
	}
	@Test

	public void testPlayerCanSellTwoOfHisCards() {

		player.getPoliticCards().add(new PoliticCard("red"));
		player.getPoliticCards().add(new PoliticCard("blue"));
		player.getPoliticCards().add(new PoliticCard("green"));

		action = new SellAction();
		action.setPlayer(player);
		action.addThingforSale(new Thing(1, new PoliticCard("red"), player.getPlayerNumber()));
		action.addThingforSale(new Thing(1, new PoliticCard("green"), player.getPlayerNumber()));
		
		assertTrue(action.actionPerformed());
	}

	@Test

	public void testPoliticCardsAreActuallySold() {

		PoliticCard p1, p2, p3, p4, p5, p6;
		p1 = new PoliticCard("red");
		p2 = new PoliticCard("blue");
		p3 = new PoliticCard("green");
		player.getPoliticCards().add(p1);
		player.getPoliticCards().add(p2);
		player.getPoliticCards().add(p3);

		action = new SellAction();
		action.addThingforSale(new Thing(1, p1, player.getPlayerNumber()));
		action.addThingforSale(new Thing(1, p2, player.getPlayerNumber()));
		action.addThingforSale(new Thing(1, p3, player.getPlayerNumber()));
		action.setPlayer(player);
		action.actionPerformed();

		p4 = (PoliticCard) game.getGameBoard().getAllThingsForSale().get(0).getThing();
		p5 = (PoliticCard) game.getGameBoard().getAllThingsForSale().get(1).getThing();
		p6 = (PoliticCard) game.getGameBoard().getAllThingsForSale().get(2).getThing();

		assertTrue(p1.equals(p4) && p2.equals(p5) && p3.equals(p6));
	}

	@Test

	public void testPlayerCanNotSellPoliticCardsHeHasNot() {

		player.getPoliticCards().add(new PoliticCard("red"));
		player.getPoliticCards().add(new PoliticCard("blue"));
		player.getPoliticCards().add(new PoliticCard("green"));

		action = new SellAction();
		action.setPlayer(player);
		action.addThingforSale(new Thing(1, new PoliticCard("green"), player.getPlayerNumber()));
		action.addThingforSale(new Thing(1, new PoliticCard("blue"), player.getPlayerNumber()));
		action.addThingforSale(new Thing(1, new PoliticCard("green"), player.getPlayerNumber()));
		assertFalse(action.actionPerformed());
	}
	@Test

	public void testPlayerCanNotSellPermissionCardHeHasNot() {

	

		action = new SellAction();
		action.setPlayer(player);
		action.addThingforSale(new Thing(1, new PermissionCard(), player.getPlayerNumber()));
	
		assertFalse(action.actionPerformed());
	}
	@Test

	public void testPlayerCanNotSellMorePoliticCardsThanHeHas() {

		player.getPoliticCards().add(new PoliticCard("red"));
		player.getPoliticCards().add(new PoliticCard("blue"));
		player.getPoliticCards().add(new PoliticCard("green"));

		action = new SellAction();
		action.setPlayer(player);
		action.addThingforSale(new Thing(1, new PoliticCard("red"), player.getPlayerNumber()));
		action.addThingforSale(new Thing(1, new PoliticCard("blue"), player.getPlayerNumber()));
		action.addThingforSale(new Thing(1, new PoliticCard("green"), player.getPlayerNumber()));
		action.addThingforSale(new Thing(1, new PoliticCard("green"), player.getPlayerNumber()));
		assertFalse(action.actionPerformed());
	}

}
