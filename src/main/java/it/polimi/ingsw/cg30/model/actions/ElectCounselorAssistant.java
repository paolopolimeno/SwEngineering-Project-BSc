package it.polimi.ingsw.cg30.model.actions;

import java.util.List;

import it.polimi.ingsw.cg30.model.Balcony;
import it.polimi.ingsw.cg30.model.Counselor;

public class ElectCounselorAssistant extends FastAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4917107461395483290L;
	private Counselor selectedCounselor;
	private Balcony selectedBalcony;
	private String choosenCounselor;
	private String choosenBalcony;
	private List<String> choice;

	/**
	 * constructor
	 * @param choice of the player
	 */
	public ElectCounselorAssistant(List<String> choice) {
		this.choice = choice;
	}

	/**
	 * put selected counselor on selected balcony.
	 * one assistant removed from player.
	 * removes itself from possibelActions and calls update.
	 * @return false if one of choices is not legit||no assistants
	 */
	@Override
	public Boolean actionPerformed() {

		if (getPlayer().getAssistants().isEmpty())
			return false;

		//counselor choice

		choosenCounselor = choice.get(0);
		choosenBalcony = choice.get(1);

		if (!isLegitChoice(choosenCounselor, getPlayer().getGame().getGameBoard().getCounselors().size())
				|| !isLegitChoice(choosenBalcony, getPlayer().getGame().getGameBoard().getBalconies().size()))
			return false;

		else {

			selectedCounselor = getPlayer().getGame().getGameBoard().getCounselors()
					.get(Integer.parseInt(choosenCounselor) - 1);
			//balcony choice
			selectedBalcony = getPlayer().getGame().getGameBoard().getBalconies()
					.get(Integer.parseInt(choosenBalcony) - 1);
			selectedBalcony.addCounselor(selectedCounselor);
			getPlayer().removeAssistants(1);
			removeFromPlayer(this);
			getPlayer().getGame().getGameState().update();
			return true;
		}
	}
}