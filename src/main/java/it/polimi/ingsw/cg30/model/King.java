package it.polimi.ingsw.cg30.model;

import java.io.Serializable;

public class King implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5933315430576774132L;
	private City locationCity;

	/**
	 * 
	 * @param city
	 * @throws NullPointerException
	 *             if the city is null
	 */
	public King(City city) {

		if (city == null)
			throw new NullPointerException();

		this.locationCity = city;
	}

	/**
	 * @return the locationCity
	 */
	public City getLocationCity() {
		return locationCity;
	}

	/**
	 * @param locationCity
	 *            the locationCity to set
	 */
	public void setLocationCity(City locationCity) {
		this.locationCity = locationCity;
	}
}