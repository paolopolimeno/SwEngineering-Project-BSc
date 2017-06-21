package it.polimi.ingsw.cg30.view.cli;

import java.util.ArrayList;
import java.util.List;

public class Model {
	/**
	 * if the model has borders or not
	 */
	boolean borders;
	/**
	 * the model height
	 */
	int MODEL_HEIGHT;
	/**
	 * the model width
	 */
	int MODEL_WIDTH;
	/**
	 * the model strings 
	 */
	List<String> draw = new ArrayList<>();
	/**
	 * this constructor create a model based on the strings given in
	 * data to modelize list with and height and a width according to
	 * the int parameters of width and heght. The borders boolean is true
	 * if you want that the model has borders.
	 * @param dataToModelize
	 * @param height
	 * @param width
	 * @param borders
	 */
	public Model(List<String> dataToModelize,int height, int width, boolean borders) {
		this.borders=borders;
		if (dataToModelize != null) {
			this.MODEL_HEIGHT=dataToModelize.size();
		}
		else {
			this.MODEL_HEIGHT=height;
			dataToModelize = new ArrayList<>();
			for (int i = 0; i < MODEL_HEIGHT; i++){
				dataToModelize.add("");
			}
		}
		this.MODEL_WIDTH=width;
		
		Link o = new Link(borders, "_");
		Link v = new Link(borders, "|");
		
		StringBuilder topBorder = new StringBuilder();
		StringBuilder bottomBorder = new StringBuilder();
		topBorder.append(" ");
		bottomBorder.append(v.print());
		for (int i = 0; i < MODEL_WIDTH; i++){
			topBorder.append(o.print());
			bottomBorder.append(o.print());
		}
		topBorder.append(" ");
		bottomBorder.append(v.print());

		draw.add(topBorder.toString());
		
		for (int i = 0; i < MODEL_HEIGHT; i++){
			if (("SEPARATOR").equals(dataToModelize.get(i))){
				draw.add(v.print()+lineSeparator(MODEL_WIDTH)+v.print());
			}
			else draw.add(new String(v.print()+nameBuilder(dataToModelize.get(i), MODEL_WIDTH)+v.print()));
		}
		draw.add(bottomBorder.toString());
	}
	/**
	 * this method return a balanced string containing the string 
	 * passed and with a bunch of spaces added to make sure that the
	 * string length equals model width
	 * @param string
	 * @param MODEL_WIDTH
	 * @return
	 */
	public static String nameBuilder(String string, int MODEL_WIDTH) {
		int numberOfSpaces=(MODEL_WIDTH-string.length())/2;
		StringBuilder stringToReturn = new StringBuilder();
		
		for (int i=0; i<numberOfSpaces; i++) {
			stringToReturn.append(" ");
		}
		stringToReturn.append(string);
				    
		if ((string.length())%2 !=0 && string.length()<MODEL_WIDTH) {
			stringToReturn.append(" ");
		}		   
		for (int i=stringToReturn.length(); i<MODEL_WIDTH; i++) {
			stringToReturn.append(" ");
		}
		return stringToReturn.toString();
	}
	/**
	 * this method create a row of "-" of the model width
	 * lenght and return this separator as a string
	 * @param MODEL_WIDTH
	 * @return
	 */
	public String lineSeparator(int MODEL_WIDTH){
		StringBuilder separator = new StringBuilder();
		for (int i = 0; i < MODEL_WIDTH; i++){
			separator.append("-");
		}
		return separator.toString();
	}
	/**
	 * @return the draw strings
	 */
	public ArrayList<String> getDraw() {
		return (ArrayList<String>) draw;
	}
	/**
	 * @param string add a row to the model
	 */
	public void addRow(String string){
		draw.add(string);
	}
	/**
	 * @return the width plus the number of char used to 
	 * make the square around the model
	 */
	public int getWidth(){
		return MODEL_WIDTH+2;
	}
}