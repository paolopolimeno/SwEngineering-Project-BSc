package it.polimi.ingsw.cg30.controller;

import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.util.Observer;
import it.polimi.ingsw.cg30.model.util.Util;

public class Controller implements Observer<Action> {
	/**
	 * the game to control
	 */
	private final Game game;
	/**
	 * this constructor create a controller that control
	 * the given game
	 * @param game
	 */
	public Controller(Game game) {
		this.game = game;
	}
	/**
	 * update method that receive actions and call the methods
	 * check current player and actionlegit to see if the action can
	 * be a right action, then call action performed and perfom the action.
	 */
	@Override
	public synchronized void update(Action action) {

		if (checkCurrentPlayer(action.getPlayer()) && actionLegit(action)) {

			try {
				if (action.actionPerformed()) {
					Util.println("I am the controller: the action "+action.getActionName()+" has been performed!");
				} else {
					
					Util.println("Azione non valida!");
				}

			} catch (NullPointerException|IndexOutOfBoundsException e) {
				Util.exception(e);
			}			
		}
	}
	/**
	 * this method check if the action is of the current player of the game
	 * and if the player on the server contains the action received
	 * @param actionCompared
	 * @return
	 */
	private boolean actionLegit(Action actionCompared) {
		for (Action action : game.getGameState().getCurrentPlayer().getPossibleActions()) {
			if (action.getClass().equals(actionCompared.getClass())) {
				return true;
			}
		}
		return false;
	}
	/**
	 * this method returns true if the current player number equals
	 * the player number of the action
	 * @param player
	 * @return
	 */
	private boolean checkCurrentPlayer(Player player){
		return player.getPlayerNumber()==game.getGameState().getCurrentPlayer().getPlayerNumber();
	}
}