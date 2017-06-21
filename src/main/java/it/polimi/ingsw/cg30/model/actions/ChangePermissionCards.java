package it.polimi.ingsw.cg30.model.actions;

import java.util.List;

import it.polimi.ingsw.cg30.model.Region;

public class ChangePermissionCards extends FastAction {

	/**
	 * serial UID
	 */
	private static final long serialVersionUID = -6594285029607293701L;
	/**
	 * selected region
	 */
	private Region selectedRegion;
	/**
	 * choosen region
	 */
	private String choosenRegion;
	/**
	 * arraylist of strings passed
	 */
	private List<String> choice;

	/**
	 * change permission cards of selected region.
	 * action removes itself from possibleActions.
	 * calls update
	 * 
	 * @param choice of the player
	 * @return false if choice is not legit
	 */
	public ChangePermissionCards(List<String> choice) {
		this.choice = choice;
	}
	
	/**
	 * performs the action if the conditions are satisfied 
	 * 
	 * changes the permission cards of a region with new ones
	 * 
	 * @return false if the player hasn't enough assistants || the chosen region is legit
	 */
	@Override
	public Boolean actionPerformed() {

		if(getPlayer().getAssistants().isEmpty())
			return false;
		
		choosenRegion = choice.get(0);

		if (!isLegitChoice(choosenRegion, getPlayer().getGame().getGameBoard().getRegions().size())) 
			return false;
		
		else {
			selectedRegion = getPlayer().getGame().getGameBoard().getRegions().get(Integer.parseInt(choosenRegion) - 1);
			selectedRegion.changePermissionCards();
			getPlayer().removeAssistants(1);
			removeFromPlayer(this);
			getPlayer().getGame().getGameState().update();
			return true;
		}
	}
}