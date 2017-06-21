package it.polimi.ingsw.cg30.model.actions;

public class AnotherMainAction extends FastAction {


	/**
	 * UID
	 */
	private static final long serialVersionUID = -3169617545466496817L;

	/**
	 *performs the action if player has enough assistants.
	 *
	 *removes 3 assistants from player.
	 *fills player possibleAction with mainActionFiller.
	 *this action removes itself from player possibleActions.
	 *calls gameState update.
	 *
	 *
	 *@return false if player has not enough assistants
	 *
	 * 
	 * */
	@Override
	public Boolean actionPerformed() {
		
		if(getPlayer().getAssistants().size() < 3)
			return false;
		
		getPlayer().removeAssistants(3);
		getPlayer().mainActionFiller();
		removeFromPlayer(this);
		getPlayer().getGame().getGameState().update();
		return true;
	}
}