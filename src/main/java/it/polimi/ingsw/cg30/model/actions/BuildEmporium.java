package it.polimi.ingsw.cg30.model.actions;

import java.util.List;

import it.polimi.ingsw.cg30.model.City;
import it.polimi.ingsw.cg30.model.Emporium;
import it.polimi.ingsw.cg30.model.PermissionCard;

public class BuildEmporium extends MainAction {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -498228137605951026L;
	/**
	 * permission card selected
	 */
	private PermissionCard selectedPermissionCard = null;
	/**
	 * choosen PermissionCard
	 */
	private String choosenPermissionCard;
	/**
	 * selected city
	 */
	private City selectedCity = null;
	/**
	 * choosen city
	 */
	private String choosenCity;
	/**
	 * initial letter of the choosen city
	 */
	private String choosenCityInitLetter;
	/**
	 * emporium taken from player and
	 * placed on city
	 */
	private Emporium emporiumToBuild;
	/**
	 * arraylist of strings selected
	 */
	private List<String> choice;

	/**
	 * constructor
	 * @param choice of the player
	 * 
	 */
	public BuildEmporium(List<String> choice) {
		this.choice = choice;
	}
	/**
	 *
	 * performs the action if choice and player's condition are satisfied:
	 * -removes assistants from player.
	 * -build emporium on the city.
	 * -removes emporium from player.
	 * -check for special bonus.
	 * -give city rewards.
	 * -permission cards is set to used.
	 * -action removes itself from possiblActions.
	 * -calls update.
	 * 
	 * @return false if: no permissionCards||no emporiums|| choice size!=2||
	 * one of the choices not legit||permissioncard already used!!permisioncard does not
	 * contain city initial letter|| assistant are less than emporium built on selected
	 * city|| player has already built emporium on the city
	 * 
	 */
	@Override
	public Boolean actionPerformed() {

		if (getPlayer().getPermissionCards().isEmpty())
			return false;

		if (getPlayer().getEmporiums().isEmpty())
			return false;
		
		if (choice.size() != 2)
			return false;

		choosenPermissionCard = choice.get(0);
		choosenCity = choice.get(1);
		
		if(!isLegitChoice(choosenPermissionCard, getPlayer().getPermissionCards().size()) ||
			!isLegitChoice(choosenCity, getPlayer().getGame().getGameBoard().getCities().size())) 
			return false;

		selectedPermissionCard = getPlayer().getPermissionCards().get(Integer.parseInt(choosenPermissionCard) - 1);
		
		if (selectedPermissionCard.getUsed())
			return false;
		
		selectedCity = getPlayer().getGame().getGameBoard().getCities().get(Integer.parseInt(choosenCity)-1);
		choosenCityInitLetter = selectedCity.getInitLetter();
		
		if(!selectedPermissionCard.getLetters().contains(choosenCityInitLetter))
			return false;
		
		if (getPlayer().getAssistants().size() < selectedCity.getEmporiumsSpace().size())
			return false;
		
		if(!selectedCity.canBuildEmporium(getPlayer())) {
			return false;
		}

		else {
			getPlayer().removeAssistants(selectedCity.getEmporiumsSpace().size());
			emporiumToBuild = getPlayer().getEmporiums().get(0);
			selectedCity.buildEmporium(emporiumToBuild);
			getPlayer().removeEmporium();
			getPlayer().getGame().getGameBoard().citiesBuildBonus(getPlayer());
			selectedCity.giveRewardToPlayer(getPlayer(), selectedCity);
			selectedPermissionCard.setUsed(true);
			removeFromPlayer(this);
			getPlayer().getGame().getGameState().update();
			return true;
		}
	}
}