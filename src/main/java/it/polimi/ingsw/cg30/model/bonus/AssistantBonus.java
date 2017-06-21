package it.polimi.ingsw.cg30.model.bonus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.polimi.ingsw.cg30.model.Player;

@XmlRootElement(name = "assistant_bonus")
@XmlAccessorType(XmlAccessType.FIELD)
public class AssistantBonus extends Bonus {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1944257084100233392L;

	/**
	 * void constructor for JAXB
	 */
	public AssistantBonus() {
		//used by jax b
	}
	
	/**
	 * constructor with numberOfBonus
	 * see {@link Bonus}
	 */
	public AssistantBonus(int numberOfBonus) {

		super(numberOfBonus);

	}
	/**
	 * give numberOfBonus assistants to player
	 * see {@link Bonus} 
	 */
	@Override
	public void giveBonusToPlayer(Player player) {

		for (int i = 0; i < this.getNumberOfBonus(); i++) {
			player.takeAssistant();
		}
	}
	/**
	 * @return "Assistant"
	 */
	
	@Override
	public String toString() {
		return "Assistant";
	}
}