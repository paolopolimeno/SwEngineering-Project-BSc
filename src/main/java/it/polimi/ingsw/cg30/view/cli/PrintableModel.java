package it.polimi.ingsw.cg30.view.cli;

import java.util.ArrayList;

public class PrintableModel {
	/**
	 * the model to print
	 */
	protected Model model;
	/**
	 * the data to modelize
	 */
	protected ArrayList<String> dataToModelize = new ArrayList<>();
	/**
	 * @return the height of the model
	 */
	public int getHeight(){
		return model.getDraw().size();
	}
	/**
	 * return the width of the model
	 * @return
	 */
	public int getWidth(){
		return model.getWidth();
	}
	/**
	 * return the string at i position in data to modelize
	 * @param line
	 * @return
	 */
	public String print(int line){
		return model.getDraw().get(line);
	}
	/**
	 * add a row to the model
	 * @param string the row to add
	 */
	public void addRow(String string){
		model.addRow(string);
	}
}