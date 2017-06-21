package it.polimi.ingsw.cg30.connection.client;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import it.polimi.ingsw.cg30.model.change.GameBoardChange;
import it.polimi.ingsw.cg30.model.change.PlayerChange;
import it.polimi.ingsw.cg30.model.util.Util;
import it.polimi.ingsw.cg30.view.cli.CLIGraphic;
import it.polimi.ingsw.cg30.view.gui.GUIGraphic;
import javafx.application.Application;

public class Client implements Runnable {
	/**
	 * the username of the player that launch the client
	 */
	private String username;
	/**
	 * the connection interface selected (RMI or Socket)
	 */
	private ClientNetworkInterface connection;
	/**
	 * the graphic selected
	 */
	private Graphic graphic;
	/**
	 * the gameboard
	 */
	private GameBoardChange gameBoard;
	/**
	 * the player
	 */
	private PlayerChange player;
	/**
	 * list of rooms opened on the server
	 */
	private List<String> roomsList;
	/**
	 * list of game maps
	 */
	private List<String> mapsList;
	/**
	 * the instance of this client
	 */
	private static Client instance;
	/**
	 * the scanner used for the selections in this class
	 */
	private Scanner sc = new Scanner(System.in);
	/**
	 * the string used to save the selection from the scanner
	 */
	private String selection;
	/**
	 * maps received state
	 */
	private boolean mapsReceived = false;
	/**
	 * UUID of the client
	 */
	private UUID uuid;
	/**
	 * this constructor create a new instance of the client
	 */
	private Client() {
		instance = this;
		roomsList = new ArrayList<>();
		mapsList = new ArrayList<>();
	}
	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new Client(), "CLIENT" + Calendar.getInstance().get(Calendar.MILLISECOND)).start();
	}
	/**
	 * the client run method that select the graphic of the client
	 */
	@Override
	public void run() {
		graphic = selectGraphic();

		if (graphic instanceof CLIGraphic) {
			username = selectUsername();
			connection = selectNetwork();
			connection.connect(username);
		}
		while (!mapsReceived) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				Util.exception(e);
			}
		}
		if (graphic instanceof CLIGraphic) {
			graphic.roomSelection(uuid, connection);
			graphic.commandListener();
		}
	}

	/**
	 * this method make a choice for the graphic interface that the user want to
	 * use.
	 * 
	 * @return a CLIGraphic if the user select one, a GUIGraphic if the user
	 *         select two
	 */
	private Graphic selectGraphic() {
		selection = "";
		while (true) {
			Util.println("His, please select a graphic: 1 for CLI, 2 for GUI");
			selection = sc.nextLine();

			if (Util.CHOICE_ONE.equals(selection)) {
				return new CLIGraphic();
			} else if (Util.CHOICE_TWO.equals(selection)) {

				new Thread(new Runnable() {

					@Override
					public void run() {
						Application.launch(GUIGraphic.class);
					}
				}).start();

				return null;
			}
		}
	}

	/**
	 * this method make a choice for the network interface of the client based
	 * on the user preference
	 * 
	 * @return a socket connection if the user select 1, an RMI connection if
	 *         the user select 2
	 */
	private ClientNetworkInterface selectNetwork() {
		selection = "";
		while (true) {
			Util.println("Hi " + username + ", please select a connection: 1 for Socket, 2 for RMI");
			selection = sc.nextLine();

			if (Util.CHOICE_ONE.equals(selection)) {
				return ClientNetworkInterfaceFactory.getSocketConnection();
			} else if (Util.CHOICE_TWO.equals(selection)) {
				return ClientNetworkInterfaceFactory.getRMIConnection();
			}
		}
	}

	/**
	 * this method send a message to the connection interface of the client
	 * 
	 * @param message
	 *            the message to send
	 */
	public void sendMessage(String message) {
		connection.sendChatMessage(uuid, message);
	}

	/**
	 * this method make the user able to choice his username
	 * 
	 * @return the username that the user wrote
	 */
	private String selectUsername() {
		selection = "";
		while (selection.isEmpty()) {
			Util.println("Select your username:");
			selection = sc.nextLine();
		}
		return selection;
	}

	/**
	 * @return the instance of the Client
	 */
	public static Client getInstance() {
		return instance;
	}
	/**
	 * @return the graphic of the client
	 */
	public Graphic getGraphic() {
		return graphic;
	}
	/**
	 * @return the gameboard model of the client
	 */
	public GameBoardChange getGameBoard() {
		return gameBoard;
	}
	/**
	 * this method set the gameboard model of the client
	 * 
	 * @param gameBoard
	 *            the gameboard to set
	 */
	public void setGameBoard(GameBoardChange gameBoard) {
		this.gameBoard = gameBoard;
	}
	/**
	 * set the playerchange of the game 
	 * @param player
	 */
	public void setPlayer(PlayerChange player){
		this.player=player;
	}
	/**
	 * @return the playerchange
	 */
	public PlayerChange getPlayer(){
		return player;
	}
	/**
	 * @return the network interface
	 */
	public ClientNetworkInterface getConnection() {
		return connection;
	}
	/**
	 * set the rooms list and call updateRooms method
	 * that update the rooms once received
	 * @param rooms
	 */
	public void setRoomsList(List<String> rooms) {
		this.roomsList = rooms;
		graphic.updateRooms();
	}
	/**
	 * set the maps list received from the server
	 * @param maps
	 */
	public void setMapsList(List<String> maps) {
		this.mapsList = maps;
		mapsReceived = true;
	}
	/**
	 * @return the rooms list 
	 */
	public List<String> getRoomsList() {
		return roomsList;
	}
	/**
	 * set the UUID
	 * @param uuid
	 */
	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}
	/**
	 * @return the username of the client
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @return the maps list
	 */
	public List<String> getMapsList() {
		return mapsList;
	}
	/**
	 * @return the UUID
	 */
	public UUID getUUID() {
		return uuid;
	}
	/**
	 * @param graphic set the graphic
	 */
	public void setGraphic(Graphic graphic) {
		this.graphic = graphic;
	}

	/**
	 * the method connects the client if it has chosen gui as graphic
	 * @param text
	 * @param selection
	 */
	public void connectGUI(String text, String selection) {
		if (Util.CHOICE_ONE.equals(selection)) {
			this.connection = ClientNetworkInterfaceFactory.getSocketConnection();
		} else if (Util.CHOICE_TWO.equals(selection)) {
			this.connection = ClientNetworkInterfaceFactory.getRMIConnection();
		}
		this.username = text;
		this.connection.connect(username);
		while (!mapsReceived) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				Util.exception(e);
			}
		}
	}
}