package it.polimi.ingsw.cg30.model.actions;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg30.model.PermissionCard;

public class RepeatPermissionCardAction extends BonusAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6185959231504262854L;
	private int choiceLength;
	private List<String> choice;
	private List<PermissionCard> permissionCardsToGetBonusFrom= new ArrayList<>();
	
	/**
	 * the constructor initialize the choice and its length
	 * @param choiceLength
	 * @param choice
	 */
	public  RepeatPermissionCardAction(int choiceLength, List<String> choice) {
		this.choiceLength = choiceLength;
		this.choice = choice;
	}
	/**
	 * gives the bonus of the selected player's permission card
	 * again to the player.
	 * calls removeBonusAction
	 * @return false if: choice length doesn't match one on server|| one of choices is not legit
	 * 
	 * 
	 */
	@Override
	public Boolean actionPerformed() {

		RepeatPermissionCardAction playerAction = (RepeatPermissionCardAction) (getPlayer().getPossibleActions().get(0));
		int numberAction = playerAction.getChoiceLength();
		
		if (choiceLength != numberAction) {
			return false;
		}
		if (choiceLength != choice.size()) {
			return false;
		}

		for (int i = 0; i < choice.size(); i++) {
		
			PermissionCard permissionCard;
			if(!isLegitChoice(choice.get(i),getPlayer().getPermissionCards().size())){
				return false;
			}
			permissionCard=getPlayer().getPermissionCards().get(Integer.parseInt(choice.get(i)) - 1);
			permissionCardsToGetBonusFrom.add(permissionCard);
		}
		
		for(PermissionCard permissionCard:permissionCardsToGetBonusFrom){
			permissionCard.useBonuses(getPlayer());
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