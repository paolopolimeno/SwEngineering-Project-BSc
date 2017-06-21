package controller.action;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg30.model.Balcony;
import it.polimi.ingsw.cg30.model.Counselor;
import it.polimi.ingsw.cg30.model.Emporium;
import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.PoliticCard;
import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.actions.BuildEmporiumKing;

public class BuildEmporiumKingTest {

	Player player;
	Game game;
	Action action;
	StartGame startGame;
	ArrayList<String> choice;

	@Before
	public void setUp() {

		game = new Game();
		startGame = new StartGame(game);
		
		player = new Player(1, "black", game, null);
		game.addPlayer(player);
		startGame.run();
		choice = new ArrayList<>();
		Balcony balcony;
	balcony= game.getGameBoard().getKingBalcony();
	balcony.addCounselor(new Counselor("black"));
	balcony.addCounselor(new Counselor("white"));
	balcony.addCounselor(new Counselor("pink"));
	balcony.addCounselor(new Counselor("red"));
	
	
	player.getPoliticCards().clear();
	player.getPoliticCards().add(new PoliticCard("black"));
	player.getPoliticCards().add(new PoliticCard("white"));
	player.getPoliticCards().add(new PoliticCard("pink"));
	player.getPoliticCards().add(new PoliticCard("red"));
	player.getPoliticCards().add(new PoliticCard("bordeaux"));	
	game.getGameBoard().getCities().get(6).getCityBonuses().clear();
	}
	
	@Test
	public void RightChoiceWorking(){
	
	choice.add("1");
	choice.add("2");
	choice.add("3");
	choice.add("4");
	choice.add("7");
	action= new BuildEmporiumKing(choice);
	action.setPlayer(player);
	assertTrue(action.actionPerformed());		
	}
	@Test
	public void RightChoiceShorterWorking(){
	
	choice.add("1");
	choice.add("2");
	choice.add("3");
	choice.add("END");
	choice.add("7");
	action= new BuildEmporiumKing(choice);
	action.setPlayer(player);
	assertTrue(action.actionPerformed());		
	}
	@Test
	public void NoIllegalCity(){
	
	choice.add("1");
	choice.add("2");
	choice.add("3");
	choice.add("END");
	choice.add("30");
	action= new BuildEmporiumKing(choice);
	action.setPlayer(player);
	assertFalse(action.actionPerformed());		
	}
	@Test
	public void NoDuplicates(){
	
	choice.add("1");
	choice.add("2");
	choice.add("2");
	choice.add("END");
	choice.add("7");
	action= new BuildEmporiumKing(choice);
	action.setPlayer(player);
	assertFalse(action.actionPerformed());		
	}
	@Test
	public void NoCardsNotWorking(){
	
	choice.add("END");
	choice.add("7");
	action= new BuildEmporiumKing(choice);
	action.setPlayer(player);
	assertFalse(action.actionPerformed());		
	}
	
	
	@Test
	public void NotMatchingCardNotWorking(){
	
	choice.add("1");
	choice.add("2");
	choice.add("3");
	choice.add("5");
	choice.add("7");
	action= new BuildEmporiumKing(choice);
	action.setPlayer(player);
	assertFalse(action.actionPerformed());		
	}	
	
	public void NotValidCityNotWorking(){
	
	choice.add("1");
	choice.add("2");
	choice.add("3");
	choice.add("4");
	choice.add("20");
	action= new BuildEmporiumKing(choice);
	action.setPlayer(player);
	assertFalse(action.actionPerformed());		
	}
	@Test
	public void NotValidCardNotWorking(){
	
	choice.add("1");
	choice.add("2");
	choice.add("3");
	choice.add("6");
	choice.add("7");
	action= new BuildEmporiumKing(choice);
	action.setPlayer(player);
	assertFalse(action.actionPerformed());
	}
	
	
	@Test
	public void PlayerBuildEmporiumOnCitySix(){
	
	choice.add("1");
	choice.add("2");
	choice.add("3");
	choice.add("4");
	choice.add("7");
	action= new BuildEmporiumKing(choice);
	action.setPlayer(player);
	action.actionPerformed();
	assertTrue(game.getGameBoard().getCities().get(6).getEmporiumsSpace().get(0).getPlayerNumber()==1);
	
	}
	
