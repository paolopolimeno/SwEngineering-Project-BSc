import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Balcony;
import it.polimi.ingsw.cg30.model.Emporium;
import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.bonus.Bonus;
import it.polimi.ingsw.cg30.model.bonus.PointsBonus;

public class GameBoardTest {

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
		player = new Player(1, "black", game, null);
		player.init();
	}
	
	@Test
	
	public void testSetKingBonusCards(){
		
		ArrayList<PointsBonus>  bon = new ArrayList<>();
		game.getGameBoard().setKingBonusCards(bon);
		assertTrue(game.getGameBoard().getKingBonusCards()==bon);
	}
	
	@Test 
	
	public void getMetalBonuses(){
		
		game.getGameBoard().getBronzeBonus();
		game.getGameBoard().getSilverBonus();
		game.getGameBoard().getGoldBonus();
		game.getGameBoard().getIronBonus();
		
	}
	
	@Test
	
	public void testGiveKingBonusCard(){
		PointsBonus b=new PointsBonus(2);
		
		game.getGameBoard().getKingBonusCards().clear();
		game.getGameBoard().getKingBonusCards().add(b);

	}

	
	@Test
	
	public void testCitiesBuildBonusPlayerGetBonusIfAllGold(){
		
		game.getGameBoard().getCities().get(0).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(5).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(7).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(14).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(10).getEmporiumsSpace().add(new Emporium("black",1));

		PointsBonus p= game.getGameBoard().getGoldBonus();
		game.getGameBoard().citiesBuildBonus(player);
		
		assertTrue(player.getBonusCards().get(0)==p && game.getGameBoard().getGoldBonus()==null);
	}
	

	@Test
	
	public void testCitiesBuildBonusGetGoldAndKingBonus(){
		
		game.getGameBoard().getCities().get(0).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(5).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(7).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(14).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(10).getEmporiumsSpace().add(new Emporium("black",1));

		PointsBonus p= game.getGameBoard().getKingBonusCards().get(0);
		int kingSize=game.getGameBoard().getKingBonusCards().size();
		game.getGameBoard().citiesBuildBonus(player);
		
		assertTrue(player.getBonusCards().get(1)==p && game.getGameBoard().getKingBonusCards().size()==kingSize-1);
	}
	
	@Test
	
	public void testCitiesBuildBonusPlayerNotGetBonusIfNotAllGold(){
		
		game.getGameBoard().getCities().get(0).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(5).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(7).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(14).getEmporiumsSpace().add(new Emporium("black",1));
		

		
		game.getGameBoard().citiesBuildBonus(player);
		
		assertTrue(player.getBonusCards().size()==0 && game.getGameBoard().getGoldBonus()!=null);
	}
	
@Test
	
	public void testCitiesBuildBonusPlayerGetBonusIfAllBronze(){
		
	game.getGameBoard().getCities().get(1).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(8).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(13).getEmporiumsSpace().add(new Emporium("black",1));
		PointsBonus p= game.getGameBoard().getBronzeBonus();
		game.getGameBoard().citiesBuildBonus(player);
		
		assertTrue(player.getBonusCards().get(0)==p && game.getGameBoard().getBronzeBonus()==null);
	}
	
	
@Test
	
	public void testCitiesBuildBonusGetBronzeAndKingBonus(){
		
		game.getGameBoard().getCities().get(1).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(8).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(13).getEmporiumsSpace().add(new Emporium("black",1));
		
		PointsBonus p= game.getGameBoard().getKingBonusCards().get(0);
		int kingSize=game.getGameBoard().getKingBonusCards().size();
		game.getGameBoard().citiesBuildBonus(player);
		
		assertTrue(player.getBonusCards().get(1)==p && game.getGameBoard().getKingBonusCards().size()==kingSize-1);
	}
	
	@Test
	
	public void testCitiesBuildBonusPlayerNotGetBonusIfNotAllBronze(){
		
		game.getGameBoard().getCities().get(1).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(8).getEmporiumsSpace().add(new Emporium("black",1));
		
		

		game.getGameBoard().citiesBuildBonus(player);
		
		assertTrue(player.getBonusCards().size()==0 && game.getGameBoard().getBronzeBonus()!=null);
;
	}
	


	
