package it.polimi.ingsw.cg30.connection.server;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import it.polimi.ingsw.cg30.model.StartGame;
import it.polimi.ingsw.cg30.model.util.GameLoader;
import it.polimi.ingsw.cg30.model.util.Util;

public class Server {
	/**
	 * all the rooms on the server
	 */
	private ArrayList<Room> rooms;
	/**
	 * all the users connected on the server
	 */
	private ArrayList<User> users;
	/**
	 * the socket server
	 */
	private ServerConnection serverSocket;
	/**
	 * the rmi server
	 */
	private ServerConnection serverRMI;
	/**
	 * the instance of this server
	 */
	private static Server instance;
	/**
	 * boolean variable for the status of the srever
	 */
	private boolean online;
	/**
	 * the scanner of the cli commands of the server
	 */
	private Scanner sc;
	/**
	 * this constructor create a single instance of the server
	 */
	private Server(){
		instance = this;
		rooms = new ArrayList<>();
		users = new ArrayList<>();
	}
	/**
	 * this method start the server and set the online state
	 * of the server to true. Start an rmi and a socket server.
	 * wait the exit command to shutdown the server.
	 */
	public void start() {

		serverRMI = ServerConnectionFactory.getRMIConnection();
		serverSocket = ServerConnectionFactory.getSocketConnection();
		serverRMI.start();
		serverSocket.start();
		online = true;
		Util.println("Server is ready!");
		sc = new Scanner(System.in);
		while (online) {
			Util.println("Type exit to stop the server...");
			String line = sc.nextLine();

			if ("exit".equals(line)) {
				stop();
			}
		}
	}
	/**
	 * this method close the rmi and the socket server
	 */
	private void stop() {
		try {
			if (serverRMI != null) {
				serverRMI.stop();
			}
			if (serverSocket != null) {
				serverSocket.stop();
			}
			online = false;
		} finally{
			sc.close();
		}
	}
	/**
	 * this method create a room with the following parameters
	 * @param owner
	 * @param name
	 * @param mapName
	 * @param maxUsers
	 * @param numEmporium
	 * @return
	 */
	public synchronized boolean createRoom(User owner, String name, String mapName, int maxUsers, int numEmporium) {

		if (Util.getFilesInPath(GameLoader.mapsPath).contains(mapName) && maxUsers >= 2 && maxUsers<5){
			Room room = new Room(owner, name, mapName, maxUsers, numEmporium);
			rooms.add(room);
			owner.setRoom(room);
			Util.println("Room "+name+" created with the map "+ mapName);
			Util.println(owner.getName()+" joined the room "+name+"!");
			sendRoomsNotInGameToUsers();
			return true;
		}
		return false;
	}
	/**
	 * this method put an user into a room if the room is in the starting state
	 * @param user
	 * @param roomName
	 */
	public synchronized void joinRoom(User user, String roomName) {
		for (Room room : rooms) {
			if (room.getName().equals(roomName) && room.getGame().getGameState() instanceof StartGame) {
				room.addUser(user);
				user.setRoom(room);
				Util.println(user.getName()+" joined the room "+room.getName()+"!");
			}
		}
	}
	/**
	 * this method add an user on the server
	 * @param user
	 */
	public synchronized void addUser(User user) {
		users.add(user);
		sendRoomsNotInGameToUsers();
		Util.println("client registered");
	}
	/**
	 * this method returns the list of users of the server
	 * @return
	 */
	public List<User> getUsers() {
		return users;
	}
	/**
	 * this main method call the start method of this class
	 * @param args
	 * @throws IOException
	 * @throws AlreadyBoundException
	 */
	public static void main(String[] args) throws IOException, AlreadyBoundException {
		new Server().start();
	}
	/**
	 * return the instance of the server
	 * @return
	 */
	public static Server getInstance() {
		return instance;
	}
	/**
	 * this method send the message to the room of the user that sent the 
	 * chat message
	 * @param user
	 * @param message
	 */
	public void sendChatMessage(User user, String message){
		for (User userInRoom : user.getRoom().getUsers()){
			if (!user.equals(userInRoom)){
				userInRoom.getServerView().printChatMessage(userInRoom.getUUID(), user.getName(), message);
			}
		}
		Util.println("The user "+user.getName()+" sent a message to the room "+user.getRoom().getName()+":"+message);
	}
	/**
	 * return the room that contains the game with that id
	 * @param gameId
	 * @return
	 */
	public Room getRoomByGameId(UUID gameId){
		for (Room room : rooms){
			if (room.getGame().getUUID().equals(gameId)){
				return room;
			}
		}
		return null;
	}
	/**
	 * this method send the rooms to the users that are still not in game
	 * if a user is playing his client will not receive this update
	 * 
	 */
	private void sendRoomsNotInGameToUsers(){
		List<String> roomsNotInGame = new ArrayList<>();
		
		for (Room room : rooms){
			if (room.getGame().getGameState() instanceof StartGame){
				roomsNotInGame.add(room.getName());
			}
		}
		
		for (User user : users){
			if (user.getRoom() == null){
				user.getServerView().sendRoomsList(user.getUUID(), roomsNotInGame);
			}
		}
	}
	/**
	 * return a string list of game maps available
	 * @return
	 */
	public List<String> getMapsList() {
		return Util.getFilesInPath(GameLoader.mapsPath);
	}
	/**
	 * disconnect an user from the server and sent a message to
	 * the other users of the room that the user is disconnected
	 * @param user
	 */
	public void disconnect(User user) {
		for (Room room : rooms){
			if (room.getUsers().contains(user)){
				room.disconnect(user);
				sendChatMessage(user, "MESSAGE FROM THE SERVER I'm disconnected!");
			}
		}	
	}
}