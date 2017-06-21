package it.polimi.ingsw.cg30.connection.client;

public class ClientNetworkInterfaceFactory {
	/**
	 * private void contructor
	 */
	private ClientNetworkInterfaceFactory(){
		
	}
	/**
	 * @return an new rmi client connection
	 */
	public static ClientNetworkInterface getRMIConnection(){
		return new RMIClient();
	}
	/** 
	 * @return a new socket client connection
	 */
	public static ClientNetworkInterface getSocketConnection(){
		return new SocketClient();
	}
}