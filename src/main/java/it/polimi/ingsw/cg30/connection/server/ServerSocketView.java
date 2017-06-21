package it.polimi.ingsw.cg30.connection.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.change.Change;
import it.polimi.ingsw.cg30.model.util.Util;

public class ServerSocketView extends View implements Runnable {
	/**
	 * the object input stream of the socket
	 */
	private ObjectInputStream socketIn;
	/**
	 * the object output stream of the socket
	 */
	private ObjectOutputStream socketOut;
	/**
	 * the user connected on this view
	 */
	private User user;
	/**
	 * the boolean state listening of the view
	 */
	private boolean listen = true;
	/**
	 * this constructor create a new server socket view
	 * @param socket
	 * @throws IOException
	 */
	public ServerSocketView(Socket socket) throws IOException {
		this.socketOut = new ObjectOutputStream(socket.getOutputStream());
		this.socketIn = new ObjectInputStream(socket.getInputStream());
	}
	/**
	 * this method is called to send to the client a change of the game
	 */
	@Override
	public void update(Change change) {
		if (!(change.getReceiver() == null)) {
			if (change.getReceiver().getUUID().equals(user.getUUID())) {
				send(change);
			}
		} else
			send(change);
	}
	/**
	 * this is the run method of the view thread that put the view in
	 * listening state and listen to objects arriving from the socket.
	 * If they are knowed objects this method send to the controller 
	 * the actions or contact the server to create or join a room.
	 * It also send message from the client to the server
	 */
	@Override
	public void run() {
		Object objectIn = null;
		while (listen) {

			try {
				objectIn = socketIn.readObject();
			} catch (ClassNotFoundException e){
				Util.exception(e);
			}catch(IOException e1) {
				Util.exception(e1);
				listen = false;
			}
			if (objectIn != null) {
				if (objectIn instanceof Action) {
					Action action = (Action) objectIn;
					Util.println("VIEW: received the action " + action.getActionName());
					action.setPlayer(user.getPlayer());

					this.notifyObservers(action);
				}

				else if (((String) objectIn).startsWith("USERNAME")) {
					String username = objectIn.toString().replaceFirst("USERNAME", "");
					user = new User(this, username);
					Server.getInstance().addUser(user);
					send(user.getUUID());
					ArrayList<String> maps = (ArrayList<String>) Server.getInstance().getMapsList();
					maps.add(0, "MAPS_LIST");
					send(maps);
					
				} else if (((String) objectIn).startsWith("CREATE_ROOM")) {
					String string = objectIn.toString().replaceFirst("CREATE_ROOM", "");
					String[] stringArray = string.split(":");
					if (user.getRoom() == null) {
						Server.getInstance().createRoom(user, stringArray[0], stringArray[1], 
																			  Integer.parseInt(stringArray[2]),
																			  Integer.parseInt(stringArray[3]));
					}

				} else if (((String) objectIn).startsWith("JOIN_ROOM")) {
					String string = objectIn.toString().replaceFirst("JOIN_ROOM", "");
					if (user.getRoom() == null) {
						Server.getInstance().joinRoom(user, string);
					}

				} else if (((String) objectIn).startsWith("CHAT")) {
					String string = objectIn.toString().replaceFirst("CHAT", "");
					Server.getInstance().sendChatMessage(user, string);
					
				} else if (((String) objectIn).startsWith("DISCONNECT")) {
					Server.getInstance().disconnect(user);
				}
			}
		}
	}
	/**
	 * this method send to the client a chat message
	 */
	@Override
	public void printChatMessage(UUID receiverId, String sender, String message) {
		send(new String("CHAT" + sender + ":" + message));
	}
	/**
	 * this is the universal method for send to the client objects
	 * @param object
	 */
	private void send(Object object) {
		try {
			socketOut.writeObject(object);
			socketOut.flush();
			socketOut.reset();
		} catch (IOException e) {
			Util.exception(e);
		}
	}
	/**
	 * this method send the rooms list to the client
	 */
	@Override
	public void sendRoomsList(UUID receiverId, List<String> rooms) {
		ArrayList<String> roomsToSend = new ArrayList<>(rooms);
		roomsToSend.add(0, "ROOMS_LIST");
		send(roomsToSend);
	}
}