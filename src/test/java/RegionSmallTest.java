import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Balcony;
import it.polimi.ingsw.cg30.model.Counselor;
import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.PoliticCard;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.actions.Action;

public class RegionSmallTest {

	Player player;
	Game game;
	Action action;
	StartGame startGame;
	ArrayList<String> choice;
	Balcony balcony;
	
	@Before
	public void setUp() {

		game = new Game();
		startGame = new StartGame(game);
		startGame.run();
		player = new Player(1, "black", game,null);
		player.init();
		game.addPlayer(player);
		choice = new ArrayList<>();
		
		
		balcony= game.getGameBoard().getBalconies().get(2);
		balcony.addCounselor(new Counselor("black"));
		balcony.addCounselor(new Counselor("white"));
		balcony.addCounselor(new Counselor("pink"));
		balcony.addCounselor(new Counselor("red"));
	
		
		player.getPoliticCards().clear();
		player.getPoliticCards().add(new PoliticCard("black"));
		player.getPoliticCards().add(new PoliticCard("white"));
		player.getPoliticCards().add(new PoliticCard("pink"));
		player.getPoliticCards().add(new PoliticCard("red"));
		
		
		
		
		
	}
	
	@Test
	
	public void tryMethodRegion(){
		
		player.takePermissionCard(balcony.getRegion().getShowedPermissionCards().get(0));
		player.takePermissionCard(balcony.getRegion().getShowedPermissionCards().get(0));
		player.takePermissionCard(balcony.getRegion().getShowedPermissionCards().get(0));
		player.takePermissionCard(balcony.getRegion().getShowedPermissionCards().get(1));
		player.takePermissionCard(balcony.getRegion().getShowedPermissionCards().get(1));
		player.takePermissionCard(balcony.getRegion().getShowedPermissionCards().get(1));
	}
	@Test
	public void getCityList(){
		game.getGameBoard().getRegions().get(0).getCityList();
	}
	
	
	

	
	
	 
}
