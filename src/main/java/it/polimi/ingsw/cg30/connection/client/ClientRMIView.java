package it.polimi.ingsw.cg30.connection.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.UUID;
import it.polimi.ingsw.cg30.model.change.Change;
import it.polimi.ingsw.cg30.model.change.GameBoardChange;
import it.polimi.ingsw.cg30.model.change.PlayerChange;

public class ClientRMIView extends UnicastRemoteObject implements ClientViewRemote{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2975784700265121528L;
	/**
	 * create a new client RMI view
	 * @throws RemoteException
	 */
	public ClientRMIView() throws RemoteException {
		super();
	}
	/**
	 * method called from the rmi server to update the client
	 * with the change sent
	 * @param c the change to sent
	 * @throws RemoteException
	 */
	@Override
	public void updateClient(Change c) throws RemoteException {
		if (c instanceof GameBoardChange) {
			Client.getInstance().setGameBoard((GameBoardChange) c);
			Client.getInstance().getGraphic().printGameBoard(Client.getInstance().getGameBoard());
		}
		else if (c instanceof PlayerChange) {
			Client.getInstance().setPlayer((PlayerChange) c);
			Client.getInstance().getGraphic().printPlayer(Client.getInstance().getPlayer());
		}
	}
	/**
	 * method called from the rmi server to update
	 * the rooms list on the client side
	 * @param rooms an arraylist of string with rooms names
	 * @throws RemoteException
	 */
	@Override
	public void updateRoomsList(List<String> rooms) throws RemoteException {
		Client.getInstance().setRoomsList(rooms);
	}
	/**
	 * method called from the rmi server to update the maps
	 * list on the client side
	 * @param maps an arraylist of string with maps names
	 * @throws RemoteException
	 */
	@Override
	public void updateMapsList(List<String> maps) throws RemoteException {
		Client.getInstance().setMapsList(maps);
	}
	/**
	 * method called from the rmi server to send a chat message to the client
	 * @param username the sender username
	 * @param message the chat message
	 * @throws RemoteException
	 */
	@Override
	public void printChatMessage(String username, String message) {
		if (message!=null && message.length() > 0)
			Client.getInstance().getGraphic().printChatMessage(username, message);
	}
	/**
	 * method called from the rmi server to set the UUID of the client
	 * @param uuid uuid of the client 
	 * @throws RemoteException
	 */
	@Override
	public void setUUID(UUID uuid) throws RemoteException {
		Client.getInstance().setUUID(uuid);
	}
}