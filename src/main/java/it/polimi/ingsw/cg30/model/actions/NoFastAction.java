package it.polimi.ingsw.cg30.model.actions;

public class NoFastAction extends FastAction {


	/**
	 * UID
	 */
	private static final long serialVersionUID = -8508482165587483232L;
	/**
	 * removes itself from possibleAction
	 * and updates.
	 * Since it is a fast action, all the 
	 * others are removed too and 
	 * player doesn't perform them
	 */
	@Override
	public Boolean actionPerformed() {
		removeFromPlayer(this);
		getPlayer().getGame().getGameState().update();
		return true;
	}
}