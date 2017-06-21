package it.polimi.ingsw.cg30.model.bonus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.polimi.ingsw.cg30.model.Player;

@XmlRootElement(name = "points_bonus")
@XmlAccessorType (XmlAccessType.FIELD)
public class PointsBonus extends Bonus {
	
	/**
	 * UID
	 */
	private static final long serialVersionUID = 189557844317872348L;
/**
 * void constructor
 */
	public PointsBonus() {	
		//used by jax b
	}

	/**
	 * 
	 * constructor with numberOfBonus
	 * 	 */
	public PointsBonus(int numberOfBonus) {
		super(numberOfBonus);
	}

	/**
	 * moves points disk of NumberOfBonus forward
	 * 
	 */
	@Override
	public void giveBonusToPlayer(Player player) {
		player.movePointsDisk(this.getNumberOfBonus());
	}
	/**
	 * return "Points"
	 */
	@Override
	public String toString() {
		return "Points";
	}
}