import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Balcony;
import it.polimi.ingsw.cg30.model.Counselor;
import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.PoliticCard;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.actions.Action;

public class TestBalcony {

	Player player;
	Game game;
	Action action;
	StartGame startGame;
	ArrayList<String> choice;
	Balcony balcony;
	ArrayList<PoliticCard> cards;

	@Before
	public void setUp() {

		game = new Game();
		startGame = new StartGame(game);
		startGame.run();
		player = new Player(1, "black", game, null);
		player.init();
		game.addPlayer(player);
		choice = new ArrayList<>();
		balcony = game.getGameBoard().getBalconies().get(0);
		balcony.getCounselors().clear();
		cards= new ArrayList<>();
	}

	@Test(expected = IllegalStateException.class)

	public void TestInitFillBalconyExceptionInsertedTooMany() {

		balcony.initFillBalcony(new Counselor("black"));
		balcony.initFillBalcony(new Counselor("black"));
		balcony.initFillBalcony(new Counselor("black"));
		balcony.initFillBalcony(new Counselor("black"));
		balcony.initFillBalcony(new Counselor("black"));

	}

	@Test
	public void TestInitFillBalconyWorking() {

		balcony.initFillBalcony(new Counselor("black"));
		balcony.initFillBalcony(new Counselor("black"));
		balcony.initFillBalcony(new Counselor("red"));
		balcony.initFillBalcony(new Counselor("black"));
		Iterator it = balcony.getCounselors().iterator();
		it.next();
		it.next();
		Counselor c = (Counselor) it.next();
		assertTrue(c.getColor() == "red");

	}

	@Test
	public void TestAddCounselorSizeOfGameBoardArrayStayTheSame() {

		List<Counselor> gbCounselors = game.getGameBoard().getCounselors();
		gbCounselors.clear();
		Counselor counselorToAdd = new Counselor("black");
		gbCounselors.add(counselorToAdd);
		gbCounselors.add(new Counselor("white"));
		int size = gbCounselors.size();

		balcony.initFillBalcony(new Counselor("red"));
		balcony.initFillBalcony(new Counselor("red"));
		balcony.initFillBalcony(new Counselor("red"));
		balcony.initFillBalcony(new Counselor("red"));
		balcony.addCounselor(counselorToAdd);
		assertTrue(gbCounselors.size() == size);

	}

	@Test
	public void TestAddCounselorBalconyConselourAddedIsgbCounselorsZero() {
		List<Counselor> gbCounselors = game.getGameBoard().getCounselors();
		gbCounselors.clear();
		Counselor counselorToAdd = new Counselor("black");
		gbCounselors.add(counselorToAdd);
		gbCounselors.add(new Counselor("white"));

		balcony.initFillBalcony(new Counselor("red"));
		balcony.initFillBalcony(new Counselor("red"));
		balcony.initFillBalcony(new Counselor("red"));
		balcony.initFillBalcony(new Counselor("red"));
		balcony.addCounselor(counselorToAdd);

		Iterator it = balcony.getCounselors().iterator();
		it.next();
		it.next();
		it.next();
		Counselor c = (Counselor) it.next();

		assertEquals(counselorToAdd, c);

	}

	@Test
	public void TestAllOtherCounselorsInBalconyStayTheSame() {

		List<Counselor> gbCounselors = game.getGameBoard().getCounselors();
		gbCounselors.clear();
		Counselor counselorToAdd = new Counselor("black");
		gbCounselors.add(counselorToAdd);
		gbCounselors.add(new Counselor("white"));
		Counselor c1, c2, c3, c4, c5, c6;

		balcony.initFillBalcony(new Counselor("blue"));
		balcony.initFillBalcony(new Counselor("green"));
		balcony.initFillBalcony(new Counselor("gray"));
		balcony.initFillBalcony(new Counselor("red"));
		Iterator it = balcony.getCounselors().iterator();
		it.next();
		c1 = (Counselor) it.next();
		c2 = (Counselor) it.next();
		c3 = (Counselor) it.next();
		balcony.addCounselor(counselorToAdd);

		Iterator it2 = balcony.getCounselors().iterator();
		c4 = (Counselor) it2.next();
		c5 = (Counselor) it2.next();
		c6 = (Counselor) it2.next();

		assertTrue(c1.equals(c4) && c2.equals(c5) && c3.equals(c6));

	}

	@Test

	public void TestCouncilorsPriceOneCard() {

		cards.add(new PoliticCard("black"));
		int price = balcony.councilorsPrice(cards);
		assertTrue(price == 10);

	}
	
