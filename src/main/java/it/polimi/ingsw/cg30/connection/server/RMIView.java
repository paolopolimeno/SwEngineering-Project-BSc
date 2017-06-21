package it.polimi.ingsw.cg30.connection.server;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import it.polimi.ingsw.cg30.connection.client.ClientViewRemote;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.change.Change;
import it.polimi.ingsw.cg30.model.util.Util;

public class RMIView extends View implements RMIViewRemote {
	/**
	 * map of rmiusers
	 */
	private Map<UUID, ClientViewRemote> RMIUsers = new HashMap<>();
	/**
	 * this method send the updates to the clients
	 */
	@Override
	public void update(Change change) {

		if (change.getReceiver() == null) {

			Room room = Server.getInstance().getRoomByGameId(change.getUUID());
			if (room != null) {
				for (User roomUser : room.getUsers()) {
					try {
						if (RMIUsers.containsKey(roomUser.getUUID())){
							RMIUsers.get(roomUser.getUUID()).updateClient(change);
						}
					} catch (RemoteException e) {
						Util.exception(e);
					}
				}
			}
		}
		else if (change.getReceiver() != null) {
			try {
				if (RMIUsers.containsKey(change.getReceiver().getUUID())){
					RMIUsers.get(change.getReceiver().getUUID()).updateClient(change);
				}
			} catch (RemoteException e) {
				Util.exception(e);
			}
		}
	}
	/**
	 * this method notify the controller that a client sent an action
	 */
	@Override
	public void actionPerform(Action action, UUID playerId) throws RemoteException {
		
		if (RMIUsers.containsKey(playerId)) {
			for (User user : Server.getInstance().getUsers()){
				if (playerId.equals(user.getUUID())){
					action.setPlayer(user.getPlayer());
					this.notifyObservers(action);
					break;
				}
			}
			
		}
	}
	/**
	 * this methos register a client on the server and send
	 * to the client the map list of game maps
	 */
	@Override
	public synchronized void registerClient(ClientViewRemote clientStub, String name) throws RemoteException {
		User user = new User(this, name);
		RMIUsers.put(user.getUUID(), clientStub);
		Server.getInstance().addUser(user);
		clientStub.setUUID(user.getUUID());
		clientStub.updateMapsList(Server.getInstance().getMapsList());
	}
	/**
	 * this method create a room on the server with the parameters
	 * passed only if the user that want to create the room is 
	 * already registered on the server
	 */
	@Override
	public void createRoomOnServer(UUID owner, String roomName, String mapName, int maxUsers, int numEmporium) throws RemoteException {
		if (RMIUsers.containsKey(owner)) {
			for (User user : Server.getInstance().getUsers()) {
				if (user.getUUID().equals(owner) && user.getRoom() == null) {
						Server.getInstance().createRoom(user, roomName, mapName, maxUsers, numEmporium);
						break;
				}
			}
		}
	}
	/**
	 * this method put the user in the selected room only if the
	 * user is already registered on the server
	 */
	@Override
	public void joinRoomOnServer(UUID userId, String roomName) throws RemoteException {
		if (RMIUsers.containsKey(userId)) {
			for (User user : Server.getInstance().getUsers()) {
				if (user.getUUID().equals(userId) && user.getRoom() == null) {
						Server.getInstance().joinRoom(user, roomName);
						break;
				}
			}
		}
	}
	/**
	 * send chat message to the room of the user
	 * 
	 * @param username:
	 *            the name of the user that send the message
	 * @param message:
	 *            the message that the user want to send
	 */
	@Override
	public void sendChatMessage(UUID senderId, String message) throws RemoteException {
		if (RMIUsers.containsKey(senderId)) {
			for (User user : Server.getInstance().getUsers()) {
				if (user.getUUID().equals(senderId)) {
					Server.getInstance().sendChatMessage(user, message);
				}
			}
		}
	}
	/**
	 * this method send a message from the server to the client stub
	 * corrisponding with the receiverId
	 */
	@Override
	public void printChatMessage(UUID receiverId, String sender, String message) {
		if (RMIUsers.containsKey(receiverId)) {
			try {
				RMIUsers.get(receiverId).printChatMessage(sender, message);
			} catch (RemoteException e) {
				Util.exception(e);
			}
		}
	}
	/**
	 * this method send the list of the rooms 
	 */
	@Override
	public void sendRoomsList(UUID receiverId, List<String> rooms) {
		if (RMIUsers.containsKey(receiverId)) {
			try {
				RMIUsers.get(receiverId).updateRoomsList(rooms);
			} catch (RemoteException e) {
				Util.exception(e);
			}
		}
	}
	/**
	 * this method disconnect the client from the server
	 */
	@Override
	public synchronized void disconnect(UUID userId) throws RemoteException {
		if (RMIUsers.containsKey(userId)){
			for (User user : Server.getInstance().getUsers()) {
				if (user.getUUID().equals(userId)) {
					Server.getInstance().disconnect(user);
					break;
				}
			}
			RMIUsers.remove(userId);
		}	
	}
}