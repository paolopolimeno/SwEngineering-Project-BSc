package it.polimi.ingsw.cg30.view.cli;

import it.polimi.ingsw.cg30.model.PermissionCard;

public class PermissionCardModel extends PrintableModel {
	/**
	 * the model width
	 */
	int MODEL_WIDTH = 9;
	/**
	 * the model height
	 */
	int MODEL_HEIGHT = 7;
	/**
	 * this constructor creates a permission card model
	 * based on the permission card given as parameter
	 * @param card
	 */
	public PermissionCardModel(PermissionCard card) {

		if (card != null) {

			dataToModelize.add("");
			String letters = new String();
			for (String letter : card.getLetters()) {
				letters += letter + " ";
			}
			dataToModelize.add(letters);
			dataToModelize.add("Bonus");
			if (card.getBonuses() != null) {
				dataToModelize.add(card.getBonuses().get(0).toString());
				dataToModelize.add(String.valueOf(card.getBonuses().get(0).getNumberOfBonus()));
			} else {
				dataToModelize.add("");
				dataToModelize.add("");
			}
			if (card.getBonuses() != null && card.getBonuses().size() > 1) {
				dataToModelize.add(card.getBonuses().get(1).toString());
				dataToModelize.add(String.valueOf(card.getBonuses().get(1).getNumberOfBonus()));
			} else {
				dataToModelize.add("");
				dataToModelize.add("");
			}

			model = new Model(dataToModelize, 0, MODEL_WIDTH, true);
		}
		else model = new Model(null, MODEL_HEIGHT, MODEL_WIDTH, false);
	}
}