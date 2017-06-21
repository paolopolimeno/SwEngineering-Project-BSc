package it.polimi.ingsw.cg30.connection.server;

public class ServerConnectionFactory {
	/**
	 * private constructor for sonar
	 */
	private ServerConnectionFactory() {
		
	}
	/**
	 * return an rmi connection
	 * @return
	 */
	public static ServerConnection getRMIConnection(){	
		return RMIServer.getInstance();
	}
	/**
	 * return a socket connection
	 * @return
	 */
	public static ServerConnection getSocketConnection(){
		return new SocketServer();
	}	
}