package model.change;

import static org.junit.Assert.*;


import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Assistant;
import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.GameBoard;
import it.polimi.ingsw.cg30.model.PermissionCard;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.actions.AssistantHiring;
import it.polimi.ingsw.cg30.model.change.GameBoardChange;

public class GameBoardChangeTest {

	Player player1, player2, player3, player4;
	Game game;
	GameBoardChange gC;
	GameBoard gB;
	@Before
	public void setUp(){
		
		game = new Game();
		StartGame startGame = new StartGame(game);
		player1 = new Player(1, "black", game, null);
		player2 = new Player(2, "red", game, null);
		player3 = new Player(3, "blue", game, null);
		player4 = new Player(4, "yellow", game, null);
		

		game.addPlayer(player1);
		game.addPlayer(player2);
		game.addPlayer(player3);
		game.addPlayer(player4);
		gB=game.getGameBoard();

		startGame.run();
		player1.getAssistants().clear();
		player2.getAssistants().clear();
		player2.getAssistants().add(new Assistant());
		player2.getAssistants().add(new Assistant());
		player2.getAssistants().add(new Assistant());
		
		
		player2.getAssistants().add(new Assistant());
		gB.setWinner(player1);
		gC= new GameBoardChange(game.getGameBoard(),game.getPlayers(),null,null);	
			
	}
	
	
	@Test
	
	public void testNumberAssistantPlayer1(){
		
		assertTrue(game.getPlayers().get(0).getAssistants().size()==(int)(gC.getAssistantsNumber().get(1)));
	}
	@Test
	
	public void testNumberAssistantPlayer2(){
		
		assertTrue(game.getPlayers().get(1).getAssistants().size()==(int)(gC.getAssistantsNumber().get(2)));
	}
	
@Test
	
	public void testSetNumberAssistant(){
	gC.setAssistantsNumber(new HashMap<Integer,Integer>());
	}
@Test

public void testPermissionCardsPlayer1(){
	
	assertTrue(game.getPlayers().get(0).getPermissionCards().equals(gC.getPermissionCards().get(1)));
}
@Test

public void testSetPermissionCard(){
gC.setPermissionCards(new HashMap<Integer,List<PermissionCard>>());
}

@Test

public void testGetRegion(){
	assertTrue(game.getGameBoard().getRegions().equals(gC.getRegions()));
}
        
@Test

public void testGetProsperityTrack(){
	assertTrue(gB.getProsperityTrack().equals(gC.getProsperityTrack()));
}



@Test

public void testGetPointsTrack(){
	assertTrue(gB.getPointsTrack().equals(gC.getPointsTrack()));

}

@Test

public void testGetCounselors(){
assertTrue(gB.getCounselors().equals(gC.getCounselors()));
}

@Test

public void testGetBalconies(){

	assertTrue(gB.getBalconies().equals(gC.getBalconies()));
}

@Test

public void testGetKingBalcony(){

	assertTrue(gB.getKingBalcony().equals(gC.getKingBalcony()));
}

@Test

public void testGetCities(){
assertTrue(gB.getCities().equals(gC.getCities()));
}

@Test

public void testGetNobilityTrack(){
assertTrue(gB.getNobilityTrack().equals(gC.getNobilityTrack()));
}

@Test

public void testGetKing(){
assertTrue(gB.getKing().equals(gC.getKing()));
}


@Test

public void testIronBonus(){
assertTrue(gB.getIronBonus().equals(gC.getIronBonus()));
}


@Test

public void testGetGoldBonus(){
assertTrue(gB.getGoldBonus().equals(gC.getGoldBonus()));
}


@Test

public void testGetSilverBonus(){
assertTrue(gB.getSilverBonus().equals(gC.getSilverBonus()));
}


@Test

public void testBronze(){
	
	assertTrue(gB.getBronzeBonus().equals(gC.getBronzeBonus()));
	
}

@Test

public void testKingsBonus(){
	
	assertTrue(gB.getKingBonusCards().equals(gC.getKingBonusCards()));
}

@Test

public void testAllThingsForSale(){
	assertTrue(gB.getAllThingsForSale().equals(gC.getAllThingsForSale()));
}

@Test

public void testWinnerPlayerNumber(){
	assertTrue(gC.getWinnerPlayerNumber()==1);
}

@Test

public void testGetProsperityTrackInfo(){
	assertTrue(gC.getProsperityTrackCoordinate().get(4).equals(13));
	
}

@Test

public void testGetNobilityTrackInfo(){
	assertTrue(gC.getNobilityTrackCoordinate().get(4).equals(0));
	
}
@Test

public void testGetPointsTrackInfo(){
	assertTrue(gC.getPointsTrackCoordinate().get(4).equals(0));
	
}



@Test

public void testEmporiumsInfo(){
	assertTrue(gC.getEmporiumsNumber().get(4).equals(10));
	
}
@Test

public void testPlayersColorInfo(){
	assertTrue(gC.getPlayersColor().get(4).equals("yellow"));
	
}

@Test

public void setWinner(){
	gC.setWinnerPlayerNumber(1);
}
@Test

public void setReceiver(){
	gC.setReceiver(player1);;
}




















}
