package it.polimi.ingsw.cg30.model.actions;

import java.util.List;

import it.polimi.ingsw.cg30.model.Balcony;
import it.polimi.ingsw.cg30.model.Counselor;

public class ElectCounselor extends MainAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3442364711629940947L;
	private Counselor selectedCounselor = null;
	private Balcony selectedBalcony = null;
	private String choosenCounselor;
	private String choosenBalcony;
	private List<String> choice;

	/**
	 * constructor
	 * @param choice of the player
	 */
	public ElectCounselor(List<String> choice) {
		this.choice = choice;
	}
	/**
	 * put selected counselor on selected balcony.
	 * gives four coins to player.
	 * removes itself from possibleActions and calls update.
	 * 
	 * @return false if:one of choices is not legit
	 * 
	 */
	@Override
	public Boolean actionPerformed() {

		// scelta del consigliere

		choosenCounselor = choice.get(0);
		choosenBalcony = choice.get(1);

		if (!isLegitChoice(choosenCounselor, getPlayer().getGame().getGameBoard().getCounselors().size())
				|| !isLegitChoice(choosenBalcony, getPlayer().getGame().getGameBoard().getBalconies().size()))
			return false;

		else {

			selectedCounselor = getPlayer().getGame().getGameBoard().getCounselors()
					.get(Integer.parseInt(choosenCounselor) - 1);
			// scelta del balcone
			selectedBalcony = getPlayer().getGame().getGameBoard().getBalconies()
					.get(Integer.parseInt(choosenBalcony) - 1);
			selectedBalcony.addCounselor(selectedCounselor);
			getPlayer().moveProsperityDisk(4);

			removeFromPlayer(this);
			getPlayer().getGame().getGameState().update();
			
			return true;
		}
	}
}