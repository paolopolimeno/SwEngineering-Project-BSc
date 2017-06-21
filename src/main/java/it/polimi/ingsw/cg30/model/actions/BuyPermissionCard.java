package it.polimi.ingsw.cg30.model.actions;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg30.model.Balcony;
import it.polimi.ingsw.cg30.model.PermissionCard;
import it.polimi.ingsw.cg30.model.PoliticCard;
import it.polimi.ingsw.cg30.model.util.Util;

public class BuyPermissionCard extends MainAction {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 2435249337209733480L;
	/**
	 * selected Balcony
	 */
	private Balcony selectedBalcony;
	/**
	 * selected politic cards
	 */
	private ArrayList<PoliticCard> selectedPoliticCards;
	/**
	 * selected permission card
	 */
	private PermissionCard selectedPermissionCard;
	/**
	 * choosen balcony
	 */
	private String choosenBalcony;
	/**
	 * 
	 */
	private String choosenPermissionCard;
	private List<String> choice;
	private int token;
	private int moneyToPay;

	/**
	 * constructor
	 * @param choice of the player
	 */
	public BuyPermissionCard(List<String> choice) {
		this.choice = choice;
	}
	/**
	 * takes money and politic cards from player.
	 * gives him permission card selected+its bonus.
	 * action removes itself from possibleActions.
	 * calls update
	 * 
	 * @return false if: one of the choices is not legit|| no politic cards|| choice contains duplicate
	 * cards|| cards don't match|| money is not enough
	 */
	@Override
	public Boolean actionPerformed() {

		selectedPoliticCards = new ArrayList<>();
		token = 0;
		
		for (int i = 0; i < 4; i++) {

			if (("END").equals(choice.get(i))) {
				token = i;
				break;
			}
			if (!isLegitChoice(choice.get(i), getPlayer().getPoliticCards().size())){
				return false;
			}
			else selectedPoliticCards.add(getPlayer().getPoliticCards().get(Integer.parseInt(choice.get(i)) - 1));
		}
		if (selectedPoliticCards.isEmpty()){
			return false;
		}
		if (token == 0){
			token = 3;
		}
		
		if(Util.containsDuplicate(new ArrayList<>(choice.subList(0,token+1)))){
			return false;
		}	
		
		choosenBalcony = choice.get(token + 1);
		if (!isLegitChoice(choosenBalcony, getPlayer().getGame().getGameBoard().getBalconies().size()-1)){
			return false;
		}
		selectedBalcony = getPlayer().getGame().getGameBoard().getBalconies().get(Integer.parseInt(choosenBalcony) - 1);
		choosenPermissionCard = choice.get(token + 2);
		if (!isLegitChoice(choosenPermissionCard, selectedBalcony.getRegion().getShowedPermissionCards().size())){
			return false;
		}
		selectedPermissionCard = selectedBalcony.getRegion().getShowedPermissionCards().get(Integer.parseInt(choosenPermissionCard) - 1);

		
		if (!selectedBalcony.matchBalconyWithCards(selectedPoliticCards)){
			return false;
		}
		
		moneyToPay = selectedBalcony.councilorsPrice(selectedPoliticCards);
		
		if (moneyToPay > getPlayer().getProsperityDisk().getSlot().getCoordinate()){
			return false;
		}
		
		else {
		getPlayer().moveProsperityDisk(-moneyToPay);
		getPlayer().takePermissionCard(selectedPermissionCard);
		selectedPermissionCard.useBonuses(getPlayer());
		getPlayer().returnPoliticCards(selectedPoliticCards);
		removeFromPlayer(this);
		getPlayer().getGame().getGameState().update();
		return true;
		}
	}
}