package it.polimi.ingsw.cg30.model;

import java.io.Serializable;

public class Counselor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3235642450104241949L;
	private String color;

	/**
	 * 
	 * @param color
	 *            of the city
	 * @throws NullPointerException
	 *             if the color is null
	 */
	public Counselor(String color) {

		if (color == null)
			throw new NullPointerException();

		this.color = color;
	}

	/**
	 * @return the color of the counselor as string
	 */
	public String getColor() {
		return color;
	}
}