@Test
	
	public void testCitiesBuildBonusPlayerGetBonusIfAllSilver(){
	game.getGameBoard().getCities().get(2).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(4).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(6).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(11).getEmporiumsSpace().add(new Emporium("black",1));



		PointsBonus p= game.getGameBoard().getSilverBonus();
		game.getGameBoard().citiesBuildBonus(player);
		
		assertTrue(player.getBonusCards().get(0)==p && game.getGameBoard().getSilverBonus()==null);
	}
	
	
@Test
	
	public void testCitiesBuildBonusGetSilverAndKingBonus(){
	game.getGameBoard().getCities().get(0).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(5).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(7).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(14).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(10).getEmporiumsSpace().add(new Emporium("black",1));

		PointsBonus p= game.getGameBoard().getKingBonusCards().get(0);
		int kingSize=game.getGameBoard().getKingBonusCards().size();
		game.getGameBoard().citiesBuildBonus(player);
		
		assertTrue(player.getBonusCards().get(1)==p && game.getGameBoard().getKingBonusCards().size()==kingSize-1);
	}
	
	@Test
	
	public void testCitiesBuildBonusPlayerNotGetBonusIfNotAllSilver(){
		game.getGameBoard().getCities().get(0).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(5).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(7).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(14).getEmporiumsSpace().add(new Emporium("black",1));
		

		
		

		game.getGameBoard().citiesBuildBonus(player);
		
		assertTrue(player.getBonusCards().size()==0 && game.getGameBoard().getSilverBonus()!=null);
	}
	

	
	
	
	
@Test
	
	public void testCitiesBuildBonusPlayerGetBonusIfAllIron(){
	game.getGameBoard().getCities().get(3).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(12).getEmporiumsSpace().add(new Emporium("black",1));
	
		PointsBonus p= game.getGameBoard().getIronBonus();
		game.getGameBoard().citiesBuildBonus(player);
		
		assertTrue(player.getBonusCards().get(0)==p && game.getGameBoard().getIronBonus()==null);
	}
	
	
@Test
	
	public void testCitiesBuildBonusGetIronAndKingBonus(){
	game.getGameBoard().getCities().get(3).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(12).getEmporiumsSpace().add(new Emporium("black",1));
	
		PointsBonus p= game.getGameBoard().getKingBonusCards().get(0);
		int kingSize=game.getGameBoard().getKingBonusCards().size();
		game.getGameBoard().citiesBuildBonus(player);
		
		assertTrue(player.getBonusCards().get(1)==p && game.getGameBoard().getKingBonusCards().size()==kingSize-1);
	}
	
	@Test
	
	public void testCitiesBuildBonusPlayerNotGetBonusIfNotAllIron(){
		game.getGameBoard().getCities().get(3).getEmporiumsSpace().add(new Emporium("black",1));


		
		

		game.getGameBoard().citiesBuildBonus(player);
		
		assertTrue(player.getBonusCards().size()==0&& game.getGameBoard().getIronBonus()!=null);
	}
	

	
	
@Test
	
	public void testCitiesBuildBonusPlayerGetBonusIfAllSea(){
	game.getGameBoard().getCities().get(0).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(1).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(2).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(3).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(4).getEmporiumsSpace().add(new Emporium("black",1));
	
		PointsBonus p= game.getGameBoard().getSea().getRegionBonus();
		game.getGameBoard().citiesBuildBonus(player);
		
		assertTrue(player.getBonusCards().get(0)==p && game.getGameBoard().getSea().getRegionBonus()==null);
	}
	
	
