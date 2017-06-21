package it.polimi.ingsw.cg30.connection.server;

import java.util.List;
import java.util.UUID;

public interface MessageInterface {
	/**
	 * method used to send a message to a client
	 * @param receiverId the id of the client that has to receive this message
	 * @param sender the sender name
	 * @param message the chat message
	 */
	public void printChatMessage(UUID receiverId, String sender, String message);
	/**
	 * method usd from the server to send a list of all the rooms available 
	 * @param receiverId the id of the client that has to receive this message
	 * @param rooms the attaylist of rooms
	 */
	public void sendRoomsList(UUID receiverId, List<String> rooms);
}
