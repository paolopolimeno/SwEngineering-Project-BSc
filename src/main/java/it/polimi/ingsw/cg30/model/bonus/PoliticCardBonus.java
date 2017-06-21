package it.polimi.ingsw.cg30.model.bonus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.polimi.ingsw.cg30.model.Player;

@XmlRootElement(name = "politic_card_bonus")
@XmlAccessorType (XmlAccessType.FIELD)
public class PoliticCardBonus extends Bonus {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5428541182421945934L;

	public PoliticCardBonus() {	
		//used by jax b
	}

	/**
	 * 
	 * see {@link Bonus}
	 */
	public PoliticCardBonus(int numberOfBonus) {
		super(numberOfBonus);
	}

	/**
	 * see {@link Bonus}
	 */
	@Override
	public void giveBonusToPlayer(Player player) {
			player.takePoliticCards(this.getNumberOfBonus());
	}
	
	@Override
	public String toString() {
		return "Politic";
	}
}