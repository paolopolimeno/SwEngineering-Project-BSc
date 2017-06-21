import org.junit.Test;

import it.polimi.ingsw.cg30.model.PoliticCard;

public class PoliticCardTest {
	
	@Test
	
	public void hashCodeTest(){
		PoliticCard p= new PoliticCard("black");
		p.hashCode();	
	}
	
@Test(expected=NullPointerException.class)
	
	public void nullColor(){
		PoliticCard p= new PoliticCard(null);

	}
}
