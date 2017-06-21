package it.polimi.ingsw.cg30.connection.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.UUID;

import it.polimi.ingsw.cg30.connection.server.RMIViewRemote;
import it.polimi.ingsw.cg30.controller.common.Connection;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.util.Util;

public class RMIClient implements ClientNetworkInterface {
	/**
	 * the rmi view remote to contact 
	 */
	RMIViewRemote serverStub;
	/**
	 * the rmi registry
	 */
	Registry registry;
	/**
	 * the client view rmi
	 */
	ClientRMIView rmiView;
	/**
	 * see {@link ClientNetworkInterface}
	 */
	@Override
	public void connect(String username) {

		try {
			registry = LocateRegistry.getRegistry(Connection.SERVER_ADDRESS, Connection.SERVER_REGISTRY_PORT);
			serverStub = (RMIViewRemote) registry.lookup(Connection.REMOTE_SERVER_RMI);
			rmiView = new ClientRMIView();
			serverStub.registerClient(rmiView, username);
		} catch (RemoteException | NotBoundException e) {
			Util.exception(e);
		}
	}
	/**
	 * see {@link ClientNetworkInterface}
	 */
	@Override
	public void disconnect() {
		try {
			serverStub.disconnect(Client.getInstance().getUUID());
		} catch (RemoteException e) {
			Util.exception(e);
		}
	}
	/**
	 * see {@link ClientNetworkInterface}
	 */
	@Override
	public void sendAction(Action action) {
		try {
			serverStub.actionPerform(action, Client.getInstance().getUUID());
		} catch (RemoteException e) {
			Util.exception(e);
		}
	}
	/**
	 * see {@link ClientNetworkInterface}
	 */
	@Override
	public void joinRoom(UUID userId, String roomName) {
		try {
			serverStub.joinRoomOnServer(userId, roomName);
		} catch (RemoteException e) {
			Util.exception(e);
		}
	}
	/**
	 * see {@link ClientNetworkInterface}
	 */
	@Override
	public void sendChatMessage(UUID uuid, String message) {
		try {
			serverStub.sendChatMessage(uuid, message);
		} catch (RemoteException e) {
			Util.exception(e);
		}
	}
	/**
	 * see {@link ClientNetworkInterface}
	 */
	@Override
	public void createRoom(UUID uuid, String roomName, String mapName, int playerNumber, int numEmporium) {
		try {
			serverStub.createRoomOnServer(uuid, roomName, mapName, playerNumber, numEmporium);
		} catch (RemoteException e) {
			Util.exception(e);
		}
	}
}