package it.polimi.ingsw.cg30.model.actions;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg30.model.Balcony;
import it.polimi.ingsw.cg30.model.City;
import it.polimi.ingsw.cg30.model.Emporium;
import it.polimi.ingsw.cg30.model.PoliticCard;
import it.polimi.ingsw.cg30.model.util.Util;

public class BuildEmporiumKing extends MainAction {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -6994474493087392081L;
	/**
	 * selected city
	 */
	private City selectedCity;
	/**
	 * emporium to build
	 */
	private Emporium emporiumToBuild;
	/**
	 * kingBalcony
	 */
	private Balcony kingBalcony;
	/**
	 * selected politic cards
	 */
	private ArrayList<PoliticCard> selectedPoliticCards;
	/**
	 * choosen city
	 */
	private String choosenCity;
	/**
	 * money calculated to move the 
	 * king
	 */
	private int moneyToPayKing;
	/**
	 * money to pay to satisfy the king
	 */
	private int moneyToPay;
	/**
	 * arraylist of strings passed
	 */
	private List<String> choice;
	/**
	 * token used for parsing
	 */
	private int token;

	/**
	 * constructor
	 * @param choice of the player
	 */
	public BuildEmporiumKing(List<String> choice) {
		this.choice = choice;
	}
	/**
	 * performs the action if conditions are satisfied:
	 * 
	 * removes money, politic cards and assistants from player.
	 * money is calculated based on cards and on distance travelled
	 * by king.
	 * emporium is built, player gets rewards and king is moved.
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @return false if: one of the choice not legit||choice contains duplicate cards||
	 * politic cards are empty||player already built emporium on city|| player's assistants less
	 * than number of emporium on city|| cards not matching|| not enough money|| no emporiums
	 */
	@Override
	public Boolean actionPerformed() {

		selectedPoliticCards = new ArrayList<>();
		token = 0;

		for (int i=0; i < 4; i++){
			
			if(("END").equals(choice.get(i))){
				token = i;
				break;
			}
			if (!isLegitChoice(choice.get(i), getPlayer().getPoliticCards().size())) {
				return false;
			}
			else selectedPoliticCards.add(getPlayer().getPoliticCards().get(Integer.parseInt(choice.get(i)) - 1));
		}
		if (selectedPoliticCards.isEmpty()) {
			return false;
		}
		if (token == 0) {
			token = 3;
		}
		if(Util.containsDuplicate(new ArrayList<>(choice.subList(0,token+1)))){
			return false;
		}

		kingBalcony = getPlayer().getGame().getGameBoard().getKingBalcony();
		choosenCity = choice.get(token+1);
		
		if (!isLegitChoice(choosenCity, getPlayer().getGame().getGameBoard().getCities().size())) {
			return false;
		}
		
		selectedCity = getPlayer().getGame().getGameBoard().getCities().get(Integer.parseInt(choosenCity) - 1);
		
		moneyToPayKing = moneyToPayForMoveKing(selectedCity);
		
		if (!selectedCity.canBuildEmporium(getPlayer())){
			return false;
		}
		if (getPlayer().getAssistants().size() < selectedCity.getEmporiumsSpace().size()){
			return false;
		}
		
		if (!kingBalcony.matchBalconyWithCards(selectedPoliticCards)){
			return false;
		}
		
		moneyToPay = kingBalcony.councilorsPrice(selectedPoliticCards);
		
		if ((moneyToPay+moneyToPayKing) > getPlayer().getProsperityDisk().getSlot().getCoordinate()){
			return false;
		}
		if (getPlayer().getEmporiums().isEmpty()){
			return false;
		}
		
		else {
			getPlayer().moveProsperityDisk(-(moneyToPay + moneyToPayKing));
			getPlayer().removeAssistants(selectedCity.getEmporiumsSpace().size());

			emporiumToBuild = getPlayer().getEmporiums().get(0);
			selectedCity.buildEmporium(emporiumToBuild);
			getPlayer().getEmporiums().remove(0);
			getPlayer().returnPoliticCards(selectedPoliticCards);
			
			getPlayer().getGame().getGameBoard().citiesBuildBonus(getPlayer());
			selectedCity.giveRewardToPlayer(getPlayer(), selectedCity);
			
			getPlayer().getGame().getGameBoard().getKing().setLocationCity(selectedCity);
			removeFromPlayer(this);
			getPlayer().getGame().getGameState().update();
			return true;
		}
	}
	
	/**
	 * 
	 * @param city
	 * @return the money to pay to move the king
	 */
	public int moneyToPayForMoveKing(City city){
		
		int moneyToPayTmp = getPlayer().getGame().getGameBoard().getGraph().getMoneyToPay(getPlayer().getGame().getGameBoard().getKing().getLocationCity(), city);
		return moneyToPayTmp*2;
	}
}