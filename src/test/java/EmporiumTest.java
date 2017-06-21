import java.awt.Color;

import org.junit.Test;

import it.polimi.ingsw.cg30.model.Emporium;

public class EmporiumTest {

	
	
	
	
	
	
	
	@Test (expected= IllegalArgumentException.class)
	public void testConstructor()
	{
		Emporium e= new Emporium("black",-1);
	}
	
	
	
}
