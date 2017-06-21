package it.polimi.ingsw.cg30.model.bonus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.polimi.ingsw.cg30.model.Player;

@XmlRootElement(name = "main_action_bonus")
@XmlAccessorType (XmlAccessType.FIELD)
public class MainActionBonus extends Bonus {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 6478431250148665644L;

	/**
	 * void constructor
	 */
	public MainActionBonus() {
		//used by jax b
	}

	/**
	 * constructor with NumberOfBonus
	 */
	public MainActionBonus(int numberOfBonus) {
		super(numberOfBonus);
	}
	
	/**
	 * see {@link Bonus}
	 */
	@Override
	public void giveBonusToPlayer(Player player){	
		player.mainActionFiller();
	}
	/**
	 * @return "M. Action"
	 */
	@Override
	public String toString() {
		return "M. Action";
	}
}