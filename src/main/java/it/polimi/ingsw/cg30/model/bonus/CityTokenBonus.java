package it.polimi.ingsw.cg30.model.bonus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.actions.CityTokenAction;

@XmlRootElement(name = "city_token_bonus")
@XmlAccessorType(XmlAccessType.FIELD)
public class CityTokenBonus extends Bonus {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -2048075106154985631L;
	/**
	 * void constructor used by JAXB
	 */
	public CityTokenBonus() {
		//used by jax b
	}
	
	/**
	 * the constructor initializes the number of bonus
	 * @param numberOfBonus
	 */
	public CityTokenBonus(int numberOfBonus) {
		super(numberOfBonus);
	}
	/**
	 * gives a city token bonus to the player
	 */
	@Override
	public void giveBonusToPlayer(Player player) {
		player.addPossibleActions(new CityTokenAction(getNumberOfBonus(),null));
	}
	/**
	 * @return "City Tok"
	 */
	@Override
	public String toString() {
		return "City Tok";
	}
}