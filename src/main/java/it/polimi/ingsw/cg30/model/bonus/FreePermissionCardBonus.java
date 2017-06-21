package it.polimi.ingsw.cg30.model.bonus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.actions.FreePermissionCardAction;

@XmlRootElement(name = "free_permission_card_bonus")
@XmlAccessorType(XmlAccessType.FIELD)
public class FreePermissionCardBonus extends Bonus {
	
	/**
	 * UID
	 */
	private static final long serialVersionUID = 6272027777845393656L;
	
/**
 * void constructor used by JAXB
 */
	public FreePermissionCardBonus() {
		//used by jax b
	}
	/**
	 * the constructor initializes the number of bonus
	 * @param numberOfBonus
	 */
	public FreePermissionCardBonus(int numberOfBonus) {
		super(numberOfBonus);
	}
	/**
	 * gives a free permission card bonus to the player
	 */
	@Override
	public void giveBonusToPlayer(Player player) {
		player.addPossibleActions(new FreePermissionCardAction(getNumberOfBonus(),null));
	}
	
	/**
	 * @return "Free Per"
	 */
	@Override
	public String toString() {
		return "Free Per";
	}
}