package it.polimi.ingsw.cg30.connection.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

import it.polimi.ingsw.cg30.connection.client.ClientViewRemote;
import it.polimi.ingsw.cg30.model.actions.Action;

public interface RMIViewRemote extends Remote{
	/**
	 * this method send action from the client to the controller
	 * @param action
	 * @param playerId
	 * @throws RemoteException
	 */
	public void actionPerform(Action action, UUID playerId) throws RemoteException;
	/**
	 * this method create the room with the selected parameters on the server
	 * @param owner
	 * @param roomName
	 * @param mapName the name of the map
	 * @param maxUsers
	 * @param numEmporium
	 * @throws RemoteException
	 */
	public void createRoomOnServer(UUID owner, String roomName, String mapName, int maxUsers, int numEmporium) throws RemoteException;
	/**
	 * this method put the user that want to join the room in the room
	 * @param userId
	 * @param roomName
	 * @throws RemoteException
	 */
	public void joinRoomOnServer(UUID userId, String roomName) throws RemoteException;
	/**
	 * this method register the client on the server
	 * @param clientStub
	 * @param name
	 * @throws RemoteException
	 */
	public void registerClient(ClientViewRemote clientStub, String name) throws RemoteException;
	/**
	 * this method send a message to the client from the server
	 * @param senderId
	 * @param message
	 * @throws RemoteException
	 */
	public void sendChatMessage(UUID senderId, String message) throws RemoteException;
	/**
	 * this method disconnect the client from the server
	 * @param uuid
	 * @throws RemoteException
	 */
	public void disconnect(UUID uuid) throws RemoteException;
}
