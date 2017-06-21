package it.polimi.ingsw.cg30.model.actions.market;

import java.util.ArrayList;

import it.polimi.ingsw.cg30.model.Assistant;
import it.polimi.ingsw.cg30.model.PermissionCard;
import it.polimi.ingsw.cg30.model.PoliticCard;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.util.Util;

public class SellAction extends Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8106403427770773192L;

	private ArrayList<Thing> sellableThings = new ArrayList<>();
	
	/**
	 * if player has set a zero or negative price
	 * the price is set to 1.
	 * adds the selected things to the gameboard list named
	 * AllThingsForSale.
	 * action is removed+update
	 * 
	 * 
	 * @return false if player has not one of the things
	 * he wants to sell
	 */
	@Override
	public Boolean actionPerformed() {
		
		ArrayList<Assistant> assistantsForSale = new ArrayList<>();
		ArrayList<PoliticCard> politicCardsforSale = new ArrayList<>();
		ArrayList<PermissionCard> permissionCardsForSale = new ArrayList<>();
		//controllo che il player abbia gli oggetti davvero
		for (Thing thing : sellableThings) {
			if (thing.getPrice()<0) 
				thing.setPrice(1);
			if (thing.getThing() instanceof Assistant) {
				assistantsForSale.add((Assistant) thing.getThing());
			}
			else if (thing.getThing() instanceof PoliticCard){
				politicCardsforSale.add((PoliticCard) thing.getThing());
			}
			else if (thing.getThing() instanceof PermissionCard) {
				permissionCardsForSale.add((PermissionCard) thing.getThing());
			}
			else return false;
		}
		if (!(assistantsForSale.size() <= getPlayer().getAssistants().size())){
			return false;
		}
		if (!Util.aIsCointainedInB(politicCardsforSale, getPlayer().getPoliticCards())){
			return false;
		}
		if (!Util.aIsCointainedInB(permissionCardsForSale, getPlayer().getPermissionCards())){
			return false;
		}
		//setto gli attributi player degli oggetti
		sellableThings.forEach(st -> st.setPlayer(getPlayer()));
		//li metto nella gameboard
		getPlayer().getGame().getGameBoard().addThingsForSale(sellableThings);
		removeFromPlayer(this);
		getPlayer().getGame().getGameState().update();
		return true;
	}

	/**
	 * 
	 * @param thing player wants to sell
	 * @throws NullPointerException if thing is null
	 */
	public void addThingforSale(Thing thing) {
		
		if(thing == null)
			throw new NullPointerException();
		
		sellableThings.add(thing);
	}
}