	@Test

	public void TestCouncilorsPriceTwoCard() {

		cards.add(new PoliticCard("black"));
		cards.add(new PoliticCard("black"));
		int price = balcony.councilorsPrice(cards);
		assertTrue(price == 7);

	}
	

	@Test

	public void TestCouncilorsPriceThreeCard() {

		cards.add(new PoliticCard("black"));
		cards.add(new PoliticCard("black"));
		cards.add(new PoliticCard("black"));
		int price = balcony.councilorsPrice(cards);
		assertTrue(price == 4);

	}
	@Test

	public void TestCouncilorsPriceFourCard() {

		cards.add(new PoliticCard("black"));
		cards.add(new PoliticCard("black"));
		cards.add(new PoliticCard("black"));
		cards.add(new PoliticCard("black"));
		int price = balcony.councilorsPrice(cards);
		assertTrue(price ==0);

	}
	@Test
	public void TestCouncilorsPriceFourCardOneJolly() {

		cards.add(new PoliticCard("black"));
		cards.add(new PoliticCard("black"));
		cards.add(new PoliticCard("black"));
		cards.add(new PoliticCard("JOLLY"));
		int price = balcony.councilorsPrice(cards);
		assertTrue(price ==1);

	}
	@Test
	public void TestCouncilorsPriceFourCardTwoJolly() {

		cards.add(new PoliticCard("black"));
		cards.add(new PoliticCard("black"));
		cards.add(new PoliticCard("JOLLY"));
		cards.add(new PoliticCard("JOLLY"));
		int price = balcony.councilorsPrice(cards);
		assertTrue(price ==2);

	}
	
	
	@Test
	public void TestMatchBalconyWithCardsSizeBiggerThanFourNotWorking(){

		balcony.initFillBalcony(new Counselor("blue"));
		balcony.initFillBalcony(new Counselor("green"));
		balcony.initFillBalcony(new Counselor("gray"));
		balcony.initFillBalcony(new Counselor("red"));
		

		cards.add(new PoliticCard("blue"));
		cards.add(new PoliticCard("green"));
		cards.add(new PoliticCard("gray"));
		cards.add(new PoliticCard("red"));
		cards.add(new PoliticCard("red"));
		
		assertFalse(balcony.matchBalconyWithCards(cards));
		
	}
	
	@Test
	public void TestMatchBalconyWithFourMatching(){

		balcony.initFillBalcony(new Counselor("blue"));
		balcony.initFillBalcony(new Counselor("green"));
		balcony.initFillBalcony(new Counselor("gray"));
		balcony.initFillBalcony(new Counselor("red"));
		

		cards.add(new PoliticCard("blue"));
		cards.add(new PoliticCard("green"));
		cards.add(new PoliticCard("gray"));
		cards.add(new PoliticCard("red"));
		
		
		assertTrue(balcony.matchBalconyWithCards(cards));
		
	}
	@Test
	public void TestMatchBalconyWithThreeMatching(){

		balcony.initFillBalcony(new Counselor("blue"));
		balcony.initFillBalcony(new Counselor("green"));
		balcony.initFillBalcony(new Counselor("gray"));
		balcony.initFillBalcony(new Counselor("red"));
		

		cards.add(new PoliticCard("blue"));
		cards.add(new PoliticCard("green"));
		cards.add(new PoliticCard("gray"));
	
		
		assertTrue(balcony.matchBalconyWithCards(cards));
		
	}
	
	@Test
	public void TestMatchBalconyWithTreeMatchingOneNot(){

		balcony.initFillBalcony(new Counselor("blue"));
		balcony.initFillBalcony(new Counselor("green"));
		balcony.initFillBalcony(new Counselor("gray"));
		balcony.initFillBalcony(new Counselor("red"));
		

		cards.add(new PoliticCard("blue"));
		cards.add(new PoliticCard("pink"));
		cards.add(new PoliticCard("green"));
		cards.add(new PoliticCard("gray"));
		
		
		assertFalse(balcony.matchBalconyWithCards(cards));
		
	}
	@Test
	public void TestMatchBalconyWithTwoMatchingTwoNot(){

		balcony.initFillBalcony(new Counselor("blue"));
		balcony.initFillBalcony(new Counselor("green"));
		balcony.initFillBalcony(new Counselor("gray"));
		balcony.initFillBalcony(new Counselor("red"));
		

		cards.add(new PoliticCard("blue"));
		cards.add(new PoliticCard("pink"));
		cards.add(new PoliticCard("pink"));
		cards.add(new PoliticCard("gray"));
		
		
		assertFalse(balcony.matchBalconyWithCards(cards));
		
	}
	@Test
	public void TestMatchBalconyWithOneMatchingThreeNot(){

		balcony.initFillBalcony(new Counselor("blue"));
		balcony.initFillBalcony(new Counselor("green"));
		balcony.initFillBalcony(new Counselor("gray"));
		balcony.initFillBalcony(new Counselor("red"));
		

		cards.add(new PoliticCard("blue"));
		cards.add(new PoliticCard("pink"));
		cards.add(new PoliticCard("pink"));
		cards.add(new PoliticCard("pink"));
		
		
		assertFalse(balcony.matchBalconyWithCards(cards));
		
	}
	
