package it.polimi.ingsw.cg30.view.cli;

public class DeckModel extends PrintableModel{
	/**
	 * the model width
	 */
	int MODEL_WIDTH=9;
	/**
	 * this constructor create a deck model that show
	 * a label containing the name of the deck and a number
	 * representing the cards which are in the deck
	 * @param numberOfCards the number of cards in the deck
	 * @param label the label of the deck
	 */
	public DeckModel(int numberOfCards, String label) {
		
		dataToModelize.add("");
		dataToModelize.add("");
		dataToModelize.add(label);
		dataToModelize.add("Cards");
		dataToModelize.add(String.valueOf(numberOfCards));
		dataToModelize.add("");
		dataToModelize.add("");
		
		model = new Model(dataToModelize, 0, MODEL_WIDTH, true);
	}
}