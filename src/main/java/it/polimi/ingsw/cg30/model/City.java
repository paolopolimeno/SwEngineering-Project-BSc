package it.polimi.ingsw.cg30.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polimi.ingsw.cg30.model.bonus.Bonus;
import it.polimi.ingsw.cg30.model.util.Edge;

@XmlRootElement(name = "city")
@XmlAccessorType(XmlAccessType.FIELD)
public class City implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6837219626696855682L;
	@XmlAttribute(name = "name")
	private String name;
	@XmlAttribute(name = "color")
	private String color;
	@XmlAttribute(name = "number")
	private int number;
	private List<Emporium> emporiumsSpace = new ArrayList<>();
	private List<Bonus> cityBonuses = new ArrayList<>();

	private transient List<City> emporiumCities;
	private transient SimpleGraph<City, DefaultEdge> newGraph;
	private transient List<Edge> edges;
	private List<City> linkedCities = new ArrayList<>();
	private transient List<City> linkedCitiesForBonus;
	/**
	 * this constructor is used by JAXB to initialize a city
	 * loaded from file directly with the file parameters
	 */
	public City() {
	}

	/**
	 * gives the bonuses contained in the rewardtoken of the city and all the
	 * others bonus of the linked cities
	 * 
	 * @param player
	 *            the player that has to receive the bonuses
	 * @throws NullPointerException
	 *             if the player or sourcecity are null
	 */
	public void giveRewardToPlayer(Player player, City sourceCity) {

		if (player == null || sourceCity == null)
			throw new NullPointerException();

		emporiumCities = new ArrayList<>();
		newGraph = new SimpleGraph<>(DefaultEdge.class);
		edges = new ArrayList<>();
		linkedCitiesForBonus = new ArrayList<>();

		emporiumCities = player.getGame().getGameBoard().getGraph().getAllEmporiumCities(player.getColor(),
				player.getGame().getGameBoard().getCities());
		edges =	player.getGame().getGameBoard().getEdges();

		newGraph = player.getGame().getGameBoard().getGraph().buildGraph(emporiumCities, edges, newGraph);
		linkedCitiesForBonus = player.getGame().getGameBoard().getGraph().getAllReachableCities(newGraph, sourceCity);

		for (City city : linkedCitiesForBonus) {
			for (Bonus bonus : city.getCityBonuses()) {
				bonus.giveBonusToPlayer(player);
			}
		}
	}

	/**
	 * 
	 * @return a string with the first letter of the city name
	 */
	public String getInitLetter() {
		return getName().substring(0, 1).toLowerCase();
	}

	/**
	 * 
	 * @return the color of the city as String
	 */
	public String getCityColor() {
		return color;
	}

	/**
	 * 
	 * @return an arraylist of all the bonuses of the city
	 */
	public List<Bonus> getCityBonuses() {
		return cityBonuses;
	}

	/**
	 * 
	 * @return a string with the name of the city
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @return the number of the city
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * 
	 * @return the emporium space of the city as an arraylist of emporiums
	 */
	public List<Emporium> getEmporiumsSpace() {
		return this.emporiumsSpace;
	}

	/**
	 * 
	 * @param playerEmporium
	 *            (first emporium of the player)
	 * @throws NullPointerException
	 *             if the emporium is null
	 */
	public void buildEmporium(Emporium playerEmporium) {

		if (playerEmporium == null)
			throw new NullPointerException();

		emporiumsSpace.add(playerEmporium);
	}

	/**
	 * 
	 * @param player
	 * @return true if the player can build an emporium in the city. it JUST check NUMBER, NOT COLOR
	 * @throws NullPointerException
	 *             if the player is null
	 */
	public boolean canBuildEmporium(Player player) {

		if (player == null)
			throw new NullPointerException();

		for (Emporium emporium : emporiumsSpace) {
			if (emporium.getPlayerNumber() == player.getPlayerNumber())
				return false;
		}
		return true;
	}
	
	/**adds city passed to the List of 
	 * 	linked cities
 
	 * @param city to be added
	 
	 * 
	 */
	public void addLinkedCity(City city) {
		linkedCities.add(city);
	}

	/**
	 * 
	 * @return all the linked cities to this city
	 */
	public List<City> getLinkedCities() {
		return linkedCities;
	}
}