package it.polimi.ingsw.cg30.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import it.polimi.ingsw.cg30.model.change.Change;
import it.polimi.ingsw.cg30.model.change.PlayerChange;
import it.polimi.ingsw.cg30.model.util.Observable;

@XmlRootElement(name = "game")
@XmlAccessorType(XmlAccessType.FIELD)
public class Game extends Observable<Change> {

	private GameState gameState;
	private GameBoard gameboard;
	private List<Player> players;
	private String mapName;
	private final UUID gameId;
	private final int numEmporium;
	private Timer timeout = new Timer();
	private static final int TIMEOUT_NEXTPLAYER = 90000;
	/**
	 * this constructor allow you to select the map.
	 * be careful to control that the number of the map is in range
	 * @param mapNumber the number of the map to play
	 */
	public Game(String mapName, int numEmporium) {
		players=new ArrayList<>();
		this.mapName=mapName;
		gameId = UUID.randomUUID();

		if (numEmporium <= 3){
			this.numEmporium=3;
		}
		else if (numEmporium > 10)
			this.numEmporium=10;
		else
			this.numEmporium=numEmporium;
	}
	
	/**
	 * this constructor is the default game initializer
	 */
	public Game(){
		players=new ArrayList<>();
		gameId = UUID.randomUUID();
		this.numEmporium=10;
	}
	
	/**
	 * 
	 * @return the name of the map
	 */
	public String getMapName(){
		return mapName;
	}
	
	/**
	 * 
	 * @return the number of emporiums
	 */
	public int getNumEmporium(){
		return numEmporium;
	}

	/**
	 * @return the gameBoard
	 */
	public GameBoard getGameBoard() {
		return gameboard;
	}

	/**
	 * @param gameBoard
	 *            the gameBoard to set
	 */
	public void setGameBoard(GameBoard gameBoard) {
		this.gameboard = gameBoard;
	}

	/**
	 * @return the players
	 */
	public List<Player> getPlayers() {
		return players;
	}
	/**
	 *adds a player to the game 
	 */
	
	public void addPlayer(Player player) {
		players.add(player);
	}

	/**
	 * @param gameState to set
	 */
	public void setGameState(GameState gameState) {
		this.gameState=gameState;
	
	}
	
	/**
	 * this method resets the timer
	 */
	public void resetTimer(){
			timeout.cancel();
			timeout = new Timer();
			timeout.schedule(new TimerTask() {
				@Override
				public void run() {
					gameState.getCurrentPlayer().getPossibleActions().clear();
					gameState.getCurrentPlayer().getActionsBackup().clear();
					notifyObservers(new PlayerChange(gameState.getCurrentPlayer(), gameState.getCurrentPlayer().getUUID()));
					gameState.nextPlayer(); 
				}
			}, TIMEOUT_NEXTPLAYER);	
	}
	
	/**
	 * @return current gameState
	 */
	public GameState getGameState() {
		return this.gameState;
	}
	/**
	 * 
	 * @return game UUID
	 */
	public UUID getUUID(){
		return gameId;
	}
}