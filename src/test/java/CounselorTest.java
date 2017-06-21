import java.awt.Color;

import org.junit.Test;

import it.polimi.ingsw.cg30.model.Counselor;
import it.polimi.ingsw.cg30.model.Emporium;

public class CounselorTest {

	@Test (expected= NullPointerException.class)
	public void testConstructor()
	{
		Counselor c= new Counselor(null);
	}
	
}
