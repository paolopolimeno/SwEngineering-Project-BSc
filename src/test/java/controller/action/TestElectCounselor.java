package controller.action;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;

import it.polimi.ingsw.cg30.model.*;
import it.polimi.ingsw.cg30.model.actions.*;

public class TestElectCounselor {

	Action action;
	Player giocatore1;
	ArrayList<String> choice = new ArrayList<>();
	Game game;

	@Before
	public void initializeAction() {

		game = new Game();
		StartGame startGame = new StartGame(game);
		giocatore1 = new Player(1, "black", game, null);
		game.addPlayer(giocatore1);
		startGame.run();
		action = new AnotherMainAction();
		action.setPlayer(giocatore1);
	}
	@Test
	public void controlPassingInvalidCounselorElectCounselor() {


		choice.add("0");
		choice.add("1");
		action = new ElectCounselor(choice);
		action.setPlayer(giocatore1);
		assertFalse(action.actionPerformed());

	}

	@Test
	public void controlPassingNotIntegerCounselorElectCounselor() {
	
		choice.add("hello");
		choice.add("1");
		action = new ElectCounselor(choice);
		action.setPlayer(giocatore1);
		assertFalse(action.actionPerformed());

	}

	@Test
	public void controlPassingInvalidBalconyElectCounselor() {
		

		choice.add("1");
		choice.add("0");
		action = new ElectCounselor(choice);
		action.setPlayer(giocatore1);
		assertFalse(action.actionPerformed());

	}

	@Test
	public void controlPassingNullBalconyElectCounselor() {

		choice.add("1");
		choice.add(null);
		action = new ElectCounselor(choice);
		action.setPlayer(giocatore1);
		assertFalse(action.actionPerformed());

	}

	@Test
	public void controlPassingValidParametersElectCounselor() {
		
		int numberCoins = giocatore1.getProsperityDisk().getSlot().getCoordinate();

		choice.add("1");
		choice.add("2");
		action = new ElectCounselor(choice);
		action.setPlayer(giocatore1);
		assertTrue(action.actionPerformed());

	}

	@Test
	public void controlCounselorisMovedinBalconyElectCounselor() {
	
		Counselor counselorAdded = game.getGameBoard().getCounselors().get(0);

		choice.add("1");
		choice.add("2");
		action = new ElectCounselor(choice);
		action.setPlayer(giocatore1);
		action.actionPerformed();

		Iterator<Counselor> i = game.getGameBoard().getBalconies().get(1).getCounselors().iterator();
		Counselor counselorBalcony1 = i.next();
		counselorBalcony1 = i.next();
		counselorBalcony1 = i.next();
		counselorBalcony1 = i.next();
		assertEquals(counselorAdded, counselorBalcony1);
	}

	@Test
	public void controlMoneyIsAddedElectCounselor() {
	
		game.addPlayer(giocatore1);
		int numberCoins = giocatore1.getProsperityDisk().getSlot().getCoordinate();

		choice.add("1");
		choice.add("2");
		action = new ElectCounselor(choice);
		action.setPlayer(giocatore1);
		action.actionPerformed();

		int newCoins = giocatore1.getProsperityDisk().getSlot().getCoordinate();
		Util.println(numberCoins+"");
		Util.println(newCoins+"");
		assertTrue(newCoins == numberCoins + 4);

	}

}