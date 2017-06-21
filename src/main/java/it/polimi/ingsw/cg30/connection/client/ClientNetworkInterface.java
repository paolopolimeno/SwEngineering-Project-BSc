package it.polimi.ingsw.cg30.connection.client;

import java.util.UUID;

import it.polimi.ingsw.cg30.model.actions.Action;

public interface ClientNetworkInterface {
	/**
	 * connect method for connect to the server
	 * @param username the username to use on the server
	 */
	public void connect(String username);
	/**
	 * disconnect method for disconnect from the server
	 */
	public void disconnect();
	/**
	 * send action method to send the actions to the server
	 * @param action the action to send
	 */
	public void sendAction(Action action);
	/**
	 * create room method to create a room on the server
	 * @param uuid the uuid that the server assigned to the client
	 * @param roomName the room name
	 * @param mapName the name of map to play
	 * @param playerNumber the max number of players
	 * @param numEmporium the number of emporiums to play (min 3 max 10)
	 */
	public void createRoom(UUID uuid, String roomName, String mapName, int playerNumber, int numEmporium);
	/**
	 * the method to join a room
	 * @param uuid the uuid that the server assigned to the client
	 * @param roomName the room name to join
	 */
	public void joinRoom(UUID uuid, String roomName);
	/**
	 * send a chat message to the room in which is the client
	 * @param senderId the UUID of the client
	 * @param message the message to send
	 */
	public void sendChatMessage(UUID senderId, String message);
}