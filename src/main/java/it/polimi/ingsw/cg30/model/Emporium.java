package it.polimi.ingsw.cg30.model;

import java.io.Serializable;

public class Emporium implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5125356535191075823L;
	private final String color;
	private int playerNumber;
	
	
	/**
	 * 
	 * @param color of the emporium
	 * @param playerNumber 
	 * @throws IllegalArgumentException if the number of the player is negative or 0
	 */
	public Emporium(String color, int playerNumber){
		
		if(playerNumber <=0)
			throw new IllegalArgumentException();
		
		this.color = color;
		this.playerNumber = playerNumber;
	}
	/**
	 * 
	 * @return the color of the emporium as String
	 */
	public String getColor() {
		return this.color;
	}
	/**
	 * 
	 * @return the player number of the player who owns the emporium
	 */
	public int getPlayerNumber() {
		return this.playerNumber;
	}
}