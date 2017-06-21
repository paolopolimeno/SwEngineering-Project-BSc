package it.polimi.ingsw.cg30.connection.server;

import java.util.UUID;

import it.polimi.ingsw.cg30.model.Player;

public class User {
	/**
	 * the name of the user
	 */
	private String name;
	/**
	 * the player of the user
	 */
	private Player player;
	/**
	 * the view of the user
	 */
	private View serverView;
	/**
	 * the room of the user
	 */
	private Room room;
	/**
	 * the universal id of the user
	 */
	private final UUID userId;
	/**
	 * the online state of the user
	 */
	private boolean isOnline;
	/**
	 * this constructor create a new user with the following parameters
	 * @param serverView
	 * @param name
	 */
	public User(View serverView, String name) {
		this.serverView = serverView;
		this.name=name;
		this.userId=UUID.randomUUID();
		isOnline = true;
	}
	/**
	 * the uuid of the user
	 * @return
	 */
	public UUID getUUID(){
		return userId;
	}
	/**
	 * 
	 * @return the player
	 */
	public Player getPlayer() {
		return this.player;
	}
	/**
	 * @return the server view
	 */
	public View getServerView() {
		return serverView;
	}
	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	/**
	 * return the name of the user
	 * @return
	 */
	public String getName(){
		return name;
	}
	/**
	 * set the room of the user
	 * @param room
	 */
	public void setRoom(Room room){
		this.room=room;
	}
	/**
	 * return the room of the user
	 * @return
	 */
	public Room getRoom(){
		return room;
	}
	/**
	 * return the online state of the user
	 * @return
	 */
	public boolean isOnline(){
		return isOnline;
	}
	/**
	 * set the user in offline state
	 */
	public void setOffline(){
		isOnline=false;
		player.setOffline();
	}
}