	@Test
	public void PlayerPays2(){
	
	choice.add("1");
	choice.add("2");
	choice.add("3");
	choice.add("4");
	choice.add("7");
	action= new BuildEmporiumKing(choice);
	int playerMoney=player.getProsperityDisk().getSlot().getCoordinate();
	action.setPlayer(player);
	action.actionPerformed();
	assertTrue(playerMoney==player.getProsperityDisk().getSlot().getCoordinate()+2);
	}
	
	
	@Test
	public void CanNotPerformIfPlayerHasNotEnoughMoney(){
	
	choice.add("1");
	choice.add("2");
	choice.add("3");
	choice.add("4");
	choice.add("7");
	action= new BuildEmporiumKing(choice);
	player.moveProsperityDisk(-9);
	action.setPlayer(player);
	assertFalse(action.actionPerformed());

	}
	@Test
	public void CanNotPerformIfPlayerHasNotEmporium(){
	
	choice.add("1");
	choice.add("2");
	choice.add("3");
	choice.add("4");
	choice.add("7");
	action= new BuildEmporiumKing(choice);
	player.getEmporiums().clear();
	action.setPlayer(player);
	assertFalse(action.actionPerformed());

	}
	
	@Test
	public void CanNotPerformIfPlayerHasNotAssistant(){
	
	choice.add("1");
	choice.add("2");
	choice.add("3");
	choice.add("4");
	choice.add("7");
	action= new BuildEmporiumKing(choice);
	game.getGameBoard().getCities().get(6).getEmporiumsSpace().add(new Emporium("black",2));
	action.setPlayer(player);
	player.getAssistants().clear();
	assertFalse(action.actionPerformed());

	}
	@Test
	public void CanPerformIfPlayerHasAssistant(){
	
	choice.add("1");
	choice.add("2");
	choice.add("3");
	choice.add("4");
	choice.add("7");
	action= new BuildEmporiumKing(choice);
	game.getGameBoard().getCities().get(6).getEmporiumsSpace().add(new Emporium("black",2));
	action.setPlayer(player);
	assertTrue(action.actionPerformed());

	}	@Test
	public void CanNotPerformIfPlayerHasAlreadyBuilt(){
	
	choice.add("1");
	choice.add("2");
	choice.add("3");
	choice.add("4");
	choice.add("7");
	action= new BuildEmporiumKing(choice);
	game.getGameBoard().getCities().get(6).getEmporiumsSpace().add(new Emporium("black",1));
	action.setPlayer(player);
	assertFalse(action.actionPerformed());

	}
	
	
	@Test
	
	public void checkRightMoneyIsPaid1(){
		
		game.getGameBoard().getKing().setLocationCity(game.getGameBoard().getCities().get(0));
		choice.add("1");
		choice.add("2");
		choice.add("3");
		choice.add("4");
		choice.add("15");
		game.getGameBoard().getCities().get(14).getCityBonuses().clear();

		player.moveProsperityDisk(30);
		int money=player.getProsperityDisk().getSlot().getCoordinate();
		action= new BuildEmporiumKing(choice);
		action.setPlayer(player);
		action.actionPerformed();
		assertTrue(money==player.getProsperityDisk().getSlot().getCoordinate()+12);
	
	}
	
@Test
	
	public void checkRightMoneyIsPaid2(){
		
	game.getGameBoard().getKing().setLocationCity(game.getGameBoard().getCities().get(9));

		choice.add("1");
		choice.add("2");
		choice.add("3");
		choice.add("4");
		choice.add("15");
		game.getGameBoard().getCities().get(14).getCityBonuses().clear();
		player.moveProsperityDisk(30);
		int money=player.getProsperityDisk().getSlot().getCoordinate();
		action= new BuildEmporiumKing(choice);
		action.setPlayer(player);
		action.actionPerformed();
		assertTrue(money==player.getProsperityDisk().getSlot().getCoordinate()+4);
	
	}
	
	
	
@Test

public void checkRightMoneyIsPaid3(){
	
game.getGameBoard().getKing().setLocationCity(game.getGameBoard().getCities().get(0));

	choice.add("1");
	choice.add("2");
	choice.add("3");
	choice.add("4");
	choice.add("2");
	game.getGameBoard().getCities().get(1).getCityBonuses().clear();
	player.moveProsperityDisk(30);
	int money=player.getProsperityDisk().getSlot().getCoordinate();
	action= new BuildEmporiumKing(choice);
	action.setPlayer(player);
	action.actionPerformed();
	assertTrue(money==player.getProsperityDisk().getSlot().getCoordinate()+14);

}




@Test

public void checkRightMoneyIsPaid4(){

game.getGameBoard().getKing().setLocationCity(game.getGameBoard().getCities().get(4));

choice.add("1");
choice.add("2");
choice.add("3");
choice.add("4");
choice.add("10");
game.getGameBoard().getCities().get(9).getCityBonuses().clear();
player.moveProsperityDisk(30);
int money=player.getProsperityDisk().getSlot().getCoordinate();
action= new BuildEmporiumKing(choice);
action.setPlayer(player);
action.actionPerformed();
assertTrue(money==player.getProsperityDisk().getSlot().getCoordinate()+4);

}



	
	
	
	
	
	
	
}
