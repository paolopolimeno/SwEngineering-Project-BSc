package it.polimi.ingsw.cg30.connection.client;

import java.util.List;
import java.util.UUID;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.actions.market.Thing;
import it.polimi.ingsw.cg30.model.change.GameBoardChange;
import it.polimi.ingsw.cg30.model.change.PlayerChange;

public interface Graphic{
	/**
	 * this is the graphic interface of the game
	 * most of this methods are used in  both of the graphics
	 * cli and gui
	 */
	
	/**
	 * this method select a permission card and return the 
	 * card selected as string
	 * @return
	 */
	public String selectPermissionCard();
	/**
	 * this method is the method called to select a city
	 * @return
	 */
	public String selectCity();
	/**
	 * this method select all the politic cards and return
	 * all the cards in a list
	 * @return
	 */
	public List<String> selectPoliticCards();
	/**
	 * this method is called to select one of the balconies
	 * of the game and return the selection as a string
	 * @return
	 */
	public String selectBalcony();
	/**
	 * this method is called to select one of the counselor
	 * of the game and return the selection as a string
	 * @return
	 */
	public String selectCounselor();
	/**
	 * this method is called to select one of the balconies
	 * of the game and return the selection as a string
	 * @return
	 */
	public String selectBalconyFromAll();
	/**
	 * this method is called to select one of the regions
	 * of the game and return the selection as a string
	 * @return
	 */
	public String selectRegion();

	public List<String> insertThings();

	public String insertThing();
	/**
	 * this method is called to print the gameboard of the 
	 * game. it receive a gameboardchange and print all the
	 * information cointained
	 * @param gameBoard
	 */
	public void printGameBoard(GameBoardChange gameBoard);
	/**
	 * this method is called to print the player passed.
	 * it receive a player change and print all the
	 * information cointained
	 * @param player
	 */
	public void printPlayer(PlayerChange player);
	/**
	 * this method print a list of all the possible actions 
	 * that the player have
	 * @param player
	 */
	public void printPossibleAction(PlayerChange player);
	/**
	 * this method is called to select which items from the
	 * market the player want to buy
	 * @param things
	 * @param action
	 */
	public void buyItems(List<Thing> things, Action action);
	/**
	 * this method is called to select all the items that the player 
	 * want to sell
	 * @param action
	 */
	public void sellItems(Action action);
	/**
	 * this method is called to select a room to connect on the server
	 * or to create a new room
	 * @param uuid
	 * @param connection
	 */
	public void roomSelection(UUID uuid, ClientNetworkInterface connection);
	/**
	 * this method is called to print a message received
	 * @param username the sender
	 * @param message the chat message
	 */
	public void printChatMessage(String username, String message);
	/**
	 * this method is the method that receives commands and create the actions
	 */
	public void commandListener();
	/**
	 * this method update the rooms list printed
	 */
	public void updateRooms();
}