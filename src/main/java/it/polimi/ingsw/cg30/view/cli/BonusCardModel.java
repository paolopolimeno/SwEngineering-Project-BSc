package it.polimi.ingsw.cg30.view.cli;

import java.util.ArrayList;

import it.polimi.ingsw.cg30.model.bonus.Bonus;

public class BonusCardModel {
	/**
	 * array list of string to modelize in the model
	 */
	private ArrayList<String> dataToModelize = new ArrayList<>();
	/**
	 * the model
	 */
	private Model model;
	/**
	 * model width
	 */
	private int MODEL_WIDTH = 6;
	
	public BonusCardModel(String name, Bonus bonus) {
		dataToModelize.add(name);
		dataToModelize.add(String.valueOf(bonus.getNumberOfBonus()));
		model = new Model(dataToModelize, 0, MODEL_WIDTH, true);
	}
	/**
	 * this metod return the string to print at line
	 * number given
	 * @param line the line number to print
	 * @return the string to print
	 */
	public String print(int line) {
		return model.getDraw().get(line);
	}
}