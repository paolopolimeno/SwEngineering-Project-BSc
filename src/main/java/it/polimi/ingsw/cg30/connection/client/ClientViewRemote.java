package it.polimi.ingsw.cg30.connection.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

import it.polimi.ingsw.cg30.model.change.Change;

public interface ClientViewRemote extends Remote{
	/**
	 * method called from the rmi server to update the client
	 * with the change sent
	 * @param c the change to sent
	 * @throws RemoteException
	 */
	public void updateClient(Change c) throws RemoteException;
	/**
	 * method called from the rmi server to update
	 * the rooms list on the client side
	 * @param rooms an arraylist of string with rooms names
	 * @throws RemoteException
	 */
	public void updateRoomsList(List<String> rooms) throws RemoteException;
	/**
	 * method called from the rmi server to update the maps
	 * list on the client side
	 * @param maps an arraylist of string with maps names
	 * @throws RemoteException
	 */
	public void updateMapsList(List<String> maps) throws RemoteException;
	/**
	 * method called from the rmi server to send a chat message to the client
	 * @param username the sender username
	 * @param message the chat message
	 * @throws RemoteException
	 */
	public void printChatMessage(String username, String message) throws RemoteException;
	/**
	 * method called from the rmi server to set the UUID of the client
	 * @param uuid uuid of the client 
	 * @throws RemoteException
	 */
	public void setUUID(UUID uuid) throws RemoteException;
}