package controller.action;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Emporium;
import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.PermissionCard;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.actions.BuildEmporium;
import it.polimi.ingsw.cg30.model.actions.ElectCounselor;

public class BuildEmporiumTest {

	Player player;
	Game game;
	Action action;
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

	}

	@Test
	public void testControlNoPermissionCard() {

		choice.add("0");
		choice.add("1");
		action = new BuildEmporium(choice);
		action.setPlayer(player);
		assertFalse(action.actionPerformed());

	}

	@Test
	public void testControlYesPermissionCard() {

		choice.add("1");
		choice.add("1");
		PermissionCard p = new PermissionCard();
		p.getLetters().add("a");

		player.getPermissionCards().add(p);
		action = new BuildEmporium(choice);
		action.setPlayer(player);
		player.createEmporiums();

		assertTrue(action.actionPerformed());

	}
	@Test
	public void testControlNotLegitCity() {

		choice.add("20");
		choice.add("1");
		PermissionCard p = new PermissionCard();
		p.getLetters().add("a");

		player.getPermissionCards().add(p);
		action = new BuildEmporium(choice);
		action.setPlayer(player);
		player.createEmporiums();

		assertFalse(action.actionPerformed());

	}	
	@Test
	public void testControlNotPermissionCard() {

		choice.add("1");
		choice.add("20");
		PermissionCard p = new PermissionCard();
		p.getLetters().add("a");

		player.getPermissionCards().add(p);
		action = new BuildEmporium(choice);
		action.setPlayer(player);
		player.createEmporiums();

		assertFalse(action.actionPerformed());

	}
	@Test
	public void testControlYesBuildIfEnoughAssistants() {

		choice.add("1");
		choice.add("1");
		PermissionCard p = new PermissionCard();
		p.getLetters().add("a");
		player.getAssistants().clear();
		player.takeAssistant();
		game.getGameBoard().getCities().get(0).getEmporiumsSpace().add(new Emporium("black",2));

		player.getPermissionCards().add(p);
		action = new BuildEmporium(choice);
		action.setPlayer(player);
		player.createEmporiums();

		assertTrue(action.actionPerformed());

	}
	@Test
	public void testControlNoBuildIfNotEnoughAssistants() {

		choice.add("1");
		choice.add("1");
		PermissionCard p = new PermissionCard();
		p.getLetters().add("a");
		player.getAssistants().clear();
		
		game.getGameBoard().getCities().get(0).getEmporiumsSpace().add(new Emporium("black",2));

		player.getPermissionCards().add(p);
		action = new BuildEmporium(choice);
		action.setPlayer(player);
		player.createEmporiums();

		assertFalse(action.actionPerformed());

	}


	@Test
	public void testControlNoBuildUsedPermissionCard() {

		choice.add("1");
		choice.add("1");
		PermissionCard p = new PermissionCard();
		p.getLetters().add("a");
		p.setUsed(true);

		player.getPermissionCards().add(p);
		action = new BuildEmporium(choice);
		action.setPlayer(player);
		player.createEmporiums();

		assertFalse(action.actionPerformed());

	}
	@Test
	public void testControlNoBuildChoiceGreaterThanTwo() {

		choice.add("1");
		choice.add("1");
		choice.add("1");
		PermissionCard p = new PermissionCard();
		p.getLetters().add("a");

		player.getPermissionCards().add(p);
		action = new BuildEmporium(choice);
		action.setPlayer(player);
		player.createEmporiums();

		assertFalse(action.actionPerformed());

	}
	
	

	@Test
	public void testControlNoBuildIfNoEmporium() {

		choice.add("1");
		choice.add("1");
		PermissionCard p = new PermissionCard();
		p.getLetters().add("a");
		player.getEmporiums().clear();
		player.getPermissionCards().add(p);
		action = new BuildEmporium(choice);
		action.setPlayer(player);
		

		assertFalse(action.actionPerformed());

	}
	@Test
	public void testControlEmporiumisBuilt() {

		choice.add("1");
		choice.add("1");
		PermissionCard p = new PermissionCard();
		p.getLetters().add("a");
		player.getPermissionCards().add(p);
		player.createEmporiums();
		Emporium builtEmporium;
		builtEmporium = player.getEmporiums().get(0);
		action = new BuildEmporium(choice);
		action.setPlayer(player);
		action.actionPerformed();
		assertEquals(builtEmporium, player.getGame().getGameBoard().getCities().get(0).getEmporiumsSpace().get(0));

	}

	@Test
	public void testControlEmporiumisRemovedFromPlayer() {

		choice.add("1");
		choice.add("1");
		PermissionCard p = new PermissionCard();
		p.getLetters().add("a");
		player.getPermissionCards().add(p);
		player.createEmporiums();
		Emporium builtEmporium;
		builtEmporium = player.getEmporiums().get(0);
		action = new BuildEmporium(choice);
		int size = player.getEmporiums().size();
		action.setPlayer(player);
		action.actionPerformed();
		assertEquals(size - 1, player.getEmporiums().size());

	}

	@Test
	public void testControlCardNullNotWorking() {

		choice.add(null);
		choice.add("1");
		PermissionCard p = new PermissionCard();
		p.getLetters().add("a");
		player.getPermissionCards().add(p);
		player.createEmporiums();
		Emporium builtEmporium;
		builtEmporium = player.getEmporiums().get(0);
		action = new BuildEmporium(choice);
		int size = player.getEmporiums().size();
		action.setPlayer(player);
		assertFalse(action.actionPerformed());

	}

	@Test
	public void testControlNotBuildWithWrongCard() {

		choice.add("1");
		choice.add("1");
		PermissionCard p = new PermissionCard();
		p.getLetters().add("b");
		player.getPermissionCards().add(p);
		player.createEmporiums();
		Emporium builtEmporium;
		builtEmporium = player.getEmporiums().get(0);
		action = new BuildEmporium(choice);
		action.setPlayer(player);
		assertFalse(action.actionPerformed());

	}

	@Test
	public void testControlNotBuildIfAlreadyHave() {

		choice.add("1");
		choice.add("1");
		PermissionCard p = new PermissionCard();
		p.getLetters().add("a");
		player.getPermissionCards().add(p);
		player.createEmporiums();
		Emporium builtEmporium;
		builtEmporium = player.getEmporiums().get(0);
		player.getGame().getGameBoard().getCities().get(0)
				.buildEmporium(new Emporium(player.getColor(), player.getPlayerNumber()));
		action = new BuildEmporium(choice);
		action.setPlayer(player);
		assertFalse(action.actionPerformed());

	}

	@Test
	public void testControlBuildIfAlreadyHaveButNotCheckNumber() {

		choice.add("1");
		choice.add("1");
		PermissionCard p = new PermissionCard();
		p.getLetters().add("a");
		player.getPermissionCards().add(p);
		player.createEmporiums();
		Emporium builtEmporium;
		builtEmporium = player.getEmporiums().get(0);
		player.getGame().getGameBoard().getCities().get(0).buildEmporium(new Emporium(player.getColor(), 2));
		action = new BuildEmporium(choice);
		action.setPlayer(player);
		assertTrue(action.actionPerformed());

	}

}