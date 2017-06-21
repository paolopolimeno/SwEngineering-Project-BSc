package it.polimi.ingsw.cg30.model;

public interface GameState {
	
	/**
	 * run the state
	 */
	public abstract void run();	
	/**
	 * @return the currentPlayer of the State
	 */
	public abstract Player getCurrentPlayer();
	/**
	 * either set currentPlayer of the State to the next one
	 * or set game's gameState to the next one
	 */
	public abstract void nextPlayer();
	/**
	 * calls game.notifyObservers()
	 * notify changes after actions are
	 * executed
	 */
	public abstract void update();
}