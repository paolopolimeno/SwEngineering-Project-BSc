import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Game;

public class TestGame {

	Game game;
	
	@Test
	public void testNumberOfEmpIsThree(){
		game= new Game(null,3);
		assertTrue(game.getNumEmporium()==3);
	}
	
	@Test
	
	public void testNumberMoreThanTen(){
		game= new Game(null,11);
		assertTrue(game.getNumEmporium()==10);

	}
	

	@Test
	
	public void testNumberLessThanThree(){
		game= new Game(null,2);
		assertTrue(game.getNumEmporium()==3);

	}
	
		
	
}
