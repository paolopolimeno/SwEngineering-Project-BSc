package it.polimi.ingsw.cg30.model.util;

import it.polimi.ingsw.cg30.model.City;

public class Edge{
	/**
	 * the first city of the edge
	 */
	private City city;
	/**
	 * the second city of the edge
	 */
	private City adjacentCity;
	/**
	 * the constructor initializes the edge setting the source and the target
	 * @param source city
	 * @param target city
	 */
	public Edge(City city, City adjacentCity) {
		this.city = city;
		this.adjacentCity = adjacentCity;
	}

	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}

	/**
	 * @return the adjacent city
	 */
	public City getAdjacentCity() {
		return adjacentCity;
	}

}