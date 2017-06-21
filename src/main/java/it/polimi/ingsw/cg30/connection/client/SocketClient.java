package it.polimi.ingsw.cg30.connection.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;

import it.polimi.ingsw.cg30.controller.common.Connection;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.change.GameBoardChange;
import it.polimi.ingsw.cg30.model.change.PlayerChange;
import it.polimi.ingsw.cg30.model.util.Util;

public class SocketClient implements ClientNetworkInterface {
	/**
	 * the socket
	 */
	private Socket socket;
	/**
	 * the object output stream
	 */
	private ObjectOutputStream oos;
	/**
	 * the object input stream
	 */
	private ObjectInputStream ois;
	/**
	 * see {@link ClientNetworkInterface}
	 */
	private boolean listen;
	@Override
	public void connect(String username) {

		try {
			socket = new Socket(Connection.SERVER_ADDRESS, Connection.SERVER_SOCKET_PORT);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			send(new String("USERNAME" + username));
		} catch (IOException e) {
			Util.exception(e);
		}
		new Thread(new ThreadListener()).start();
	}
	
	private class ThreadListener implements Runnable {
		@Override
		public void run() {
			listen = true;
			SocketClient.this.listen();
		}
	}
	/**
	 * see {@link ClientNetworkInterface}
	 */
	@Override
	public void disconnect() {
		try {
			send("DISCONNECT");
			listen = false;
			ois.close();
			oos.close();
			socket.close();
		} catch (IOException e) {
			Util.exception(e);
		}
	}
	/**
	 * this method listen to objects that arrive from socket connections
	 * and cast them into an useful information for the client
	 */
	public void listen() {
		while (listen) {
			try {
				Object objectIn = ois.readObject();
				
				if (objectIn instanceof UUID) {
					Client.getInstance().setUUID((UUID) objectIn);
				}
				else if (objectIn instanceof ArrayList) {
					
					if (("ROOMS_LIST").equals(((ArrayList) objectIn).get(0))) {
						ArrayList<String> rooms = new ArrayList<>((ArrayList) objectIn);
						rooms.remove(0);
						Client.getInstance().setRoomsList(rooms);
					}
					
					else if (("MAPS_LIST").equals(((ArrayList) objectIn).get(0))) {
						ArrayList<String> maps = new ArrayList<>((ArrayList) objectIn);
						maps.remove(0);
						Client.getInstance().setMapsList(maps);
					}
				}
				
				else if (objectIn instanceof GameBoardChange) {
					Client.getInstance().setGameBoard((GameBoardChange) objectIn);
					Client.getInstance().getGraphic().printGameBoard(Client.getInstance().getGameBoard());
				}

				else if (objectIn instanceof PlayerChange) {
					Client.getInstance().setPlayer((PlayerChange) objectIn);
					Client.getInstance().getGraphic().printPlayer(Client.getInstance().getPlayer());
				}
				else if (((String) objectIn).startsWith("CHAT")) {
					String string = objectIn.toString().replaceFirst("CHAT", "");
					String[] stringArray = string.split(":");
					if (stringArray.length==2){
						Client.getInstance().getGraphic().printChatMessage(stringArray[0], stringArray[1]);
					}
				}

			} catch (ClassNotFoundException | IOException e) {
				Util.exception(e);
			}
		}
	}
	/**
	 * see {@link ClientNetworkInterface}
	 */
	@Override
	public void sendAction(Action action) {
		send(action);
	}
	/**
	 * this method send a command to the socket server to create a room
	 * @param UUID the id of the client that the server needs for
	 * 		  know which user would to create a room
	 * @param roomName the name of the room
	 * @param playerNumber the max numbers of player of the room
	 * @param numEmporium the number of emporiums to play
	 */
	@Override
	public void createRoom(UUID uuid, String roomName, String mapName, int playerNumber, int numEmporium) {
		send(new String("CREATE_ROOM"+ roomName + ":" + mapName + ":" + playerNumber + ":" + numEmporium));
	}
	/**
	 * this method send a command to the socket server to join a room
	 * @param UUID the id of the client that the server needs to know
	 * 		  which user wants to join the room
	 * @param roomName the name of the room that the client wants to join
	 */
	@Override
	public void joinRoom(UUID uuid, String roomName) {
		send(new String("JOIN_ROOM" + roomName));
	}
	/**
	 * this method send a command to the socket server to send a chat message
	 * to the room in which the client is in.
	 * @param UUID the id of the client that write the message
	 * @param message the message to sent
	 */
	@Override
	public void sendChatMessage(UUID uuid, String message) {
		send(new String("CHAT" + message));
	}
	/**
	 * this method send an object to the server socket view
	 * @param object the object to send
	 */
	private void send(Object object) {
		try {
			oos.writeObject(object);
			oos.flush();
		} catch (IOException e) {
			Util.exception(e);
		}
	}
}