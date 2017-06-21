package it.polimi.ingsw.cg30.connection.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import it.polimi.ingsw.cg30.controller.common.Connection;
import it.polimi.ingsw.cg30.model.util.Util;

public class SocketServer implements ServerConnection {
	/**
	 * the thread of the server
	 */
	private Thread thread;
	/**
	 * the boolean listening state of the server
	 */
	private boolean listening;
	/**
	 * the server socket
	 */
	private ServerSocket serverSocket;
	/**
	 * create a socket server, until the thread run method
	 * is called, the server is not listening.
	 */
	public SocketServer() {
		this.listening = false;
	}
	/**
	 * the start method that create a new thread of the server
	 * and start the thread
	 */
	@Override
	public void start() {
		thread = new Thread(this, "socketServer");
		thread.start();
	}
	/**
	 * the thread run method that start listening
	 */
	@Override
	public void run() {
		startListening();
	}
	/**
	 * the stop method that call end listening
	 */
	@Override
	public void stop() {
		endListening();
	}
	/**
	 * this method close the socket if the server is in
	 * a listening state
	 */
	private void endListening() {

		if (listening) {
			listening = false;

			try {
				serverSocket.close();
			} catch (IOException e) {
				Util.exception(e);			
			}
		}
	}
	/**
	 * this method listen to incoming connections
	 */
	private void startListening() {

		if (!listening) {
			try {
				serverSocket = new ServerSocket(Connection.SERVER_SOCKET_PORT);
			} catch (IOException e) {
				Util.exception(e);
			}
			listening = true;

			while (listening) {

				try {
					Socket socket = serverSocket.accept();
					new Thread(new ServerSocketView(socket)).start();
				} catch (IOException e) {
					Util.exception(e);
				}
			}
		}
	}
}