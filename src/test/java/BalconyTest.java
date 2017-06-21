import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Balcony;
import it.polimi.ingsw.cg30.model.Counselor;
import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.GameBoard;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.PoliticCard;
import it.polimi.ingsw.cg30.model.Region;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.actions.Action;

public class BalconyTest {

	

	Player player;
	Game game;
	Action action;
	StartGame start;
	ArrayList<String> choice;
	Balcony balconytest;
	GameBoard gb;

	@Before
	public void setUp() {

		game = new Game();
		start = new StartGame(game);
		start.run();
		player = new Player(1, "black", game, null);
		player.init();
		game.addPlayer(player);
	}
	
	@Test
	public void testMatchBalconyWithCards() {
		game = new Game();
		start = new StartGame(game);
		gb = game.getGameBoard();

		Balcony balconytest = gb.getBalconies().get(0);
		
		balconytest.addCounselor(new Counselor("BLUE"));
		balconytest.addCounselor(new Counselor("WHITE"));
		balconytest.addCounselor(new Counselor("BLUE"));
		balconytest.addCounselor(new Counselor("PINK"));
		
		
		ArrayList<PoliticCard> cards = new ArrayList<>();
		
		cards.add(new PoliticCard("BLUE"));
		cards.add(new PoliticCard("JOLLY"));
		cards.add(new PoliticCard("WHITE"));
		
		
		assertTrue(balconytest.matchBalconyWithCards(cards));
		
		cards.clear();
		
		cards.add(new PoliticCard("BLUE"));
		cards.add(new PoliticCard("WHITE"));
		
		assertTrue(balconytest.matchBalconyWithCards(cards));
		
		cards.clear();
		
	}
	
	@Test(expected=NullPointerException.class)
	
	public void BalconyCityNull(){
		
		Balcony balcony= new Balcony(null);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void BalconyCityNullSecondConstructorC(){
		
		Balcony balcony= new Balcony(null,new Region());
		
	}
	
	@Test(expected=NullPointerException.class)
	
	public void initBalconyNull(){
		
		balconytest.initFillBalcony(null);
	}
	
	@Test(expected=NullPointerException.class)

	public void addCounselorNullC(){
	balconytest.addCounselor(null);
	}
	
	
	
	
	
	
	
	
	
	
}
