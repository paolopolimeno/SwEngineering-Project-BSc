package it.polimi.ingsw.cg30.model.bonus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.polimi.ingsw.cg30.model.Player;

@XmlRootElement(name = "prosperity_bonus")
@XmlAccessorType (XmlAccessType.FIELD)
public class ProsperityBonus extends Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3070452269636205498L;

	public ProsperityBonus() {
		//used by jax b
	}

	/**
	 * 
	 * see {@link Bonus}
	 */
	public ProsperityBonus(int numberOfBonus) {
		super(numberOfBonus);
	}

	/**
	 * see {@link Bonus}
	 */
	@Override
	public void giveBonusToPlayer(Player player) {
		player.moveProsperityDisk(getNumberOfBonus());
	}
	
	@Override
	public String toString() {
		return "Prosper.";
	}
}