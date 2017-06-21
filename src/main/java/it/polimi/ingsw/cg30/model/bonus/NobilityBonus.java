package it.polimi.ingsw.cg30.model.bonus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.polimi.ingsw.cg30.model.NobilitySlot;
import it.polimi.ingsw.cg30.model.Player;

@XmlRootElement(name = "nobility_bonus")
@XmlAccessorType(XmlAccessType.FIELD)
public class NobilityBonus extends Bonus {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 6478431250148665644L;

	/**
	 * void constructor
	 */
	public NobilityBonus() {
		//used by jax b
	}

	/**
	 * constructor with numberOfBonus
	 * 
	 */
	public NobilityBonus(int numberOfBonus) {
		super(numberOfBonus);
	}

	/**
	 * gives the player the bonuses of the slot he is on
	 * 
	 */
	@Override
	public void giveBonusToPlayer(Player player) {
		player.moveNobilityDisk(this.getNumberOfBonus());
		NobilitySlot slot = (NobilitySlot) player.getNobilityDisk().getSlot();
			if (slot.getBonuses()!=null) {
				for (Bonus bonus : slot.getBonuses()) {
					if(!(bonus.getClass()==CityTokenBonus.class && player.getGame().getNumEmporium()-player.getEmporiums().size()<bonus.getNumberOfBonus()) && !(bonus.getClass()==RepeatPermissionCardBonus.class && player.getPermissionCards().size()<bonus.getNumberOfBonus()))
					bonus.giveBonusToPlayer(player);
				}
			}
		}
		
/**
 * @return "Nobil"
 */
	@Override
	public String toString() {
		return "Nobil";
	}

}