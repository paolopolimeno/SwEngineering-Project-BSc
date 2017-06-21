package it.polimi.ingsw.cg30.model.actions;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg30.model.PermissionCard;
import it.polimi.ingsw.cg30.model.Region;

public class FreePermissionCardAction extends BonusAction {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5927451815748440352L;
	private int choiceLength;
	private List<String> choice;
	private List<PermissionCard> permissionCardsToGet=new ArrayList<>();

	/**
	 * the constructor initialize the choice and its length
	 * @param choiceLength
	 * @param choice
	 */
	public FreePermissionCardAction(int choiceLength, List<String> choice) {
		this.choiceLength = choiceLength;
		this.choice = choice;
	}
	/**
	 * gives selected permission cards to player.
	 * calls removeBonusAction
	 * @return false if: choiceLength not matching one on server||
	 * one of choices not legit 
	 */
	@Override
	public Boolean actionPerformed() {

		FreePermissionCardAction playerAction = (FreePermissionCardAction) (getPlayer().getPossibleActions().get(0));
		int numberAction = playerAction.getChoiceLength();

		if (choiceLength != numberAction) {
			return false;
		}
		if (choiceLength*2 != choice.size()) {
			return false;
		}


		for (int i = 0; i < choice.size(); i++) {

			Region selectedRegion;
			PermissionCard selectedPermissionCard;
			if (!isLegitChoice(choice.get(i), getPlayer().getGame().getGameBoard().getRegions().size())) {
				return false;
			}
			selectedRegion = getPlayer().getGame().getGameBoard().getRegions().get(Integer.parseInt(choice.get(i)) - 1);

			i=i+1;
			
			if (!isLegitChoice(choice.get(i), selectedRegion.getShowedPermissionCards().size())) {
				return false;
			}
			selectedPermissionCard = selectedRegion.getShowedPermissionCards().get(Integer.parseInt(choice.get(i)) - 1);
			permissionCardsToGet.add(selectedPermissionCard);
		}

		for (PermissionCard permissionCard : permissionCardsToGet) {
			getPlayer().takePermissionCard(permissionCard);
		}
		removeBonusAction(this);
		return true;
	}

	/**
	 * 
	 * @return the length of the choice
	 */
	public int getChoiceLength() {
		return choiceLength;
	}
}