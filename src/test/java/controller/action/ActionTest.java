package controller.action;

import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.actions.AnotherMainAction;

public class ActionTest {

	@Test
	public void testIsLegitChoiceWithChoiceAsNumber() {
		Action action = new AnotherMainAction();
		String choice = "1";
		int length = 8;
		
		assertEquals(true, action.isLegitChoice(choice, length));
	}
	
	@Test
	public void testIsLegitChoiceWithChoiceAsText() {
		Action action = new AnotherMainAction();
		String choice = "text";
		int length = 8;
		
		assertEquals(false, action.isLegitChoice(choice, length));
	}
	
	@Test
	public void testIsLegitChoiceWithLengthZero() {
		Action action = new AnotherMainAction();
		String choice = "text";
		int length = 0;
		
		assertEquals(false, action.isLegitChoice(choice, length));
	}
	
	@Test
	public void testIsLegitChoiceWithChoiceNull(){
		Action action = new AnotherMainAction();
		String choice = null;
		int length = 0;
		
		assertEquals(false, action.isLegitChoice(choice, length));
	}
	
	@Test
	public void testGetActionName(){
		Action action = new AnotherMainAction();
		
		assertEquals("AnotherMainAction", action.getActionName());
	}
	
	@Test
	public void testGetAndSetPlayer(){
		Action action = new AnotherMainAction();
		Game game = new Game();
		Player player = new Player(1, "black", game, null);
		action.setPlayer(player);
		
		assertEquals(player, action.getPlayer());
	}
	
	@Test(expected = NullPointerException.class)
	public void testSetPlayerNull(){
		Action action = new AnotherMainAction();
		action.setPlayer(null);
	}
}