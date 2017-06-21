package it.polimi.ingsw.cg30.model.actions.market;

import java.util.ArrayList;

import it.polimi.ingsw.cg30.model.Assistant;
import it.polimi.ingsw.cg30.model.PermissionCard;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.PoliticCard;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.util.Util;

public class BuyAction extends Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 164525274961581339L;

	private ArrayList<Thing> thingsToBuy = new ArrayList<>();

	/**
	 * player gets the thing he has selected from the
	 * arraylist AllThingsForSale and transaction is performed.
	 * Action removed+update
	 * 
	 * 
	 * @return false if: the array player has selected is not contained in the 
	 * things which have been put up for sale|| player has not enough money||
	 * 
	 */
	@Override
	public Boolean actionPerformed() {
		// check in the client if the player has enough money to buy every
		// single item that this action receive
		// only to make this action less annoing... here the check are obviusly
		// present
		int totalPrice = 0;

		
		if (!Util.aIsCointainedInB( thingsToBuy,getPlayer().getGame().getGameBoard().getAllThingsForSale()))
			return false;

		// se i prezzi tra client e server sono stati truccati il confronto
		// sopra se ne accorge e torna false giusto?
		for (Thing thing : thingsToBuy) {
			totalPrice += thing.getPrice();
		}

		if (getPlayer().getProsperityDisk().getSlot().getCoordinate() < totalPrice)
			return false;
		else {
			for (Thing thing : thingsToBuy) {
				transaction(getPlayer(), thing);
			}
			removeFromPlayer(this);
			getPlayer().getGame().getGameState().update();
			return true;
		}
	}
	/**
	 * remove sold thing from owner and gives it 
	 * to the buyer 
	 * 
	 * 
	 * 
	 * @param buyer
	 * @param object
	 */
	private void transaction(Player buyer, Thing object) {
		Player owner = null;
		for (Player player : buyer.getGame().getPlayers()) {
			if (player.getPlayerNumber() == object.getOwnerNumber()) {
				owner = player;
				break;
			}
		}
		if (owner != null) {
			if (object.getThing() instanceof Assistant) {
				owner.removeAssistants(1);
				buyer.takeAssistant();
				getPlayer().getGame().getGameBoard().getAllThingsForSale().remove(object);
			} else if (object.getThing() instanceof PoliticCard) {
				buyer.getPoliticCards().add((PoliticCard) object.getThing());
				owner.getPoliticCards().remove(object.getThing());
				getPlayer().getGame().getGameBoard().getAllThingsForSale().remove(object);
			} else if (object.getThing() instanceof PermissionCard) {
				buyer.getPermissionCards().add((PermissionCard) object.getThing());
				owner.getPermissionCards().remove(object.getThing());
				getPlayer().getGame().getGameBoard().getAllThingsForSale().remove(object);
			}

			owner.moveProsperityDisk(object.getPrice());
			buyer.moveProsperityDisk(-object.getPrice());
		}
	}

	/**
	 * 
	 * @param thing
	 *            player wants to buy
	 * @throws NullPointerException
	 *             if thing is null
	 */
	public void addThingToBuy(Thing thing) {

		if (thing == null)
			throw new NullPointerException();

		thingsToBuy.add(thing);
	}
}