	@Test
	public void TestMatchBalconyWithOneJollyThreeMatching(){

		balcony.initFillBalcony(new Counselor("blue"));
		balcony.initFillBalcony(new Counselor("green"));
		balcony.initFillBalcony(new Counselor("gray"));
		balcony.initFillBalcony(new Counselor("red"));
		

		cards.add(new PoliticCard("blue"));
		cards.add(new PoliticCard("green"));
		cards.add(new PoliticCard("gray"));
		cards.add(new PoliticCard("JOLLY"));
		
		
		assertTrue(balcony.matchBalconyWithCards(cards));
		
	}
	

	@Test
	public void TestMatchBalconyWithTwoJollyTwoMatching(){

		balcony.initFillBalcony(new Counselor("blue"));
		balcony.initFillBalcony(new Counselor("green"));
		balcony.initFillBalcony(new Counselor("gray"));
		balcony.initFillBalcony(new Counselor("red"));
		

		cards.add(new PoliticCard("JOLLY"));
		cards.add(new PoliticCard("JOLLY"));
		cards.add(new PoliticCard("blue"));
		cards.add(new PoliticCard("green"));

		
		assertTrue(balcony.matchBalconyWithCards(cards));
		
	}
	
	@Test
	public void TestMatchBalconyWithThreeJollyOneMatching(){

		balcony.initFillBalcony(new Counselor("blue"));
		balcony.initFillBalcony(new Counselor("green"));
		balcony.initFillBalcony(new Counselor("gray"));
		balcony.initFillBalcony(new Counselor("red"));
		

		cards.add(new PoliticCard("JOLLY"));
		cards.add(new PoliticCard("JOLLY"));
		cards.add(new PoliticCard("JOLLY"));
		cards.add(new PoliticCard("green"));

		
		assertTrue(balcony.matchBalconyWithCards(cards));
		
	}
	
	@Test
	public void TestMatchBalconyWithAllJolly(){

		balcony.initFillBalcony(new Counselor("blue"));
		balcony.initFillBalcony(new Counselor("green"));
		balcony.initFillBalcony(new Counselor("gray"));
		balcony.initFillBalcony(new Counselor("red"));
		

		cards.add(new PoliticCard("JOLLY"));
		cards.add(new PoliticCard("JOLLY"));
		cards.add(new PoliticCard("JOLLY"));
		cards.add(new PoliticCard("JOLLY"));

		
		assertTrue(balcony.matchBalconyWithCards(cards));
		
	}
	@Test
	public void TestMatchBalconyWithThreeJollyOneNotMatching(){

		balcony.initFillBalcony(new Counselor("blue"));
		balcony.initFillBalcony(new Counselor("green"));
		balcony.initFillBalcony(new Counselor("gray"));
		balcony.initFillBalcony(new Counselor("red"));
		

		cards.add(new PoliticCard("JOLLY"));
		cards.add(new PoliticCard("white"));
		cards.add(new PoliticCard("JOLLY"));
		cards.add(new PoliticCard("JOLLY"));

		
		assertFalse(balcony.matchBalconyWithCards(cards));
		
	}
	@Test
	public void TestMatchBalconyWithTwoJollyOneNotMatchingOneMatching(){

		balcony.initFillBalcony(new Counselor("blue"));
		balcony.initFillBalcony(new Counselor("green"));
		balcony.initFillBalcony(new Counselor("gray"));
		balcony.initFillBalcony(new Counselor("red"));
		

		cards.add(new PoliticCard("JOLLY"));
		cards.add(new PoliticCard("white"));
		cards.add(new PoliticCard("gray"));
		cards.add(new PoliticCard("JOLLY"));

		
		assertFalse(balcony.matchBalconyWithCards(cards));
		
	}
	
	
	
	
	
	

}
