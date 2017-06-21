package it.polimi.ingsw.cg30.connection.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import it.polimi.ingsw.cg30.controller.Controller;
import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.StartGame;

public class Room {
	/**
	 * timeout for the timer 
	 */
	private static final int TIMEOUT_NEWGAME = 20000;
	/**
	 * the list of users in the room
	 */
	private ArrayList<User> users;
	/**
	 * the game of the room
	 */
	private Game game;
	/**
	 * the controller of the game
	 */
	private Controller controller;
	/**
	 * the name of the room
	 */
	private String name;
	/**
	 * the max users of the room
	 */
	private int maxUsers;
	/**
	 * the min number of users
	 */
	private int minUsers = 2;
	/**
	 * the timer for start the game
	 */
	private Timer timeout = new Timer();
	/**
	 * colors of users
	 */
	private Map<Integer, String> colors = new HashMap<>();
	/**
	 * this constructor create a new room with the owner inside
	 * @param owner
	 * @param name
	 * @param mapNumber
	 * @param maxUsers
	 * @param numEmporium
	 */
	public Room(User owner, String name, String mapName, int maxUsers, int numEmporium){
		users = new ArrayList<>();
		this.name=name;
		if (maxUsers>4)
			this.maxUsers=4;
		else if (maxUsers<2)
			this.maxUsers=2;
		else
			this.maxUsers=maxUsers;

		colors.put(1, "RED");
		colors.put(2, "YELLOW");
		colors.put(3, "BLUE");
		colors.put(4, "GREEN");
		game = new Game(mapName, numEmporium);
		game.setGameState(new StartGame(game));
		addUser(owner);
	}
	/**
	 * this method add an user to the room and reset the timer
	 * @param user
	 */
	public void addUser(User user){
		
		users.add(user);
		Player player = createPlayer(game, user.getUUID());
		user.setPlayer(player);
		game.addPlayer(player);
		
		if (users.size() == maxUsers){
			timeout.cancel();
			startGame();
		}
		else {
			timeout.cancel();
			timeout = new Timer();
			timeout.schedule(new TimerTask() {

				@Override
				public void run() {
					if (users.size() < minUsers) {
						return;
					}
					startGame();
				}
			}, TIMEOUT_NEWGAME);
		}
	}
	/**
	 * this method start the game
	 */
	private void startGame(){
		//setto gli osservatori e creo il controllore
		controller = new Controller(game);
		
		boolean RMI = false;
		for (User user : users){
			if (!RMI && user.getServerView() instanceof RMIView){
				game.registerObserver(user.getServerView());
				user.getServerView().registerObserver(controller);
				RMI = true;
			}
			else if (user.getServerView() instanceof ServerSocketView){
				game.registerObserver(user.getServerView());
				user.getServerView().registerObserver(controller);
			}
		}
		game.getGameState().run();
	}
	/**
	 * this method create a player
	 * @param game
	 * @param userId
	 * @return
	 */
	private Player createPlayer(Game game, UUID userId) {
		return new Player(users.size(), colors.get(users.size()), game, userId);
	}
	/**
	 * this method return the game
	 * @return
	 */
	public Game getGame(){
		return game;
	}
	/**
	 * this method return the name of the room
	 * @return
	 */
	public String getName(){
		return name;
	}
	/**
	 * this method return the max users of the room
	 * @return
	 */
	public int getMaxUsers(){
		return maxUsers;
	}
	/**
	 * this method returns the users of the room
	 * @return
	 */
	public List<User> getUsers(){
		return users;
	}
	/**
	 * this method disconnect an user from the game
	 * @param user
	 */
	public void disconnect(User user) {
		if (!(user.getServerView() instanceof RMIView))
			game.unregisterObserver(user.getServerView());
		user.setOffline();	
	}
}