package it.polimi.ingsw.cg30.model.bonus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.actions.RepeatPermissionCardAction;

@XmlRootElement(name = "repeat_permission_card_bonus")
@XmlAccessorType(XmlAccessType.FIELD)
public class RepeatPermissionCardBonus extends Bonus{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3128450392073706576L;

	public RepeatPermissionCardBonus() {
		//used by jax b
	}
	/**
	 * the constructor initializes the number of bonus
	 * @param numberOfBonus
	 */
	public RepeatPermissionCardBonus(int numberOfBonus) {
		super(numberOfBonus);
	}
	/**
	 * gives a repeat permission card bonus to the player
	 */
	@Override
	public void giveBonusToPlayer(Player player) {	
		player.addPossibleActions(new RepeatPermissionCardAction(getNumberOfBonus(),null));
	}
	@Override
	public String toString(){
		return "Perm bonus";
	}
}
