package it.polimi.ingsw.cg30.view.cli;

import it.polimi.ingsw.cg30.model.change.GameBoardChange;

public class NobilitySlotModel extends PrintableModel {
	/**
	 * the model width
	 */
	int MODEL_WIDTH = 10;

	/**
	 * this constructor create a nobility slot model of the i slot of the track
	 * 
	 * @param gameBoard
	 *            the gameboard that contains the model to represent
	 * @param i
	 *            the number of the slot to represent
	 */
	public NobilitySlotModel(GameBoardChange gameBoard, int i) {

		dataToModelize.add(String.valueOf(i));
		dataToModelize.add("SEPARATOR");

		if (gameBoard.getNobilityTrack().get(i).getBonuses() != null) {
			dataToModelize.add("Bonus");
			dataToModelize.add(gameBoard.getNobilityTrack().get(i).getBonuses().get(0).toString());
			dataToModelize
					.add(String.valueOf(gameBoard.getNobilityTrack().get(i).getBonuses().get(0).getNumberOfBonus()));
		} else {
			dataToModelize.add("");
			dataToModelize.add("");
			dataToModelize.add("");
		}
		if (gameBoard.getNobilityTrack().get(i).getBonuses() != null
				&& gameBoard.getNobilityTrack().get(i).getBonuses().size() > 1) {
			dataToModelize.add(gameBoard.getNobilityTrack().get(i).getBonuses().get(1).toString());
			dataToModelize
					.add(String.valueOf(gameBoard.getNobilityTrack().get(i).getBonuses().get(1).getNumberOfBonus()));
		} else {
			dataToModelize.add("");
			dataToModelize.add("");
		}
		dataToModelize.add("SEPARATOR");
		// players number assign
		String player1 = " ";
		String player2 = " ";
		String player3 = " ";
		String player4 = " ";
		if (0 < gameBoard.getNobilityTrackCoordinate().size() && i == gameBoard.getNobilityTrackCoordinate().get(1)) {
			player1 = "1";
		}
		if (gameBoard.getNobilityTrackCoordinate().size() > 1 && i == gameBoard.getNobilityTrackCoordinate().get(2)) {
			player2 = "2";
		}
		if (gameBoard.getNobilityTrackCoordinate().size() > 2 && i == gameBoard.getNobilityTrackCoordinate().get(3)) {
			player3 = "3";
		}
		if (gameBoard.getNobilityTrackCoordinate().size() > 3 && i == gameBoard.getNobilityTrackCoordinate().get(4)) {
			player4 = "4";
		}
		dataToModelize.add(player1 + " " + player2 + " " + player3 + " " + player4);

		model = new Model(dataToModelize, 0, MODEL_WIDTH, true);
	}
}