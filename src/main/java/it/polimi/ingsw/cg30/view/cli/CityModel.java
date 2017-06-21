package it.polimi.ingsw.cg30.view.cli;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg30.model.Emporium;
import it.polimi.ingsw.cg30.model.bonus.Bonus;

public class CityModel {
	/**
	 * the list of the draw strings
	 */
	private ArrayList<String> draw = new ArrayList<>();
	/**
	 * the city name
	 */
	private String name;
	/**
	 * the city colot
	 */
	private String color;
	/**
	 * the first bonus name on the city
	 */
	private String bonus1Name;
	/**
	 * the second bonus name on the city
	 */
	private String bonus2Name;
	/**
	 * the number of the city
	 */
	private int number;
	/**
	 * an empty line used to print empty rows
	 */
	private String emptyLine ="                      ";
	/**
	 * city model height
	 */
	private int MODEL_HEIGHT = 11;
	/**
	 * city model width
	 */
	private int MODEL_WIDTH = 14;
	/**
	 * this constructor create a new city model containing the following parameters
	 * @param cityname
	 * @param citycolor
	 * @param number
	 * @param bonuses
	 * @param emporiumspace
	 * @param offsets a list of offsets used to calculate if a street has to be printed or void
	 * @param kingHere true if the city has the king on
	 */
	public CityModel(String cityname, String citycolor, int number, List<Bonus> bonuses, 
					 List<Emporium> emporiumspace, List<Integer> offsets, boolean kingHere) {
		
		name = Model.nameBuilder(cityname, MODEL_WIDTH-1);
		color = Model.nameBuilder(citycolor, MODEL_WIDTH);
		
		if (bonuses == null) {
			bonus1Name=Model.nameBuilder("", MODEL_WIDTH);
			bonus2Name=Model.nameBuilder("", MODEL_WIDTH);
		}
		else {
			bonus1Name=Model.nameBuilder(bonuses.get(0).toString()+" "+bonuses.get(0).getNumberOfBonus(), MODEL_WIDTH);
			
			if (bonuses.size() > 1) {
				bonus2Name=Model.nameBuilder(bonuses.get(1).toString()+" "+bonuses.get(1).getNumberOfBonus(), MODEL_WIDTH);
			}
			else bonus2Name=Model.nameBuilder("", MODEL_WIDTH);
		}

		this.number=number;
		
		//calculating offsets
		boolean o1 = false;
		boolean o2 = false;
		boolean v1 = false;
		boolean v2 = false;
		boolean d1 = false;
		boolean d2 = false;
		boolean d3 = false;
		boolean d4 = false;
		
		for (int i = 0;i < offsets.size(); i++) {
			if (offsets.get(i).intValue() == -2 || offsets.get(i).intValue() == -1) {
				o1 = true;
			}
			if (offsets.get(i).intValue() == 2 || offsets.get(i).intValue() == 1) {
				o2 = true;
			}
			if (offsets.get(i).intValue() == -6) {
				v1 = true;
			}
			if (offsets.get(i).intValue() == 6) {
				v2 = true;
			}
			if (offsets.get(i).intValue() == -7) {
				d1 = true;
			}
			if (offsets.get(i).intValue() == -5) {
				d2 = true;
			}
			if (offsets.get(i).intValue() == 5) {
				d3 = true;
			}
			if (offsets.get(i).intValue() == 7) {
				d4 = true;
			}
		}

		Link o1link = new Link(o1, "-");
		Link o2link = new Link(o2, "-");
		Link v1link = new Link(v1, "|");
		Link v2link = new Link(v2, "|");
		Link d1link = new Link(d1, "\\");
		Link d2link = new Link(d2, "/");
		Link d3link = new Link(d3, "/");
		Link d4link = new Link(d4, "\\");

		String player1 = " ";
		String player2 = " ";
		String player3 = " ";
		String player4 = " ";
		String king;
		if (kingHere) {
			king = "K";
		}
		else {
			king = " ";
		}
		
		if (!emporiumspace.isEmpty()) {
			player1 = String.valueOf(emporiumspace.get(0).getPlayerNumber());
		}
		if (emporiumspace.size() > 1) {
			player2 = String.valueOf(emporiumspace.get(1).getPlayerNumber());
		}
		if (emporiumspace.size() > 2) {
			player3 = String.valueOf(emporiumspace.get(2).getPlayerNumber());
		}
		if (emporiumspace.size() > 3) {
			player4 = String.valueOf(emporiumspace.get(3).getPlayerNumber());
		}
		//Create strings of the model
		draw.add(new String(" "+d1link.print()+"        "+v1link.print()+"         "+d2link.print()+" "));
		draw.add(new String("   "+d1link.print()+"______________"+d2link.print()+"   "));
		draw.add(new String("   |"+king+name+"|   "));
		draw.add(new String("   |"+color+"|   "));
		draw.add(new String(o1link.print()+o1link.print()+o1link.print()+"|   Emporiums: |"+o2link.print()+o2link.print()+o2link.print()));
		draw.add(new String("   | ["+player1+"]["+player2+"]["+player3+"]["+player4+"] |   "));
		draw.add(new String("   |"+bonus1Name+"|   "));
		draw.add(new String("   |"+bonus2Name+"|   "));
		draw.add(new String("   |______________|   "));
		draw.add(new String("   "+d3link.print()+"              "+d4link.print()+"   "));
		draw.add(new String(" "+d3link.print()+"        "+v2link.print()+"         "+d4link.print()+" "));
	}
	/**
	 * this constructor is used to create an empty city model with streets that link
	 * the previous and the next cities if they are linked
	 * @param number
	 * @param previousAndThisLinked boolean true if they are linked
	 * @param thisAndNextLinked boolean true if they are linked
	 */
	public CityModel(int number, boolean previousAndThisLinked, boolean thisAndNextLinked) {
		this.number=number;
		
		Link o1 = new Link(previousAndThisLinked, "-");
		Link o2 = new Link(thisAndNextLinked, "-");
	
		String line5 =o1.print()+o1.print()+o1.print()+
					  o1.print()+o1.print()+o1.print()+
					  o1.print()+o1.print()+o1.print()+
					  o1.print()+
					  o2.print()+o2.print()+o2.print()+
					  o2.print()+o2.print()+o2.print()+
					  o2.print()+o2.print()+o2.print()+
					  o2.print()+o2.print()+o2.print();
		
		for (int i = 0; i < MODEL_HEIGHT; i++) {
			if (i == 4) {
				draw.add(line5);
			}
			else draw.add(emptyLine);
		}
	}
	/**
	 * this constructor is used to create an empty city model
	 * that is useful to have right prints where a space is empty
	 * @param number the number on the grid of cities that is empty
	 */
	public CityModel(int number) {
		this.number = number;

		for (int i = 0; i < MODEL_HEIGHT; i++) {
			draw.add(emptyLine);
		}
	}
	/**
	 * @return the draw
	 */
	public List<String> getDraw() {
		return this.draw;
	}
	/**
	 * @return the number of the citymodel
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * @param line the line to print
	 * @return the line corresponding to the line number requested
	 */
	public String print(int line) {
		return this.draw.get(line);
	}
}