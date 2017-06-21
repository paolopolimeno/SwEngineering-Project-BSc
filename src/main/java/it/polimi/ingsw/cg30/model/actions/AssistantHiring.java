package it.polimi.ingsw.cg30.model.actions;

import it.polimi.ingsw.cg30.model.bonus.AssistantBonus;
import it.polimi.ingsw.cg30.model.bonus.Bonus;

public class AssistantHiring extends FastAction {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -4164011899796345831L;
	/**
	 * Bonus which is given to the player(Assistant +1)
	 */
	private Bonus bonus;
	/**
	 * performs the action if player has enough propserity points
	 * 
	 * players prosperity points are removed by 3.
	 * players gets 1 assistant.
	 * this action removes itself from player possibleActions.
	 * calls gameState update.
	 * 
	 * 
	 * 
	 * @return false if player's has not enough prosperity points
	 */
	@Override
	public Boolean actionPerformed() {

		if (getPlayer().getProsperityDisk().getSlot().getCoordinate() < 3)
			return false;

		else {
			getPlayer().moveProsperityDisk(-3);
			bonus = new AssistantBonus(1);
			bonus.giveBonusToPlayer(getPlayer());
			removeFromPlayer(this);
			getPlayer().getGame().getGameState().update();
			return true;
		}
	}
}