@Test
	
	public void testCitiesBuildBonusGetSeaAndKingBonus(){
	game.getGameBoard().getCities().get(0).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(1).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(2).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(3).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(4).getEmporiumsSpace().add(new Emporium("black",1));
		PointsBonus p= game.getGameBoard().getKingBonusCards().get(0);
		int kingSize=game.getGameBoard().getKingBonusCards().size();
		game.getGameBoard().citiesBuildBonus(player);
		
		assertTrue(player.getBonusCards().get(1)==p && game.getGameBoard().getKingBonusCards().size()==kingSize-1);
	}
	
	@Test
	
	public void testCitiesBuildBonusPlayerNotGetBonusIfNotAllSea(){
		game.getGameBoard().getCities().get(0).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(1).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(2).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(3).getEmporiumsSpace().add(new Emporium("black",1));

		
		

		game.getGameBoard().citiesBuildBonus(player);
		
		assertTrue(player.getBonusCards().size()==0&& game.getGameBoard().getSea().getRegionBonus()!=null);
	}
	
	
	
@Test
	
	public void testCitiesBuildBonusPlayerGetBonusIfAllHill(){
	game.getGameBoard().getCities().get(5).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(6).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(7).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(8).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(9).getEmporiumsSpace().add(new Emporium("black",1));
	
		PointsBonus p= game.getGameBoard().getHill().getRegionBonus();
		game.getGameBoard().citiesBuildBonus(player);
		
		assertTrue(player.getBonusCards().get(0)==p && game.getGameBoard().getHill().getRegionBonus()==null);
	}
	
	
@Test
	
	public void testCitiesBuildBonusGetHillAndKingBonus(){
	game.getGameBoard().getCities().get(5).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(6).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(7).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(8).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(9).getEmporiumsSpace().add(new Emporium("black",1));
		PointsBonus p= game.getGameBoard().getKingBonusCards().get(0);
		int kingSize=game.getGameBoard().getKingBonusCards().size();
		game.getGameBoard().citiesBuildBonus(player);
		
		assertTrue(player.getBonusCards().get(1)==p && game.getGameBoard().getKingBonusCards().size()==kingSize-1);
	}
	
	@Test
	
	public void testCitiesBuildBonusPlayerNotGetBonusIfNotAllHill(){
		game.getGameBoard().getCities().get(5).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(6).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(7).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(8).getEmporiumsSpace().add(new Emporium("black",1));

		
		

		game.getGameBoard().citiesBuildBonus(player);
		
		assertTrue(player.getBonusCards().size()==0&& game.getGameBoard().getHill().getRegionBonus()!=null);
	}
	
	
	
	
@Test
	
	public void testCitiesBuildBonusPlayerGetBonusIfAllMountain(){
	game.getGameBoard().getCities().get(10).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(11).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(12).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(13).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(14).getEmporiumsSpace().add(new Emporium("black",1));
	
		PointsBonus p= game.getGameBoard().getMountain().getRegionBonus();
		game.getGameBoard().citiesBuildBonus(player);
		
		assertTrue(player.getBonusCards().get(0)==p && game.getGameBoard().getMountain().getRegionBonus()==null);
	}
	
	
@Test
	
	public void testCitiesBuildBonusGetMountainAndKingBonus(){
	game.getGameBoard().getCities().get(10).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(11).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(12).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(13).getEmporiumsSpace().add(new Emporium("black",1));
	game.getGameBoard().getCities().get(14).getEmporiumsSpace().add(new Emporium("black",1));
		PointsBonus p= game.getGameBoard().getKingBonusCards().get(0);
		int kingSize=game.getGameBoard().getKingBonusCards().size();
		game.getGameBoard().citiesBuildBonus(player);
		
		assertTrue(player.getBonusCards().get(1)==p && game.getGameBoard().getKingBonusCards().size()==kingSize-1);
	}
	
	@Test
	
	public void testCitiesBuildBonusPlayerNotGetBonusIfNotAllMountain(){
		game.getGameBoard().getCities().get(10).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(11).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(12).getEmporiumsSpace().add(new Emporium("black",1));
		game.getGameBoard().getCities().get(13).getEmporiumsSpace().add(new Emporium("black",1));
		
		

		game.getGameBoard().citiesBuildBonus(player);
		
		assertTrue(player.getBonusCards().size()==0 && game.getGameBoard().getMountain().getRegionBonus()!=null);
	}
	
	
	
	@Test
	
	public void getMountainTest(){
		
		game.getGameBoard().getMountain();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
