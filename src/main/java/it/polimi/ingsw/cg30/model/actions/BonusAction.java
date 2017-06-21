package it.polimi.ingsw.cg30.model.actions;

import it.polimi.ingsw.cg30.model.util.Util;

public abstract class BonusAction extends Action{

	/**
	 * UID
	 */
	private static final long serialVersionUID = 3390430760778391015L;
	
	/**calls removeFromPlayer with the parameter passed.
	 * it adds the backup to possibleActions Only if player has not
	 * another bonus.
	 * then calls update.
	 * 
	 * @param actionPerformed
	 */
	public void removeBonusAction(Action actionPerformed){
		removeFromPlayer(actionPerformed);
		boolean anotherBonusAction = false;
		
		for (Action action : getPlayer().getPossibleActions()){
			if (action instanceof BonusAction)
				anotherBonusAction=true;
		}
		if (!anotherBonusAction){
				getPlayer().getPossibleActions().addAll(getPlayer().getActionsBackup());
				getPlayer().getActionsBackup().clear();
		}
		getPlayer().getGame().getGameState().update();
	}

}
