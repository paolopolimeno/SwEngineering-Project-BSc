package it.polimi.ingsw.cg30.view.cli;

import it.polimi.ingsw.cg30.model.PoliticCard;

public class PoliticCardModel extends PrintableModel{
	/**
	 * the model width
	 */
	int MODEL_WIDTH=9;
	/**
	 * this constructor create a politic card model
	 * based on the politic card given as parameter
	 * @param card
	 */
	public PoliticCardModel(PoliticCard card) {
		
		dataToModelize.add("Politic");
		dataToModelize.add("");
		dataToModelize.add("");
		dataToModelize.add(card.getColor());
		dataToModelize.add("");
		
		model = new Model(dataToModelize, 0, MODEL_WIDTH, true);
	}
}