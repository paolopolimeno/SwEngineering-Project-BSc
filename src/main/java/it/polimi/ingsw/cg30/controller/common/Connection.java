package it.polimi.ingsw.cg30.controller.common;

import java.net.InetAddress;

public class Connection {
	/**
	 * RMI registry port
	 */
	public static final int SERVER_REGISTRY_PORT = 2020;

	//public static final int SERVER_SOCKET_PORT = 29000;
	/**
	 * local host socket port
	 */
	public static final int SERVER_SOCKET_PORT = 8888;

	//public static final String SERVER_ADDRESS = "40.68.33.47";
	/**
	 * local ip
	 */
	public static final String SERVER_ADDRESS = InetAddress.getLoopbackAddress().getHostAddress();
	/**
	 * string remote client
	 */
	public static final String REMOTE_CLIENT_RMI = "remote_client";
	/**
	 * string remote server
	 */
	public static final String REMOTE_SERVER_RMI = "remote_server";
	/**
	 * private constructor
	 */
	private Connection(){
		
	}
}
