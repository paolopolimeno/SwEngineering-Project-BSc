package it.polimi.ingsw.cg30.model.actions;

import java.io.Serializable;

import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.actions.market.BuyAction;
import it.polimi.ingsw.cg30.model.actions.market.SellAction;
import it.polimi.ingsw.cg30.model.util.Util;

public abstract class Action implements Serializable {


	/**
	 * serial UID
	 */
	private static final long serialVersionUID = 3463440802734407627L;
	/**
	 * player of the action
	 */
	private Player player;

	/**
	 * 
	 * @return true if the action is performed
	 */
	public abstract Boolean actionPerformed();
	/**
	 * the method sets the player
	 * 
	 * @param player
	 * @throws NullPointerException
	 *             if the player is null
	 */
	public void setPlayer(Player player) {

		if (player == null)
			throw new NullPointerException();

		this.player = player;
	}
	/**
	 * 
	 * @return the player
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * 
	 * @return the name of the action
	 */
	public String getActionName() {
		return this.getClass().getSimpleName();
	}

	/**
	 * 
	 * @param choice
	 *            of the player
	 * @param length
	 *            of the considered array
	 * @return false if the choice is out of bound or null
	 */
	public boolean isLegitChoice(String choice, int length) {

		if (Util.isInteger(choice) && (Integer.parseInt(choice) < length + 1 && Integer.parseInt(choice) > 0)) {
			return true;
		}
		return false;
	}
	/**
	 * calls one of the action removers based on
	 * parameter class. if player has bonus and 
	 * his backup is empty, the method calls makeBackUpAndRemove 
	 * @param action
	 */
	protected void removeFromPlayer(Action action){
		
		if (action instanceof FastAction){
			action.getPlayer().fastActionRemover();
		}
		else if (action instanceof MainAction){
			action.getPlayer().mainActionRemover();
		}
		else if (action instanceof BonusAction){
		
			action.getPlayer().bonusActionRemover((BonusAction) action);
		
		}
		else if (action instanceof BuyAction){
			action.getPlayer().buyActionRemover();
		}
		else if (action instanceof SellAction){
			action.getPlayer().sellActionRemover();
		}
		if (action.getPlayer().checkIfHasBonus() && player.getActionsBackup().isEmpty()){
				action.getPlayer().makeBackUpAndRemove();
			}
	}
}