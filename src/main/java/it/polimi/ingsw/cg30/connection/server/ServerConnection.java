package it.polimi.ingsw.cg30.connection.server;

public interface ServerConnection extends Runnable{
	/**
	 * the start method of the interface
	 */
	public void start();
	/**
	 * the stop method of the interface
	 */
	public void stop();
}
