package it.polimi.ingsw.cg30.connection.server;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.cg30.controller.common.Connection;
import it.polimi.ingsw.cg30.model.util.Util;

public class RMIServer extends UnicastRemoteObject implements ServerConnection, Remote {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2912783163445536304L;
	/**
	 * the instance of the rmi server
	 */
	private static RMIServer instance;
	/**
	 * the thread
	 */
	private transient Thread thread;
	/**
	 * the rmi registry
	 */
	private transient Registry registry;
	/**
	 * the rmi view
	 */
	private transient RMIView rmiView;
	/**
	 * constructor for the rmi server
	 * @throws RemoteException
	 */
	protected RMIServer() throws RemoteException {
		super();
	}
	/**
	 * start method that start the rmi server in a new thread
	 */
	@Override
	public void start() {

		thread = new Thread(this, "rmi_server");
		thread.start();
	}
	/**
	 * stop method that stop the rmi server 
	 */
	@Override
	public void stop() {

		if (registry != null) {

			try {
				registry.unbind(Connection.REMOTE_SERVER_RMI);
			} catch (RemoteException | NotBoundException e) {
				Util.exception(e);
			}
		}

		if (thread != null) {
			thread.interrupt();
		}
	}
	/**
	 * @return the instance of the server
	 */
	public static RMIServer getInstance() {

		if (instance == null) {
			try {
				return new RMIServer();
			} catch (RemoteException e) {
				Util.exception(e);
				return null;
			}
		}
		return instance;
	}
	/**
	 * run method that create the registry and the rmi view
	 */
	@Override
	public void run() {

		try {

			registry = LocateRegistry.createRegistry(Connection.SERVER_REGISTRY_PORT);

			rmiView = new RMIView();
			UnicastRemoteObject.exportObject(rmiView, 0);
			registry.bind(Connection.REMOTE_SERVER_RMI, rmiView);

		} catch (RemoteException | AlreadyBoundException e) {
			Util.exception(e);
		}
	}
}